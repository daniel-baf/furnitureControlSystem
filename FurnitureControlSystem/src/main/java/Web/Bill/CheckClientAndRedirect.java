package Web.Bill;

import Database.ClientDAO;
import Domain.Client;
import Web.DashboardAcions.FinancialArea.ShowReport;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "CheckClientAndRedirect", urlPatterns = {"/CheckClientAndRedirect"})
public class CheckClientAndRedirect extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        //validate client exist
        HttpSession session = request.getSession();
        Client clientTmp = new ClientDAO().selectClient(request.getParameter("client-nit"));
        if (clientTmp != null) {
            //client exist
            session.setAttribute("client-bill-user", clientTmp);
            session.setAttribute("buy-cart", session.getAttribute("buy-cart"));
            request.getRequestDispatcher("/Dashboards/BillingPages/process-bill.jsp").forward(request, response);
        } else {
            new ShowReport().redirectToURL(request, response, "Dashboards/BillingPages/fill-client-info.jsp");
        }
    }
}
