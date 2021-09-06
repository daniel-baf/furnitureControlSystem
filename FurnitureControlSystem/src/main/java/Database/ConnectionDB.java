package Database;

import java.sql.*;

public class ConnectionDB {

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/Furniture_Assembly_Pjct";
    private static final String USER = "ipc2";
    private static final String PASSWORD = "ipc2+contraPjct0s";

    public ConnectionDB() {
    }

    /**
     * Lot of classes extends this one, 'cause is better to split SQL sentences
     * using DAO
     *
     * @return connection for SQL instructions
     * @throws SQLException error on insert
     * @throws ClassNotFoundException Driver manager error
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(MYSQL_URL, USER, PASSWORD);
    }

    /**
     * closes prepared statements in case of use without try with resources
     *
     * @param ps
     * @throws SQLException
     */
    public static void close(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    /**
     * close a simple statement in case of use without try with resources
     *
     * @param stmt
     * @throws SQLException
     */
    public static void close(Statement stmt) throws SQLException {
        stmt.close();
    }

    /**
     * close a result set in case of use without try with resources
     *
     * @param rs
     * @throws SQLException
     */
    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    /**
     * closes a connection in case of use without try with resources
     *
     * @param conn
     * @throws SQLException
     */
    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
