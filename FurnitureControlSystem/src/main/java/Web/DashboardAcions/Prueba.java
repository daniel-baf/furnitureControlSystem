package Web.DashboardAcions;

import Database.BillDAO;
import Database.RefundDAO;
import Domain.Bill;
import Domain.Refund;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Prueba", urlPatterns = {"/Prueba"})
public class Prueba extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Prueba</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Prueba at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // let's validate dates
        InsertUtilities iu = new InsertUtilities();
        // if any date is null, report set to lifetime
        LocalDate d1 = iu.stringToLocalDateyyyyMMMdd(request.getParameter("startDate"));
        LocalDate d2 = iu.stringToLocalDateyyyyMMMdd(request.getParameter("endDate"));
        // redirect path
        response.getWriter().print(d1);
        response.getWriter().print(request.getParameter("startDate"));
        String URL = "Reports/";
        boolean isBetweenDates = iu.isBetweenDates(d1, d2);
        // swich actions, different page
        switch (request.getParameter("report-type-FAAP")) {
            case "sells" -> {
                redirectSells(request, isBetweenDates, d1, d2, null);
                URL += "SellsReport.jsp";
            }
            case "refunds" -> {
                redirectRefunds(request, isBetweenDates, d1, d2);
                URL += "RefundReport.jsp";
            }
            default -> {
            }
        }
//        request.getRequestDispatcher(URL).forward(request, response);

    }

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
        processRequest(request, response);
    }

    private void redirectSells(HttpServletRequest request, boolean isBetweenDates, LocalDate d1, LocalDate d2, User user) {
        // let's get the Bill table
        ArrayList<Bill> bills = new BillDAO().getBillingReport(d1, d2, isBetweenDates, user);
        request.setAttribute("bills", bills);
    }

    private void redirectRefunds(HttpServletRequest request, boolean isBetweenDates, LocalDate d1, LocalDate d2) {
        // let's get the Bill table
        ArrayList<Refund> refunds = new RefundDAO().getRefundsReport(d1, d2, isBetweenDates);
        request.setAttribute("refunds", refunds);
    }
}
