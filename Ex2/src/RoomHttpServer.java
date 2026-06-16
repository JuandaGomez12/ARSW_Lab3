import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collection;

public class RoomHttpServer {

    public static void main(String[] args) throws Exception {
        RoomRepository repository = new RoomRepository();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/rooms", new RoomsHandler(repository));
        server.createContext("/rooms/reserve", new ReserveHandler(repository));
        server.createContext("/rooms/release", new ReleaseHandler(repository));

        server.setExecutor(null);
        server.start();
        System.out.println("RoomHttpServer listening on http://localhost:8080");
        System.out.println("  GET  /rooms                 -> list all rooms");
        System.out.println("  GET  /rooms?id=E301         -> query a room");
        System.out.println("  POST /rooms/reserve?id=E301 -> reserve a room");
        System.out.println("  POST /rooms/release?id=E301 -> release a room");
    }

    // GET /rooms[?id=]
    static class RoomsHandler implements HttpHandler {
        private final RoomRepository repository;

        RoomsHandler(RoomRepository repository) {
            this.repository = repository;
        }

        @Override
        public void handle(HttpExchange exchange) throws java.io.IOException {
            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                sendResponse(exchange, 405, "<h1>405 Method Not Allowed</h1>");
                return;
            }

            String query = exchange.getRequestURI().getQuery();
            String response;

            if (query != null && query.startsWith("id=")) {
                String id = query.substring(3);
                Room room = repository.findById(id);
                if (room == null) {
                    response = "<html><body><h1>Room not found: " + id + "</h1></body></html>";
                } else {
                    response = buildRoomHtml(room);
                }
            } else {
                response = buildAllRoomsHtml(repository.findAll());
            }

            sendResponse(exchange, 200, response);
        }
    }

    // POST /rooms/reserve
    static class ReserveHandler implements HttpHandler {
        private final RoomRepository repository;

        ReserveHandler(RoomRepository repository) {
            this.repository = repository;
        }

        @Override
        public synchronized void handle(HttpExchange exchange) throws java.io.IOException {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                sendResponse(exchange, 405, "<h1>405 Method Not Allowed</h1>");
                return;
            }

            String id = extractId(exchange.getRequestURI().getQuery());
            Room room = repository.findById(id);

            String response;
            int status;

            if (room == null) {
                response = "<html><body><h1>Room not found: " + id + "</h1></body></html>";
                status = 404;
            } else if (room.isReserved()) {
                response = "<html><body><h1>Room " + id + " is already reserved.</h1></body></html>";
                status = 409;
            } else {
                room.reserve();
                response = "<html><body><h1>Room " + id + " reserved successfully.</h1></body></html>";
                status = 200;
            }

            sendResponse(exchange, status, response);
        }
    }

    // POST /rooms/release
    static class ReleaseHandler implements HttpHandler {
        private final RoomRepository repository;

        ReleaseHandler(RoomRepository repository) {
            this.repository = repository;
        }

        @Override
        public synchronized void handle(HttpExchange exchange) throws java.io.IOException {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                sendResponse(exchange, 405, "<h1>405 Method Not Allowed</h1>");
                return;
            }

            String id = extractId(exchange.getRequestURI().getQuery());
            Room room = repository.findById(id);

            String response;
            int status;

            if (room == null) {
                response = "<html><body><h1>Room not found: " + id + "</h1></body></html>";
                status = 404;
            } else if (!room.isReserved()) {
                response = "<html><body><h1>Room " + id + " is not reserved.</h1></body></html>";
                status = 409;
            } else {
                room.release();
                response = "<html><body><h1>Room " + id + " released successfully.</h1></body></html>";
                status = 200;
            }

            sendResponse(exchange, status, response);
        }
    }

    private static String buildRoomHtml(Room room) {
        return "<html><body>"
                + "<h1>Room: " + room.getId() + "</h1>"
                + "<p>Status: <strong>" + room.getStatus() + "</strong></p>"
                + "</body></html>";
    }

    private static String buildAllRoomsHtml(Collection<Room> rooms) {
        StringBuilder sb = new StringBuilder("<html><body><h1>All Rooms</h1><ul>");
        for (Room r : rooms) {
            sb.append("<li>").append(r.getId()).append(" - ").append(r.getStatus()).append("</li>");
        }
        sb.append("</ul></body></html>");
        return sb.toString();
    }

    private static String extractId(String query) {
        if (query == null || !query.startsWith("id=")) return "";
        return query.substring(3);
    }

    static void sendResponse(HttpExchange exchange, int statusCode, String body) throws java.io.IOException {
        byte[] bytes = body.getBytes();
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
