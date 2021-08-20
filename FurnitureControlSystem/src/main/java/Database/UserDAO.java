package Database;

import Domain.User;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    // SELECT 
    private static final String SQL_SELECT_USERS = "SELECT * FROM `User`";
    private static final String SQL_SELECT_A_USER = "SELECT * FROM `User` WHERE `name`= ?";
    // INSERT
    private static final String SQL_INSERT_USER = "INSERT INTO `User` VALUES (?,?,?)"; // name, password, code area
    // UPDATE
    private static final String SQL_UPDATE_USER = "UPDATE `User` SET `password` = ?, `codeArea` = ? WHERE `name` = ?";
    // DELETE
    private static final String SQL_DELETE_USER = "DELETE FROM `User` WHERE `name` = ?";

    public ArrayList<User> selectUsers() {
        ArrayList<User> users = new ArrayList<>();

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USERS)) {
            // data
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("password"), rs.getShort("areaCode")));
            }
            return users;
        } catch (Exception e) {
            return null;
        }
    }

    public User selectUser(String username) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_A_USER)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("name"), rs.getString("password"), rs.getShort("areaCode"));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public int insert(User user) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_USER)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setShort(3, user.getAreaCode());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            // error on insrt, Servlet create table
            return 0;
        }
    }

    public int update(User user) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_USER)) {
            ps.setString(1, user.getPassword());
            ps.setShort(2, user.getAreaCode());
            ps.setString(3, user.getName());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            // error on update, Servlet create table
            return 0;
        }
    }

    public int delete(User user) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_DELETE_USER)) {
            ps.setString(1, user.getName());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            // error on update, Servlet create table
            return 0;
        }
    }
}
