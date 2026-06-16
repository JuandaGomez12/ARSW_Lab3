# Exercise 5 - Wellness University Microservices

## Description
A decomposition of the wellness system into four independent gRPC microservices, each with a single clearly defined responsibility. Every service has its own `.proto` contract, its own port, and its own in-memory state. A single client opens four channels and routes commands to the appropriate service.

## Microservices Diagram

```
                    WellnessClient
                         |
          +--------------+--------------+--------------+
          |              |              |              |
          v              v              v              v
 AppointmentService  MedicalService  GymService  RecreationService
    port 50051         port 50052    port 50053    port 50054
```

The diagram shows a direct many-to-one relationship: the client opens four separate gRPC channels, one per service. Each service is fully independent with its own process, port, and in-memory state. The client must know all four addresses. This is what makes Exercise 6 necessary: the client carries too much topology knowledge here.

## Service Responsibilities

| Service | Responsibility | Port |
|---|---|---|
| AppointmentService | Manages medical appointment requests and cancellations | 50051 |
| MedicalService | Exposes available specialties and their slot availability | 50052 |
| GymService | Handles gym session bookings per student | 50053 |
| RecreationService | Controls borrowing and returning of recreational resources | 50054 |

## Project Structure

```
Ex5/
├── pom.xml
├── src/main/proto/
│   ├── appointment.proto
│   ├── medical.proto
│   ├── gym.proto
│   └── recreation.proto
└── src/main/java/edu/eci/arsw/wellness/
    ├── AppointmentServer.java
    ├── MedicalServer.java
    ├── GymServer.java
    ├── RecreationServer.java
    └── WellnessClient.java
```

Each `.proto` file is an independent contract. A change to `gym.proto` does not require recompiling `appointment.proto` or any other service. In a real deployment each service would live in its own repository and Maven project; here they share one project for simplicity, but the boundary between them is already clear.

## How to Compile
The first compile downloads dependencies, so internet access is required.
```
cd Ex5
mvn clean compile
```

## How to Run
Open five separate terminals inside the `Ex5/` folder.

Terminal 1 - AppointmentService:
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.AppointmentServer"
```

Terminal 2 - MedicalService:
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.MedicalServer"
```

Terminal 3 - GymService:
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.GymServer"
```

Terminal 4 - RecreationService:
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.RecreationServer"
```

Terminal 5 - Client:
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.WellnessClient"
```

## Example

```
=== Wellness Client (Microservices) ===

Enter command: med list
  [MED] General Medicine - Primary care and general consultations (slots: 10)
  [PSY] Psychology - Mental health and counseling (slots: 6)
  [DEN] Dentistry - Oral health and dental care (slots: 4)

Enter command: appt request S001 PSYCHOLOGY 2026-06-15
Appointment created: APT-1

Enter command: appt list S001
  APT-1 | PSYCHOLOGY | 2026-06-15 | REQUESTED

Enter command: gym book S001 Monday 08:00-09:00
Session booked: GYM-1

Enter command: rec list
  [BB01] Basketball (SPORT) - AVAILABLE
  [TT01] Table Tennis (SPORT) - AVAILABLE
  [BD01] Badminton Set (SPORT) - AVAILABLE
  [BK01] Board Game Kit (LEISURE) - AVAILABLE

Enter command: rec borrow BB01 S001
BB01 lent to S001

Enter command: rec list
  [BB01] Basketball (SPORT) - BORROWED by S001
  [TT01] Table Tennis (SPORT) - AVAILABLE
  ...
```

## Notes
- Each service manages its own state. There is no shared memory or shared database.
- The client must know all four ports, which is the problem that the API Gateway (Exercise 6) solves.
- `ConcurrentHashMap` and `synchronized` methods are used for thread safety under concurrent gRPC calls.

## Verification

Servers running (one per terminal):

![Servers execution](servers.png)

Client session:

![Client execution](client.png)

---

## Reflection Questions

**1. Why did you choose to separate these services and not others?**

Each service was separated along a natural boundary of responsibility: appointments are a transactional concern (create, cancel, query); medical specialties are reference data about available care; gym sessions are a resource-booking problem with time slots; recreational resources are a lending problem with availability state. These four domains have different data, different lifecycles, and different reasons to change, which is exactly the criterion for separating a service. Splitting further (e.g., separating `RequestAppointment` from `CancelAppointment`) would produce overly fine-grained services with no independent deployment benefit.

**2. What data belongs to each service?**

- **AppointmentService** owns: appointment records (id, studentId, serviceType, date, status).
- **MedicalService** owns: specialty catalog (id, name, description, available slots).
- **GymService** owns: gym session bookings (id, studentId, day, timeSlot).
- **RecreationService** owns: physical resource inventory (id, name, type, availability, borrowedBy).

No service shares a data store with another. If `AppointmentService` needs to know whether a specialty exists, it would call `MedicalService` instead of accessing its data directly.

**3. What risk appears when the client knows all the services?**

The client becomes tightly coupled to the deployment topology and must know four addresses and four ports. If any service moves to a different host or port, the client must be updated. If a new service is added, the client must be extended. The client also needs to handle partial failures: if `RecreationService` is down, it must decide whether to degrade gracefully or fail the entire session. This coupling is exactly the problem that an API Gateway solves by acting as a single entry point that hides the internal service topology from the client.
