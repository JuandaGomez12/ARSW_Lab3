import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RoomServer {

    public static void main(String[] args) throws Exception {
        RoomRepository repository = new RoomRepository();
        try (ServerSocket serverSocket = new ServerSocket(35001)) {
        System.out.println("RoomServer TCP listening on port 35001...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = in.readLine();
            String response = processRequest(request, repository);
            out.println(response);

            in.close();
            out.close();
            clientSocket.close();
        }
        }
    }

    private static synchronized String processRequest(String request, RoomRepository repository) {
        if (request == null || request.trim().isEmpty()) {
            return "ERROR_OPERACION_INVALIDA";
        }

        String[] parts = request.split(",");
        if (parts.length != 2) {
            return "ERROR_OPERACION_INVALIDA";
        }

        String operation = parts[0].trim();
        String roomId = parts[1].trim();

        Room room = repository.findById(roomId);
        if (room == null) {
            return "ERROR_SALON_NO_EXISTE";
        }

        switch (operation) {
            case "CONSULTAR_SALON":
                return room.getStatus();

            case "RESERVAR_SALON":
                if (room.isReserved()) {
                    return "ERROR_OPERACION_INVALIDA";
                }
                room.reserve();
                return "RESERVA_EXITOSA";

            case "LIBERAR_SALON":
                if (!room.isReserved()) {
                    return "ERROR_OPERACION_INVALIDA";
                }
                room.release();
                return "LIBERACION_EXITOSA";

            default:
                return "ERROR_OPERACION_INVALIDA";
        }
    }
}
