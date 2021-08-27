package Database;

import Domain.FurnitureAssembly;
import Domain.FurniturePiece;
import Domain.PieceAssembly;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;

public class FurnitureAssemblyDAO {

    private final String SQL_INSERT_FURN_ASSM = "INSERT INTO `Furniture_Assembly` (`user_Name`, `date`,`sold`,`furniture_Name`,`assembly_Price`) VALUES (?,?,?,?,?)";

    public int insertFurnAssmebly(FurnitureAssembly furnAssm) {
        int result = 0;
        try ( Connection conn = ConnectionDB.getConnection()) {

            conn.setAutoCommit(false);
            try ( PreparedStatement ps = conn.prepareStatement(SQL_INSERT_FURN_ASSM)) {

                //We need the assemblies to know wich pieces use
                ArrayList<PieceAssembly> assemblies = new PieceAssemblyDAO().selectAssemblies(furnAssm.getFurnitureName());
                ArrayList<FurniturePiece> pieces = new ArrayList<>();
                double assembPrice = 0;

                if (assemblies.isEmpty() || assemblies.size() == 0) {
                    return 0;
                }

                for (PieceAssembly assembly : assemblies) {
                    ArrayList<FurniturePiece> tmp = new FurniturePieceDAO().selectPiecesLimitX(assembly.getPieceName(), assembly.getCuantity());
                    if (tmp.isEmpty() || tmp == null) {
                        return 0;
                    } else if (tmp.size() < assembly.getCuantity()) {
                        return 0;
                    } else {
                        for (FurniturePiece furniturePiece : tmp) {
                            pieces.add(furniturePiece);
                            assembPrice += furniturePiece.getCost();
                        }
                    }
                }
                // delete pieces
                for (FurniturePiece piece : pieces) {
                    new FurniturePieceDAO().delete(piece);
                }
                // create furniture
                ps.setString(1, furnAssm.getUsername());
                ps.setDate(2, parseLocalDateSQLDate(furnAssm.getDate()));
                ps.setInt(3, 0);
                ps.setString(4, furnAssm.getFurnitureName());
                ps.setDouble(5, assembPrice);

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

    public Date parseLocalDateSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }
}
