import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RoomService extends Remote {
    Room getRoom(String id) throws RemoteException;
    List<Room> getAllRooms() throws RemoteException;
    String reserveRoom(String id) throws RemoteException;
    String releaseRoom(String id) throws RemoteException;
}
