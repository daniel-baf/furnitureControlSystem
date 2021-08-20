package Web;

import Database.UserDAO;
import Domain.User;
import Error.TransactionCodeFIle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

// 12345asf123\
public class FileUpload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // receive file uploaded
        Part filePart = request.getPart("file");
        // read
        BufferedReader bufReader;
        ArrayList<TransactionCodeFIle> errors = new ArrayList<>();
        ArrayList<TransactionCodeFIle> success = new ArrayList<>();

        // validate is text
        if (filePart.getContentType().equals("text/plain")) {
            for (Part part : request.getParts()) {
                // create a buffered to read text line by line
                bufReader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));
                String text; // text read by bufReader
                int lineCounter = 0; // to know line where error ocurred
                TransactionCodeFIle object = new TransactionCodeFIle(lineCounter);
                // read line by line
                while ((text = bufReader.readLine()) != null) {
                    response.getWriter().print("<br>" + text + "<br>");
                    if (tryInsert(text, object, response)) {
                        success.add(object);
                    } else {
                        errors.add(object);
                    }
                    lineCounter++;
                }
            }

            response.getWriter().print("Correctos: " + success.size() + "<br>");
            response.getWriter().print("Inorrectos: " + errors.size());
        } else {
            response.getWriter().print("no es archivo te texto");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private boolean tryInsert(String readLine, TransactionCodeFIle object, HttpServletResponse response) {
        // save data to send as atribute to JSP
        object.setSentece(getTokensManual(readLine)[0]);
        object.setValues(getTokensManual(readLine)[1]);

        try {
            boolean insertStatus = false;

            if (object.getValues() == null) {
                return false;
            }

            // redirect INSERT
            switch (object.getSentece()) {
                case "USUARIO":
                    insertStatus = insertUser(object); // insert to SQL and return state
                    break;
                case "PIEZA":
                    break;
                case "MUEBLE":
                    break;
                case "ENSAMBLE_PIEZA":
                    break;
                case "ENSAMBLAR_MUEBLE":
                    break;
                case "CLIENTE":
                    break;
                default:
                    object.setStatus("Instrucción desconocida: " + object.getSentece());
            }
            return insertStatus;
        } catch (Exception e) {
            object.setStatus("Instrucción desconocida: " + object.getSentece());
            return false;
        }
    }

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
            }
            message += entry.charAt(i);
        }

        return tokens;
    }

    private String removeDoubleComillas(String word) throws IOException {
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

    private boolean haveTwoComillas(String st1, String st2) {
        return (st1.trim().replace("\"", "").length() == st1.trim().length() - 2)
                && (st2.trim().replace("\"", "").length() == st2.trim().length() - 2);
    }

    private boolean insertUser(TransactionCodeFIle object) throws IOException {
        // USER("name","password",int)
        String[] vals = object.getValues().split(",");
        // validate 1st val have "
        if (vals.length != 3) {
            object.setStatus("Se detectaron " + vals.length + " y se esperaban 3 para USUARIO");
            return false;
        }
        // count cuantity of "
        if (!haveTwoComillas(vals[0], vals[1])) {
            object.setStatus("Error en sitanxis con comillas");
            return false;
        }
        // remove " and trim
        vals[0] = removeDoubleComillas(vals[0]);
        vals[1] = removeDoubleComillas(vals[1]);
        vals[2] = vals[2].trim();

        UserDAO uDao = new UserDAO();

        if (uDao.insert(new User(vals[0], vals[1], Short.valueOf(vals[2]))) != 0) {
            object.setStatus("0");
            return true;
        } else {
            object.setStatus("Insert rechazado por Base de datos, posible duplicado");
            return false;
        }
    }

    private boolean insertPiece(TransactionCodeFIle object) {
        return false;
    }
}
