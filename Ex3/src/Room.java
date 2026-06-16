import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private boolean reserved;

    public Room(String id, boolean reserved) {
        this.id = id;
        this.reserved = reserved;
    }

    public String getId() {
        return id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public String getStatus() {
        return reserved ? "RESERVED" : "AVAILABLE";
    }

    @Override
    public String toString() {
        return id + " - " + getStatus();
    }
}
