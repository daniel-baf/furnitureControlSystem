package Database;

import Domain.FurniturePiece;
import GeneralUse.InsertUtilities;
import TransactionObjects.PieceByStock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FurniturePieceDAO {

    private final String SQL_INSERT_PIECE = "INSERT INTO `Furniture_Piece` (`name`, `cost`) VALUES (?, ?);";
    private final String SQL_SELECT_PIECE = "SELECT * FROM `Furniture_Piece` ";
    private final String SQL_SELECT_PIECES_NAME_LIMIT = "SELECT * FROM `Furniture_Piece` WHERE `name`= ? ORDER BY `id` ASC LIMIT ?";
    private final String SQL_SELECT_PIECES_STOCK = "SELECT `name`, COUNT(`name`) AS `stock` FROM `Furniture_Piece` GROUP BY `name`";
    private final String SQL_DELETE_PIECE = "DELETE FROM `Furniture_Piece` WHERE `id` = ?";
    private final String SQL_UPDATE_PIECE = "UPDATE `Furniture_Piece` SET `name` = ?, `cost` = ? WHERE (`id` = ?)";

    /**
     * insert a user on DB
     *
     * @param piece object Piece assembly
     * @return status of SQL action
     * @throws java.lang.Exception
     */
    public int insert(FurniturePiece piece) throws Exception {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PIECE)) {
            ps.setString(1, piece.getName().toLowerCase());
            ps.setDouble(2, piece.getCost());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al insertar pieza, verifica los datos ingresados, " + e.getMessage());
            return 0;
        }
    }

    /**
     * select all Piece Assemblies on DB, but only the one with the name 'name'
     *
     * @param name
     * @param byName
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<FurniturePiece> selectPieces(String name, boolean byName) throws Exception {
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
            new InsertUtilities().throwCustomError("Error al seleccionar las peizas, verifica los datos ingresados, " + e.getMessage());
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
     * @throws java.lang.Exception
     */
    public ArrayList<FurniturePiece> selectPiecesLimitX(String name, int limit) throws Exception {
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
            new InsertUtilities().throwCustomError("Error al sleeccionar numero de piezas, verifica los datos ingresados, " + e.getMessage());
        }
        return pieces;
    }

    /**
     * select pieces with the available stock, this method is used to show no
     * stock pieces
     *
     * @param furnitureName
     * @param byName
     * @return
     * @throws java.lang.Exception
     */
    public ArrayList<PieceByStock> selectPieceAndStock(String furnitureName, boolean byName) throws Exception {
        ArrayList<PieceByStock> list = new ArrayList<>();
        String TMP_QUERY = byName ? "SELECT `name`, COUNT(`name`) AS `stock` FROM `Furniture_Piece` WHERE `name` = ? GROUP BY `name`" : SQL_SELECT_PIECES_STOCK;
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(TMP_QUERY)) {
            // data
            if (byName) {
                ps.setString(1, furnitureName);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PieceByStock(rs.getInt("stock"), rs.getString("name")));
            }
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al seleccionar piezas y stock, verifica los datos ingresados, " + e.getMessage());
        }
        return list;
    }

    /**
     * delete a furniture piece from DB
     *
     * @param piece Object FurniturePiece
     * @return SQL query status
     * @throws java.lang.Exception
     */
    public int delete(FurniturePiece piece) throws Exception {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PIECE)) {
            ps.setInt(1, piece.getId());
            int reg = ps.executeUpdate();
            return reg;
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al borrar pieza, verifica los datos ingresados, " + e.getMessage());
            return 0;
        }
    }

    /**
     * this method update a piece on DB
     *
     * @param piece
     * @return
     * @throws java.lang.Exception
     */
    public int update(FurniturePiece piece) throws Exception {
        try ( Connection conn = ConnectionDB.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_PIECE)) {
            ps.setString(1, piece.getName());
            ps.setDouble(2, piece.getCost());
            ps.setInt(3, piece.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            new InsertUtilities().throwCustomError("Error al actualizar pieza, verifica los datos ingresados, " + e.getMessage());
            return 0;
        }
    }
}
