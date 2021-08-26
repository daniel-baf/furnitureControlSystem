package Database;

import Domain.PieceAssembly;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PieceAssemblyDAO {

    // INSERT
    private final String SQL_INSERT_PIECE_ASSEMB = "INSERT INTO `PieceAssembly` VALUES (?, ?, ?)";
    // SELECT
    private final String SQL_SELECT_PIECE_ASEMBS = "SELECT * FROM `PieceAssembly` WHERE `furnitureName` = ?";

    public int insert(PieceAssembly pieceAssm) {
        // let's see if pieceName exist
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PIECE_ASSEMB)) {
            if (new FurniturePieceDAO().selectPieceByName(pieceAssm.getPieceName().toLowerCase()).size() > 0) {
                ps.setString(1, pieceAssm.getFurnitureName().toLowerCase());
                ps.setString(2, pieceAssm.getPieceName().toLowerCase());
                ps.setInt(3, pieceAssm.getCuantity());
                int reg = ps.executeUpdate();
                return reg;
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }

    }

    public ArrayList<PieceAssembly> selectAssemblies(String furnitureName) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PIECE_ASEMBS)) {
            ArrayList<PieceAssembly> assemblies = new ArrayList<>();
            // data
            ps.setString(1, furnitureName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                assemblies.add(new PieceAssembly(rs.getString("furnitureName"), rs.getString("pieceName"), rs.getInt("cuantity")));
            }
            return assemblies;
        } catch (Exception e) {
            return null;
        }
    }
}
