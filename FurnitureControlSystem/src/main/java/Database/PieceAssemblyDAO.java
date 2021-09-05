package Database;

import Domain.PieceAssembly;
import GeneralUse.InsertUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PieceAssemblyDAO {

    private final String SQL_INSERT_PIECE_ASSEMB = "INSERT INTO `Piece_Assembly` VALUES (?, ?, ?)";
    private final String SQL_SELECT_PIECE_ASEMBS = "SELECT * FROM `Piece_Assembly` ";

    /**
     * Insert a new Piece Assembly to Db
     *
     * @param pieceAssm
     * @return
     * @throws java.lang.Exception
     */
    public int insert(PieceAssembly pieceAssm) throws Exception {
        // let's see if pieceName exist
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PIECE_ASSEMB)) {
            if (new FurniturePieceDAO().selectPieces(pieceAssm.getPieceName().toLowerCase(), true).size() > 0) {
                ps.setString(1, pieceAssm.getFurnitureName().toLowerCase());
                ps.setString(2, pieceAssm.getPieceName().toLowerCase());
                ps.setInt(3, pieceAssm.getCuantity());
                int reg = ps.executeUpdate();
                return reg;
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al insertar pieza, verifica los datos ingresados, " + e.getMessage());
        }
        return 0;
    }

    /**
     * Return a list with all the assemblies of piece on DB
     *
     * @param furnitureName
     * @param byName
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<PieceAssembly> selectAssemblies(String furnitureName, boolean byName) throws Exception {
        String SQL_TEMP = SQL_SELECT_PIECE_ASEMBS;
        SQL_TEMP += byName ? " WHERE `furniture_Name` = ? " : "";
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_TEMP)) {
            ArrayList<PieceAssembly> assemblies = new ArrayList<>();
            if (byName) {
                ps.setString(1, furnitureName);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                assemblies.add(new PieceAssembly(rs.getString("furniture_Name"), rs.getString("piece_Name"), rs.getInt("cuantity")));
            }
            return assemblies;
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al seleccionar ensamble de pieza, verifica los datos ingresados, " + e.getMessage());
            return null;
        }
    }
}
