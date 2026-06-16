public class Room {
    private String id;
    private boolean reserved;

    public Room(String id) {
        this.id = id;
        this.reserved = false;
    }

    public String getId() {
        return id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve() {
        this.reserved = true;
    }

    public void release() {
        this.reserved = false;
    }

    public String getStatus() {
        return reserved ? "SALON_RESERVADO" : "SALON_DISPONIBLE";
    }
}
