package Web.DashboardAcions;

import Database.ClientDAO;
import Database.FurnitureAssemblyDAO;
import Database.FurnitureDAO;
import Database.FurniturePieceDAO;
import Database.UserDAO;
import Domain.Client;
import Domain.Furniture;
import Domain.FurnitureAssembly;
import Domain.FurniturePiece;
import Domain.User;
import GeneralUse.AES256;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "InsertToDB", urlPatterns = {"/InsertToDB"})
public class InsertToDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // variables
        String action = request.getParameter("action-perf");
        //different actions

        try {
            InsertUtilities iu = new InsertUtilities();
            switch (action) {
                case "insert-furniture" -> {
                    insertFurnitureDB(request, response, iu);
                    break;
                }
                case "insert-user" -> {
                    insertUserDB(request, response, iu);
                }
                case "insert-furn-assbm" -> {
                    HttpSession session = request.getSession();
                    insertFurnAssemblyDB(request, response, (String) session.getAttribute("usr"), iu);
                }
                case "insert-piece" -> {
                    insertPieceDB(request, response, iu);
                }
                case "insert-client" -> {
                    insertClientDB(request, response, iu);
                }
                default -> {
                    response.getWriter().print("aaaa");
                }
            }
        } catch (Exception e) {
            new InsertUtilities().sendErrorMessage(response, request, e, "Error en el formato o valor invalido, revisa la solicitud y vuelve a intentarlo");
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

    /**
     * insert a furniture to DB, this method can be called anywhere
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void insertFurnitureDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws Exception {
        Double price = iu.getDoubleFromString(request.getParameter("furnPrice"));
        Furniture furn = new Furniture(request.getParameter("furnName"), price);
        String repBody = " insertar el mueble con nombre " + furn.getName();
        repBody = new FurnitureDAO().insert(furn) != 0 ? "Se ha logrado " + repBody : "No se ha logrado " + repBody;
        iu.configAttibutes(request, "Registrar nuevo mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    private void insertUserDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws ServletException, ServletException, IOException, Exception {
        // encrypt password
        AES256 aes356 = new AES256();
        short tmp = (short) ((int) iu.getIntegerFromString(request.getParameter("areaCode")));
        User user = new User(request.getParameter("usrName"), aes356.encrypt(request.getParameter("userPswrd")), tmp);
        String repBody = " insertar el mueble con nombre " + user.getName();
        repBody = new UserDAO().insert(user) != 0 ? "Se ha insertado " + repBody : "No se ha borrado " + repBody;
        iu.configAttibutes(request, "Registrar nuevo mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    private void insertFurnAssemblyDB(HttpServletRequest request, HttpServletResponse response, String username, InsertUtilities iu) throws Exception {
        FurnitureAssembly fa = new FurnitureAssembly(username, LocalDate.now(), request.getParameter("furniture-assemb"));
        String repBody = " registrar el nuevo ensamble de mueble";
        repBody = new FurnitureAssemblyDAO().insertFurnAssmebly(fa) != 0 ? "Se ha logrado insertar " + repBody : "No se ha logrado crear " + repBody;
        iu.configAttibutes(request, "Registrar nuevo ensamble de mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    private void insertPieceDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws ServletException, IOException, Exception {
        FurniturePiece piece = new FurniturePiece(request.getParameter("piece-name"), iu.getDoubleFromString(request.getParameter("piece-price")));
        String repBody = " registrar la pieza a la base de datos";
        repBody = new FurniturePieceDAO().insert(piece) != 0 ? "Se ha logrado " + repBody : "No se ha logrado " + repBody;
        iu.configAttibutes(request, "Registrar pieza", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    private void insertClientDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws Exception {
        Client client = new Client(
                request.getParameter("nit"),
                request.getParameter("name"),
                request.getParameter("adress"),
                request.getParameter("municipality"),
                request.getParameter("department"));
        String repBody = " registrar al nuevo cliente en la base de datos";

        if (client.getMunicipality().length() > 0 && client.getDepartment().isEmpty()
                || client.getMunicipality().isEmpty() && client.getDepartment().length() > 0) {
            repBody = "Uno de los atributos no cumple con los requisitos";
        } else {
            repBody = new ClientDAO().insert(client, response) != 0 ? "se ha logrado" + repBody : "no se ha logrado " + repBody;
        }

        iu.configAttibutes(request, "Registrar usuario", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }
}
