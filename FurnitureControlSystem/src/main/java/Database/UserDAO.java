package Database;

import Domain.User;
import GeneralUse.AES256;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

public class UserDAO {

    // SELECT 
    private final String SQL_SELECT_USERS = "SELECT * FROM `User`";
    private final String SQL_SELECT_A_USER = "SELECT * FROM `User` WHERE `name`= ?";
    private final String SQL_SELECT_MOST_SELLS_USER = "SELECT COUNT(`user_Name`) AS `sold`, `user_Name` AS `worker` FROM `Bill` GROUP BY `worker` ORDER BY `sold` DESC LIMIT 1";
    private final String SQL_SELECT_MOST_EARNS_USER = "SELECT `b`.`user_Name` AS `worker`, SUM(`b`.`ammount` - `f`.`assembly_Price`) AS `profit` FROM `Bill` AS `b` "
            + "INNER JOIN `Furniture_Assembly` AS `f` ON `f`.`id` = `b`.`furniture_Assemby_Id` GROUP BY `b`.`user_Name` ORDER BY `profit` DESC LIMIT 1";
    // INSERT
    private static final String SQL_INSERT_USER = "INSERT INTO `User` VALUES (?,?,?)"; // name, password, code area
    // UPDATE
    private static final String SQL_UPDATE_USER = "UPDATE `User` SET `password` = ?, `code_Area` = ? WHERE `name` = ?";
    // DELETE
    private static final String SQL_DELETE_USER = "DELETE FROM `User` WHERE `name` = ?";

    /**
     * select all users in DB
     *
     * @return
     */
    public ArrayList<User> selectUsers() {
        ArrayList<User> users = new ArrayList<>();

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USERS)) {
            // data
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("password"), rs.getShort("area_Code")));
            }
        } catch (Exception e) { // error catch
        }
        return users;
    }

    public User selectUser(String username) {
        User user = new User();
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_A_USER)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("password"), rs.getShort("area_Code"));
            }
        } catch (Exception e) {
        }
        return user;
    }

    public User selectMostSellsOrEarnigsUser(String mostSellsOrEarn) {
        User user = new User();
        String tmp = mostSellsOrEarn.equalsIgnoreCase("most") ? SQL_SELECT_MOST_SELLS_USER : SQL_SELECT_MOST_EARNS_USER;
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(tmp)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("worker"));
            }

        } catch (Exception e) {
        }
        return user;
    }

    public int insert(User user) {
        String pswdEncrypted = new AES256().encrypt(user.getPassword());
        if (pswdEncrypted == null) {
            return 0;
        }

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_USER)) {
            if (user.getName().trim().equals("") || user.getName() == null) {
                return 0;
            }
            ps.setString(1, user.getName());
            ps.setString(2, pswdEncrypted);
            ps.setShort(3, user.getAreaCode());
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public int update(User user) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_USER)) {
            ps.setString(1, user.getPassword());
            ps.setShort(2, user.getAreaCode());
            ps.setString(3, user.getName());
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public int delete(User user) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_DELETE_USER)) {
            ps.setString(1, user.getName());
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
