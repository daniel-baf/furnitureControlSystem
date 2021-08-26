package Web;

import Database.ClientDAO;
import Database.FurnitureAssemblyDAO;
import Database.FurnitureDAO;
import Database.FurniturePieceDAO;
import Database.PieceAssemblyDAO;
import Database.UserDAO;
import Domain.Client;
import Domain.Furniture;
import Domain.FurnitureAssembly;
import Domain.FurniturePiece;
import Domain.PieceAssembly;
import Domain.User;
import TransactionObjects.FileInsertStatusObject;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileUploadInstructions {

    public FileUploadInstructions() {
    }

    // UTILITIES
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

    public boolean insertCommonTask(String[] vals, int lengthExpected, FileInsertStatusObject object, String tableDir,
            int[] expectedComillas) {

        try {
            // validate the length
            if (vals.length != lengthExpected) {
                object.setStatus("Se detectaron " + vals.length + " atributos. Se esperaban " + lengthExpected + " para la instrucción" + tableDir);
                return false;
            }
            // now we need to ensure thers's two comillas
            for (int i = 0; i < expectedComillas.length; i++) {
                vals[expectedComillas[i]] = vals[expectedComillas[i]].trim();
                if (haveTwoComillas(vals[expectedComillas[i]])) {
                    vals[expectedComillas[i]] = removeDoubleComillas(vals[expectedComillas[i]]);
                } else {
                    object.setStatus("1 o mas atributos tienen menos/mas comillas, se esperaban 2");
                    return false;
                }
            }
            object.setValuesSplited(vals);
            return true;
        } catch (IOException e) {
            setStatusAndResult(false, object);
            addErrorMsgToObject(object, e);
            return false;
        }
    }

    public boolean addErrorMsgToObject(FileInsertStatusObject object, Exception e) {
        object.setStatus("Hubo un error al intentar insertar, mas inf en \"Tipo de error\"");
        object.setErrorMsg(e.getMessage());
        return false;
    }

    public LocalDate stringToLocalDate(String dateDDMMYY) {
        try {
            return LocalDate.parse(dateDDMMYY, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return null;
        }
    }

    public void setStatusAndResult(boolean inserted, FileInsertStatusObject object) {
        String status = inserted == false ? "Insert rechazado por Base de datos, posible duplicado o error de sintaxis" : "0";
        object.setStatus(status);
    }

    public Double getDoubleFromString(String doubleN) {
        try {
            return Double.valueOf(doubleN);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Integer getIntegerFromString(String integerN) {
        try {
            return Integer.valueOf(integerN);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // INSERT
    public boolean insertUser(FileInsertStatusObject object) {
        // USER("name","password",int)
        String[] vals = object.getValues().split(",");
        boolean inserted = false;
        if (insertCommonTask(vals, 3, object, "USUARIO", new int[]{0, 1})) {
            vals = object.getValuesSplited();
            try {
                Short sht = Short.valueOf(vals[2].trim());
                inserted = new UserDAO().insert(new User(vals[0], vals[1], sht)) != 0;
            } catch (NumberFormatException e) {
                inserted = false;
                addErrorMsgToObject(object, e);
            }
        }
        setStatusAndResult(inserted, object);
        return inserted;
    }

    public boolean insertPiece(FileInsertStatusObject object) {
        String[] vals = object.getValues().split(",");
        boolean inserted = false;
        //PIEZA("nombre", precioDecimal)
        if (insertCommonTask(vals, 2, object, "PIEZA", new int[]{0})) {
            vals = object.getValuesSplited();
            Double tmp = getDoubleFromString(vals[1]);
            if (tmp != null) {
                inserted = new FurniturePieceDAO().insert(new FurniturePiece(vals[0], tmp)) != 0;
            }
        }
        setStatusAndResult(inserted, object);
        return inserted;
    }

    public boolean insertFurniture(FileInsertStatusObject object) {
        //MUEBLE("nombre", precioDecimal)
        String[] vals = object.getValues().split(",");
        boolean inserted = false;
        if (insertCommonTask(vals, 2, object, "MUEBLE", new int[]{0})) {
            vals = object.getValuesSplited();
            Double tmp = getDoubleFromString(vals[1].trim());
            if (tmp != null) {
                inserted = new FurnitureDAO().insert(new Furniture(vals[0], tmp)) != 0;
            }
        }
        setStatusAndResult(inserted, object);
        return inserted;
    }

    public boolean insertClient(FileInsertStatusObject object) {
        //CLIENTE("nombre","NIT","direccion","municipio","departamento") --> puede venir sin departamento y municipio
        String[] vals = object.getValues().split(",");
        boolean inserted = false;
        int[] expectedComillas;
        int queryType;
        Client client;

        if (!(vals.length == 5 || vals.length == 3)) {
            return false;
        }

        expectedComillas = vals.length == 3 ? new int[]{0, 1, 2} : new int[]{0, 1, 2, 3, 4};
        queryType = vals.length == 3 ? 0 : 1;

        if (insertCommonTask(vals, vals.length, object, "CLIENTE", expectedComillas)) {
            client = vals.length == 3 ? new Client(vals[1], vals[0], vals[2]) : new Client(vals[1], vals[0], vals[2], vals[3], vals[4]);
            inserted = new ClientDAO().insert(client, queryType) != 0;
        }
        setStatusAndResult(inserted, object);
        return inserted;
    }

    public boolean insertPieceAssembly(FileInsertStatusObject object) {
        //ENSAMBLE_PIEZAS("nombre muble", "nombre pieza", 1)
        String[] vals = object.getValues().split(",");
        boolean inserted = false;
        if (insertCommonTask(vals, 3, object, "ENSAMBLE_PIEZAS", new int[]{0, 1})) {
            vals = object.getValuesSplited();
            Integer tmp = getIntegerFromString(vals[2].trim());

            if (tmp != null) {
                inserted = new PieceAssemblyDAO().insert(new PieceAssembly(vals[0], vals[1], tmp)) != 0;
            }
        }
        setStatusAndResult(inserted, object);
        return inserted;
    }

    public boolean insertFurnitureAsembly(FileInsertStatusObject object) {
        //ENSAMBLAR_MUEBLE("furn name",workerIdWhoEnsabledIt, "date")
        String[] vals = object.getValues().split(",");
        boolean inserted = false;
        if (insertCommonTask(vals, 3, object, "ENSAMBLAR_MUEBLE", new int[]{0, 2})) {
            LocalDate tmp = stringToLocalDate(vals[2]);
            inserted = new FurnitureAssemblyDAO().insertFurnAssmebly(new FurnitureAssembly(vals[1], tmp, vals[0])) != 0;
        }
        setStatusAndResult(inserted, object);
        return inserted;
    }
}
