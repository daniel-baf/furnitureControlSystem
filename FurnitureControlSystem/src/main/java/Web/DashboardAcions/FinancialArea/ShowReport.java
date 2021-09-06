package Web.DashboardAcions.FinancialArea;

import Database.FurnitureDAO;
import Database.UserDAO;
import Domain.Client;
import Domain.Furniture;
import Domain.User;
import GeneralUse.InsertUtilities;
import GeneralUse.Redirect;
import java.io.IOException;
import java.time.LocalDate;
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
            ShowReportsFinancialAreaActions sra = new ShowReportsFinancialAreaActions();
            ShowRerpotsSellPointActions srs = new ShowRerpotsSellPointActions();
            boolean validDates = date1 != null && date2 != null;

            switch (request.getParameter("report-type-FAAP")) {
                case "sells" -> {
                    sra.getReportSells(request, response, date1, date2, validDates, null, null, null);
                }
                case "most-sells-user" -> {
                    // create user
                    User user = new UserDAO().selectMostSellsOrEarnigsUser("most");
                    sra.getReportSells(request, response, date1, date2, validDates, user, null, null);
                }
                case "refunds" -> {
                    sra.generateReportRefunds(request, response, date1, date2, validDates, null);
                }
                case "earnings" -> {
                    sra.generateReportEarnings(request, response, date1, date2, validDates, null);
                }
                case "most-profits-user" -> {
                    User user = new UserDAO().selectMostSellsOrEarnigsUser("earnigns");
                    sra.generateReportEarnings(request, response, date1, date2, validDates, user);
                }
                case "most-sold-furniture" -> {
                    Furniture furn = new FurnitureDAO().getMostOrLessSoldFurniture("most");
                    sra.genearateReportFurniture(request, response, date1, date2, validDates, furn);
                }
                case "less-sold-furniture" -> {
                    Furniture furn = new FurnitureDAO().getMostOrLessSoldFurniture("less");
                    sra.genearateReportFurniture(request, response, date1, date2, validDates, furn);
                }
                case "show-buys-client-info" -> {
                    Client client = new Client(request.getParameter("client-nit"));
                    sra.getReportSells(request, response, date1, date2, validDates, null, null, client);
                }
                case "refunds-client" -> {
                    Client client = new Client(request.getParameter("client-nit"));
                    sra.generateReportRefunds(request, response, date1, date2, validDates, client);
                }
            }
        } catch (Exception e) {
            new InsertUtilities().sendErrorMessage(response, request, e, "Ha ocurrido un error");
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

    public void redirectToURL(HttpServletRequest request, HttpServletResponse response, String URL) throws ServletException, IOException {
        new Redirect().redirectToURL(request, response, URL);
    }
}
