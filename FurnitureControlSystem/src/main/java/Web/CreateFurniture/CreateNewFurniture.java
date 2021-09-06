package Web.CreateFurniture;

import Database.ConnectionDB;
import Database.FurnitureDAO;
import Database.PieceAssemblyDAO;
import Domain.Furniture;
import Domain.FurniturePieceCuantity;
import Domain.PieceAssembly;
import GeneralUse.InsertUtilities;
import GeneralUse.Redirect;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateNewFurniture", urlPatterns = {"/CreateNewFurniture"})
public class CreateNewFurniture extends HttpServlet {

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
        // varibales
        ArrayList<FurniturePieceCuantity> pieces = (ArrayList<FurniturePieceCuantity>) request.getSession().getAttribute("furn-recet");
        Furniture furniture = new Furniture(request.getParameter("furnName"), new InsertUtilities().getDoubleFromString(request.getParameter("furnPrice")));
        int errorCounter = 0;
        try ( Connection conn = ConnectionDB.getConnection()) {
            // desable auto commit
            conn.setAutoCommit(false);
            try {
                if (new FurnitureDAO().insert(furniture) != 0) {
                    for (FurniturePieceCuantity fornCuantity : pieces) {
                        PieceAssembly pa = new PieceAssembly(request.getParameter("furnName"), fornCuantity.getFurnPiece().getName(), fornCuantity.getCuantity());
                        response.getWriter().print("<br>pieza: " + pa);
                        if (new PieceAssemblyDAO().insert(pa) == 0) {
                            response.getWriter().print("<br>Pieza insertada");
                            errorCounter++;
                        }
                    }
                } else {
                    errorCounter++;
                }
                // disable connection
            } catch (Exception e) {
                errorCounter++;
            } finally {
                if (errorCounter != 0) {
                    response.getWriter().print("<br>Rollback");
                    conn.rollback();
                }
                conn.setAutoCommit(true);
            }
            if (errorCounter != 0) {
                new InsertUtilities().throwCustomError("No fue posible registrar el nuevo mueble, verifica que los datos sean validos oel mueble no exista actualmente");
            }
        } catch (Exception e) {
            try {
                new InsertUtilities().throwCustomError("Error, al conectar a la base de datos, " + e.getMessage());
            } catch (Exception ex) {
            }
        }

        if (errorCounter == 0) {
            request.setAttribute("rep-body", "Se ha creado el nuevo mueble, ya está disponible para la venta");
            request.setAttribute("rep-title", "Exito");
        } else {
            request.setAttribute("rep-body", "NO se pudo insertar nuevo mueble, revisa la sintaxis");
            request.setAttribute("rep-title", "Error");
        }
        new Redirect().redirectToURL(request, response, "Reports/Message.jsp");
    }

}
