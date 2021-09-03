package Web.DashboardAcions.FinancialArea;

import Database.BillDAO;
import Database.EarningsDAO;
import Database.FurnitureDAO;
import Database.RefundDAO;
import Database.UserDAO;
import Domain.Bill;
import Domain.Earning;
import Domain.Furniture;
import Domain.Refund;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ShowReport", urlPatterns = {"/ShowReport"})
public class ShowReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // save rep type
        request.setAttribute("rep-type", request.getParameter("eport-type-FAAP"));
        // validate if need betweenDates
        InsertUtilities iu = new InsertUtilities();

        try {
            LocalDate date1 = iu.stringToLocalDateyyyyMMMdd(request.getParameter("dateStart"));
            LocalDate date2 = iu.stringToLocalDateyyyyMMMdd(request.getParameter("dateEnd"));
            boolean validDates = date1 != null && date2 != null;

            switch (request.getParameter("report-type-FAAP")) {
                case "sells" -> {
                    getReportSells(request, response, date1, date2, validDates, null, null);
                }
                case "most-sells-user" -> {
                    // create user
                    User user = new UserDAO().selectMostSellsOrEarnigsUser("most");
                    getReportSells(request, response, date1, date2, validDates, user, null);

                }
                case "refunds" -> {
                    generateReportRefunds(request, response, date1, date2, validDates);
                }
                case "earnings" -> {
                    generateReportEarnings(request, response, date1, date2, validDates, null);
                }
                case "most-profits-user" -> {
                    User user = new UserDAO().selectMostSellsOrEarnigsUser("earnigns");
                    generateReportEarnings(request, response, date1, date2, validDates, user);
                }
                case "most-sold-furniture" -> {
                    Furniture furn = new FurnitureDAO().getMostOrLessSoldFurniture("most");
                    genearateReportFurniture(request, response, date1, date2, validDates, furn);
                }
                case "less-sold-furniture" -> {
                    Furniture furn = new FurnitureDAO().getMostOrLessSoldFurniture("less");
                    genearateReportFurniture(request, response, date1, date2, validDates, furn);
                }
            }
        } catch (Exception e) {
            new InsertUtilities().sendErrorMessage(response, request, e, "Un error al seleccionar fechas o de formato");
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

    private void getReportSells(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, boolean validDates, User user, Furniture furniture) throws IOException, ServletException {
        ArrayList<Bill> bills = new BillDAO().getBillingReport(date1, date2, validDates, user, furniture);
        String reportDescription = user != null ? "Reporte de ventas en el intervalo " + date1 + " " + date2 : "Reporte de ventas por el usuario con mas ventas dureante el intervalo " + date1 + " " + date2;
        request.setAttribute("rep-desc", reportDescription);
        request.setAttribute("bills", bills);
        redirectToURL(request, response, "Reports/bills-result.jsp");
    }

    private void generateReportRefunds(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, boolean validDates) throws ServletException, IOException {
        ArrayList<Refund> refunds = new RefundDAO().getRefundsReport(date1, date2, validDates);
        String reportDescription = "Se muestran las devoluciones generadas durante el periodo " + date1 + " " + date2;
        request.setAttribute("rep-desc", reportDescription);
        request.setAttribute("refunds", refunds);
        redirectToURL(request, response, "Reports/refund-report.jsp");
    }

    private void generateReportEarnings(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, boolean validDates, User user) throws ServletException, IOException {
        ArrayList<Earning> earnings = new EarningsDAO().getEarningsReport(validDates, date1, date2, user);
        String reportDescription = "Se muestran las ganancias generadas durante el periodo " + date1 + " " + date2;
        request.setAttribute("rep-desc", reportDescription);
        request.setAttribute("earnings", earnings);
        redirectToURL(request, response, "Reports/earnings-report.jsp");
    }

    private void genearateReportFurniture(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, boolean validDates, Furniture furn) throws IOException, ServletException {
        getReportSells(request, response, date1, date2, validDates, null, furn);
    }

    public void redirectToURL(HttpServletRequest request, HttpServletResponse response, String URL) throws ServletException, IOException {
        request.getRequestDispatcher(URL).forward(request, response);
    }
}
