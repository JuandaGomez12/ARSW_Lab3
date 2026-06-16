import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RoomServiceImpl extends UnicastRemoteObject implements RoomService {

    private final Map<String, Boolean> rooms = new LinkedHashMap<>();

    public RoomServiceImpl() throws RemoteException {
        rooms.put("E301", false);
        rooms.put("E302", false);
        rooms.put("E303", false);
        rooms.put("E304", false);
    }

    @Override
    public Room getRoom(String id) throws RemoteException {
        if (!rooms.containsKey(id)) return null;
        return new Room(id, rooms.get(id));
    }

    @Override
    public List<Room> getAllRooms() throws RemoteException {
        List<Room> list = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : rooms.entrySet()) {
            list.add(new Room(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    @Override
    public synchronized String reserveRoom(String id) throws RemoteException {
        if (!rooms.containsKey(id)) return "ERROR: room not found";
        if (rooms.get(id)) return "ERROR: room is already reserved";
        rooms.put(id, true);
        return "Room " + id + " reserved successfully";
    }

    @Override
    public synchronized String releaseRoom(String id) throws RemoteException {
        if (!rooms.containsKey(id)) return "ERROR: room not found";
        if (!rooms.get(id)) return "ERROR: room is not reserved";
        rooms.put(id, false);
        return "Room " + id + " released successfully";
    }
}
