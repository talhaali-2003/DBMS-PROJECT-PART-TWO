
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clientDAO {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public clientDAO() {
        // Default constructor
    }

    protected void connect() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=LilPlug2323!");
        }
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    public List<client> listAllClients() throws SQLException {
        List<client> clientList = new ArrayList<client>();

        String sql = "SELECT * FROM Client";

        connect();
        preparedStatement = connect.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int clientID = resultSet.getInt("clientID");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String address = resultSet.getString("address");
            String creditCardInfo = resultSet.getString("creditCardInfo");
            String phoneNumber = resultSet.getString("phoneNumber");
            String email = resultSet.getString("email");

            client Client = new client(clientID, firstName, lastName, address, creditCardInfo, phoneNumber, email);
            clientList.add(Client);
        }

        resultSet.close();
        disconnect();

        return clientList;
    }

    public void insert(client client) throws SQLException {
        connect();
        String sql = "INSERT INTO Client (firstName, lastName, address, creditCardInfo, phoneNumber, email) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = connect.prepareStatement(sql);

        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setString(3, client.getAddress());
        preparedStatement.setString(4, client.getCreditCardInfo());
        preparedStatement.setString(5, client.getPhoneNumber());
        preparedStatement.setString(6, client.getEmail());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }
    
    public boolean delete(int clientID) throws SQLException {
        connect();
        String sql = "DELETE FROM Client WHERE clientID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, clientID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    public boolean update(client client) throws SQLException {
        connect();
        String sql = "UPDATE Client SET firstName = ?, lastName = ?, address = ?, creditCardInfo = ?, phoneNumber = ?, email = ? WHERE clientID = ?";
        preparedStatement = connect.prepareStatement(sql);

        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setString(3, client.getAddress());
        preparedStatement.setString(4, client.getCreditCardInfo());
        preparedStatement.setString(5, client.getPhoneNumber());
        preparedStatement.setString(6, client.getEmail());
        preparedStatement.setInt(7, client.getClientID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    public client getClientById(int clientID) throws SQLException {
        connect();
        String sql = "SELECT * FROM Client WHERE clientID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, clientID);

        ResultSet resultSet = preparedStatement.executeQuery();

        client Client = null;
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String address = resultSet.getString("address");
            String creditCardInfo = resultSet.getString("creditCardInfo");
            String phoneNumber = resultSet.getString("phoneNumber");
            String email = resultSet.getString("email");

            Client = new client(clientID, firstName, lastName, address, creditCardInfo, phoneNumber, email);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return Client;
    }

    public boolean isValidClientID(int clientID) throws SQLException {
        connect();
        String sql = "SELECT * FROM Client WHERE clientID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, clientID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean valid = !resultSet.next(); // If resultSet is empty, the ID is valid

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return valid;
    }

    // Add other methods for updating, deleting, and retrieving clients as needed
}
