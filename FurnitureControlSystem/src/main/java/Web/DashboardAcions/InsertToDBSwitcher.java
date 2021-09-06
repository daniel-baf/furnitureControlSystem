package Web.DashboardAcions;

import GeneralUse.InsertUtilities;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "InsertToDB", urlPatterns = {"/InsertToDB"})
public class InsertToDBSwitcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //different actions
        try {
            InsertUtilities iu = new InsertUtilities();
            InsertToDBSwitcherActions idba = new InsertToDBSwitcherActions();
            switch (request.getParameter("action-perf")) {
                case "insert-furniture" -> {
                    idba.insertFurnitureDB(request, response, iu);
                    break;
                }
                case "insert-user" -> {
                    idba.insertUserDB(request, response, iu);
                }
                case "insert-furn-assbm" -> { // TODO delete when ValidateNewFurniture done
                    HttpSession session = request.getSession();
                    idba.insertFurnAssemblyDB(request, response, (String) session.getAttribute("usr"), iu);
                }
                case "insert-piece" -> {
                    idba.insertPieceDB(request, response, iu);
                }
                case "insert-client" -> {
                    idba.insertClientDB(request, response, iu);
                }
                case "regist-refund" -> {
                    idba.ProcessRefundDB(request, response, iu);
                }
                default -> {
                    new InsertUtilities().sendErrorMessage(response, request, null, "No existe un reporte solicitado");
                }
            }
        } catch (Exception e) {
            new InsertUtilities().sendErrorMessage(response, request, e, "Error en el formato o valor invalido, revisa la solicitud y vuelve a intentarlo");
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
}
