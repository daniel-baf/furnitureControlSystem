package Database;

import Domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClientDAO {

    private final String SQL_INSERT_CLIENT_FULL = "INSERT INTO `Client` VALUES (?, ?, ?, ?, ?)";
    private final String SQL_INSERT_CLIENT_NO_DEP = "INSERT INTO `Client` (`nit`,`name`,`adress`) VALUES (?,?,?)";

    /**
     * Insert a client on DB
     *
     * @param client
     * @param type
     * @return
     */
    public int insert(Client client, int type) {
        String SQL_TMP = type == 0 ? SQL_INSERT_CLIENT_NO_DEP : SQL_INSERT_CLIENT_FULL;

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_TMP)) {
            ps.setString(1, client.getNit());
            ps.setString(2, client.getName().toLowerCase());
            ps.setString(3, client.getAdress().toLowerCase());
            if (type == 1) {
                ps.setString(4, client.getMunicipality().toLowerCase());
                ps.setString(5, client.getDepartment().toLowerCase());
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
