package Web.DashboardAcions;

import Database.UserDAO;
import Domain.User;
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
                default -> {
                    response.getWriter().print("aaaa");
                }
            }
        } catch (Exception e) {
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
     * @param request httpservletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException 
     */
    public void deleteUserDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // get user name
        User user = new User(request.getParameter("username"));
        String repBody = "el usuario con registro " + user.getName();
        repBody = new UserDAO().delete(user) != 0 ? "Se ha borrado " + repBody : "No se ha borrado " + repBody;
        request.setAttribute("rep-title", "Borrar registro");
        request.setAttribute("rep-body", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }
}
