package Web.DashboardAcions.FinancialArea;

import Database.BillDAO;
import Domain.Bill;
import GeneralUse.Redirect;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRerpotsSellPointActions {

    void selectTodayBillings(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2) throws Exception {
        ArrayList<Bill> bills = new BillDAO().selectBillsToday();
        String reportDescription = bills != null ? "Reporte de ventas del dia " + LocalDate.now() : "No hay ventas hoy";
        request.setAttribute("rep-desc", reportDescription);
        request.setAttribute("bills", bills);
        new Redirect().redirectToURL(request, response, "Reports/Financial/bills-result.jsp");
    }
}
