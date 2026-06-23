# Exercise 6 - Wellness API Gateway

## Description
A single entry point that centralizes access to all four wellness microservices. The client connects only to the gateway on port 50050 and has no knowledge of the internal services behind it. The gateway has its own `.proto` contract (`gateway.proto`) and translates each client call into one or more internal service calls.

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

Compare with Exercise 5: the client used to connect to four services directly. Now it connects to one. All the internal connections moved from the client into the gateway.

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

`gateway.proto` is what the client sees. The other four protos are what the gateway uses internally to talk to the backing services. The client never knows about them.

## How to Compile
```
cd Ex6
mvn clean compile
```

## How to Run
Start the four microservices from Exercise 5 first, then the gateway and the client.

Terminals 1-4 (from Ex5 folder):
```
mvn exec:java "-Dexec.mainClass=edu.eci.arsw.wellness.AppointmentServer"
mvn exec:java "-Dexec.mainClass=edu.eci.arsw.wellness.MedicalServer"
mvn exec:java "-Dexec.mainClass=edu.eci.arsw.wellness.GymServer"
mvn exec:java "-Dexec.mainClass=edu.eci.arsw.wellness.RecreationServer"
```

Terminal 5 - Gateway (from Ex6 folder):
```
mvn exec:java "-Dexec.mainClass=edu.eci.arsw.wellness.WellnessGatewayServer"
```

Terminal 6 - Client (from Ex6 folder):
```
mvn exec:java "-Dexec.mainClass=edu.eci.arsw.wellness.GatewayClient"
```

## Test Data

The gateway forwards to the same backing services as Exercise 5. All pre-loaded data is the same:

- Student IDs: any value (S001, S002, S003).
- Appointment service types: MEDICINE, PSYCHOLOGY, DENTISTRY.
- Medical specialties: MED, PSY, DEN.
- Gym sessions: any student ID, day, and time slot (e.g. Monday 08:00-09:00).
- Recreation resource IDs: BB01 (Basketball), TT01 (Table Tennis), BD01 (Badminton Set), BK01 (Board Game Kit).

IDs are auto-generated (APT-1, GYM-1, ...).

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
- The client opens exactly one channel (port 50050) and knows nothing about the internal services.
- `GetStudentWellnessSummary` combines data from three services in a single gateway call.
- If any backing service is down when the gateway starts, the gateway will fail on the first call to that service.

---

## Reflection Questions

**1. What does the Gateway simplify for the client?**

The client no longer needs to know how many services exist, what ports they use, or how to combine their responses. It calls four simple operations on one address (port 50050). The `GetStudentWellnessSummary` operation internally calls three services and aggregates the responses, work that the client would otherwise do itself.

**2. What complexity does the Gateway add to the system?**

The gateway is a **single point of failure**: if it goes down, the client loses everything even if all four services are healthy. It also adds an extra network hop on every call. If `GymService` is down during a summary call, should the gateway return partial data or fail the whole request? These decisions need explicit design. In production, the gateway itself needs redundancy and health checks.

**3. What would happen if the Gateway starts to contain too much business logic?**

It would become a new monolith — a "smart gateway" that validates business rules, enforces authorization, and couples itself to every service's internal model. This defeats the purpose of microservices: services can no longer evolve independently because every change also requires changing the gateway. The gateway should only route, aggregate, and translate. Business logic belongs inside the service that owns that domain.
