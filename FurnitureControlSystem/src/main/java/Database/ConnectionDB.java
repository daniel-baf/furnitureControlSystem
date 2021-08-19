package Database;

import java.sql.*;

public class ConnectionDB {

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/FurnitureAssemblyPjct";
    // ?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true
    private static final String USER = "jefemayoneso";
    private static final String PASSWORD = "PepePeka_123";

    public ConnectionDB() {
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(MYSQL_URL,USER,PASSWORD);
    }
    
    public static void close(PreparedStatement ps) throws SQLException {
        ps.close();
    }
    
    public static void close(Statement stmt) throws SQLException {
        stmt.close();
    }
    
    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }
    
    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
