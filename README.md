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

**TCP Sockets** showed that distributed communication is hard to do from scratch. Every message format, separator, and error must be designed manually. If something changes, both sides must be updated.

**HTTP** removed the need to invent a protocol. Any tool (browser, curl, Postman) can talk to the server without a custom client. Status codes like 200, 404, and 409 replace custom error strings.

**Java RMI** made calling a remote method look exactly like calling a local one. The network disappears behind the method call. The tradeoff is that only Java programs can participate.

**gRPC** kept the simplicity of RMI but removed the Java requirement. The `.proto` file defines the contract once and generates client/server code for any language.

**Microservices** split the system so each service does exactly one thing. The downside is that the client must know the address of every service.

**API Gateway** solved that: the client talks to one place, and the gateway handles everything behind it.

---

# Final Integrative Exercise - ECICIENCIA Platform

## Context

Design a distributed platform for the ECICIENCIA event at ECI. It must let users register as attendees, view the schedule, reserve workshop spots, and control capacity per activity.

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

The `EciGateway` is the only entry point for external clients. It exposes four operations:

- **RegisterAttendee** → goes to `AttendeeService`.
- **GetSchedule** → goes to `AgendaService`; can filter by time slot.
- **BookWorkshop** → calls `WorkshopService` to reserve a spot, then `CapacityService` to confirm the activity is not full.
- **GetAttendeeSummary** → combines attendee info, booked workshops, and check-in records from three services into one response.

---

## Why Not a Single Monolithic Service?

A single service would mix concerns that change at different rates and for different reasons. With a monolith:

- A bug in capacity tracking can bring down registration.
- Scaling for peak check-in traffic forces scaling everything, even idle parts.
- Teams working on the schedule and teams working on reservations step on each other.

Separate services let each part be deployed and scaled on its own. The gateway keeps it simple for the client.

---

## Architectural Evolution Reflection

Each exercise in this lab solved a specific problem that the previous one left open.

**TCP sockets** made the network fully explicit. We had to define every byte and every error case ourselves. There was no abstraction.

**HTTP** gave both sides a shared vocabulary. We no longer invented a protocol; we used one that every tool already understands.

**RMI** hid the network behind a method call. But that transparency only worked between JVMs and broke across firewalls and non-Java clients.

**gRPC** brought back the explicitness of a formal contract (the `.proto` file) but made it language-agnostic and compiler-enforced.

**Microservices** separated each domain into its own process. The new problem: the client had to know every address.

**API Gateway** solved that coupling. One entry point, and the internal topology becomes invisible to the client.

The takeaway: no style is universally better. Each one solves a problem and introduces a new one. Choosing well means knowing which problem hurts most right now.
