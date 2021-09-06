package Database;

import Domain.Bill;
import Domain.Client;
import Domain.FurnitureAssembly;
import Domain.Refund;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

public class RefundDAO {

    private final String SQL_SELECT_REFUND_BILL_REPORT = "SELECT `r`.`bill_Code` AS `bID`, `f`.`furniture_Name` AS `furniture`, `r`.`date` AS `refDate`, `b`.`buy_Date` AS `buyDay`, "
            + "`r`.`ammount` AS `refMoney`, `b`.`client_NIT` AS `cNIT`, `f`.`assembly_Price` AS `prcAssembly`, (`f`.`assembly_Price`/3) AS `lstMoney` "
            + "FROM `Refund` AS `r` INNER JOIN `Bill` AS `b` ON `b`.`code` = `r`.`bill_Code` INNER JOIN `Furniture_Assembly` AS `f` ON `f`.`id` = `b`.`furniture_Assemby_ID` ";
    private final String SQL_INSERT_REFUND = "INSERT INTO `Refund` VALUES (?, ?, ?);";

    /**
     * return all refunds, is flexible method who differences if he wants query
     * between dates or not
     *
     * @param initDate start date for report
     * @param endDate end date for report
     * @param betweenDates dates are not null?
     * @param client
     * @return refunds
     * @throws java.lang.Exception
     */
    public ArrayList<Refund> getRefundsReport(LocalDate initDate, LocalDate endDate, boolean betweenDates, Client client) throws Exception {
        // get dates, what if no dates?
        String bckup = betweenDates ? SQL_SELECT_REFUND_BILL_REPORT + " AND `r`.`date` BETWEEN ? AND ?" : SQL_SELECT_REFUND_BILL_REPORT;
        int psCounter = 1;
        bckup += client != null ? " AND `b`.`client_NIT` = ? " : "";
        ArrayList<Refund> refunds = new ArrayList<>();

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(bckup)) {
            if (betweenDates) {
                if (initDate.isAfter(endDate)) {
                    LocalDate tmp = initDate;
                    initDate = endDate;
                    endDate = tmp;
                }
                ps.setDate(psCounter++, new InsertUtilities().parseLocalDateSQLDate(initDate));
                ps.setDate(psCounter++, new InsertUtilities().parseLocalDateSQLDate(endDate));
            }
            if (client != null) {
                ps.setString(psCounter++, client.getNit());
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
            new InsertUtilities().throwCustomError("Error al obtener devolucion, verifica los datos ingresados, " + e.getMessage());
        }
        return refunds;
    }

    public int insertRefund(Bill bill, User user, HttpServletResponse response) throws Exception {
        int result = 0;
        // connect to db
        response.getWriter().print("Vamos a empezar transaccion<br>");
        try ( Connection conn = ConnectionDB.getConnection()) {
            response.getWriter().print("Conecatdo a DB<br>");
            conn.setAutoCommit(false);
            // get furniture
            FurnitureAssembly fa = new FurnitureAssemblyDAO().selectFurnAssembly(bill.getCode());
            fa.setSold(2);
            response.getWriter().print("fa: " + fa + "<br>");
            // validate the update of furniture to DB to state 2 = refunded
            new FurnitureAssemblyDAO().update(fa);
            response.getWriter().print("Mueble actualizado<br>");
            // create refund
            try ( PreparedStatement ps = conn.prepareStatement(SQL_INSERT_REFUND)) {
                // SQL execute
                ps.setInt(1, bill.getCode());
                ps.setDate(2, new InsertUtilities().parseLocalDateSQLDate(LocalDate.now()));
                ps.setDouble(3, bill.getAmmount());
                result = ps.executeUpdate(); // success?
                // re integrate furniture
                if (result != 0) {
                    fa.setSold(0);
                    fa.setDate(LocalDate.now());
                    fa.setUsername(user.getName());
                    result = new FurnitureAssemblyDAO().insertFurnAssmebly(fa);
                }
            } catch (Exception e) {
                new InsertUtilities().throwCustomError("Error, al insertar usuario, revisa los datos ingresados, " + e.getMessage());
                result = 0;
            } finally {
                if (result == 0) {
                    conn.rollback();
                }
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al conectar a la base de datos, " + e.getMessage());
        }
        return result;
    }
}
