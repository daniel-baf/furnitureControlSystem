<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    if (!session.getAttribute("area").equals("2")) {
        response.sendError(response.SC_NOT_ACCEPTABLE, "No tienes permitido entrar a esta pagina");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel Ventas</title>
    </head>
    <body>
        <h1>Usted es parte del punto de ventas</h1>
        <label>area: <%=session.getAttribute("area")%></label>
        <label>area: <%=session.getAttribute("usr")%></label>
        
        <a href="BillingPages/create-bill.jsp">Crear factura</a>
    </body>
</html>
