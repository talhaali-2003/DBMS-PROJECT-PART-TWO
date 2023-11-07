import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class quoteDAO {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public quoteDAO() {
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
            System.out.println(connect);
        }
    }

    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    public List<quote> listAllQuotes() throws SQLException {
        List<quote> quoteList = new ArrayList<quote>();

        String sql = "SELECT * FROM Quote";

        connect();
        preparedStatement = connect.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int quoteID = resultSet.getInt("quoteID");
            String initialPrice = resultSet.getString("initialprice");
            String note = resultSet.getString("note");
            String timeWindow = resultSet.getString("timeWindow");

            quote Quote = new quote(quoteID, initialPrice, note, timeWindow);
            quoteList.add(Quote);
        }

        resultSet.close();
        disconnect();

        return quoteList;
    }

    public void insert(quote Quote) throws SQLException {
        connect();
        String sql = "INSERT INTO Quote (initialprice, note, timeWindow) VALUES (?, ?, ?)";
        preparedStatement = connect.prepareStatement(sql);

        preparedStatement.setString(1, Quote.getInitialprice());
        preparedStatement.setString(2, Quote.getNote());
        preparedStatement.setString(3, Quote.getTimeWindow());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }
    
    public boolean delete(int quoteID) throws SQLException {
        connect();
        String sql = "DELETE FROM Quote WHERE quoteID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    public boolean update(quote Quote) throws SQLException {
        connect();
        String sql = "UPDATE Quote SET initialprice = ?, note = ?, timeWindow = ? WHERE quoteID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setString(1, Quote.getInitialprice());
        preparedStatement.setString(2, Quote.getNote());
        preparedStatement.setString(3, Quote.getTimeWindow());
        preparedStatement.setInt(4, Quote.getQuoteID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    public quote getQuoteById(int quoteID) throws SQLException {
        connect();
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);

        ResultSet resultSet = preparedStatement.executeQuery();

        quote Quote = null;
        if (resultSet.next()) {
            String initialPrice = resultSet.getString("initialprice");
            String note = resultSet.getString("note");
            String timeWindow = resultSet.getString("timeWindow");

            Quote = new quote(quoteID, initialPrice, note, timeWindow);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return Quote;
    }

    public boolean isValid(int quoteID) throws SQLException {
        connect();
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean valid = !resultSet.next(); // If resultSet is empty, the ID is valid

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return valid;
    }
}
    


