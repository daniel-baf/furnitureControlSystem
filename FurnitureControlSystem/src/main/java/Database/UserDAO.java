package Database;

import Domain.User;
import GeneralUse.AES256;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    // SELECT 
    private static final String SQL_SELECT_USERS = "SELECT * FROM `User`";
    private static final String SQL_SELECT_A_USER = "SELECT * FROM `User` WHERE `name`= ?";
    // INSERT
    private static final String SQL_INSERT_USER = "INSERT INTO `User` VALUES (?,?,?)"; // name, password, code area
    // UPDATE
    private static final String SQL_UPDATE_USER = "UPDATE `User` SET `password` = ?, `code_Area` = ? WHERE `name` = ?";
    // DELETE
    private static final String SQL_DELETE_USER = "DELETE FROM `User` WHERE `name` = ?";

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
