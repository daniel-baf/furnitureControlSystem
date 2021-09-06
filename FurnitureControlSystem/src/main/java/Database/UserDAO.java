package Database;

import Domain.User;
import GeneralUse.InsertUtilities;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    // SELECT 
    private final String SQL_SELECT_USERS = "SELECT * FROM `User`";
    private final String SQL_SELECT_A_USER = "SELECT * FROM `User` WHERE `name`= ?";
    private final String SQL_SELECT_MOST_SELLS_USER = "SELECT COUNT(`user_Name`) AS `sold`, `user_Name` AS `worker` FROM `Bill` GROUP BY `worker` ORDER BY `sold` DESC LIMIT 1";
    private final String SQL_SELECT_MOST_EARNS_USER = "SELECT `b`.`user_Name` AS `worker`, SUM(`b`.`ammount` - `f`.`assembly_Price`) AS `profit` FROM `Bill` AS `b` "
            + "INNER JOIN `Furniture_Assembly` AS `f` ON `f`.`id` = `b`.`furniture_Assemby_Id` GROUP BY `b`.`user_Name` ORDER BY `profit` DESC LIMIT 1";
    // INSERT
    private static final String SQL_INSERT_USER = "INSERT INTO `User` VALUES (?,?,?, ?)"; // name, password, code area
    // UPDATE
    private static final String SQL_UPDATE_USER = "UPDATE `User` SET `password` = ?, `area_Code` = ?, `authorized` = ? WHERE (`name` = ?)";
    // DELETE
    private static final String SQL_DELETE_USER = "DELETE FROM `User` WHERE `name` = ?";

    /**
     * select all users in DB
     *
     * @return
     * @throws java.lang.Exception custom error with a "error" message
     */
    public ArrayList<User> selectUsers() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USERS)) {
            // data
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(getUserFromRs(rs));
            }
        } catch (Exception e) { // error catch
            new InsertUtilities().throwCustomError("Error, al seleccionar usuario, revisa los datos ingresados " + e.getMessage());
        }
        return users;
    }

    /**
     * select a user by name
     *
     * @param username
     * @return
     * @throws java.lang.Exception custom error with a "message" error
     */
    public User selectUser(String username) throws Exception {
        User user = null;
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_A_USER)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = getUserFromRs(rs);
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al seleccionar usuario, revisa los datos ingresados, " + e.getMessage());
        }
        return user;
    }

    /**
     * select the user with more or less sells in DB registered
     *
     * @param mostSellsOrEarn
     * @return
     * @throws java.lang.Exception
     */
    public User selectMostSellsOrEarnigsUser(String mostSellsOrEarn) throws Exception {
        User user = new User();
        String tmp = mostSellsOrEarn.equalsIgnoreCase("most") ? SQL_SELECT_MOST_SELLS_USER : SQL_SELECT_MOST_EARNS_USER;
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(tmp)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("worker"));
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al seleccionar usuario con ganancias, revisa los datos ingresados " + e.getMessage());
        }
        return user;
    }

    /**
     * insert a new User on DB, encrypt password before insert
     *
     * @param user a user, the password is plain text
     * @return true if no error on insert
     * @throws java.lang.Exception
     */
    public int insert(User user) throws Exception {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_USER)) {
            if (user.getName().trim().equals("") || user.getName() == null) {
                return 0;
            }
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setShort(3, user.getAreaCode());
            ps.setInt(4, user.getAuthorized());
            return ps.executeUpdate();
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al insertar usuario, revisa los datos ingresados, " + e.getMessage());
            return 0;
        }
}

    /**
     * update a User on DB
     *
     * @param user object with the info to update
     * @return
     * @throws java.lang.Exception
     */
    public int update(User user) throws Exception {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_USER)) {
            ps.setString(1, user.getPassword());
            ps.setShort(2, user.getAreaCode());
            ps.setInt(3, user.getAuthorized());
            ps.setString(4, user.getName());
            return ps.executeUpdate();
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al actualizar usuario, revisa los datos ingresados, " + e.getMessage());
            return 0;
        }
    }

    /**
     * delete a user from DB via username
     *
     * @param user
     * @return
     * @throws java.lang.Exception
     */
    public int delete(User user) throws Exception {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_DELETE_USER)) {
            ps.setString(1, user.getName());
            return ps.executeUpdate();
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al borrar usuario, revisa los datos ingresados, " + e.getMessage());
            return 0;
        }
    }

    /**
     * return a User getting the values from the ResultSet
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private User getUserFromRs(ResultSet rs) throws SQLException {
        return new User(rs.getString("name"), rs.getString("password"), rs.getShort("area_Code"), rs.getInt("authorized"));
    }
}
