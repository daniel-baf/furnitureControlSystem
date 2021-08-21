package Database;

import Domain.FurniturePiece;
import Domain.PieceAssembly;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;

public class PieceAssemblyDAO {

    private final String SQL_INSERT_PIECE_ASSEMB = "INSERT INTO `PieceAssembly` VALUES (?, ?, ?)";

    public int insert(PieceAssembly pieceAssm) throws IOException, SQLException, ClassNotFoundException {
        // let's see if pieceName exist

        if (new FurniturePieceDAO().selectPieceByName(pieceAssm.getPieceName().toLowerCase()).size() > 0) {
            try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PIECE_ASSEMB)) {
                ps.setString(1, pieceAssm.getFurnitureName().toLowerCase());
                ps.setString(2, pieceAssm.getPieceName().toLowerCase());
                ps.setInt(3, pieceAssm.getCuantity());
                int reg = ps.executeUpdate();
                return reg;
            } catch (Exception e) {
                // error on insrt, Servlet create table
                return 0;
            }
        }
        return 0;
    }
}
