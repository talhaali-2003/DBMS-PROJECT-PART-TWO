public class orderOfWork {
    private int orderID;
    private String workStatus;
    private int quoteID;

    public orderOfWork() {
        // Default constructor
    }

    public orderOfWork(int orderID, String workStatus, int quoteID) {
        this.orderID = orderID;
        this.workStatus = workStatus;
        this.quoteID = quoteID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public int getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }
}
