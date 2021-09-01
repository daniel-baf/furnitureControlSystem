package Database;

import Domain.Earning;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EarningsDAO {

    private final String SQL_SELECT_EARNINGS = "SELECT `b`.`code` AS `billId`, `f`.`user_Name` AS `worker`,`f`.`furniture_Name` AS `furniture`, `b`.`ammount` AS `sellprice`, `b`.`buy_Date` AS `date`,"
            + "(`b`.`ammount` - `f`.`assembly_Price`) AS `profit`, `f`.`sold` AS `furState` FROM `Bill` AS `b`INNER JOIN `Furniture_Assembly` AS `f` ON `f`.`id` = `b`.`furniture_Assemby_Id` ";

    /**
     * Get the earnings as array list from DB, this includes info like user,
     * amount, the earnings = sell price - assembly price
     *
     * @param betweenDates true if both dates aren't null
     * @param initDate to set on BETWEEN SQL instruction
     * @param endDate to set on BETWEEN, SLQ query
     * @param user User object
     * @return Earnings List
     */
    public ArrayList<Earning> getEarningsReport(boolean betweenDates, LocalDate initDate, LocalDate endDate, User user) {
        // validate Query for multiple actions
        String bckup = SQL_SELECT_EARNINGS;
        bckup += betweenDates ? " AND (`b`.`buy_Date` BETWEEN ? AND ?)" : "";
        bckup += user != null ? " AND `f`.`user_Name` = ?" : "";
        ArrayList<Earning> earnings = new ArrayList<>();
        boolean activeDates = false; // to know if theres's dates sat on RS
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(bckup)) {
            // config resultSet
            confResultSet(betweenDates, initDate, endDate, ps, user);
            ResultSet rs = ps.executeQuery();
            InsertUtilities iu = new InsertUtilities();
            while (rs.next()) {
                earnings.add(new Earning(
                        rs.getInt("billId"),
                        rs.getDouble("profit"),
                        rs.getDouble("sellprice"),
                        rs.getString("furniture"),
                        rs.getShort("furState"),
                        iu.parseSQLDateToLocalDate(rs.getDate("date")),
                        rs.getString("worker")
                ));
            }
        } catch (Exception e) {
        }
        return earnings;
    }

    /**
     * a common task for future methods
     *
     * @param betweenDates
     * @param initDate
     * @param endDate
     * @param ps
     * @param user
     * @throws SQLException
     */
    private void confResultSet(boolean betweenDates, LocalDate initDate, LocalDate endDate, PreparedStatement ps, User user) throws SQLException {
        boolean activeDates = false;
        if (betweenDates) {
            if (initDate.isAfter(endDate)) {
                LocalDate tmp = initDate;
                initDate = endDate;
                endDate = tmp;
            }
            ps.setDate(1, new InsertUtilities().parseLocalDateSQLDate(initDate));
            ps.setDate(2, new InsertUtilities().parseLocalDateSQLDate(endDate));
            activeDates = true;
        }
        if (activeDates && user != null) {
            ps.setString(3, user.getName());
        } else if (!activeDates && user != null) {
            ps.setString(1, user.getName());
        }
    }
}
