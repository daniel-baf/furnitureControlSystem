package Database;

import Domain.Bill;
import Domain.Client;
import Domain.Furniture;
import Domain.FurnitureAssembly;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BillDAO {

    private final String SQL_SELECT_BILL = "SELECT * FROM `Bill` ";
    private final String SQL_SELECT_BILL_TODAY = "SELECT * FROM `Bill` WHERE `buy_Date` >= NOW()";
    private final String SQL_SELECT_REFUND_BILL_REPORT = "SELECT `b`.`code` AS `billId`, `f`.`furniture_Name` AS `furniture`, `b`.`buy_Date` AS `buyDate`, `b`.`furniture_Assemby_Id` AS `idFnt`,\n"
            + "`b`.`ammount` AS `ammount`, `b`.`client_NIT` AS `NIT`, `b`.`user_Name` AS `username` FROM `Bill` AS `b` INNER JOIN `Furniture_Assembly` AS `f`  ON `f`.`id` = `b`.`furniture_Assemby_Id`";
    private final String SQL_INSERT_BILL = "INSERT INTO `Bill` (`user_Name`, `furniture_Assemby_Id`, `ammount`, `client_NIT`, `buy_Date`) VALUES (?, ?, ?, ?, ?)";

    /**
     * get a full report of billing, uses User, Furniture and LocalDates
     *
     * @param initDate where billing report start
     * @param endDate where billing report ends
     * @param betweenDates if both dates aren't null, true
     * @param user used to search bills from specific worker, if requested
     * @param furn used to search bills where sold a specific furniture
     * @param client
     * @return bills list
     * @throws java.lang.Exception
     */
    public ArrayList<Bill> getBillingReport(LocalDate initDate, LocalDate endDate, boolean betweenDates, User user, Furniture furn, Client client) throws Exception {
        // get dates, what if no dates?
        String bckup = SQL_SELECT_REFUND_BILL_REPORT;
        bckup += betweenDates ? " AND (`b`.`buy_Date` BETWEEN ? AND ?)" : "";
        bckup += user != null ? " AND `f`.`user_Name` = ?" : "";
        bckup += furn != null ? " AND `f`.`furniture_Name` = ?" : "";
        bckup += client != null ? " AND `b`.`client_NIT` = ?" : "";

        ArrayList<Bill> bills = new ArrayList<>();
        InsertUtilities iu = new InsertUtilities();
        // let's suppose date 1 < date 2
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(bckup)) {
            configPreparedStatement(ps, user, furn, initDate, endDate, client);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(getBillForReport(rs));
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al obtener reportes, verifica la sintaxis y que los datos sean validos, " + e.getMessage());
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
    private void configPreparedStatement(PreparedStatement ps, User user, Furniture furniture, LocalDate date1, LocalDate date2, Client client) throws SQLException {
        InsertUtilities iu = new InsertUtilities();
        int psCounterItem = 1;
        if (date1 != null && date2 != null) {
            ps.setDate(psCounterItem++, iu.parseLocalDateSQLDate(date1));
            ps.setDate(psCounterItem++, iu.parseLocalDateSQLDate(date2));
        }
        if (user != null) {
            ps.setString(psCounterItem++, user.getName());
        }
        if (furniture != null) {
            ps.setString(psCounterItem++, furniture.getName());
        }
        if (client != null) {
            ps.setString(psCounterItem++, client.getNit());
        }
    }

    /**
     * this method return all bills from today
     *
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<Bill> selectBillsToday() throws Exception {
        ArrayList<Bill> bills = new ArrayList<>();
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BILL_TODAY)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bills.add(getBillFromResultSet(rs));
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al obtener las ventas del dia, verifica la sintaxis y que los datos sean validos, " + e.getMessage());
        }
        return bills;
    }

    /**
     * this method creates a new bill and update the furn status to 1 (sold)
     *
     * @param bill
     * @return
     * @throws IOException
     */
    public int insert(Bill bill) throws Exception {
        //1st desable autocomit
        int result = 0;
        try ( Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false);
            // try update
            FurnitureAssembly fa = new FurnitureAssemblyDAO().selectFurnAssembly(bill.getFurnitureAssemblyId());
            fa.setSold(1);
            if (new FurnitureAssemblyDAO().update(fa) != 0) {
                try ( PreparedStatement ps = conn.prepareStatement(SQL_INSERT_BILL)) {
                    ps.setString(1, bill.getUsername());
                    ps.setInt(2, bill.getFurnitureAssemblyId());
                    ps.setDouble(3, bill.getAmmount());
                    ps.setString(4, bill.getClientNit());
                    ps.setDate(5, new InsertUtilities().parseLocalDateSQLDate(bill.getBuyDate()));
                    result = ps.executeUpdate();
                } catch (Exception e) {
                    new InsertUtilities().throwCustomError("Erroren los valores para la factura, posible item ya vendido o sintaxis mala, " + e.getMessage());
                } finally {
                    if (result == 0) {
                        conn.rollback();
                    } else {
                        conn.commit();
                    }
                    conn.setAutoCommit(true);
                }
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("No se encuntra la BD, contactate con el encargado de programacion, " + e.getMessage());
        }

        return result;
    }

    /**
     * type 0 = select by furn name, type 1 = select by Id
     *
     * @param type
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public Bill select(int type, int id) throws Exception {
        Bill bill = null;
        String SQL_TMP = type == 0 ? SQL_SELECT_BILL + " WHERE `furniture_Assemby_Id` = ?" : SQL_SELECT_BILL + " WHERE `code` = ?";
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_TMP)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bill = getBillFromResultSet(rs);
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error, al obtener las ventas del dia, verifica la sintaxis y que los datos sean validos, " + e.getMessage());
        }
        return bill;
    }

    /**
     * get all bills from a result set
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Bill getBillFromResultSet(ResultSet rs) throws SQLException {
        return new Bill(
                rs.getInt("code"),
                rs.getInt("furniture_Assemby_Id"),
                rs.getString("user_Name"),
                rs.getString("client_NIT"),
                rs.getDouble("ammount"),
                new InsertUtilities().parseSQLDateToLocalDate(rs.getDate("buy_Date"))
        );
    }

    /**
     * get bills with extra info for financial reports
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Bill getBillForReport(ResultSet rs) throws SQLException {
        return new Bill(
                rs.getInt("billId"),
                rs.getInt("idFnt"),
                rs.getString("furniture"),
                rs.getString("username"),
                rs.getString("NIT"),
                rs.getDouble("ammount"),
                new InsertUtilities().parseSQLDateToLocalDate(rs.getDate("buyDate"))
        );
    }
}
