package bb.utils;
import java.sql.*;

public class DBHelper {

    public static Connection getConnection() {

        String dbUrl = DataUtils.getValueConf("database.url");
        String dbUser = DataUtils.getValueConf("database.username");
        String dbPass = DataUtils.getValueConf("database.password");

        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to database: " + e.getMessage(), e);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (Exception e) {
            LoggerUtil.logError("Could not close ResultSet: " + e.getMessage());
        }

        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (Exception e) {
            LoggerUtil.logError("Could not close Statement: " + e.getMessage());
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            LoggerUtil.logError("Could not close Connection: " + e.getMessage());
        }
    }

}
