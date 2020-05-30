package followalong;

import java.sql.*;

public class MyFirstDatabaseConnection {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby:zoo";
        String sql = "SELECT NAme FROM Animal";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next())
                System.out.println(resultSet.getString(1));
        }
    }
}
