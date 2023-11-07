public class treeRequest {
    private int requestID;
    private double size;
    private double height;
    private String location;
    private boolean nearHouse;
    private String note;
    private int clientID;
    private boolean rejected;

    public treeRequest() {
        // Default constructor
    }

    public treeRequest(int requestID, double size, double height, String location, boolean nearHouse, String note, int clientID, boolean rejected) {
        this.requestID = requestID;
        this.size = size;
        this.height = height;
        this.location = location;
        this.nearHouse = nearHouse;
        this.note = note;
        this.clientID = clientID;
        this.rejected = rejected;
    }
    
    public treeRequest( double size, double height, String location, boolean nearHouse, String note) {
        this.size = size;
        this.height = height;
        this.location = location;
        this.nearHouse = nearHouse;
        this.note = note;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isNearHouse() {
        return nearHouse;
    }

    public void setNearHouse(boolean nearHouse) {
        this.nearHouse = nearHouse;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }
}
