import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RoomRmiServer {

    public static void main(String[] args) throws Exception {
        RoomService service = new RoomServiceImpl();
        Registry registry = LocateRegistry.createRegistry(23000);
        registry.rebind("roomService", service);
        System.out.println("RoomService RMI published on port 23000...");
    }
}
