package bb.utils;
import java.sql.*;

public class DatabaseUtils {
    static Connection connection = null;
    static PreparedStatement statement = null;
    static ResultSet resultSet = null;

    public static String getAuthCode(String cif) {
        try {
            String sqlQuery = "select * from CUSTOMERS_PERSIST_SERVICE_E.customer where CIF_NUMBER = '" + cif + "'";
            connection = DBHelper.getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("HIGHRISK_AUTH_METHOD");
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching auth code for CIF: " + cif, e);
        }finally {
            DBHelper.closeConnection(connection, statement, resultSet);
        }
    }

}