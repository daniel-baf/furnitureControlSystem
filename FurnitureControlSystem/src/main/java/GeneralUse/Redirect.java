package GeneralUse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect {

    public void redirectToURL(HttpServletRequest request, HttpServletResponse response, String URL) throws ServletException, IOException {
        request.getRequestDispatcher(URL).forward(request, response);
    }
}
