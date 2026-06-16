# Exercise 2 - HTTP Salon Reservation System

## Description
An evolution of the TCP salon management system, now exposed over HTTP using Java's built-in `HttpServer`. The same four salons (E301-E304) are managed in memory, but instead of a custom text protocol over sockets, the server speaks standard HTTP, so no custom Java client is needed. A browser, curl, or Postman all work.

## How to Compile
```
javac Ex2/src/Room.java Ex2/src/RoomRepository.java Ex2/src/RoomHttpServer.java
```

## How to Run
```
java -cp Ex2/src RoomHttpServer
```

Then open a browser or use curl to interact with the API.

## Endpoints

| Method | Path                      | Description              |
|--------|---------------------------|--------------------------|
| GET    | /rooms                    | List all rooms           |
| GET    | /rooms?id=E301            | Query a specific room    |
| POST   | /rooms/reserve?id=E301    | Reserve a room           |
| POST   | /rooms/release?id=E301    | Release a room           |

GET is used for read-only operations and POST for state-changing ones, following standard HTTP semantics. Status codes carry meaning: 200 for success, 404 when the salon ID does not exist, and 409 (Conflict) when an operation is logically invalid, for example trying to reserve a salon that is already reserved. This replaces the opaque `ERROR_OPERACION_INVALIDA` strings from Exercise 1.

## Example

List all rooms:
```
curl http://localhost:8080/rooms
```

Query a specific room:
```
curl http://localhost:8080/rooms?id=E303
```

Reserve a room:
```
curl -X POST http://localhost:8080/rooms/reserve?id=E303
```

Release a room:
```
curl -X POST http://localhost:8080/rooms/release?id=E303
```

## Notes
- The server listens on port 8080.
- Sending a POST to `/rooms/reserve` on an already-reserved room returns HTTP 409.
- Sending a POST to `/rooms/release` on an available room returns HTTP 409.
- The `handle` methods in ReserveHandler and ReleaseHandler are `synchronized` to prevent race conditions under concurrent requests.
- All salon state is kept in memory and resets when the server restarts.

## Verification

Server startup:

![Server execution](server.png)

GET /rooms in browser:

![GET all rooms](get_rooms.png)

POST /rooms/reserve via curl:

![Reserve room](reserve.png)

---

## Reflection Questions

**1. What advantages does HTTP offer over a manually defined text protocol?**

HTTP provides a universally understood structure (method, path, query parameters, headers, and status codes) that any tool or language can speak without needing a custom client. A browser or curl can interact with the server directly. Status codes like 200, 404, and 409 carry semantic meaning that both humans and machines understand, replacing opaque strings like `ERROR_OPERACION_INVALIDA`. The protocol is also stateless by design, which makes scaling and debugging easier.

**2. What limitations does building an HTTP server without a framework have?**

Without a framework, every concern must be handled manually: routing (deciding which handler owns a given path), query parameter parsing, HTTP method validation, content-type headers, and error responses. Adding a new route means writing a new `HttpHandler` class and registering it. There is no middleware pipeline, no dependency injection, and no automatic serialization. The code becomes repetitive and harder to maintain as the number of endpoints grows. Frameworks like Spring Boot or Javalin eliminate this boilerplate while keeping the same HTTP semantics.

**3. How would this solution change if JSON were used instead of HTML?**

The response body would contain a JSON string (e.g., `{"id":"E301","status":"AVAILABLE"}`) instead of HTML markup. The `Content-Type` header would change to `application/json`. The client would no longer need to parse HTML to read the data; instead it would deserialize the JSON directly into an object. This makes the API usable by any frontend or backend system, not just a browser. A library like Jackson or Gson would handle serialization automatically, removing the manual string building currently done in `buildSalonHtml` and `buildAllRoomsHtml`.
