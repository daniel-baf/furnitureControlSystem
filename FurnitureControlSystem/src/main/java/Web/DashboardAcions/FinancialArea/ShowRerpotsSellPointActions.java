package Web.DashboardAcions.FinancialArea;

import Database.BillDAO;
import Domain.Bill;
import GeneralUse.Redirect;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRerpotsSellPointActions {

    void selectTodayBillings(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2) throws ServletException, IOException {
        ArrayList<Bill> bills = new BillDAO().selectBillsToday();
        String reportDescription = bills != null ? "Reporte de ventas en el intervalo " + date1 + " " + date2 : "Reporte de ventas por el usuario con mas ventas dureante el intervalo " + date1 + " " + date2;
        request.setAttribute("rep-desc", reportDescription);
        request.setAttribute("bills", bills);
        new Redirect().redirectToURL(request, response, "Reports/Financial/bills-result.jsp");
    }
}
