package Database;

import Domain.FurniturePiece;
import Domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FurniturePieceDAO {

    // INSERT
    private static final String SQL_INSERT_PIECE = "INSERT INTO `FurniturePiece` (`name`, `cost`) VALUES (?, ?);";
    private static final String SQL_SELECT_PIECE_BY_NAME = "SELECT * FROM `FurniturePiece` WHERE `name` = ?";

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
                pieces.add(new FurniturePiece(rs.getInt("id"), rs.getString("name"), Integer.valueOf(rs.getInt("cost"))));
            }
        } catch (Exception e) {
        }
        return pieces;
    }

}
