package Database;

import Domain.Furniture;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FurnitureDAO {

    private final String SQL_INSERT_FURNITURE = "INSERT INTO `Furniture` VALUES (?, ?)";

    public int insert(Furniture furniture) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_FURNITURE)) {
            // validate exist the piece name
            ps.setString(1, furniture.getName().toLowerCase());
            ps.setDouble(2, furniture.getSellPrice());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            // error on insrt, Servlet create table
            return 0;
        }
    }
}
