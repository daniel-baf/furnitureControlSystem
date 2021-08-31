package Database;

import Domain.Furniture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FurnitureDAO {

    private final String SQL_INSERT_FURNITURE = "INSERT INTO `Furniture` VALUES (?, ?)";
    private final String SQL_MOST_SOLD_FURNITURE = "SELECT COUNT(`furniture_Name`) AS `sold`, `furniture_Name` FROM `Furniture_Assembly` WHERE `sold` != 0 GROUP BY `furniture_Name` ORDER BY `sold` DESC LIMIT 1";
    private final String SQL_LESS_SOLD_FUNRNITURE = "SELECT COUNT(`furniture_Name`) AS `sold`, `furniture_Name` FROM `Furniture_Assembly` WHERE `sold` != 0 GROUP BY `furniture_Name` ORDER BY `sold` ASC LIMIT 1";

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

    public Furniture getMostOrLessSoldFurniture(String mostOrLess) {
        Furniture furniture = null;
        String tmp = mostOrLess.equalsIgnoreCase("most") ? SQL_MOST_SOLD_FURNITURE : SQL_LESS_SOLD_FUNRNITURE;
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(tmp)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                furniture = new Furniture(rs.getString("furniture_Name"));
            }

        } catch (Exception e) {
        }
        return furniture;
    }
}
