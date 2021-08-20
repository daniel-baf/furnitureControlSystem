package Web;

import Database.UserDAO;
import Domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // DATA ACCES OBJECT
        User user = new UserDAO().selectUser(request.getParameter("user"));

        // validate user
        if (user != null) {
            // user found
            if (userOK(user.getName(), request.getParameter("user").trim())
                    && passwordOK(user.getPassword(), request.getParameter("password").trim())) {
                HttpSession session = request.getSession();

                // user auth
                String URL = null;
                switch (user.getAreaCode()) {
                    case 1:
                        configSession(session, user, URL);
                        response.sendRedirect(URL);
                        break;
                    case 2:
                        URL = request.getContextPath() + "/Dashboards/Sale-Point-Panel.jsp";
                        configSession(session, user, URL);
                        response.sendRedirect(URL);
                        break;
                    case 3:
                        URL = request.getContextPath() + "/Dashboards/Fin-Admin-Panel.jsp";
                        configSession(session, user, URL);
                        response.sendRedirect(URL);
                        break;
                    default:
                        response.sendRedirect(request.getContextPath());
                        break;
                }
            } else {
                response.sendRedirect(request.getContextPath());
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

    private boolean userOK(String userIn, String userLog) {
        return userIn.equals(userLog);
    }

    private boolean passwordOK(String passIn, String passLog) {
        return passIn.equals(passLog);
    }

    private void configSession(HttpSession session, User user, String URL) {
        session.setAttribute("area", String.valueOf(user.getAreaCode()));
        session.setAttribute("loged", "true");
        session.setAttribute("panelUrl", URL);
        session.setAttribute("usr", user);
    }
}
