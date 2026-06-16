# ARSW Lab 3 - Integrative Workshop on Distributed Architectures

## Exercises

| Exercise | Style | Description |
|---|---|---|
| [Exercise 1](Ex1/README.md) | TCP Sockets | Room reservation system over a custom text protocol |
| [Exercise 2](Ex2/README.md) | HTTP | Room reservation exposed via Java HttpServer |
| [Exercise 3](Ex3/README.md) | Java RMI | Room reservation using remote method invocation |
| [Exercise 4](Ex4/README.md) | gRPC | Wellness appointment service with Protocol Buffers |
| [Exercise 5](Ex5/README.md) | Microservices | Wellness system decomposed into four gRPC services |
| [Exercise 6](Ex6/README.md) | API Gateway | Single entry point centralizing access to Ex5 services |

---

## Conclusions

**TCP Sockets** showed that distributed communication is not free. Every byte, delimiter, and error case must be designed manually. The protocol was entirely informal: two files with matching string literals. Adding or changing an operation required coordinating both sides with no tooling to help.

**HTTP** eliminated the need to invent a protocol. By adopting a well-known standard, the server became accessible from any tool without a custom client. Status codes gave errors meaning, and the path structure made the API readable. The tradeoff is that without a framework, every routing and parsing detail must still be written by hand.

**Java RMI** made the network invisible. Calling a remote method looked exactly like calling a local one. The contract moved from string conventions to a Java interface enforced by the compiler. The cost was tight coupling to the JVM: non-Java clients cannot participate, and deployment across firewalls requires extra configuration.

**gRPC** kept the transparency of RMI while removing the Java lock-in. The `.proto` file is a formal, language-agnostic contract that generates client stubs and server skeletons for any supported language. Messages are binary and compact. The contract is versioned and enforced by the compiler, not by documentation or convention.

**Microservices** applied the single-responsibility principle at the deployment level. Each service owns its data, its contract, and its lifecycle. This is powerful for large teams and varied workloads, but it pushes complexity onto the client: managing multiple channels, ports, and partial failures.

**API Gateway** solved the client-coupling problem without giving up independence. One entry point, one channel for the client, one contract to read. Internal services can move, be replaced, or be added without the client ever knowing.

The pattern across all six exercises is consistent: every architectural style solves a specific problem and introduces a new one. The skill is in recognizing which problem is most urgent at a given moment and choosing the style that addresses it without creating worse problems downstream.

---

# Final Integrative Exercise - ECICIENCIA Platform

## Context

Design a distributed platform to support the ECICIENCIA event at ECI. The system must allow registering attendees, browsing the schedule, reserving workshop spots, and controlling the capacity of each activity.

---

## Architecture Diagram

```
EciGatewayClient
       |
       v  [port 50060]
  EciGateway
       |── AttendeeService   [port 50061]
       |── AgendaService     [port 50062]
       |── WorkshopService   [port 50063]
       └── CapacityService   [port 50064]
```

---

## Services and Responsibilities

| Service | Responsibility | Port |
|---|---|---|
| **AttendeeService** | Register attendees, query registration status | 50061 |
| **AgendaService** | Manage activities, time slots, and full schedule | 50062 |
| **WorkshopService** | Handle workshop reservations and spot availability | 50063 |
| **CapacityService** | Track real-time attendance and enforce capacity limits | 50064 |
| **EciGateway** | Single entry point that exposes a simplified API to the client | 50060 |

---

## Proposed gRPC Contract

The full contract is defined in [ECICIENCIA/eciciencia.proto](ECICIENCIA/eciciencia.proto).

It declares five services with their respective messages:

- AttendeeService: RegisterAttendee, GetAttendee
- AgendaService: GetSchedule, GetActivity
- WorkshopService: BookWorkshop, CancelBooking, GetAvailability
- CapacityService: CheckIn, GetCapacity
- EciGateway: RegisterAttendee, GetSchedule, BookWorkshop, GetAttendeeSummary

---

## Gateway Description

The `EciGateway` acts as the sole entry point for external clients (mobile app, web, kiosk). It exposes four high-level operations that hide the internal decomposition:

- **RegisterAttendee** → delegates to `AttendeeService`.
- **GetSchedule** → delegates to `AgendaService`; optionally filters by time slot.
- **BookWorkshop** → calls `WorkshopService` to reserve a spot and `CapacityService` to verify the activity is not at capacity before confirming.
- **GetAttendeeSummary** → aggregates attendee info from `AttendeeService`, booked workshops from `WorkshopService`, and check-in records from `CapacityService`.

---

## Why Not a Single Monolithic Service?

A single service for ECICIENCIA would mix four different concerns in one codebase. Attendee registration changes at a different rate than the schedule, and capacity enforcement has stricter consistency requirements than browsing the agenda. With a monolith:

- A bug in capacity tracking can bring down registration.
- Scaling the system for peak check-in traffic forces scaling everything, including parts that are idle.
- Teams working on the schedule and teams working on reservations would constantly step on each other in the same code.

Separate services allow each concern to be deployed, scaled, and evolved independently. The gateway preserves simplicity for the client while the internal boundaries preserve simplicity for developers.

---

## Architectural Evolution Reflection

This workshop traced a path from raw socket communication to a structured microservices architecture, and the progression revealed something important: each new style did not simply replace the previous one. It resolved a specific problem that the previous style left exposed.

TCP sockets made the network explicit. Writing the custom `RESERVAR_SALON,E301` protocol forced a direct confrontation with the reality of distributed communication: you must define every byte, every delimiter, every error case. There is no abstraction standing between your code and the network.

HTTP introduced a shared vocabulary. Instead of inventing a protocol, both sides agreed on methods, paths, and status codes that any tool (a browser, curl, Postman) already understands. The contract moved from string conventions buried in code to URLs that can be read by humans and machines alike.

RMI traded explicitness for transparency. The network disappeared behind a method call. But the transparency was fragile: it worked only between JVMs, broke across firewalls, and failed non-Java clients entirely. The contract was a Java interface, not a universal document.

gRPC restored the explicitness of TCP but at a higher level: the `.proto` file is a formal, language-agnostic contract enforced by the compiler. Any language, any platform, can participate. The communication is binary and efficient, but the contract is human-readable and versioned.

Microservices applied the principle of single responsibility at the deployment level. Services that change for different reasons are separated into independent processes. But decomposition revealed a new problem: the client accumulated knowledge of every service address and port.

The API Gateway resolved that coupling without sacrificing independence. The client sees one door; behind it, the internal topology can change without the client ever knowing.

The lesson is not that one style is universally better. It is that each style carries a set of trade-offs, and the right choice depends on what problem is most painful at that moment. A small internal tool is well-served by HTTP. A polyglot ecosystem with strict contract requirements benefits from gRPC. A complex product with independent teams scaling different workloads points toward microservices and a gateway. Understanding where each style breaks down is what makes it possible to choose wisely.
