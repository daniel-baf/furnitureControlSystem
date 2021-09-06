package Web.DashboardAcions;

import Database.BillDAO;
import Database.ClientDAO;
import Database.FurnitureAssemblyDAO;
import Database.FurnitureDAO;
import Database.FurniturePieceDAO;
import Database.RefundDAO;
import Database.UserDAO;
import Domain.Bill;
import Domain.Client;
import Domain.Furniture;
import Domain.FurnitureAssembly;
import Domain.FurniturePiece;
import Domain.User;
import GeneralUse.AES256;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertToDBSwitcherActions {

    /**
     * insert a furniture to DB, this method can be called anywhere
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param iu
     * @throws ServletException
     * @throws IOException
     */
    public void insertFurnitureDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws Exception {
        Double price = iu.getDoubleFromString(request.getParameter("furnPrice"));
        Furniture furn = new Furniture(request.getParameter("furnName"), price);
        String repBody = " insertar el mueble con nombre " + furn.getName();
        repBody = new FurnitureDAO().insert(furn) != 0 ? "Se ha logrado " + repBody : "No se ha logrado " + repBody;
        iu.configAttibutes(request, "Registrar nuevo mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @param iu
     * @throws ServletException
     * @throws IOException
     * @throws Exception
     */
    public void insertUserDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws Exception {
        // encrypt password
        AES256 aes356 = new AES256();
        short tmp = (short) ((int) iu.getIntegerFromString(request.getParameter("areaCode")));
        User user = new User(request.getParameter("usrName"), aes356.encrypt(request.getParameter("userPswrd")), tmp, 1);
        String repBody = " insertar el mueble con nombre " + user.getName();
        repBody = new UserDAO().insert(user) != 0 ? "Se ha insertado " + repBody : "No se ha borrado " + repBody;
        iu.configAttibutes(request, "Registrar nuevo mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @param username
     * @param iu
     * @throws Exception
     */
    public void insertFurnAssemblyDB(HttpServletRequest request, HttpServletResponse response, String username, InsertUtilities iu) throws Exception {
        FurnitureAssembly fa = new FurnitureAssembly(username, LocalDate.now(), request.getParameter("furniture-assemb"));
        String repBody = " registrar el nuevo ensamble de mueble";
        repBody = new FurnitureAssemblyDAO().insertFurnAssmebly(fa) != 0 ? "Se ha logrado insertar " + repBody : "No se ha logrado crear " + repBody;
        iu.configAttibutes(request, "Registrar nuevo ensamble de mueble", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @param iu
     * @throws ServletException
     * @throws IOException
     * @throws Exception
     */
    public void insertPieceDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws Exception {
        FurniturePiece piece = new FurniturePiece(request.getParameter("piece-name"), iu.getDoubleFromString(request.getParameter("piece-price")));
        String repBody = " registrar la pieza a la base de datos";
        repBody = new FurniturePieceDAO().insert(piece) != 0 ? "Se ha logrado " + repBody : "No se ha logrado " + repBody;
        iu.configAttibutes(request, "Registrar pieza", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @param iu
     * @throws Exception
     */
    public void insertClientDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws Exception {
        Client client = new Client(
                request.getParameter("nit"),
                request.getParameter("name"),
                request.getParameter("adress"),
                request.getParameter("municipality"),
                request.getParameter("department"));
        String repBody = " registrar al nuevo cliente en la base de datos";

        if (client.getMunicipality().length() > 0 && client.getDepartment().isEmpty()
                || client.getMunicipality().isEmpty() && client.getDepartment().length() > 0) {
            repBody = "Uno de los atributos no cumple con los requisitos";
        } else {
            repBody = new ClientDAO().insert(client, response) != 0 ? "se ha logrado" + repBody : "no se ha logrado " + repBody;
        }

        iu.configAttibutes(request, "Registrar usuario", repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @param iu
     * @throws java.lang.Exception
     */
    public void ProcessRefundDB(HttpServletRequest request, HttpServletResponse response, InsertUtilities iu) throws Exception {
        User user = new User((String) request.getSession().getAttribute("usr"));
        Bill bill = new BillDAO().select(1, iu.getIntegerFromString(request.getParameter("bill-id")));
        String repBody = " procesar la devolucion del articulo " + bill.getCode();
        repBody = new RefundDAO().insertRefund(bill, user, response) != 0 ? "Se ha completado satisfactoriamente " + repBody : "No se ha podidio " + repBody + " revisa que los datos sean validos";
        iu.configAttibutes(request, repBody, repBody);
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }
}
