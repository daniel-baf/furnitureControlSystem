package Database;

import Domain.FurnitureAssembly;
import Domain.FurniturePiece;
import Domain.PieceAssembly;
import GeneralUse.InsertUtilities;
import TransactionObjects.BillFurniture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FurnitureAssemblyDAO {

    private final String SQL_INSERT_FURN_ASSM = "INSERT INTO `Furniture_Assembly` (`user_Name`, `date`,`sold`,`furniture_Name`,`assembly_Price`) VALUES (?,?,?,?,?)";
    private final String SQL_SELECT_FURN_ASSMS = "SELECT * FROM `Furniture_Assembly`";
    private final String SQL_SELECT_FURN_ASSM = "SELECT * FROM `Furniture_Assembly` WHERE id = ?";
    private final String SQL_SELECT_FURN_FOR_BILL = "SELECT `fa`.`id` AS `id`, `fa`.`furniture_Name` AS `name`, `f`.`sell_Price` AS `price`, "
            + "`fa`.`sold` AS `sold` FROM `Furniture_Assembly` AS `fa` INNER JOIN `Furniture` AS `f`ON `f`.`name` = `fa`.`furniture_Name` AND `fa`.`id` = ?";
    private final String SQL_DELETE_FURN_ASSM = "DELETE FROM `Furniture_Assembly` WHERE `id` = ?";
    private final String SQL_UPDATE_FURN_ASSM = "UPDATE `Furniture_Assembly` SET `user_Name` = ?, `date` = ?, `sold` = ?, `furniture_Name` = ?, `assembly_Price` = ? WHERE (`id` = ?)";

    /**
     * A long method, insert a new Furniture, but first search for available
     * pieces and if they exists, then they are deleted, then, insert Furniture,
     * if SQL error, rollback DB
     *
     * @param furnAssm
     * @return
     * @throws java.lang.Exception
     */
    public int insertFurnAssmebly(FurnitureAssembly furnAssm) throws Exception {
        int result = 0;
        // connect to db
        try ( Connection conn = ConnectionDB.getConnection()) {
            conn.setAutoCommit(false);
            try ( PreparedStatement ps = conn.prepareStatement(SQL_INSERT_FURN_ASSM)) {
                //We need the assemblies to know wich pieces use
                ArrayList<PieceAssembly> assemblies = new PieceAssemblyDAO().selectAssemblies(furnAssm.getFurnitureName(), true);
                ArrayList<FurniturePiece> pieces = new ArrayList<>();
                double assembPrice = 0;
                // VALIDATE assembly
                if (assemblies.isEmpty() || assemblies == null) {
                    return 0;
                }
                for (PieceAssembly assembly : assemblies) {
                    ArrayList<FurniturePiece> tmp = new FurniturePieceDAO().selectPiecesLimitX(assembly.getPieceName(), assembly.getCuantity());
                    if (tmp.isEmpty()) {
                        return 0;
                    } else if (tmp.size() < assembly.getCuantity()) {
                        return 0;
                    } else {
                        assembPrice = tmp.stream().map(furniturePiece -> { // add piece to arraylist
                            pieces.add(furniturePiece);
                            return furniturePiece;
                        }).map(furniturePiece -> furniturePiece.getCost()).reduce(assembPrice, (accumulator, _item) -> accumulator + _item);
                    }
                }
                // delete pieces
                pieces.forEach(piece -> {
                    try {
                        new FurniturePieceDAO().delete(piece);
                    } catch (Exception ex) {
                    }
                });
                // create furniture
                ps.setString(1, furnAssm.getUsername());
                ps.setDate(2, new InsertUtilities().parseLocalDateSQLDate(furnAssm.getDate()));
                ps.setInt(3, 0);
                ps.setString(4, furnAssm.getFurnitureName());
                ps.setDouble(5, assembPrice);
                // SQL execute
                result = ps.executeUpdate(); // success?
            } catch (Exception e) {
                conn.rollback();
            } finally {
                if (result == 0) {
                    conn.rollback();
                }
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("No se ha podido terminar la transaccion para insertar un nuevo mueble, verifica los datos ingresados, " + e.getMessage());
        }
        return result;
    }

    /**
     * get all furnitures available for sell from DB
     *
     * @param onlyAvailable
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<FurnitureAssembly> getFurnitures(boolean onlyAvailable) throws Exception {
        String SQL_TMP = onlyAvailable ? SQL_SELECT_FURN_ASSMS + " WHERE `sold` = 0" : SQL_SELECT_FURN_ASSMS; // query
        ArrayList<FurnitureAssembly> furnitures = new ArrayList<>();
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_TMP)) {
            // data
            ResultSet rs = ps.executeQuery();
            InsertUtilities iu = new InsertUtilities();
            while (rs.next()) { // furniture
                furnitures.add(new FurnitureAssembly(
                        rs.getInt("id"),
                        rs.getString("user_Name"),
                        iu.parseSQLDateToLocalDate(rs.getDate("date")),
                        rs.getInt("sold"),
                        rs.getString("furniture_Name"),
                        iu.getDoubleFromString(rs.getString("assembly_Price"))));
            }
        } catch (Exception e) { // error catch
            new InsertUtilities().throwCustomError("Error al seleccionar muebles, verifica los datos ingresados, " + e.getMessage());
        }
        return furnitures;
    }

    /**
     * delete a Furniture Assembly from DB
     *
     * @param furnAssem Furniture Assembly object
     * @return
     * @throws java.lang.Exception
     */
    public int deleteFurnAssembly(FurnitureAssembly furnAssem) throws Exception {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_DELETE_FURN_ASSM)) {
            ps.setInt(1, furnAssem.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al borrar mueble, verifica los datos ingresados, " + e.getMessage());
            return 0;
        }
    }

    /**
     * select a furniture by id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public FurnitureAssembly selectFurnAssembly(int id) throws Exception {
        FurnitureAssembly fa = null;
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_FURN_ASSM)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            InsertUtilities iu = new InsertUtilities();
            if (rs.next()) {
                fa = new FurnitureAssembly(rs.getInt("id"), rs.getString("user_Name"), iu.parseSQLDateToLocalDate(rs.getDate("date")), rs.getInt("sold"), rs.getString("furniture_Name"), rs.getDouble("assembly_Price"));
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al seleccionar mueble , verifica los datos ingresados, " + e.getMessage());
        }
        return fa;
    }

    /**
     * select all furniture and add a sell price for them by GROUP
     *
     * @param fa
     * @return
     * @throws java.lang.Exception
     */
    public BillFurniture selectFurniturePriceAndSellPrice(FurnitureAssembly fa) throws Exception {
        BillFurniture bf = null;
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_FURN_FOR_BILL)) {
            ps.setInt(1, fa.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bf = new BillFurniture(rs.getString("name"), rs.getInt("id"), rs.getDouble("price"), rs.getInt("sold"));
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al seleccionar muebles  y precio, verifica los datos ingresados, " + e.getMessage());
        }
        return bf;
    }

    /**
     *
     * @param furn
     * @return
     * @throws java.lang.Exception
     */
    public int update(FurnitureAssembly furn) throws Exception {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_FURN_ASSM)) {
            ps.setString(1, furn.getUsername());
            ps.setDate(2, new InsertUtilities().parseLocalDateSQLDate(furn.getDate()));
            ps.setInt(3, furn.getSold());
            ps.setString(4, furn.getFurnitureName());
            ps.setDouble(5, furn.getAssemblyPrice());
            ps.setInt(6, furn.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al actualizar mueble, verifica los datos ingresados, " + e.getMessage());
            return 0;
        }
    }
}
