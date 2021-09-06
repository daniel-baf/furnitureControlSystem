package Web.Bill;

import Database.BillDAO;
import Database.FurnitureAssemblyDAO;
import Domain.Bill;
import Domain.Client;
import Domain.FurnitureAssembly;
import GeneralUse.InsertUtilities;
import TransactionObjects.BillFurniture;
import Web.DashboardAcions.FinancialArea.ShowReport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        // try insert
        Client client = (Client) request.getSession().getAttribute("client-bill-user");
        InsertUtilities iu = new InsertUtilities();
        FurnitureAssembly furnAssm;
        Bill bill;
        String results = "";
        boolean success = false;

        response.getWriter().print("<br>Inicia a recorrer lista");
        for (BillFurniture item : (ArrayList<BillFurniture>) request.getSession().getAttribute("buy-cart")) {
            // generate bill per item
            response.getWriter().print("<br>Item id: " + item.getId());
            try {
                response.getWriter().print("<br>INtentando crear mueble y factura");
                furnAssm = new FurnitureAssemblyDAO().selectFurnAssembly(item.getId());
                bill = new Bill(
                        item.getId(),
                        (String) request.getSession().getAttribute("usr"),
                        client.getNit(),
                        item.getSellPrice(),
                        LocalDate.now());
                if (new BillDAO().insert(bill) != 0) {
                    bill = new BillDAO().select(0, item.getId());
                    results += ("<br>Factura No." + bill.getCode() + ", Item: " + item.getId());
                    success = true;
                } else {
                    results += ("<br>No se puede generar factura para el item con id: " + item.getId());
                }
            } catch (Exception e) {
                results += ("<br>No se pudo generar factura para el item con ID " + item.getId() + " Error: " + e.getMessage());
            }
        }
        if (success) {
            request.getSession().removeAttribute("buy-cart");
        }
        request.setAttribute("rep-body", results);
        request.setAttribute("rep-title", "Resultado facturas");
        new ShowReport().redirectToURL(request, response, "/Reports/Message.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

}
