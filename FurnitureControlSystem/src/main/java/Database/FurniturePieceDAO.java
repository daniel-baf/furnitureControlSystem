package Database;

import Domain.FurniturePiece;
import TransactionObjects.PieceByStock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

public class FurniturePieceDAO {

    // INSERT
    private final String SQL_INSERT_PIECE = "INSERT INTO `Furniture_Piece` (`name`, `cost`) VALUES (?, ?);";
    // SELECT
    private final String SQL_SELECT_PIECE = "SELECT * FROM `Furniture_Piece` ";
    private final String SQL_SELECT_PIECES_NAME_LIMIT = "SELECT * FROM `Furniture_Piece` WHERE `name`= ? ORDER BY `id` ASC LIMIT ?";
    //DELTE
    private final String SQL_DELETE_PIECE = "DELETE FROM `Furniture_Piece` WHERE `id` = ?";

    /**
     * insert a user on DB
     *
     * @param piece object Piece assembly
     * @return status of SQL action
     */
    public int insert(FurniturePiece piece) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PIECE)) {
            ps.setString(1, piece.getName().toLowerCase());
            ps.setDouble(2, piece.getCost());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * select all Piece Assemblies on DB, but only the one with the name 'name'
     *
     * @param name
     * @param byName
     * @return
     */
    public ArrayList<FurniturePiece> selectPieces(String name, boolean byName) {
        ArrayList<FurniturePiece> pieces = new ArrayList<>();
        String SQL_TEMP = SQL_SELECT_PIECE;
        SQL_TEMP += byName == true ? " WHERE `name` = ? " : "";
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_TEMP)) {
            // data
            if (byName) {
                ps.setString(1, name.toLowerCase());
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pieces.add(new FurniturePiece(rs.getInt("id"), rs.getString("name"), rs.getDouble("cost")));
            }
        } catch (Exception e) {
        }
        return pieces;
    }

    /**
     * select x pieces, this method is used before insert a new Furniture
     * Assembly
     *
     * @param name piece name
     * @param limit limit of pieces
     * @return
     */
    public ArrayList<FurniturePiece> selectPiecesLimitX(String name, int limit) {
        ArrayList<FurniturePiece> pieces = new ArrayList<>();
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PIECES_NAME_LIMIT)) {
            // data
            ps.setString(1, name.toLowerCase());
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pieces.add(new FurniturePiece(rs.getInt("id"), rs.getString("name"), rs.getInt("cost")));
            }
        } catch (Exception e) {
        }
        return pieces;
    }

    /**
     * select pieces with the available stock, this method is used to show no
     * stock pieces
     *
     * @param furnitureName
     * @param response
     * @return
     */
    public ArrayList<PieceByStock> selectPieceAndStock(String furnitureName, HttpServletResponse response) {
        ArrayList<PieceByStock> list = new ArrayList<>();
        String TMP_QUERY = "SELECT `piece_Name` , (SELECT COUNT(`name`) FROM `Furniture_Piece` WHERE `name` = `piece_Name` GROUP BY `name`) AS `stock` "
                + "FROM `Piece_Assembly` WHERE `furniture_Name` = ?";
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(TMP_QUERY)) {
            // data
            ps.setString(1, furnitureName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PieceByStock(rs.getInt("stock"), rs.getString("piece_Name")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    /**
     * delete a furniture piece from DB
     *
     * @param piece Object FurniturePiece
     * @return SQL query status
     */
    public int delete(FurniturePiece piece) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PIECE)) {
            ps.setInt(1, piece.getId());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            return 0;
        }
    }
}
