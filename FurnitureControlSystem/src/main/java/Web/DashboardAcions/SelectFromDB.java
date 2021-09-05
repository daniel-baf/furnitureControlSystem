package Web.DashboardAcions;

import Database.FurnitureAssemblyDAO;
import Database.FurniturePieceDAO;
import Domain.FurnitureAssembly;
import Domain.FurniturePiece;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SelectFromDB", urlPatterns = {"/SelectFromDB"})
public class SelectFromDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            switch (request.getParameter("show-obj-value")) {
                case "select-furnitures" -> {
                    configRequestFurniture(request, response, false);
                }
                case "select-pieces" -> {
                    configRequestPieces(request, response);
                }
                case "select-available-furns" -> {
                    configRequestFurniture(request, response, true);
                }
                case "select-sells-today" -> {
                }
            }
        } catch (Exception e) {
            new InsertUtilities().sendErrorMessage(response, request, e, "Error de formato o valores invalidos en la solicitud");
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

    private void configRequestFurniture(HttpServletRequest request, HttpServletResponse response, boolean onlyAvailable) throws IOException, ServletException {
        ArrayList<FurnitureAssembly> furns = new FurnitureAssemblyDAO().getFurnitures(onlyAvailable);
        sharedSettinsResponse("furniture", "Muebles disponibles", request, response, new String[]{"ID", "Mueble", "Precio Ensamble", "Ensamblado por", "Vendido"});
        request.setAttribute("furnitures", furns);
        request.getRequestDispatcher("/Reports/Edits/TableEdits.jsp").forward(request, response);
    }

    private void configRequestPieces(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<FurniturePiece> pieces = new FurniturePieceDAO().selectPieces(null, false);
        sharedSettinsResponse("pieces", "Piezas disponibles", request, response, new String[]{"id", "nombre", "precio"});
        request.setAttribute("pieces", pieces);
        request.getRequestDispatcher("/Reports/Edits/TableEdits.jsp").forward(request, response);
    }

    private void sharedSettinsResponse(String type, String title, HttpServletRequest request, HttpServletResponse response, String... titles) {
        request.setAttribute("type", type);
        request.setAttribute("title", title);
        request.setAttribute("titles", titles);
    }
}
