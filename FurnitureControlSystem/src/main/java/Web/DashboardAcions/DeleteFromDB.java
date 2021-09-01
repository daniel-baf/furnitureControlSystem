package Web.DashboardAcions;

import Database.FurnitureAssemblyDAO;
import Database.FurniturePieceDAO;
import Database.UserDAO;
import Domain.FurnitureAssembly;
import Domain.FurniturePiece;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteFromDB", urlPatterns = {"/DeleteFromDB"})
public class DeleteFromDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // variables
        String action = request.getParameter("action-perf");
        //different actions
        try {
            switch (action) {
                case "delete-user" -> {
                    deleteUserDB(request, response);
                    break;
                }
                case "delete-furn-assemb" -> {
                    deleteFurnAssembly(request, response);
                }
                case "delete-furn-piece" -> {
                    deleteFurnPiece(request, response);
                }
                default -> {
                    response.sendRedirect(request.getContextPath() + "404.jsp");
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
     * delete a user from DB
     *
     * @param request httpservletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void deleteUserDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get user name
        User user = new User(request.getParameter("username"));
        String repBody = "el usuario con registro " + user.getName();
        repBody = new UserDAO().delete(user) != 0 ? "Se ha borrado " + repBody : "No se ha logrado borrar " + repBody;
        request.setAttribute("rep-title", "Borrar registro");
        request.setAttribute("rep-body", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    /**
     * delete a furniture assembly from DB
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteFurnAssembly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FurnitureAssembly fa = new FurnitureAssembly(new InsertUtilities().getIntegerFromString(request.getParameter("id")));
        String repBody = "el mueble con ID " + fa.getId();
        repBody += new FurnitureAssemblyDAO().deleteFurnAssembly(fa) != 0 ? " Se ha borrado " : "No se ha logrado borrar ";
        request.setAttribute("rep-title", "Borrar registro");
        request.setAttribute("rep-body", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    private void deleteFurnPiece(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FurniturePiece fp = new FurniturePiece(new InsertUtilities().getIntegerFromString(request.getParameter("id")));
        String repBody = "la pieza con ID " + fp.getId();
        repBody += new FurniturePieceDAO().delete(fp) != 0 ? " Se ha borrado " : "No se ha logrado borrar ";
        request.setAttribute("rep-title", "Borrar registro");
        request.setAttribute("rep-body", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }
}
