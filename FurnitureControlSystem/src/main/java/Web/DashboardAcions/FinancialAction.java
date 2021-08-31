package Web.DashboardAcions;

import Database.UserDAO;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FinancialAction", urlPatterns = {"/FinancialAction"})
public class FinancialAction extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        LocalDate date1 = new InsertUtilities().stringToLocalDateyyyyMMMdd(request.getParameter("dateStart"));
        LocalDate date2 = new InsertUtilities().stringToLocalDateyyyyMMMdd(request.getParameter("dateEnd"));
        double profitSum = 0;
        double refundMoney = 0;
        double losesMoney = 0;

        request.setAttribute("reportType", request.getParameter("report-type-FAAP"));
        FinancialActionMethods fam = new FinancialActionMethods();
        switch (request.getParameter("report-type-FAAP")) {
            case "sells" -> {
                fam.confReportCommonTask(request, "Ventas", "ventas realizadas durante el periodo " + date1 + " y " + date2, new String[]{"Factura", "Id mueb", "Mueble", "Fecha", "Ingreso", "NIT"});
                profitSum = fam.confSells(request, response, date1, date2, "list", null);
                break;
            }
            case "refunds" -> {
                fam.confReportCommonTask(request, "Devoluciones", "las devoluciones generadas durante el periodo " + date1 + " y " + date2, new String[]{"Factura", "Mueble", "Fecha devolucion", "Fecha compra", "Reembolso", "NIT", "Precio ensamble", "Perdida"});
                int[] tmp = fam.confRefunds(request, date1, date2, "list");
                refundMoney = tmp[0];
                losesMoney = tmp[1];
                break;
            }
            case "earnings" -> {
                // earnings = sells - refunds - loses
                fam.confReportCommonTask(request, "Ganancias", "las ganancias obtenidas en el intervalo de " + date1 + " y " + date2 + "estado 1 = vendido, estado2 = devuelto", new String[]{"Factura", "Item", "Dinero", "Fecha", "Ganancia", "Estado"});
                int[] tmp = fam.confEarnings(request, date1, date2, "list");
                profitSum = tmp[0];
                refundMoney = tmp[1];
                losesMoney = tmp[2];
                break;
            }
            case "most-sells-user" -> {
                User usr = new UserDAO().selectMostLessUser("most");
                fam.confReportCommonTask(request, "Ventas " + usr.getName(), "ventas realizadas durante el periodo " + date1 + " y " + date2 + " registradas por el usuario " + usr.getName(), new String[]{"Factura", "Id mueb", "Mueble", "Fecha", "Ingreso", "NIT"});
                profitSum = fam.confSells(request, response, date1, date2, "test", usr);
                break;
            }
            case "most-profits-user" -> {
                break;
            }
            case "most-sold-furniture" -> {
                break;
            }
            case "less-sold-furniture" -> {
                break;
            }
            default ->
                response.sendRedirect(request.getContextPath() + "/404.jsp");
        }
        fam.conReportCommonTask(request, profitSum, losesMoney, refundMoney);
//        request.getRequestDispatcher("Reports/DinamicTableResult.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
