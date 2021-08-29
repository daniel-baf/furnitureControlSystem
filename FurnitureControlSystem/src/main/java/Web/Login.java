package Web;

import Database.UserDAO;
import Domain.User;
import GeneralUse.AES256;
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
        AES256 aes256 = new AES256();
        //validate user
        if (user != null) {
            // user found
            if (userOK(user.getName(), request.getParameter("user").trim())
                    && passwordOK(aes256.decrypt(user.getPassword()), request.getParameter("password").trim())) {
                HttpSession session = request.getSession();

                // user auth
                String URL;
                switch (user.getAreaCode()) {
                    case 1 -> {
                        URL = request.getContextPath() + "/Dashboards/Factory-Panel.jsp";
                        configSession(session, user);
                        response.sendRedirect(URL);
                    }
                    case 2 -> {
                        URL = request.getContextPath() + "/Dashboards/Sale-Point-Panel.jsp";
                        configSession(session, user);
                        response.sendRedirect(URL);
                    }
                    case 3 -> {
                        URL = request.getContextPath() + "/Dashboards/Fin-Admin-Panel.jsp";
                        configSession(session, user);
                        response.sendRedirect(URL);
                    }
                    default -> {
                        session.invalidate();
                        response.sendRedirect(request.getContextPath());
                    }
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

    /**
     * searches for match between user on DB an user typed
     *
     * @param userIn user in DB
     * @param userLog user on <input>
     * @return match?
     */
    private boolean userOK(String userIn, String userLog) {
        try {
            return userIn.equals(userLog);
        } catch (Exception e) { // null pointer
            return false;
        }
    }

    /**
     * searches for match between user password and password on database
     *
     * @param passIn password on database
     * @param passLog password on <input>
     * @return
     */
    private boolean passwordOK(String passIn, String passLog) {
        try {
            return passIn.equals(passLog);
        } catch (Exception e) {
            return false;
        }

    }

    /**
     *
     * @param session
     * @param user
     * @param URL
     */
    private void configSession(HttpSession session, User user) {
        session.setAttribute("area", String.valueOf(user.getAreaCode()));
        session.setAttribute("usr", user.getName());
    }
}
