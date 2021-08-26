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
    private final String SQL_INSERT_PIECE = "INSERT INTO `FurniturePiece` (`name`, `cost`) VALUES (?, ?);";
    // SELECT
    private final String SQL_SELECT_PIECE_BY_NAME = "SELECT * FROM `FurniturePiece` WHERE `name` = ?";
    private final String SQL_SELECT_PIECES_NAME_LIMIT = "SELECT * FROM `FurniturePiece` WHERE `name`= ? ORDER BY `id` ASC LIMIT ?";
    //DELTE
    private final String SQL_DELETE_PIECE = "DELETE FROM `FurniturePiece` WHERE `id` = ?";

    public int insert(FurniturePiece piece) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PIECE)) {
            ps.setString(1, piece.getName().toLowerCase());
            ps.setDouble(2, piece.getCost());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            // error on insrt, Servlet create table
            return 0;
        }
    }

    public ArrayList<FurniturePiece> selectPieceByName(String name) {
        ArrayList<FurniturePiece> pieces = new ArrayList<>();

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PIECE_BY_NAME)) {
            // data
            ps.setString(1, name.toLowerCase());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pieces.add(new FurniturePiece(rs.getInt("id"), rs.getString("name"), rs.getDouble("cost")));
            }
        } catch (Exception e) {
        }
        return pieces;
    }

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

    public ArrayList<PieceByStock> selectPieceAndStock(String furnitureName, HttpServletResponse response) {
        ArrayList<PieceByStock> list = new ArrayList<>();
        String TMP_QUERY = "SELECT `pieceName` , (SELECT COUNT(`name`) FROM `FurniturePiece` WHERE `name` = `pieceName` GROUP BY `name`) AS `stock` "
                + "FROM `PieceAssembly` WHERE `furnitureName` = ?";

        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(TMP_QUERY)) {
            // data
            ps.setString(1, furnitureName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PieceByStock(rs.getInt("stock"), rs.getString("pieceName")));
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    public int delete(FurniturePiece piece) {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PIECE)) {
            ps.setInt(1, piece.getId());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            // error on update, Servlet create table
            return 0;
        }
    }
}
