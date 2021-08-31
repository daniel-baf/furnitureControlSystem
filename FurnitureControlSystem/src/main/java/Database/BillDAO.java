package Database;

import Domain.Bill;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class BillDAO {

    private final String SQL_SELECT_REFUND_BILL_REPORT = "SELECT `b`.`code` AS `billId`, `f`.`furniture_Name` AS `furniture`, `b`.`buy_Date` AS `buyDate`, `b`.`furniture_Assemby_Id` AS `idFnt`,\n"
            + "`b`.`ammount` AS `ammount`, `b`.`client_NIT` AS `NIT` FROM `Bill` AS `b` INNER JOIN `Furniture_Assembly` AS `f`  ON `f`.`id` = `b`.`furniture_Assemby_Id`";

    public ArrayList<Bill> getBillingReport(LocalDate initDate, LocalDate endDate, boolean betweenDates, User user) {
        // get dates, what if no dates?
        String bckup = SQL_SELECT_REFUND_BILL_REPORT;
        bckup += user != null ? " AND `f`.`user_Name` = ?" : "";
        bckup += betweenDates ? " AND (`b`.`buy_Date` BETWEEN ? AND ?)" : "";
        ArrayList<Bill> bills = new ArrayList<>();

        if (betweenDates) {
            if (initDate.isAfter(endDate)) {
                LocalDate tmp = initDate;
                initDate = endDate;
                endDate = tmp;
            }
        }

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(bckup)) {
            if (user != null) {
                ps.setString(1, user.getName());
                if (betweenDates) {
                    ps.setDate(2, new InsertUtilities().parseLocalDateSQLDate(initDate));
                    ps.setDate(3, new InsertUtilities().parseLocalDateSQLDate(endDate));
                }
            } else if (betweenDates) {
                ps.setDate(1, new InsertUtilities().parseLocalDateSQLDate(initDate));
                ps.setDate(2, new InsertUtilities().parseLocalDateSQLDate(endDate));
            }
            ResultSet rs = ps.executeQuery();
            InsertUtilities iu = new InsertUtilities();
            while (rs.next()) {
                bills.add(new Bill(
                        rs.getInt("billId"),
                        rs.getInt("idFnt"),
                        rs.getString("furniture"),
                        null,// I don't need username in this query
                        rs.getString("NIT"),
                        rs.getDouble("ammount"),
                        iu.parseSQLDateToLocalDate(rs.getDate("buyDate"))
                ));
            }
        } catch (Exception e) {
        }
        return bills;
    }
}
