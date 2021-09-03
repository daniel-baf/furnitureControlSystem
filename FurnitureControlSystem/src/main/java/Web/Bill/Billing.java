package Web.Bill;

import Database.FurnitureAssemblyDAO;
import Domain.FurnitureAssembly;
import GeneralUse.InsertUtilities;
import Web.DashboardAcions.FinancialArea.ShowReport;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Billing", urlPatterns = {"/Billing"})
public class Billing extends HttpServlet {

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
        HttpSession session = request.getSession();
        ArrayList<FurnitureAssembly> cartBckup = (ArrayList<FurnitureAssembly>) session.getAttribute("buy-cart");
        //create item if not created
        if (cartBckup == null) {
            cartBckup = new ArrayList<>();
        }
        try {
            // work with backup
            switch (request.getParameter("action-perf")) {
                case "add-item-to-cart" -> {
                    addToCart(request, response, session, cartBckup);
                }
                case "remove-from-cart" -> {
                    removeFromCart(request, response, session, cartBckup);
                }
            }
        } catch (Exception e) {
            new InsertUtilities().sendErrorMessage(response, request, e, "Error de formato");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addToCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, ArrayList<FurnitureAssembly> cartBckup) throws ServletException, IOException, Exception {
        // validate if item exists on arraylist
        InsertUtilities iu = new InsertUtilities();
        if (itemNoOnCartYet(iu.getIntegerFromString(request.getParameter("code-item")), cartBckup)) {
            FurnitureAssembly fa = new FurnitureAssemblyDAO().selectFurnAssembly(new InsertUtilities().getIntegerFromString(request.getParameter("code-item")));
            if (fa != null) {
                confCartAndRedirect(cartBckup, session, fa);
            } else {
                throw new Exception("La pieza que buscas no existe");
            }
        }
        new ShowReport().redirectToURL(request, response, "Dashboards/BillingPages/create-bill.jsp");
    }

    private boolean itemNoOnCartYet(Integer id, ArrayList<FurnitureAssembly> cartBckup) {
        return cartBckup.stream().noneMatch(furnitureAssembly -> (furnitureAssembly.getId() == id));
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response, HttpSession session, ArrayList<FurnitureAssembly> cartBckup) throws ServletException, IOException {
        for (int i = 0; i < cartBckup.size(); i++) {
            if (cartBckup.get(i).getId() == new InsertUtilities().getIntegerFromString(request.getParameter("id"))) {
                cartBckup.remove(i);
                break;
            }
        }
        session.setAttribute("buy-cart", cartBckup);
        new ShowReport().redirectToURL(request, response, "Dashboards/BillingPages/create-bill.jsp");
    }

    private void confCartAndRedirect(ArrayList<FurnitureAssembly> cartBckup, HttpSession session, FurnitureAssembly fa) {
        cartBckup.add(fa);
        session.setAttribute("buy-cart", cartBckup);
    }

}
