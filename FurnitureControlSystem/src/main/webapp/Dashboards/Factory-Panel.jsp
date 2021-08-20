<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    if (!session.getAttribute("area").equals("1")) {
        response.sendError(response.SC_NOT_ACCEPTABLE, "No tienes permitido entrar a esta pagina");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel Fabrica</title>
    </head>
    <body>
        <h1>Usted es parte del area de fabrica</h1>
    </body>
</html>
