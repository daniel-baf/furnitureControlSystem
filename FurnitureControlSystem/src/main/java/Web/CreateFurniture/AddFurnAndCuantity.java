package Web.CreateFurniture;

import Database.FurniturePieceDAO;
import Domain.FurniturePiece;
import Domain.FurniturePieceCuantity;
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
@WebServlet(name = "AddFurnAndCuantity", urlPatterns = {"/AddFurnAndCuantity"})
public class AddFurnAndCuantity extends HttpServlet {

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
        // ADD TO LIST
        HttpSession session = request.getSession();
        ArrayList<FurniturePieceCuantity> recetBckup = (ArrayList<FurniturePieceCuantity>) session.getAttribute("furn-recet");
        //create item if not created
        if (recetBckup == null) {
            recetBckup = new ArrayList<>();
        }
        try {
            // work with backup
            switch (request.getParameter("action-perf")) {
                case "add-item-to-recet" -> {
                    addToRecet(request, response, session, recetBckup);
                }
                case "remove-item-to-recet" -> {
                    removeFromRecet(request, response, session, recetBckup);
                }
            }
        } catch (Exception e) {
            new InsertUtilities().sendErrorMessage(response, request, e, "Error de formato");
        }
    }

    private boolean itemNoOnRecetYet(String name, ArrayList<FurniturePieceCuantity> cartBckup){
        return cartBckup.stream().noneMatch(fpc -> (fpc.getFurnPiece().getName().equalsIgnoreCase(name)));
    }

    private void removeFromRecet(HttpServletRequest request, HttpServletResponse response, HttpSession session, ArrayList<FurniturePieceCuantity> recetBckup) throws ServletException, IOException {
        for (int i = 0; i < recetBckup.size(); i++) {
            if (recetBckup.get(i).getFurnPiece().getName().equalsIgnoreCase(request.getParameter("piece-name"))) {
                recetBckup.remove(i);
                break;
            }
        }
        request.setAttribute("furnName", request.getParameter("furnName"));
        request.setAttribute("furnPrice", request.getParameter("furnPrice"));
        session.setAttribute("furn-recet", recetBckup);
        new ShowReport().redirectToURL(request, response, "Dashboards/Furniture/NewFurniture.jsp");
    }

    private void confRecetAndRedirect(ArrayList<FurniturePieceCuantity> recetBckup, HttpSession session, FurniturePieceCuantity fpc, HttpServletRequest request) {
        recetBckup.add(fpc);
        request.setAttribute("furnName", request.getParameter("furnName"));
        request.setAttribute("furnPrice", request.getParameter("furnPrice"));
        session.setAttribute("furn-recet", recetBckup);
    }

    private void addToRecet(HttpServletRequest request, HttpServletResponse response, HttpSession session, ArrayList<FurniturePieceCuantity> recetBckup) throws ServletException, IOException, Exception {
        // validate if item exists on arraylist
        if (itemNoOnRecetYet(request.getParameter("piece-name"), recetBckup)) {
            FurniturePiece fp = new FurniturePieceDAO().selectPiecesLimitX(request.getParameter("piece-name"), 1).get(0);
            response.getWriter().print("Pieza: " + fp + " " + fp.getName() + " " + fp.getId() + "\n");
            int cuantity = new InsertUtilities().getIntegerFromString(request.getParameter("cuantity"));
            FurniturePieceCuantity fpc = new FurniturePieceCuantity(fp, cuantity);
            confRecetAndRedirect(recetBckup, session, fpc, request);
        } else {
            response.getWriter().print("item encontrado");
        }

        new ShowReport().redirectToURL(request, response, "Dashboards/Furniture/NewFurniture.jsp");
    }

}
