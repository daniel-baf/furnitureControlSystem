package Database;

import Domain.Earning;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class EarningsDAO {

    private final String SQL_SELECT_EARNINGS = "SELECT `b`.`code` AS `billId`, `f`.`furniture_Name` AS `furniture`, `b`.`ammount` AS `sellprice`, `b`.`buy_Date` AS `date`,"
            + "(`b`.`ammount` - `f`.`assembly_Price`) AS `profit`, `f`.`sold` AS `furState` FROM `Bill` AS `b`INNER JOIN `Furniture_Assembly` AS `f` ON `f`.`id` = `b`.`furniture_Assemby_Id` ";

    public ArrayList<Earning> getEarningsReport(boolean betweenDates, LocalDate initDate, LocalDate endDate) {
        String bckup = betweenDates ? SQL_SELECT_EARNINGS + " WHERE `b`.`buy_Date` BETWEEN ? AND ?" : SQL_SELECT_EARNINGS;
        ArrayList<Earning> earnings = new ArrayList<>();
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
                earnings.add(new Earning(
                        rs.getInt("billId"),
                        rs.getDouble("profit"),
                        rs.getDouble("sellprice"),
                        rs.getString("furniture"),
                        rs.getShort("furState"),
                        iu.parseSQLDateToLocalDate(rs.getDate("date"))
                ));
            }
        } catch (Exception e) {
        }
        return earnings;
    }
}
