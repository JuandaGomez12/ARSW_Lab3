# Exercise 6 - Wellness API Gateway

## Description
A single entry point that centralizes access to all four wellness microservices. The client connects only to the gateway on port 50050 and does not know that four independent services exist behind it. The gateway has its own `.proto` contract (`gateway.proto`) that exposes a simplified, aggregated API; internally it holds connections to each service and translates each gateway call into one or more internal service calls.

## Architecture

```
GatewayClient
      |
      | gRPC port 50050
      v
WellnessGatewayServer
      |
      +----> AppointmentService  @ 50051
      +----> GymService          @ 50053
      +----> RecreationService   @ 50054
      (MedicalService is available but not needed by these 4 operations)
```

Compare this with Exercise 5: the client used to touch four boxes; now it touches one. The four internal connections have moved from the client into the gateway. The client does not know which ports exist behind the gateway, which services are called for each operation, or even how many services there are. All of that is the gateway's concern.

## Project Structure

```
Ex6/
├── pom.xml
├── src/main/proto/
│   ├── gateway.proto          <- external contract (client-facing)
│   ├── appointment.proto      <- internal service contract
│   ├── gym.proto              <- internal service contract
│   ├── recreation.proto       <- internal service contract
│   └── medical.proto          <- internal service contract
└── src/main/java/edu/eci/arsw/wellness/
    ├── WellnessGatewayServer.java
    └── GatewayClient.java
```

There are two types of proto files here. `gateway.proto` is the external contract that defines what the client sees and calls. The other four protos are internal contracts the gateway uses to talk to the backing services. The client only ever references `gateway.proto`; the internal protos are invisible to it. This two-layer design is what allows the internal topology to change without affecting the client.

## How to Compile
```
cd Ex6
mvn clean compile
```

## How to Run
Start the four microservices from Exercise 5 first, then start the gateway and client.

Terminals 1-4 (from Ex5 folder):
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.AppointmentServer"
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.MedicalServer"
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.GymServer"
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.RecreationServer"
```

Terminal 5 - Gateway (from Ex6 folder):
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.WellnessGatewayServer"
```

Terminal 6 - Client (from Ex6 folder):
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.GatewayClient"
```

## Example

```
=== Wellness Gateway Client ===
Single entry point, port 50050 only.

Enter command: request S001 PSYCHOLOGY 2026-06-20
Appointment created: APT-1
Appointment ID: APT-1

Enter command: gym S001 Monday 08:00-09:00
Session booked: GYM-1

Enter command: borrow S001 BB01
BB01 lent to S001

Enter command: summary S001
Wellness Summary for: S001
  Appointments:
    - APT-1 | PSYCHOLOGY | 2026-06-20
  Gym Sessions:
    - GYM-1 | Monday 08:00-09:00
  Borrowed Resources:
    - BB01 | Basketball
```

## Notes
- The client opens exactly one channel (port 50050) and has no knowledge of the internal service topology.
- `GetStudentWellnessSummary` aggregates data from three services in a single gateway call.
- If any backing service is down when the gateway starts, the gateway will fail on the first call to that service.

## Verification

Gateway startup:

![Gateway execution](gateway.png)

Client session:

![Client execution](client.png)

---

## Reflection Questions

**1. What does the Gateway simplify for the client?**

The client no longer needs to know how many services exist, what ports they use, or how to combine their responses. It calls four simple operations on a single address (port 50050). The `GetStudentWellnessSummary` operation, for example, internally calls three different services and aggregates the responses, work that the client would otherwise have to do itself. Adding, removing, or relocating a backing service is completely transparent to the client.

**2. What complexity does the Gateway add to the system?**

The gateway is now a **single point of failure**: if it goes down, the client loses access to everything even if all four services are healthy. The gateway also introduces an extra network hop on every call, adding latency. It needs to handle partial failures gracefully. If `GymService` is unavailable during a summary call, should the gateway return partial data or fail the entire request? These decisions require explicit design. In production, the gateway itself needs redundancy, health checks, and timeout policies.

**3. What would happen if the Gateway starts to contain too much business logic?**

The gateway would gradually become a new monolith, a "smart gateway" that validates business rules, enforces complex authorization, and couples itself to the internal models of every service. This defeats the purpose of microservices: services can no longer evolve independently because changing a service requires changing the gateway too. The correct pattern is to keep the gateway "dumb": routing, aggregation, and protocol translation only. Any logic that belongs to a domain should stay inside the service that owns that domain.
