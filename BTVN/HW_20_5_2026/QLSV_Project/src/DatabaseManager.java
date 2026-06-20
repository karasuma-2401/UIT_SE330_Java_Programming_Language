import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QLSV;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
