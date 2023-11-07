import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class billDAO {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // Constructor
    public billDAO() {
        // Default constructor
    }

    // Establish a database connection
    protected void connect() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project?" +
                "allowPublicKeyRetrieval=true&useSSL=false&user=root&password=LilPlug2323!");
        }
    }

    // Close the database connection
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    // Get a list of all bills
    public List<bill> listAllBills() throws SQLException {
        List<bill> billList = new ArrayList<bill>();

        // SQL query to select all bills
        String sql = "SELECT * FROM Bill";

        connect();
        preparedStatement = connect.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int billID = resultSet.getInt("billID");
            String billStatus = resultSet.getString("billStatus");
            int quoteID = resultSet.getInt("quoteID");
            String note = resultSet.getString("note");

            bill Bill = new bill(billID, billStatus, quoteID, note);
            billList.add(Bill);
        }

        resultSet.close();
        disconnect();

        return billList;
    }

    // Insert a new bill
    public void insert(bill bill) throws SQLException {
        connect();
        String sql = "INSERT INTO Bill (billStatus, quoteID, note) VALUES (?, ?, ?)";
        preparedStatement = connect.prepareStatement(sql);

        preparedStatement.setString(1, bill.getBillStatus());
        preparedStatement.setInt(2, bill.getQuoteID());
        preparedStatement.setString(3, bill.getNote());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    // Update a bill
    public boolean update(bill bill) throws SQLException {
        connect();
        String sql = "UPDATE Bill SET billStatus = ?, quoteID = ?, note = ? WHERE billID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1, bill.getBillStatus());
        preparedStatement.setInt(2, bill.getQuoteID());
        preparedStatement.setString(3, bill.getNote());
        preparedStatement.setInt(4, bill.getBillID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    // Delete a bill
    public boolean delete(int billID) throws SQLException {
        connect();
        String sql = "DELETE FROM Bill WHERE billID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, billID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    // Get a bill by ID
    public bill getBillById(int billID) throws SQLException {
        connect();
        String sql = "SELECT * FROM Bill WHERE billID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, billID);

        ResultSet resultSet = preparedStatement.executeQuery();

        bill bill = null;
        if (resultSet.next()) {
            String billStatus = resultSet.getString("billStatus");
            int quoteID = resultSet.getInt("quoteID");
            String note = resultSet.getString("note");

            bill = new bill(billID, billStatus, quoteID, note);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return bill;
    }

    // Check the validity of a billID
    public boolean isValid(int billID) throws SQLException {
        connect();
        String sql = "SELECT * FROM Bill WHERE billID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, billID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean valid = !resultSet.next(); // If resultSet is empty, the ID is valid

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return valid;
    }
}
