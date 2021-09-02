<%@page import="Database.UserDAO"%>
<%@page import="Domain.User"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    boolean procedRequest;
    String[] links = new String[]{"./Fin-Admin-Panel.jsp"};
    try {
        procedRequest = request.getSession() != null && request.isRequestedSessionIdValid(); // return true if session is valid
        procedRequest = request.getSession().getAttribute("area").equals("3") && procedRequest ? true : false;
    } catch (Exception e) {
        procedRequest = false;
    }
    if (!procedRequest) {
        response.sendRedirect("../login.jsp");
    }
    ArrayList<User> listUsers = (ArrayList<User>) new UserDAO().selectUsers();
%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Administración y finanzas</title>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/MyOwnCss.css">

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="../login.jsp">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Muebleria <sup>Mi</sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="../Dashboards/Fin-Admin-Panel.jsp">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Panel</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Tus acciones
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                       aria-expanded="true" aria-controls="collapseTwo">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>Reportes</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Reportes financieros</h6>
                            <a class="collapse-item" href="#generate-report-faap">Ventas</a>
                            <a class="collapse-item" href="#generate-report-faap">Devoluciones</a>
                            <a class="collapse-item" href="#generate-report-faap"">Ganancias</a>
                            <h6 class="collapse-header">Trabajadores</h6>
                            <a class="collapse-item" href="#generate-report-faap">Con más ventas</a>
                            <a class="collapse-item" href="#generate-report-faap">Con más ganancias</a>
                            <h6 class="collapse-header">Muebles</h6>
                            <a class="collapse-item" href="#generate-report-faap">Más vendido</a>
                            <a class="collapse-item" href="#generate-report-faap">Menos vendido</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                       aria-expanded="true" aria-controls="collapseUtilities">
                        <i class="fas fa-fw fa-wrench"></i>
                        <span>Administración</span>
                    </a>
                    <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Creación</h6>
                            <a class="collapse-item" href="#insert-usr-FAAP">Nuevo usuario</a>
                            <a class="collapse-item" href="#insert-frn-FAAP">Tipo de mueble</a>
                            <h6 class="collapse-header">Borrar</h6>
                            <a class="collapse-item" href="#delete-user-form">Usuario</a>
                        </div>
                    </div>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">
                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>
            </ul>
            <!-- End of Sidebar -->
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">
                    <jsp:include page="../Includes/top-bar.jsp"/>

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Dashboard: Area financiera</h1>
                            <a href="#load-file-db-txt-faap" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" id="load-text-file-DB-faap"><i
                                    class="fas fa-download fa-sm text-white-50" ></i> Cargar Archivo</a>
                        </div>

                        <!-- Content Row -->
                        <div class="row">
                            <!-- Earnings (Monthly) Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-primary shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                    Ganancias (Dia)</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Earnings (Monthly) Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-success shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                    Ganancias (Mes)</div>
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Earnings (Monthly) Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-info shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div class="col mr-2">
                                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Muy pronto...
                                                </div>
                                                <div class="row no-gutters align-items-center">
                                                    <div class="col-auto">
                                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="progress progress-sm mr-2">
                                                            <div class="progress-bar bg-info" role="progressbar"
                                                                 style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                                 aria-valuemax="100"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Pending Requests Card Example -->
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card border-left-warning shadow h-100 py-2">
                                    <div class="card-body">
                                        <div class="row no-gutters align-items-center">
                                            <div href="#table-stock" class="col mr-2">
                                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                    Piezas con stock bajo</div>

                                                <a href="#table-stock">

                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        <p>Ver</p>
                                                    </div>

                                                </a>
                                            </div>
                                            <div class="col-auto">
                                                <i class="fas fa-comments fa-2x text-gray-300">.</i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Content Row -->
                        <div class="row">
                            <!-- Area Chart -->
                            <div class="col-xl-8 col-lg-7">
                                <div class="card shadow mb-4">
                                    <!-- Card Header - Dropdown -->
                                    <div
                                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <div>
                                            <h6 class="m-0 font-weight-bold text-primary">Creación</h6>
                                            <p>Registra un nuevo mueble para la venta, presiona los tres puntos para cambiar de ventana</p>
                                        </div>

                                        <div class="dropdown no-arrow">
                                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                 aria-labelledby="dropdownMenuLink">
                                                <div class="dropdown-header">¿Qué deseas registar?</div>
                                                <a class="dropdown-item" id="shw-usr-hde-frn-faap">Mueble</a>
                                                <a class="dropdown-item" id="shw-frn-hde-usr-faap">Usuario</a>
                                                <div class="dropdown-divider"></div>
                                                <a class="dropdown-item" id="toggle-result-faap">Limpiar</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Card Body -->
                                    <div class="card-body">
                                        <div class="chart-area">
                                            <!-- FUNNITURE START -->
                                            <div id="insert-frn-FAAP">
                                                <h6 class="row d-flex p-2 justify-content-center border-bottom-primary">Mueble</h6>
                                                <div class="m-0 row justify-content-center">
                                                    <div class="col-auto align-content-around">
                                                        <form action="../InsertToDB" method="POST">
                                                            <input name="action-perf" type="text" value="insert-furniture" hidden>
                                                            <div class="form-group">
                                                                <label for="exampleInputName">Nombre</label>
                                                                <input required name="furnName" type="text" class="form-control" id="exampleInputName" aria-describedby="emailHelp" placeholder="Un nombre de usuario">
                                                                <small>Los nombres no se pueden repetir con los ya existentes</small>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="exampleInputPrice">Precio</label>
                                                                <input required name="furnPrice" type="number" min="0" step=".01" class="form-control" id="exampleInputPrice" aria-describedby="priceHelp" placeholder="Un nombre de usuario">
                                                                <small>El precio de venta del mueble</small>
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Registrar</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- FURNITURE END -->
                                            <!-- USER START -->
                                            <div  class="d-none" id="insert-usr-FAAP">
                                                <h6 class="row d-flex p-2 justify-content-center border-bottom-primary">Usuario</h6>
                                                <div class="m-0 row justify-content-center">
                                                    <div class="col-auto align-content-around">
                                                        <form action="../InsertToDB" method="POST">
                                                            <div class="d-flex justify-content-between">
                                                                <div class="col-xl-8 col-lg-7">
                                                                    <input name="action-perf" type="text" value="insert-user" hidden>
                                                                    <div class="form-group">
                                                                        <label for="exampleInputName1">Nombre</label>
                                                                        <input required name="usrName" type="text" class="form-control" id="exampleInputName1" aria-describedby="nameHelp" placeholder="Un nombre de usuario">
                                                                        <small>Los nombres no se pueden repetir con los ya existentes</small>
                                                                    </div>
                                                                    <div class="form-group"> 
                                                                        <label for="exampleInputPassword">Contraseña</label>
                                                                        <input required name="userPswrd" type="password" class="form-control" id="exampleInputPassword" aria-describedby="passwordHelp" placeholder="Ingresa la contraseña">
                                                                        <small>La contraseña para el nuevo usuario</small>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xl-4 col-lg-5">
                                                                    <div class="form-group d-flex flex-column justify-content-center">
                                                                        <div>
                                                                            <lable for="select-insert-user-FAAP"">Codigo de area</lable>
                                                                            <select id="select-insert-user-FAAP" name="areaCode" class="form-control" aria-label=".form-select-lg example">
                                                                                <option required value="1">Fabrica</option>
                                                                                <option required value="2">Punto de ventas</option>
                                                                                <option required value="3">Financiera y adm.</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Crear</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="content-async-insert" class="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Pie Chart -->
                            <div class="col-xl-4 col-lg-5">
                                <div class="card shadow mb-4">
                                    <!-- Card Header - Dropdown -->
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between" id="delete-user-form">
                                        <h6 class="m-0 font-weight-bold text-primary">Borrar usuario</h6>
                                        <div class="dropdown no-arrow">
                                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                 aria-labelledby="dropdownMenuLink">
                                                <div class="dropdown-header">Acciones formulario</div>
                                                <a class="dropdown-item" href="#">Limpiar</a>
                                                <div class="dropdown-divider"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Card Body -->
                                    <div class="card-body">
                                        <div class="chart-pie pt-4 pb-2">
                                            <div class="m-0 row justify-content-center">
                                                <div class="col-auto align-content-around">
                                                    <form action="../DeleteFromDB" method="POST">
                                                        <div class="form-group">
                                                            <input name="action-perf" type="text" value="delete-user" hidden>
                                                            <label for="exampleInputName">Nombre</label>
                                                            <select name="username" class="form-control btn-block" id="examplePieceName">
                                                                <%for (User elem : listUsers) {%>
                                                                <option value="<%=elem.getName()%>"><%=elem.getName()%></option>
                                                                <br>
                                                                <%}%>
                                                            </select>
                                                            <small>El usuario debe existir</small>
                                                        </div>
                                                        <button type="submit" class="btn btn-primary">Borrar</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="mt-4 text-center small">
                                            <span class="mr-2">
                                                <i class="fas fa-circle text-primary"></i> Esta accion borra usuario de la base de datos
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Content Row -->
                        <div class="row">

                            <!-- Content Column -->
                            <div class="col-lg-6 mb-4">

                                <!-- Project Card Example -->
                                <div id="generate-report-faap" class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Reportes</h6>
                                    </div>
                                    <div class="card-body">
                                        <form action="../ShowReport" method="GET" class="border-bottom-secondary">
                                            <div>
                                                <input name="action-perf" type="text" value="get-report" hidden>
                                                <div>
                                                    <div class="form-group">
                                                        <label for="exampleInputDate">Fecha inicial</label>
                                                        <input name="dateStart" id="exampleInputDate" type="date" class="form-control" id="exampleInputName" aria-describedby="emailHelp" placeholder="Un nombre de usuario">
                                                        <small>Selecciona la fecha de inicio para el reporte, dejalo vacio para que el reporte sea de toda la vida</small>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="exampleInputDate">Fecha final</label>
                                                        <input name="dateEnd" id="exampleInputDate" type="date" class="form-control" id="exampleInputName" aria-describedby="emailHelp" placeholder="Un nombre de usuario">
                                                        <small>Selecciona la fecha donde quieres que el repotre termine, dejalo vacio para que el reporte sea de toda la vida</small>
                                                    </div>
                                                </div>
                                                <div class="container m-3">
                                                    <div class="d-block text-right">
                                                        <h6 class="ali">Selecciona un tipo de reporte</h6>
                                                        <small>Los reportes pueden ser exportados a formato compatible con excel(CSV)</small>
                                                        <br>
                                                    </div>
                                                    <div class="form-check">
                                                        <input required class="form-check-input" type="radio" id="report-name-FAAP-choise1" name="report-type-FAAP" value="sells">
                                                        <label class="form-check-label" for="report-name-FAAP-choise1">Ventas realizadas a clientes</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input required class="form-check-input" type="radio" id="report-name-FAAP-choise2" name="report-type-FAAP" value="refunds">
                                                        <label class="form-check-label" for="report-name-FAAP-choise2">Devoluciones hechas por clientes</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input required class="form-check-input" type="radio" id="report-name-FAAP-choise3" name="report-type-FAAP" value="earnings">
                                                        <label class="form-check-label" for="report-name-FAAP-choise3">Ganancias netas generadas</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input required class="form-check-input" type="radio" id="report-name-FAAP-choise4" name="report-type-FAAP" value="most-sells-user">
                                                        <label class="form-check-label" for="report-name-FAAP-choise4">Usuario que mas ventas ha registrado </label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input required class="form-check-input" type="radio" id="report-name-FAAP-choise5" name="report-type-FAAP" value="most-profits-user">
                                                        <label class="form-check-label" for="report-name-FAAP-choise5">Usuario que mas ganancias ha registrado</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input required class="form-check-input" type="radio" id="report-name-FAAP-choise6" name="report-type-FAAP" value="most-sold-furniture">
                                                        <label class="form-check-label" for="report-name-FAAP-choise6">Mueble mas vendido</label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input required class="form-check-input" type="radio" id="report-name-FAAP-choise8" name="report-type-FAAP" value="less-sold-furniture">
                                                        <label class="form-check-label" for="report-name-FAAP-choise7">Mueble menos vendido</label>
                                                    </div>
                                                </div>
                                                <div class="form-group d-block justify-content-end">
                                                    <button type="submit" class="btn btn-primary">Crear reporte</button>
                                                </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6 mb-4">

                            <!-- Illustrations -->
                            <div class="card shadow mb-4">
                                <!-- Card Header - Accordion -->
                                <a href="#collapseCardExample1" class="d-block card-header py-3" data-toggle="collapse"
                                   role="button" aria-expanded="true" aria-controls="collapseCardExample">
                                    <h6 class="m-0 font-weight-bold text-primary">Bienvenido</h6>
                                </a>
                                <!-- Card Content - Collapse -->
                                <div class="collapse show" id="collapseCardExample1">
                                    <div class="card-body">
                                        <div class="text-center">
                                            <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"
                                                 src="../img/undraw_posting_photo.svg" alt="...">
                                        </div>
                                        <p>Espero te encuentres bien, <span><%=session.getAttribute("usr")%></span></p>
                                        <a target="_blank" rel="nofollow" href="https://github.com/daniel-baf/furnitureControlSystem">Codigo fuente &rarr;</a>
                                    </div>
                                </div>

                            </div>
                            <!-- Approach -->
                            <div id="load-file-db-txt-faap" class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Archivo para base de datos</h6>
                                </div>
                                <div class="card-body">
                                    <p>Sube un archivo plano/texto para ser insertado</p>
                                    <form action="../FileUpload" method="POST" enctype="multipart/form-data" target="_blank" class="justify-content-end">
                                        <input class="btn btn-sm" type="file" name="file" value="Buscar">
                                        <input class="btn btn-block btn-primary"type="submit" value="Actualiar BD">
                                    </form>
                                </div>
                            </div>

                            <jsp:include page="/Includes/pieces-stock.jsp"></jsp:include>
                            </div>
                        </div>
                        <!-- /.container-fluid -->

                    </div>
                    <!-- End of Main Content -->
                <jsp:include page="../Includes/footer.jsp"></jsp:include>
                </div>
                <!-- End of Content Wrapper -->
            </div>
            <!-- End of Page Wrapper -->
        <jsp:include page="/Includes/scroll-top-logout-pop-up.jsp"/>

        <!-- Bootstrap core JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="${pageContext.request.contextPath}/js/demo/chart-area-demo.js"></script>
        <script src="${pageContext.request.contextPath}/js/demo/chart-pie-demo.js"></script>
        <!-- own scripts -->
        <script src="${pageContext.request.contextPath}/js/MyOwnFunction.js"></script>

        <!-- Page level plugins -->
        <script src="${pageContext.request.contextPath}/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="${pageContext.request.contextPath}/js/demo/datatables-demo.js"></script>

    </body>

</html>
