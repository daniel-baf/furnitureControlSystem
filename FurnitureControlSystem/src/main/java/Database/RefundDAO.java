package Database;

import Domain.Refund;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class RefundDAO {

    private final String SQL_SELECT_REFUND_BILL_REPORT = "SELECT `r`.`bill_Code` AS `bID`, `f`.`furniture_Name` AS `furniture`, `r`.`date` AS `refDate`, `b`.`buy_Date` AS `buyDay`, "
            + "`r`.`ammount` AS `refMoney`, `b`.`client_NIT` AS `cNIT`, `f`.`assembly_Price` AS `prcAssembly`, (`f`.`assembly_Price`/3) AS `lstMoney` "
            + "FROM `Refund` AS `r` INNER JOIN `Bill` AS `b` ON `b`.`code` = `r`.`bill_Code` INNER JOIN `Furniture_Assembly` AS `f` ON `f`.`id` = `b`.`furniture_Assemby_ID` ";

    /**
     * return all refunds, is flexible method who differences if he wants query between dates or not
     * @param initDate start date for report
     * @param endDate end date for report
     * @param betweenDates dates are not null?
     * @return refunds
     */
    public ArrayList<Refund> getRefundsReport(LocalDate initDate, LocalDate endDate, boolean betweenDates) {
        // get dates, what if no dates?
        String bckup = betweenDates ? SQL_SELECT_REFUND_BILL_REPORT + " WHERE `r`.`date` BETWEEN ? AND ?" : SQL_SELECT_REFUND_BILL_REPORT;
        ArrayList<Refund> refunds = new ArrayList<>();

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(bckup)) {
            if (betweenDates) {
                if (initDate.isAfter(endDate)) {
                    LocalDate tmp = initDate;
                    initDate = endDate;
                    endDate = tmp;
                }
                ps.setDate(1, new InsertUtilities().parseLocalDateSQLDate(initDate));
                ps.setDate(2, new InsertUtilities().parseLocalDateSQLDate(endDate));
            }
            ResultSet rs = ps.executeQuery();
            InsertUtilities iu = new InsertUtilities();
            while (rs.next()) {
                refunds.add(new Refund(
                        rs.getInt("bID"),
                        rs.getString("furniture"),
                        iu.parseSQLDateToLocalDate(rs.getDate("refDate")),
                        iu.parseSQLDateToLocalDate(rs.getDate("buyDay")),
                        rs.getDouble("refMoney"),
                        rs.getString("cNIT"),
                        rs.getDouble("prcAssembly"),
                        rs.getDouble("lstMoney")
                ));
            }
        } catch (Exception e) {
        }
        return refunds;
    }
}
