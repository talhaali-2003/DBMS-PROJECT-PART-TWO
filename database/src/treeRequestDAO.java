import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class treeRequestDAO {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public treeRequestDAO() {
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

    public List<treeRequest> listAllRequests() throws SQLException {
        List<treeRequest> requestList = new ArrayList<treeRequest>();

        String sql = "SELECT * FROM TreeRequest";

        connect();
        preparedStatement = connect.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int requestID = resultSet.getInt("requestID");
            double size = resultSet.getDouble("size");
            double height = resultSet.getDouble("height");
            String location = resultSet.getString("location");
            boolean nearHouse = resultSet.getBoolean("nearHouse");
            String note = resultSet.getString("note");
            int clientID = resultSet.getInt("clientID");
            boolean rejected = resultSet.getBoolean("rejected");

            treeRequest request = new treeRequest(requestID, size, height, location, nearHouse, note, clientID, rejected);
            requestList.add(request);
        }

        resultSet.close();
        disconnect();

        return requestList;
    }

    public void insert(treeRequest request) throws SQLException {
        connect();
        String sql = "INSERT INTO TreeRequest (size, height, location, nearHouse, note, clientID, rejected) VALUES (?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connect.prepareStatement(sql);

        preparedStatement.setDouble(1, request.getSize());
        preparedStatement.setDouble(2, request.getHeight());
        preparedStatement.setString(3, request.getLocation());
        preparedStatement.setBoolean(4, request.isNearHouse());
        preparedStatement.setString(5, request.getNote());
        preparedStatement.setInt(6, request.getClientID());
        preparedStatement.setBoolean(7, request.isRejected());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }
    
    public boolean delete(int requestID) throws SQLException {
        connect();
        String sql = "DELETE FROM TreeRequest WHERE requestID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, requestID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    public boolean update(treeRequest request) throws SQLException {
        connect();
        String sql = "UPDATE TreeRequest SET size = ?, height = ?, location = ?, nearHouse = ?, note = ?, clientID = ?, rejected = ? WHERE requestID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setDouble(1, request.getSize());
        preparedStatement.setDouble(2, request.getHeight());
        preparedStatement.setString(3, request.getLocation());
        preparedStatement.setBoolean(4, request.isNearHouse());
        preparedStatement.setString(5, request.getNote());
        preparedStatement.setInt(6, request.getClientID());
        preparedStatement.setBoolean(7, request.isRejected());
        preparedStatement.setInt(8, request.getRequestID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    public treeRequest getRequestById(int requestID) throws SQLException {
        connect();
        String sql = "SELECT * FROM TreeRequest WHERE requestID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, requestID);

        ResultSet resultSet = preparedStatement.executeQuery();

        treeRequest request = null;
        if (resultSet.next()) {
            double size = resultSet.getDouble("size");
            double height = resultSet.getDouble("height");
            String location = resultSet.getString("location");
            boolean nearHouse = resultSet.getBoolean("nearHouse");
            String note = resultSet.getString("note");
            int clientID = resultSet.getInt("clientID");
            boolean rejected = resultSet.getBoolean("rejected");

            request = new treeRequest(requestID, size, height, location, nearHouse, note, clientID, rejected);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return request;
    }

    public boolean isValid(int requestID) throws SQLException {
        connect();
        String sql = "SELECT * FROM TreeRequest WHERE requestID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, requestID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean valid = !resultSet.next(); // If resultSet is empty, the ID is valid

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return valid;
    }

}

