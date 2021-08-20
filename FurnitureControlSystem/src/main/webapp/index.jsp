<%-- 
    Document   : index
    Created on : Aug 19, 2021, 11:17:42 PM
    Author     : jefemayoneso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Mueb JSP</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Mi muebleria</h1>
        <form name="form1" action="/FurnitureControlSystem/Login" method="POST">
            <div>
                <label for="user">Usuario </label>
                <input type="text" id="user" name="user" placeholder="ingresa tu usuario">
            </div>
            <div>
                <label for="password">Contraseña </label>
                <input type="password" id="password" name="password" placeholder="introduce tu contraseña"> 
            </div>
            <input type="submit" value="login">
        </form>
    </body>
</html>
