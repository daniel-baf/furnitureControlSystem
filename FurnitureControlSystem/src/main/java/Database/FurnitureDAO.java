package Database;

import Domain.Furniture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FurnitureDAO {

    private final String SQL_INSERT_FURNITURE = "INSERT INTO `Furniture` VALUES (?, ?)";
    private final String SQL_MOST_SOLD_FURNITURE = "SELECT COUNT(`furniture_Name`) AS `sold`, `furniture_Name` FROM `Furniture_Assembly` WHERE `sold` != 0 GROUP BY `furniture_Name` ORDER BY `sold` DESC LIMIT 1";
    private final String SQL_LESS_SOLD_FUNRNITURE = "SELECT COUNT(`furniture_Name`) AS `sold`, `furniture_Name` FROM `Furniture_Assembly` WHERE `sold` != 0 GROUP BY `furniture_Name` ORDER BY `sold` ASC LIMIT 1";
    private final String SQL_SELECT_FURNITURES = "SELECT * FROM `Furniture`";
    
    /**
     * insert a furniture on DB
     *
     * @param furniture Furniture Object
     * @return
     */
    public int insert(Furniture furniture) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_FURNITURE)) {
            ps.setString(1, furniture.getName().toLowerCase());
            ps.setDouble(2, furniture.getSellPrice());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Return the furniture most sold or the less sold, between dates or
     * lifetime
     *
     * @param mostOrLess
     * @return
     */
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

    public ArrayList<Furniture> selectFurnitures() {
        ArrayList<Furniture> furnitures = new ArrayList<>();

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_FURNITURES)) {
            // data
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                furnitures.add(new Furniture(rs.getString("name"), rs.getDouble("sell_Price")));
            }
        } catch (Exception e) { // error catch
        }
        return furnitures;
    }
}
