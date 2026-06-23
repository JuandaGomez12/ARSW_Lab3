# Exercise 1 - TCP Salon Reservation System

## Description
A client-server app using TCP sockets that manages room reservations. The server keeps four salons (E301–E304) in memory and handles three operations: query, reserve, and release. Both sides communicate with plain text strings.

## How to Compile
```
javac Ex1/src/Room.java Ex1/src/RoomRepository.java Ex1/src/RoomServer.java Ex1/src/RoomClient.java
```

## How to Run
Open two separate terminals.

Terminal 1 - Server:
```
java -cp Ex1/src RoomServer
```

Terminal 2 - Client:
```
java -cp Ex1/src RoomClient
```

## Test Data

| Salon ID | Initial Status |
|----------|----------------|
| E301     | AVAILABLE      |
| E302     | AVAILABLE      |
| E303     | AVAILABLE      |
| E304     | AVAILABLE      |

Any other ID (e.g. E999) returns `ERROR_SALON_NO_EXISTE`.

## Example
```
=== Salon Reservation Client ===
Operations: CONSULTAR_SALON, RESERVAR_SALON, LIBERAR_SALON
Format: OPERATION,SALON_ID  (e.g. CONSULTAR_SALON,E301)
Type 'exit' to quit.

Enter request: CONSULTAR_SALON,E301
Server response: SALON_DISPONIBLE

Enter request: RESERVAR_SALON,E301
Server response: RESERVA_EXITOSA

Enter request: RESERVAR_SALON,E301
Server response: ERROR_OPERACION_INVALIDA

Enter request: LIBERAR_SALON,E301
Server response: LIBERACION_EXITOSA

Enter request: CONSULTAR_SALON,E999
Server response: ERROR_SALON_NO_EXISTE
```

The client sends a plain string, the server splits it by comma, identifies the operation and the salon ID, updates the state in memory, and sends back another plain string. The third request fails because the salon is already reserved.

## Notes
- The server listens on port 35001.
- All salon state is kept in memory and resets when the server restarts.
- `processRequest` is synchronized to prevent race conditions if the server later handles concurrent connections.

## Verification

Server:

![Server execution](Server.png)

Client:

![Client execution](Client.png)

---

## Reflection Questions

**1. How easy would it be to add a new operation to the protocol?**

You would need to change both the server's `processRequest` switch and every client that uses the new command. There is no formal contract: the protocol is just string conventions agreed upon informally in the code. A typo or a field order change breaks communication silently with no compile-time warning.

**2. What happens if two clients try to reserve the same salon at the same time?**

As written, the server handles one client at a time, so true simultaneous access is not possible. If the server were changed to use threads, two threads could both read `salon.isReserved() == false` before either one calls `salon.reserve()`, causing a double booking. `processRequest` is already `synchronized` to guard against this, but the server loop would also need threads for that guard to matter.

**3. Where is the communication contract actually defined: in a formal file or in text conventions?**

In text conventions only. String literals are hardcoded in both `SalonServer.java` and `SalonClient.java`. There is no schema or formal file. Tooling cannot validate it, and any mismatch produces a silent runtime error instead of a compile-time one.
