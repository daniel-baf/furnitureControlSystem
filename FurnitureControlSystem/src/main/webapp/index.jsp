<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
        <meta name="author" content="jefe mayoneso">
        <meta name="keyword" content="IPC2, Dashboard, templateMod, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
        <title>Login MiMuebleria</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- bootstrap theme -->
        <link href="css/bootstrap-theme.css" rel="stylesheet">
        <!-- font icon -->
        <link href="css/elegant-icons-style.css" rel="stylesheet" />
        <link href="css/font-awesome.css" rel="stylesheet" />
        <!-- Custom styles -->
        <link href="css/style.css" rel="stylesheet">
        <link href="css/style-responsive.css" rel="stylesheet" />
    </head>
    <body class="login-img-3-body">
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
