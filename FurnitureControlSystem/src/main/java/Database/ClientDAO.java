package Database;

import Domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClientDAO {

    private final String SQL_INSERT_CLIENT = "INSERT INTO `Client` VALUES (?, ?, ?, ?, ?);";

    public int insert(Client client) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_CLIENT)) {
            ps.setString(1, client.getNit());
            ps.setString(2, client.getName());
            ps.setString(3, client.getAdress());
            ps.setString(4, client.getMunicipality());
            ps.setString(5, client.getDepartment());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            // error on insrt, Servlet create table
            return 0;
        }
    }
}
