package Web.CreateFurniture;

import Database.FurnitureDAO;
import Domain.FurniturePieceCuantity;
import GeneralUse.InsertUtilities;
import GeneralUse.Redirect;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ValidateNewFurniture", urlPatterns = {"/ValidateNewFurniture"})
public class ValidateNewFurniture extends HttpServlet {

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
        try {
            if (new FurnitureDAO().select(request.getParameter("furnName")) == null) {
                request.setAttribute("furnName", request.getParameter("furnName"));
                request.getSession().setAttribute("furn-recet", new ArrayList<FurniturePieceCuantity>());
                new Redirect().redirectToURL(request, response, "Dashboards/Furniture/NewFurniture.jsp");
            } else {
                new InsertUtilities().sendErrorMessage(response, request, new Exception("Ya existe"), "El mueble ya existe");
            }
        } catch (Exception ex) {
            new InsertUtilities().sendErrorMessage(response, request, ex, "Error al buscar mueble");
        }
    }
}
