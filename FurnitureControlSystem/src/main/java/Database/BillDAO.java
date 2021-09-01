package Database;

import Domain.Bill;
import Domain.Furniture;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BillDAO {

    private final String SQL_SELECT_REFUND_BILL_REPORT = "SELECT `b`.`code` AS `billId`, `f`.`furniture_Name` AS `furniture`, `b`.`buy_Date` AS `buyDate`, `b`.`furniture_Assemby_Id` AS `idFnt`,\n"
            + "`b`.`ammount` AS `ammount`, `b`.`client_NIT` AS `NIT`, `b`.`user_Name` AS `username` FROM `Bill` AS `b` INNER JOIN `Furniture_Assembly` AS `f`  ON `f`.`id` = `b`.`furniture_Assemby_Id`";

    /**
     * get a full report of billing, uses User, Furniture and LocalDates
     *
     * @param initDate where billing report start
     * @param endDate where billing report ends
     * @param betweenDates if both dates aren't null, true
     * @param user used to search bills from specific worker, if requested
     * @param furn used to search bills where sold a specific furniture
     * @return bills list
     */
    public ArrayList<Bill> getBillingReport(LocalDate initDate, LocalDate endDate, boolean betweenDates, User user, Furniture furn) {
        // get dates, what if no dates?
        String bckup = SQL_SELECT_REFUND_BILL_REPORT;
        bckup += betweenDates ? " AND (`b`.`buy_Date` BETWEEN ? AND ?)" : "";
        bckup += user != null ? " AND `f`.`user_Name` = ?" : "";
        bckup += furn != null ? " AND `f`.`furniture_Name` = ?" : "";
        ArrayList<Bill> bills = new ArrayList<>();
        InsertUtilities iu = new InsertUtilities();
        // let's suppose date 1 < date 2
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(bckup)) {
            configPreparedStatement(ps, user, furn, initDate, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(
                        rs.getInt("billId"),
                        rs.getInt("idFnt"),
                        rs.getString("furniture"),
                        rs.getString("username"),// I don't need username in this query
                        rs.getString("NIT"),
                        rs.getDouble("ammount"),
                        iu.parseSQLDateToLocalDate(rs.getDate("buyDate"))
                ));
            }
        } catch (Exception e) {
        }
        return bills;
    }

    /**
     * a configuration for method Get Billing, used to make method readable,
     * just configures a Prepared Statement and auto set attributes
     *
     * @param ps prepared statemente
     * @param user User, can be null
     * @param furniture furniture, Can be null
     * @param date1 date for billing report, can be null
     * @param date2 date for end billing report, can be null
     * @throws SQLException
     */
    private void configPreparedStatement(PreparedStatement ps, User user, Furniture furniture, LocalDate date1, LocalDate date2) throws SQLException {
        InsertUtilities iu = new InsertUtilities();
        boolean haveDates = date1 != null && date2 != null;
        if (haveDates) {
            ps.setDate(1, iu.parseLocalDateSQLDate(date1));
            ps.setDate(2, iu.parseLocalDateSQLDate(date2));
            if (user != null) {
                ps.setString(3, user.getName());
                if (furniture != null) {
                    ps.setString(4, furniture.getName());
                }
            } else if (user == null && furniture != null) {
                ps.setString(3, furniture.getName());
            }
        } else {
            if (user != null) {
                ps.setString(1, user.getName());
                if (furniture != null) {
                    ps.setString(2, furniture.getName());
                }
            } else if (user == null && furniture != null) {
                ps.setString(1, furniture.getName());
            }
        }
    }
}
