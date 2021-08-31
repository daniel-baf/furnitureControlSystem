package Web.DashboardAcions;

import Database.FurnitureDAO;
import Database.UserDAO;
import Domain.Furniture;
import Domain.User;
import GeneralUse.AES256;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InsertToDB", urlPatterns = {"/InsertToDB"})
public class InsertToDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // variables
        String action = request.getParameter("action-perf");
        //different actions

        try {
            switch (action) {
                case "insert-furniture" -> {
                    insertFurnitureDB(request, response);
                    break;
                }
                case "insert-user" -> {
                    insertUserDB(request, response);
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
    private void insertFurnitureDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InsertUtilities iu = new InsertUtilities();
        Double price = iu.getDoubleFromString(request.getParameter("furnPrice"));
        Furniture furn = new Furniture(request.getParameter("furnName"), price);
        String repBody = " insertar el mueble con nombre " + furn.getName();
        repBody = new FurnitureDAO().insert(furn) != 0 ? "Se ha insertado " + repBody : "No se ha borrado " + repBody;
        configAttibutes(request, "Registrar nuevo mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    /**
     * this is a common task from furniture, add attributes to show into
     * message.jsp
     *
     * @param request
     * @param repTitle
     * @param repBody
     */
    private void configAttibutes(HttpServletRequest request, String repTitle, String repBody) {
        request.setAttribute("rep-title", repTitle);
        request.setAttribute("rep-body", repBody);

    }

    private void insertUserDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException {
        // encrypt password
        AES256 aes356 = new AES256();
        InsertUtilities iu = new InsertUtilities();
        // if (iu.haveNoNulls(request.getParameter("usrName"), request.getParameter("userPswrd"), request.getParameter("areaCode"))) {
        short tmp = (short) ((int) iu.getIntegerFromString(request.getParameter("areaCode")));
        User user = new User(request.getParameter("usrName"), aes356.encrypt(request.getParameter("userPswrd")), tmp);
        String repBody = " insertar el mueble con nombre " + user.getName();
        repBody = new UserDAO().insert(user) != 0 ? "Se ha insertado " + repBody : "No se ha borrado " + repBody;
        configAttibutes(request, "Registrar nuevo mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

}
