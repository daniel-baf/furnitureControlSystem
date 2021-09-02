package Web.DashboardAcions;

import Database.FurnitureAssemblyDAO;
import Database.FurnitureDAO;
import Database.FurniturePieceDAO;
import Database.UserDAO;
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
                default -> {
                    response.getWriter().print("aaaa");
                }
            }
        } catch (IOException | ServletException e) {
            response.getWriter().print(e.getMessage());
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
    private void insertFurnitureDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws ServletException, IOException {
        Double price = iu.getDoubleFromString(request.getParameter("furnPrice"));
        Furniture furn = new Furniture(request.getParameter("furnName"), price);
        String repBody = " insertar el mueble con nombre " + furn.getName();
        repBody = new FurnitureDAO().insert(furn) != 0 ? "Se ha logrado " + repBody : "No se ha logrado " + repBody;
        iu.configAttibutes(request, "Registrar nuevo mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    private void insertUserDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws ServletException, ServletException, IOException {
        // encrypt password
        AES256 aes356 = new AES256();
        short tmp = (short) ((int) iu.getIntegerFromString(request.getParameter("areaCode")));
        User user = new User(request.getParameter("usrName"), aes356.encrypt(request.getParameter("userPswrd")), tmp);
        String repBody = " insertar el mueble con nombre " + user.getName();
        repBody = new UserDAO().insert(user) != 0 ? "Se ha insertado " + repBody : "No se ha borrado " + repBody;
        iu.configAttibutes(request, "Registrar nuevo mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    private void insertFurnAssemblyDB(HttpServletRequest request, HttpServletResponse response, String username, InsertUtilities iu) throws ServletException, IOException {
        FurnitureAssembly fa = new FurnitureAssembly(username, LocalDate.now(), request.getParameter("furniture-assemb"));
        String repBody = " registrar el nuevo ensamble de mueble";
        repBody = new FurnitureAssemblyDAO().insertFurnAssmebly(fa) != 0 ? "Se ha logrado insertar " + repBody : "No se ha logrado crear " + repBody;
        iu.configAttibutes(request, "Registrar nuevo ensamble de mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    private void insertPieceDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws ServletException, IOException {
        FurniturePiece piece = new FurniturePiece(request.getParameter("piece-name"), iu.getDoubleFromString(request.getParameter("piece-price")));
        String repBody = " registrar la pieza a la base de datos";
        repBody = new FurniturePieceDAO().insert(piece) != 0 ? "Se ha logrado " + repBody : "No se ha logrado " + repBody;
        iu.configAttibutes(request, "Registrar pieza", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }
}
