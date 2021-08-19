package Web;

import Database.UserDAO;
import Domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        User user = new UserDAO().selectUser(request.getParameter("user"));

        if (user != null) {
            // user found
            if (user.getName().equals(request.getParameter("user").trim()) && user.getPassword().equals(request.getParameter("password").trim())) {
                // user auth
                RequestDispatcher rd;
                switch (user.getAreaCode()) {
                    // note: if i delete Dashboard and type just Factory is working
                    case 1:
                        response.sendRedirect(request.getContextPath() + "/Dashboards/Factory-Panel.jsp");
                        break;
                    case 2:
                        response.sendRedirect(request.getContextPath() + "/Dashboards/Sale-Point-Panel.jsp");
                        break;
                    case 3:
                        response.sendRedirect(request.getContextPath() + "/Dashboards/Fin-Admin-Panel.jsp");
                        break;
                    default:
                        response.sendRedirect(request.getContextPath());
                        break;
                }
            }

        } else {
            response.sendRedirect(request.getContextPath());
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
    }// </editor-fold>

}
