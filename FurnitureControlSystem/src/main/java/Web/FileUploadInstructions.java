package Web;

import Database.ClientDAO;
import Database.FurnitureDAO;
import Database.FurniturePieceDAO;
import Database.PieceAssemblyDAO;
import Database.UserDAO;
import Domain.Client;
import Domain.Furniture;
import Domain.FurniturePiece;
import Domain.PieceAssembly;
import Domain.User;
import Error.TransactionCodeFIle;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;

public class FileUploadInstructions {

    public FileUploadInstructions() {
    }

    public String removeDoubleComillas(String word) throws IOException {
        String newWord = "";
        boolean open = false;

        for (char c : word.toCharArray()) {
            if (c != '"') {
                newWord += c;
            } else if (c == '"' && open == false) {
                open = true;
            }
        }

        return newWord.trim();
    }

    public boolean haveTwoComillas(String st1) {
        return (st1.trim().replace("\"", "").length() == st1.trim().length() - 2);
    }

    public boolean insertCommonTask(String[] vals, int lengthExpected, TransactionCodeFIle object, String tableDir,
            int[] expectedComillas) throws IOException {

        // validate the length
        if (vals.length != lengthExpected) {
            object.setStatus("Se detectaron " + vals.length + " atributos y se esperaban " + lengthExpected + " para " + tableDir);
            return false;
        }
        // now we need to ensure thers's two comillas
        for (int i = 0; i < expectedComillas.length; i++) {
            vals[expectedComillas[i]] = vals[expectedComillas[i]].trim();
            if (haveTwoComillas(vals[expectedComillas[i]])) {
                vals[expectedComillas[i]] = removeDoubleComillas(vals[expectedComillas[i]]);
            } else {
                object.setStatus("Error en sitanxis con comillas");
                return false;
            }
        }
        object.setValuesSplited(vals);
        return true;
    }

    public boolean insertUser(TransactionCodeFIle object) throws IOException {
        // USER("name","password",int)
        String[] vals = object.getValues().split(",");
        if (insertCommonTask(vals, 3, object, "USUARIO", new int[]{0, 1})) {
            object.setStatus("0");
            vals = object.getValuesSplited();

            if (new UserDAO().insert(new User(vals[0], vals[1], Short.valueOf(vals[2].trim()))) != 0) {
                object.setStatus("0");
                return true;
            } else {
                object.setStatus("Insert rechazado por Base de datos, posible duplicado");
            }
        }
        return false;
    }

    public boolean insertPiece(TransactionCodeFIle object) throws IOException {
        //PIEZA("nombre", precioDecimal)
        String[] vals = object.getValues().split(",");
        if (insertCommonTask(vals, 2, object, "PIEZA", new int[]{0})) {
            object.setStatus("0");
            vals = object.getValuesSplited();

            if (new FurniturePieceDAO().insert(new FurniturePiece(vals[0], Double.valueOf(vals[1].trim()))) != 0) {
                object.setStatus("0");
                return true;
            } else {
                object.setStatus("Insert rechazado por Base de datos, posible duplicado");
            }
        }
        return false;
    }

    public boolean insertFurniture(TransactionCodeFIle object) throws IOException {
        //MUEBLE("nombre", precioDecimal)
        String[] vals = object.getValues().split(",");
        if (insertCommonTask(vals, 2, object, "MUEBLE", new int[]{0})) {
            object.setStatus("0");
            vals = object.getValuesSplited();

            if (new FurnitureDAO().insert(new Furniture(vals[0], Double.valueOf(vals[1].trim()))) != 0) {
                object.setStatus("0");
                return true;
            } else {
                object.setStatus("Insert rechazado por Base de datos, posible duplicado");
            }
        }
        return false;
    }

    public boolean insertPieceAssembly(TransactionCodeFIle object) throws IOException, SQLException, ClassNotFoundException {
        //ENSAMBLE_PIEZAS("nombre muble", "nombre pieza", 1)
        String[] vals = object.getValues().split(",");

        if (insertCommonTask(vals, 3, object, "ENSAMBLE_PIEZAS", new int[]{0, 1})) {
            object.setStatus("0");
            vals = object.getValuesSplited();

            if (new PieceAssemblyDAO().insert(new PieceAssembly(vals[0], vals[1], Integer.valueOf(vals[2].trim()))) != 0) {
                object.setStatus("0");
                return true;
            } else {
                object.setStatus("Insert rechazado por Base de datos, posible duplicado");
            }
        }
        return false;
    }

    public boolean insertClient(TransactionCodeFIle object) throws IOException, SQLException, ClassNotFoundException {
        //CLIENTE("nombre","NIT","direccion","municipio","departamento") --> puede venir sin departamento y municipio
        String[] vals = object.getValues().split(",");
        boolean success = false;

        switch (vals.length) {
            case 3:
                if (insertCommonTask(vals, 3, object, "CLIENTE", new int[]{0, 1, 2})) {
                    if (new ClientDAO().insert(new Client(vals[1], vals[0], vals[2]), 0) != 0) {
                        object.setStatus("0");
                        success = true;
                    }
                }
                break;
            case 5:
                if (insertCommonTask(vals, 5, object, "CLIENTE", new int[]{0, 1, 2, 3, 4})) {
                    if (new ClientDAO().insert(new Client(vals[1], vals[0], vals[2], vals[3], vals[4]), 1) != 0) {
                        object.setStatus("0");
                        success = true;
                    }
                }
                break;
            default:
                object.setStatus("EL numero de argumentos no es el esperado");
        }
        return success;
    }
}
