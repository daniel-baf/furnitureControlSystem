package Web.DashboardAcions.FinancialArea;

import Database.BillDAO;
import Database.EarningsDAO;
import Database.RefundDAO;
import Domain.Bill;
import Domain.Client;
import Domain.Earning;
import Domain.Furniture;
import Domain.Refund;
import Domain.User;
import GeneralUse.Redirect;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowReportsFinancialAreaActions {

    public void getReportSells(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, boolean validDates, User user, Furniture furniture, Client client) throws IOException, ServletException {
        ArrayList<Bill> bills = new BillDAO().getBillingReport(date1, date2, validDates, user, furniture, null);
        String reportDescription = user != null ? "Reporte de ventas en el intervalo " + date1 + " " + date2 : "Reporte de ventas por el usuario con mas ventas dureante el intervalo " + date1 + " " + date2;
        request.setAttribute("rep-desc", reportDescription);
        request.setAttribute("bills", bills);
        new Redirect().redirectToURL(request, response, "Reports/Financial/bills-result.jsp");
    }

    public void generateReportRefunds(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, boolean validDates) throws ServletException, IOException {
        ArrayList<Refund> refunds = new RefundDAO().getRefundsReport(date1, date2, validDates);
        String reportDescription = "Se muestran las devoluciones generadas durante el periodo " + date1 + " " + date2;
        request.setAttribute("rep-desc", reportDescription);
        request.setAttribute("refunds", refunds);
        new Redirect().redirectToURL(request, response, "Reports/Financial/refund-report.jsp");
    }

    public void generateReportEarnings(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, boolean validDates, User user) throws ServletException, IOException {
        ArrayList<Earning> earnings = new EarningsDAO().getEarningsReport(validDates, date1, date2, user);
        String reportDescription = "Se muestran las ganancias generadas durante el periodo " + date1 + " " + date2;
        request.setAttribute("rep-desc", reportDescription);
        request.setAttribute("earnings", earnings);
        new Redirect().redirectToURL(request, response, "Reports/Financial/earnings-report.jsp");
    }

    public void genearateReportFurniture(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, boolean validDates, Furniture furn) throws IOException, ServletException {
        getReportSells(request, response, date1, date2, validDates, null, furn, null);
    }
}
