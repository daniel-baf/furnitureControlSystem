<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registar cliente</title>
        <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/MyOwnCss.css">

    </head>
    <body>
        <jsp:include page="/Includes/top-bar.jsp"></jsp:include>
            <div id="insert-frn-FAAP">
                <h3 class="row d-flex p-2 justify-content-center">Registrar información de cliente</h3>
                <p class="row d-flex p-2 justify-content-center border-bottom-primary">  Necesitamos informacion sobre el cliente para generar facturas</p>
                <div class="m-0 row justify-content-center">
                    <div class="col-auto align-content-around">
                        <form action="${pageContext.request.contextPath}/InsertToDB" method="POST">
                        <input name="action-perf" type="text" value="insert-client" hidden>
                        <div class="form-group">
                            <label for="nit">NIT</label>
                            <input required name="nit" type="text" class="form-control" id="nit" aria-describedby="emailHelp" placeholder="Un NIT del cliente"z>
                            <small>Solicitale al cliente el nit</small>
                        </div>
                        <div class="form-group">
                            <label for="name">Nombre</label>
                            <input required name="name" type="text" min="0" step=".01" class="form-control" id="name" aria-describedby="priceHelp" placeholder="Ingresa el nombre del cliente">
                            <small>El nombre del cliente</small>
                        </div>
                        <div class="form-group">
                            <label for="adress">Dirección</label>
                            <input required name="adress" type="text" min="0" step=".01" class="form-control" id="adress" aria-describedby="priceHelp" placeholder="La dirección del cliente">
                            <small>Ingresa la dirección donde el cliente recide, puede ser ciudad</small>
                        </div>
                        <div class="form-group">
                            <label for="municipality">Municipalidad</label>
                            <input name="municipality" type="text" min="0" step=".01" class="form-control" id="municipality" aria-describedby="priceHelp" placeholder="El municipio no es obligatorio">
                            <small>El precio de venta del mueble</small>
                        </div>
                        <div class="form-group">
                            <label for="department">Departamento</label>
                            <input name="department" type="text" min="0" step=".01" class="form-control" id="department" aria-describedby="priceHelp" placeholder="Un nombre de usuario">
                            <small>El precio de venta del mueble</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Registrar cliente</button>
                        <button type="button" class="btn btn-warning" onclick="history.go(-1)">Pagina anterior</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
