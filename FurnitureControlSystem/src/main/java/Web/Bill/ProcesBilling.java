package Web.Bill;

import Database.ClientDAO;
import Web.DashboardAcions.FinancialArea.ShowReport;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "ProcesBilling", urlPatterns = {"/ProcesBilling"})
public class ProcesBilling extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        //validate client exist
        if (new ClientDAO().selectClient(request.getParameter("client-nit")) != null) {
            //client exist
            // TODO validate furniture sell as transaction
            response.getWriter().print("aaaaaaaaa");
        } else {
            new ShowReport().redirectToURL(request, response, "Dashboards/BillingPages/fill-client-info.jsp");
        }
    }

}
