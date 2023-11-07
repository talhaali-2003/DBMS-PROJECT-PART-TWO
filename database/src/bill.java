public class bill {
    private int billID;
    private String billStatus;
    private int quoteID;
    private String note;

    public bill() {
        // Default constructor
    }

    public bill(int billID, String billStatus, int quoteID, String note) {
        this.billID = billID;
        this.billStatus = billStatus;
        this.quoteID = quoteID;
        this.note = note;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public int getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
