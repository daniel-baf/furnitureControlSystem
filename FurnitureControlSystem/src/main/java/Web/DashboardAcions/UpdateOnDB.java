package Web.DashboardAcions;

import Database.FurniturePieceDAO;
import Domain.FurniturePiece;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefemayoneso
 */
@WebServlet(name = "UpdateOnDB", urlPatterns = {"/UpdateOnDB"})
public class UpdateOnDB extends HttpServlet {

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
        response.setContentType("text/html");
        try {
            switch (request.getParameter("update-action")) {
                case "edit-piece" -> {
                    updatePieceOnDB(request, response);
                }

            }
        } catch (Exception e) {
            new InsertUtilities().sendErrorMessage(response, request, e, "Ha ocurrido un error");
        }
    }

    /**
     * this method calls other methods and update the info of any furniture with
     * the data of any form
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void updatePieceOnDB(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // create Piece Object
        InsertUtilities iu = new InsertUtilities();
        FurniturePiece fp = new FurniturePiece(iu.getIntegerFromString(request.getParameter("piece-id")), request.getParameter("piece-name"), iu.getDoubleFromString(request.getParameter("piece-price")));
        String repBody = "actualizar la información de la pieza con Id " + fp.getId();
        repBody = new FurniturePieceDAO().update(fp) != 0 ? "Se ha logrado " + repBody : "No se ha logrado " + repBody;
        iu.configAttibutes(request, "Insertar usuario", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }
}
