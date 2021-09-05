package Database;

import Domain.Client;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import javax.servlet.http.HttpServletResponse;

public class ClientDAO {

    private final String SQL_INSERT_CLIENT_FULL = "INSERT INTO `Client` VALUES (?, ?, ?, ?, ?)";
    private final String SQL_SELECT_CLIENT = "SELECT * FROM `Client` WHERE `nit` = ?";

    /**
     * Insert a client on DB
     *
     * @param client
     * @param response
     * @return
     * @throws java.lang.Exception
     */
    public int insert(Client client, HttpServletResponse response) throws Exception {
        String municipality = client.getMunicipality() == null || client.getMunicipality().isEmpty() ? null : client.getMunicipality().toLowerCase();
        String department = client.getDepartment() == null || client.getDepartment().isEmpty() ? null : client.getDepartment().toLowerCase();

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_CLIENT_FULL)) {
            ps.setString(1, client.getNit());
            ps.setString(2, client.getName().toLowerCase());
            ps.setString(3, client.getAdress().toLowerCase());
            if (municipality == null || department == null) {
                ps.setNull(4, Types.NULL);
                ps.setNull(5, Types.NULL);
            } else {
                ps.setString(4, municipality);
                ps.setString(5, department);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al insertar un nuevo cliente, verifica la sintaxis y que los datos sean validos, " + e.getMessage());
            return 0;
        }
    }

    /**
     * this method select a client by NIT on DB
     *
     * @param NIT
     * @return
     * @throws Exception
     */
    public Client selectClient(String NIT) throws Exception {
        Client client = null;
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_CLIENT)) {
            ps.setString(1, NIT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client = new Client(rs.getString("nit"), rs.getString("name"), rs.getString("adress"), rs.getString("municipality"), rs.getString("department"));
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al buscar al cliente, verifica la sintaxis y que los datos sean validos, " + e.getMessage());
        }
        return client;
    }
}
