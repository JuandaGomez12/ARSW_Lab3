import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class RoomRmiClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 23000);
        RoomService service = (RoomService) registry.lookup("roomService");

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Room Reservation Client (RMI) ===");
        System.out.println("Commands: list | get <id> | reserve <id> | release <id> | exit");
        System.out.println();

        while (true) {
            System.out.print("Enter command: ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                break;
            } else if (line.equalsIgnoreCase("list")) {
                List<Room> rooms = service.getAllRooms();
                for (Room r : rooms) {
                    System.out.println("  " + r);
                }
            } else if (line.startsWith("get ")) {
                String id = line.substring(4).trim();
                Room room = service.getRoom(id);
                System.out.println(room == null ? "Room not found: " + id : room);
            } else if (line.startsWith("reserve ")) {
                String id = line.substring(8).trim();
                System.out.println(service.reserveRoom(id));
            } else if (line.startsWith("release ")) {
                String id = line.substring(8).trim();
                System.out.println(service.releaseRoom(id));
            } else {
                System.out.println("Unknown command. Use: list | get <id> | reserve <id> | release <id> | exit");
            }

            System.out.println();
        }

        scanner.close();
    }
}
