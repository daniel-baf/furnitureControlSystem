package Database;

import Domain.FurnitureAssembly;
import Domain.FurniturePiece;
import Domain.PieceAssembly;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FurnitureAssemblyDAO {

    private final String SQL_INSERT_FURN_ASSM = "INSERT INTO `Furniture_Assembly` (`user_Name`, `date`,`sold`,`furniture_Name`,`assembly_Price`) VALUES (?,?,?,?,?)";
    private final String SQL_SELECT_FURNITURES = "SELECT * FROM `Furniture_Assembly`";
    private final String SQL_DELETE_FURN_ASSM = "DELETE FROM `Furniture_Assembly` WHERE `id` = ?";

    /**
     * A long method, insert a new Furniture, but first search for available
     * pieces and if they exists, then they are deleted, then, insert Furniture,
     * if SQL error, rollback DB
     *
     * @param furnAssm
     * @return
     */
    public int insertFurnAssmebly(FurnitureAssembly furnAssm) {
        int result = 0;
        try ( Connection conn = ConnectionDB.getConnection()) {

            conn.setAutoCommit(false);
            try ( PreparedStatement ps = conn.prepareStatement(SQL_INSERT_FURN_ASSM)) {
                //We need the assemblies to know wich pieces use
                ArrayList<PieceAssembly> assemblies = new PieceAssemblyDAO().selectAssemblies(furnAssm.getFurnitureName(), true);
                ArrayList<FurniturePiece> pieces = new ArrayList<>();
                double assembPrice = 0;

                if (assemblies.isEmpty() || assemblies.isEmpty()) {
                    return 0;
                }

                for (PieceAssembly assembly : assemblies) {
                    ArrayList<FurniturePiece> tmp = new FurniturePieceDAO().selectPiecesLimitX(assembly.getPieceName(), assembly.getCuantity());
                    if (tmp.isEmpty()) {
                        return 0;
                    } else if (tmp.size() < assembly.getCuantity()) {
                        return 0;
                    } else {
                        assembPrice = tmp.stream().map(furniturePiece -> {
                            pieces.add(furniturePiece);
                            return furniturePiece;
                        }).map(furniturePiece -> furniturePiece.getCost()).reduce(assembPrice, (accumulator, _item) -> accumulator + _item);
                    }
                }
                // delete pieces
                pieces.forEach(piece -> {
                    new FurniturePieceDAO().delete(piece);
                });
                // create furniture
                ps.setString(1, furnAssm.getUsername());
                ps.setDate(2, new InsertUtilities().parseLocalDateSQLDate(furnAssm.getDate()));
                ps.setInt(3, 0);
                ps.setString(4, furnAssm.getFurnitureName());
                ps.setDouble(5, assembPrice);
                // SQL execute
                result = ps.executeUpdate();
            } catch (Exception e) {
                conn.rollback();
            } finally {
                if (result == 0) {
                    conn.rollback();
                }
                conn.setAutoCommit(true);
            }
            return result;
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<FurnitureAssembly> getFurnitures() {
        ArrayList<FurnitureAssembly> furnitures = new ArrayList<>();
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_FURNITURES)) {
            // data
            ResultSet rs = ps.executeQuery();
            InsertUtilities iu = new InsertUtilities();
            while (rs.next()) {
                furnitures.add(new FurnitureAssembly(
                        rs.getInt("id"),
                        rs.getString("user_Name"),
                        iu.parseSQLDateToLocalDate(rs.getDate("date")),
                        rs.getInt("sold"),
                        rs.getString("furniture_Name"),
                        iu.getDoubleFromString(rs.getString("assembly_Price"))));
            }
        } catch (Exception e) { // error catch
        }
        return furnitures;
    }

    /**
     * delete a Furniture Assembly from DB
     *
     * @param furnAssem Furniture Assembly object
     * @return
     */
    public int deleteFurnAssembly(FurnitureAssembly furnAssem) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_DELETE_FURN_ASSM)) {
            ps.setInt(1, furnAssem.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
