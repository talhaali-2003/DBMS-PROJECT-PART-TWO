import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderOfWorkDAO {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public orderOfWorkDAO() {
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

    public List<orderOfWork> listAllOrders() throws SQLException {
        List<orderOfWork> orderList = new ArrayList<orderOfWork>();

        String sql = "SELECT * FROM OrderOfWork";

        connect();
        preparedStatement = connect.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int orderID = resultSet.getInt("orderID");
            String workStatus = resultSet.getString("workStatus");
            int quoteID = resultSet.getInt("quoteID");

            orderOfWork order = new orderOfWork(orderID, workStatus, quoteID);
            orderList.add(order);
        }

        resultSet.close();
        disconnect();

        return orderList;
    }

    public void insert(orderOfWork order) throws SQLException {
        connect();
        String sql = "INSERT INTO OrderOfWork (workStatus, quoteID) VALUES (?, ?)";
        preparedStatement = connect.prepareStatement(sql);

        preparedStatement.setString(1, order.getWorkStatus());
        preparedStatement.setInt(2, order.getQuoteID());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    public boolean delete(int orderID) throws SQLException {
        connect();
        String sql = "DELETE FROM OrderOfWork WHERE orderID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, orderID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    public boolean update(orderOfWork order) throws SQLException {
        connect();
        String sql = "UPDATE OrderOfWork SET workStatus = ?, quoteID = ? WHERE orderID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1, order.getWorkStatus());
        preparedStatement.setInt(2, order.getQuoteID());
        preparedStatement.setInt(3, order.getOrderID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    public orderOfWork getOrderById(int orderID) throws SQLException {
        connect();
        String sql = "SELECT * FROM OrderOfWork WHERE orderID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, orderID);

        ResultSet resultSet = preparedStatement.executeQuery();

        orderOfWork order = null;
        if (resultSet.next()) {
            String workStatus = resultSet.getString("workStatus");
            int quoteID = resultSet.getInt("quoteID");

            order = new orderOfWork(orderID, workStatus, quoteID);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return order;
    }

    public boolean isValid(int orderID) throws SQLException {
        connect();
        String sql = "SELECT * FROM OrderOfWork WHERE orderID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, orderID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean valid = !resultSet.next(); // If resultSet is empty, the ID is valid

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return valid;
    }
}
