public class client {
    private int clientID;
    private String firstName;
    private String lastName;
    private String address;
    private String creditCardInfo;
    private String phoneNumber;
    private String email;

    public client() {
        // Default constructor
    }

    public client(int clientID, String firstName, String lastName, String address, String creditCardInfo, String phoneNumber, String email) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.creditCardInfo = creditCardInfo;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public client(String firstName, String lastName, String address, String creditCardInfo, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.creditCardInfo = creditCardInfo;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

