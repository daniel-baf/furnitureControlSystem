<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (!session.getAttribute("area").equals("3")) {
        response.sendError(response.SC_NOT_ACCEPTABLE, "No tienes permitido entrar a esta pagina");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel F&A</title>
    </head>
    <body>
        <h1>Usted es parte del area financiera</h1>

        <fieldset>
            <h3>Cargar datos</h3>
            <form action="../FileUpload" method="POST" enctype="multipart/form-data">
                <input type="file" name="file" value="Buscar">
                <input type="submit" value="Actualiar BD">
            </form>
        </fieldset>

        <!-- SCRIPTS -->
        <script src="../Build/JS/jQuery.js"></script>
        <script src="../Build/JS/bundle.js"></script>
    </body>
</html>
