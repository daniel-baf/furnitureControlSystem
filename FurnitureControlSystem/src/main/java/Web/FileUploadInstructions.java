package Web;

import Database.*;
import Domain.*;
import GeneralUse.InsertUtilities;
import TransactionObjects.InsertObjectStatus;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.http.HttpServletResponse;

public class FileUploadInstructions {

    private final InsertUtilities insUtilites;

    public FileUploadInstructions() {
        this.insUtilites = new InsertUtilities();
    }

    /**
     * this is a shared steps with other methods
     *
     * @param lengthExpected the number of values expected after split vals
     * @param object the object to save errors on validation to don't let insert
     * @param tableDir the INSTURCTION, can be MUEBLE, PIEZA...
     * @param expectedComillas an array with the number of vals's cells where
     * there must be a value with comillas
     * @return
     */
    public boolean insertCommonTask(int lengthExpected, InsertObjectStatus object, String tableDir,
            int[] expectedComillas) {

        String[] vals = object.getValues().split(",");

        // validate the length
        if (vals.length != lengthExpected) {
            insUtilites.setStatusAndResult(2, object);
            return false;
        }
        // now we need to ensure thers's two comillas
        for (int i = 0; i < expectedComillas.length; i++) {
            vals[expectedComillas[i]] = vals[expectedComillas[i]].trim();
            if (insUtilites.haveTwoComillas(vals[expectedComillas[i]])) {
                vals[expectedComillas[i]] = insUtilites.removeDoubleComillas(vals[expectedComillas[i]]);
            } else {
                object.setStatus("1 o mas atributos tienen menos/mas comillas, se esperaban 2");
                return false;
            }
        }
        object.setValuesSplited(vals);
        return true;
    }

    /**
     * Try to insert a user on DB,from file read
     *
     * @param object
     * @return
     */
    public boolean insertUser(InsertObjectStatus object) {
        // USER("name","password",int)
        boolean inserted = false;
        int typeErrorInsert = 2; // 2 = format error
        if (insertCommonTask(3, object, "USUARIO", new int[]{0, 1})) {
            String[] vals = object.getValuesSplited();
            try {
                Short sht = Short.valueOf(vals[2].trim());
                inserted = new UserDAO().insert(new User(vals[0], vals[1], sht)) != 0;
                typeErrorInsert = inserted ? 0 : 1;
            } catch (NumberFormatException e) {
                inserted = false;
            }
        }
        insUtilites.setStatusAndResult(typeErrorInsert, object);
        return inserted;
    }

    /**
     * try to insert a Furniture Piece to DB from text file read
     *
     * @param object to save error
     * @return insert status, Boolean
     */
    public boolean insertPiece(InsertObjectStatus object) {
        boolean inserted = false;
        int typeErrorInsert = 2; // 2 = format error
        //PIEZA("nombre", precioDecimal)
        if (insertCommonTask(2, object, "PIEZA", new int[]{0})) {
            String[] vals = object.getValuesSplited();
            Double tmp = insUtilites.getDoubleFromString(vals[1]);
            if (tmp != null) {
                inserted = new FurniturePieceDAO().insert(new FurniturePiece(vals[0], tmp)) != 0;
                typeErrorInsert = inserted ? 0 : 1;
            }
        }
        insUtilites.setStatusAndResult(typeErrorInsert, object);
        return inserted;
    }

    /**
     * try to insert Furniture to DB from text file read
     *
     * @param object to save error
     * @return Boolean, insert status
     */
    public boolean insertFurniture(InsertObjectStatus object) {
        //MUEBLE("nombre", precioDecimal)
        boolean inserted = false;
        int typeErrorInsert = 2; // 2 = format error
        if (insertCommonTask(2, object, "MUEBLE", new int[]{0})) {
            String[] vals = object.getValuesSplited();
            Double tmp = insUtilites.getDoubleFromString(vals[1].trim());
            if (tmp != null) {
                inserted = new FurnitureDAO().insert(new Furniture(vals[0], tmp)) != 0;
                typeErrorInsert = inserted ? 0 : 1;
            }
        }
        insUtilites.setStatusAndResult(typeErrorInsert, object);
        return inserted;
    }

    /**
     * try to insert Client to DB from text file read
     *
     * @param object object to save error
     * @return Boolean, insert status
     */
    public boolean insertClient(InsertObjectStatus object) throws IOException {
        //CLIENTE("nombre","NIT","direccion","municipio","departamento") --> puede venir sin departamento y municipio
        String[] vals = object.getValues().split(",");
        boolean inserted = false;
        int[] expectedComillas;
        int typeErrorInsert = 2; // 2 = format error
        Client client;

        if (!(vals.length == 5 || vals.length == 3)) {
            insUtilites.setStatusAndResult(typeErrorInsert, object);
            return false;
        }
        expectedComillas = vals.length == 3 ? new int[]{0, 1, 2} : new int[]{0, 1, 2, 3, 4};

        int length = object.getValues().split(",").length;
        if (insertCommonTask(length, object, "CLIENTE", expectedComillas)) {
            vals = object.getValuesSplited();
            client = vals.length == 3 ? new Client(vals[1], vals[0], vals[2], null, null) : new Client(vals[1], vals[0], vals[2], vals[3], vals[4]);
            inserted = new ClientDAO().insert(client, null) != 0;
            typeErrorInsert = inserted ? 0 : 1;
        }
        insUtilites.setStatusAndResult(typeErrorInsert, object);
        return inserted;
    }

    /**
     * Try to insert Piece Assembly to DB from text file read
     *
     * @param object
     * @return
     */
    public boolean insertPieceAssembly(InsertObjectStatus object) {
        //ENSAMBLE_PIEZAS("nombre muble", "nombre pieza", 1)
        boolean inserted = false;
        int typeErrorInsert = 2; // 2 = format error
        if (insertCommonTask(3, object, "ENSAMBLE_PIEZAS", new int[]{0, 1})) {
            String[] vals = object.getValuesSplited();
            Integer tmp = insUtilites.getIntegerFromString(vals[2].trim());

            if (tmp != null) {
                inserted = new PieceAssemblyDAO().insert(new PieceAssembly(vals[0], vals[1], tmp)) != 0;
                typeErrorInsert = inserted ? 0 : 1;
            }
        }
        insUtilites.setStatusAndResult(typeErrorInsert, object);
        return inserted;
    }

    /**
     * try to insert Furniture Assembly from text file read
     *
     * @param object
     * @return
     */
    public boolean insertFurnitureAsembly(InsertObjectStatus object) {
        //ENSAMBLAR_MUEBLE("furn name",workerIdWhoEnsabledIt, "date")
        boolean inserted = false;
        int typeErrorInsert = 2; // 2 = format error
        if (insertCommonTask(3, object, "ENSAMBLAR_MUEBLE", new int[]{0, 2})) {
            String vals[] = object.getValuesSplited();
            LocalDate tmp = insUtilites.stringToLocalDate(vals[2]);
            inserted = new FurnitureAssemblyDAO().insertFurnAssmebly(new FurnitureAssembly(vals[1], tmp, vals[0])) != 0;
            typeErrorInsert = inserted ? 0 : 1;
        }
        insUtilites.setStatusAndResult(typeErrorInsert, object);
        return inserted;
    }
}
