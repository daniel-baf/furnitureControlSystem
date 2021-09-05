package Web;

import TransactionObjects.InsertObjectStatus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "FileUpload", urlPatterns = {"/FileUpload"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100 // 100MB
)

public class FileUpload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // receive file uploaded
        Part filePart = request.getPart("file");
        // read
        BufferedReader bufReader;
        ArrayList<InsertObjectStatus> errors = new ArrayList<>();
        ArrayList<InsertObjectStatus> success = new ArrayList<>();

        // validate is text
        for (Part part : request.getParts()) {
            // create a buffered to read text line by line
            bufReader = new BufferedReader(new InputStreamReader(filePart.getInputStream(), "utf-8"));
            String text;
            int lineCounter = 0;
            InsertObjectStatus object;
            // read line by line
            while ((text = bufReader.readLine()) != null) {
                object = new InsertObjectStatus(lineCounter);
                object.setLineRead(text);
                try {
                    if (tryInsert(text, object, response)) {
                        object.setStatus("no error");
                        success.add(object);
                    } else {
                        errors.add(object);
                    }
                } catch (Exception ex) {
                    errors.add(object);
                }
                lineCounter++;
            }
        }

        request.setAttribute("insertOK", success);
        request.setAttribute("insertNoOK", errors);
        request.getRequestDispatcher("/Dashboards/FileLoadResult/File-Load-Results.jsp").forward(request, response);
    }

    /**
     * this method splits lines read to try insert INSTRUCTION(...)
     *
     * @param readLine the actual line
     * @param object a object to save status, if inserted or not
     * @return insert status, true if inserted
     */
    private boolean tryInsert(String readLine, InsertObjectStatus object, HttpServletResponse response) throws IOException, Exception {
        // save data to send as atribute to JSP
        object.setSentece(getTokensManual(readLine)[0]);
        object.setValues(getTokensManual(readLine)[1]);

        boolean insertStatus = false;
        FileUploadInstructions fups = new FileUploadInstructions();
        if (object.getValues() == null || object.getSentece() == null) {
            object.setStatus("Error logico");
            return false;
        }
        // redirect INSERT
        switch (object.getSentece()) {
            case "USUARIO":
                insertStatus = fups.insertUser(object); // insert to SQL and return state
                break;
            case "PIEZA":
                insertStatus = fups.insertPiece(object);
                break;
            case "MUEBLE":
                insertStatus = fups.insertFurniture(object);
                break;
            case "ENSAMBLE_PIEZAS":
                insertStatus = fups.insertPieceAssembly(object);
                break;
            case "ENSAMBLAR_MUEBLE":
                insertStatus = fups.insertFurnitureAsembly(object);
                break;
            case "CLIENTE":
                insertStatus = fups.insertClient(object);
                break;
            default:
                object.setStatus(object.getSentece() + " no reconocido ");
        }
        return insertStatus;
    }

    /**
     * splits a INSTRUCTION(values) into INSTRUCTION and values
     *
     * @param entry
     * @return
     */
    private String[] getTokensManual(String entry) {
        String message = "";
        String[] tokens = new String[2];
        boolean openParentheses = false;

        for (int i = 0; i < entry.length(); i++) {
            if (entry.charAt(i) == '(' && !openParentheses) {
                tokens[0] = message;
                message = "";
                continue;
            } else if (entry.charAt(i) == ')' && i == entry.length() - 1) {
                tokens[1] = message;
                continue;
            } else if (i >= entry.length() - 1 && openParentheses == false) {
                tokens[0] = "No se ha colocado apaertura de parentesis";
            }
            message += entry.charAt(i);
        }
        return tokens;
    }
}
