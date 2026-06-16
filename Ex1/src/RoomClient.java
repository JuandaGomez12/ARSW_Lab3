import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RoomClient {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Room Reservation Client ===");
        System.out.println("Operations: CONSULTAR_SALON, RESERVAR_SALON, LIBERAR_SALON");
        System.out.println("Format: OPERATION,ROOM_ID  (e.g. CONSULTAR_SALON,E301)");
        System.out.println("Type 'exit' to quit.");
        System.out.println();

        while (true) {
            System.out.print("Enter request: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            Socket socket = new Socket("127.0.0.1", 35001);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(input);
            String response = in.readLine();
            System.out.println("Server response: " + response);
            System.out.println();

            in.close();
            out.close();
            socket.close();
        }

        scanner.close();
    }
}
