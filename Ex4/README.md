# Exercise 4 - gRPC Wellness Appointment System

## Description
A university wellness appointment service built with gRPC and Protocol Buffers. The contract is defined in a `.proto` file that describes the service, the request/response messages, and the enumerations for service type and appointment status. The server manages appointments in memory; the client is an interactive command-line interface.

## Project Structure
```
Ex4/
├── pom.xml
├── src/main/proto/
│   └── wellness.proto              <- formal contract
└── src/main/java/edu/eci/arsw/wellness/
    ├── WellnessGrpcServer.java
    └── WellnessGrpcClient.java
```

The only hand-written files are `wellness.proto`, `WellnessGrpcServer.java`, and `WellnessGrpcClient.java`. Everything else (the message classes, the service stubs, and the base server class) is generated automatically by Maven from the proto file. Neither the server nor the client can be compiled until after this generation step runs.

## How to Compile
The first compile downloads dependencies from Maven Central, so internet access is required.
```
cd Ex4
mvn clean compile
```
After this, the generated classes from `wellness.proto` will appear in `target/generated-sources/`.

## How to Run
Open two separate terminals inside the `Ex4/` folder.

Terminal 1 - Server:
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.WellnessGrpcServer"
```

Terminal 2 - Client:
```
mvn exec:java -Dexec.mainClass="edu.eci.arsw.wellness.WellnessGrpcClient"
```

## Example
```
=== Wellness Appointment Client (gRPC) ===
Commands:
  request <studentId> <MEDICINE|PSYCHOLOGY|DENTISTRY> <date>
  cancel <appointmentId>
  list <studentId>
  exit

Enter command: request S001 PSYCHOLOGY 2026-06-15
Appointment created with ID: APT-1
  ID: APT-1 | Student: S001 | Service: PSYCHOLOGY | Date: 2026-06-15 | Status: REQUESTED

Enter command: list S001
  APT-1 | PSYCHOLOGY | 2026-06-15 | REQUESTED

Enter command: cancel APT-1
Appointment APT-1 cancelled successfully

Enter command: list S001
No active appointments for student: S001
```

## Notes
- The gRPC server listens on port 50051.
- `getAppointments` only returns appointments with status `REQUESTED`; cancelled ones are filtered out.
- Appointment IDs are generated as `APT-1`, `APT-2`, etc. using an `AtomicInteger`.
- `ConcurrentHashMap` is used on the server to handle concurrent clients safely without explicit synchronization.

## Verification

Server:

![Server execution](server.png)

Client:

![Client execution](client.png)

---

## Reflection Questions

**1. Why is the `.proto` file considered a contract?**

The `.proto` file formally and unambiguously defines what messages exist, what fields they contain, what types those fields have, and what operations the service exposes. Both the client and the server generate their code from this same file, so any mismatch is caught at compile time rather than at runtime. Unlike the text conventions in Exercise 1 or the Java interface in Exercise 3, the proto contract is language-agnostic and can generate code for Java, Python, Go, C++, and many others from a single source of truth. This makes it a true contract: explicit, versioned, and enforced by tooling.

**2. How easy would it be to create a client in another language?**

Very easy. The same `wellness.proto` file can be fed to the `protoc` compiler with a different language plugin (e.g., `--python_out` or `--go_out`) to generate an equivalent client stub in Python or Go. The developer would only need to write the application logic. All serialization, channel management, and method dispatch are generated automatically. This is a fundamental advantage over RMI, which cannot leave the JVM.

**3. What differences do you find between RMI and gRPC?**

| Aspect | RMI | gRPC |
|---|---|---|
| Contract | Java interface (`.java`) - JVM only | `.proto` file - language-agnostic |
| Language support | Java only | Java, Python, Go, C++, and more |
| Serialization | Java object serialization | Protocol Buffers (binary, compact) |
| Interoperability | Cannot cross language boundaries | Designed for polyglot systems |
| Firewall/cloud | Uses dynamic ports, problematic | Runs over HTTP/2, standard port |
| Tooling | Minimal | Rich ecosystem (grpc-web, streaming, deadlines) |

Both follow the same RPC idea of calling remote methods as if they were local, but gRPC is the modern version of that concept.
