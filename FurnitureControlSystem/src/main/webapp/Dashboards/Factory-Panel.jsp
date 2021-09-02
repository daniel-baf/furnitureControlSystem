<%@page import="Database.FurnitureDAO"%>
<%@page import="Domain.Furniture"%>
<%@page import="TransactionObjects.PieceByStock"%>
<%@page import="Database.FurniturePieceDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (!session.getAttribute("area").equals("1")) {
        response.sendError(response.SC_NOT_ACCEPTABLE, "No tienes permitido entrar a esta pagina");
    }
    ArrayList<PieceByStock> listPieces = new FurniturePieceDAO().selectPieceAndStock(null, false);
    ArrayList<Furniture> listFurnitures = (ArrayList<Furniture>) new FurnitureDAO().selectFurnitures();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel Fabrica</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
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

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Muebleria<sup>Mi</sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item">
                    <a class="nav-link" href="Factory-Panel.jsp">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Interface
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                       aria-expanded="true" aria-controls="collapseTwo">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>Inventario y stock</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Custom Components:</h6>
                            <a class="collapse-item" href="#inventory-pieces-stock-div">Piezas</a>
                            <a class="collapse-item" href="#">Muebles</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                       aria-expanded="true" aria-controls="collapseUtilities">
                        <i class="fas fa-fw fa-wrench"></i>
                        <span>Utilities</span>
                    </a>
                    <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Custom Utilities:</h6>
                            <a class="collapse-item" href="utilities-color.html">Colors</a>
                            <a class="collapse-item" href="utilities-border.html">Borders</a>
                            <a class="collapse-item" href="utilities-animation.html">Animations</a>
                            <a class="collapse-item" href="utilities-other.html">Other</a>
                        </div>
                    </div>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Heading -->
                <div class="sidebar-heading">
                    Addons
                </div>

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item active">
                    <a class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true"
                       aria-controls="collapsePages">
                        <i class="fas fa-fw fa-folder"></i>
                        <span>Pages</span>
                    </a>
                    <div id="collapsePages" class="collapse show" aria-labelledby="headingPages"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Login Screens:</h6>
                            <a class="collapse-item" href="login.html">Login</a>
                            <a class="collapse-item" href="register.html">Register</a>
                            <a class="collapse-item" href="forgot-password.html">Forgot Password</a>
                            <div class="collapse-divider"></div>
                            <h6 class="collapse-header">Other Pages:</h6>
                            <a class="collapse-item" href="404.html">404 Page</a>
                            <a class="collapse-item active" href="blank.html">Blank Page</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="charts.html">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Charts</span></a>
                </li>

                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="tables.html">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Tables</span></a>
                </li>

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

                    <jsp:include page="/Includes/top-bar.jsp"></jsp:include>

                        <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <h1 class="h3 mb-4 text-gray-800">Dashboard: Fábrica</h1>
                            <div class="row">

                                <div class="col-lg-6">

                                    <!-- Default Card Example -->
                                    <div class="card mb-4">
                                        <div class="card-header">
                                            Crear o modificar pieza
                                        </div>
                                        <div class="card-body">
                                            Obten una lista con todas las piezas o muebles y editalas. También podrás borrarlas segun lo que elijas
                                            <form action="../SelectFromDB" method="GET" class="border-bottom-secondary">
                                                <div>
                                                    <input name="action-perf" type="text" value="get-report" hidden>
                                                    <div class="container m-3">
                                                        <div class="d-block text-right">
                                                            <h6 class="ali">Elije lo que deseas consultar</h6>
                                                        </div>
                                                        <div class="form-check">
                                                            <input required class="form-check-input" type="radio" id="report-name-FAAP-choise1" name="show-obj-value" value="select-pieces">
                                                            <label class="form-check-label" for="report-name-FAAP-choise1">Ver piezas disponibles</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input required class="form-check-input" type="radio" id="report-name-FAAP-choise1" name="show-obj-value" value="select-furnitures">
                                                            <label class="form-check-label" for="report-name-FAAP-choise1">Ver muebles disponibles</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group d-block justify-content-end">
                                                        <button type="submit" class="btn btn-primary">Ver items</button>
                                                    </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            <jsp:include page="/Includes/Furnitures-list.jsp"></jsp:include>

                            </div>

                            <div class="col-lg-6">

                                <!-- Dropdown Card Example -->
                                <div class="card shadow mb-4">
                                    <!-- Card Header - Dropdown -->

                                    <div class="card shadow mb-4">
                                        <!-- Card Header - Dropdown -->
                                        <div
                                            class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                            <div>
                                                <h6 class="m-0 font-weight-bold text-primary">Registrar</h6>
                                                <p>Puedes seleccionar los tres puntos para cambiar de ventana</p>
                                            </div>

                                            <div class="dropdown no-arrow">
                                                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                     aria-labelledby="dropdownMenuLink">
                                                    <div class="dropdown-header">¿Qué deseas registar?</div>
                                                    <a class="dropdown-item" id="shw-usr-hde-frn-faap">Pieza</a>
                                                    <a class="dropdown-item" id="shw-frn-hde-usr-faap">Mueble Ensamblado</a>
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
                                                    <h6 class="row d-flex p-2 justify-content-center border-bottom-primary">Registar una nueva pieza</h6>
                                                    <div class="m-0 row justify-content-center">
                                                        <div class="col-auto align-content-around">
                                                            <form action="../InsertToDB" method="POST">
                                                                <input name="action-perf" type="text" value="insert-piece" hidden>
                                                                <div class="form-group">
                                                                    <label for="exampleInputName">Nombre</label>
                                                                    <select name="piece-name" class="form-control btn-block" id="examplePieceName">
                                                                    <%for (PieceByStock elem : listPieces) {
                                                                            String selected = elem.getName().equals(request.getParameter("name")) ? "selected" : "";
                                                                    %>
                                                                    <option <%=selected%> value="<%=elem.getName()%>"><%=elem.getName()%></option>
                                                                    <%}%>
                                                                </select>

                                                                <small>Elige un tipo de pieza existente</small>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="exampleInputPrice">Precio</label>
                                                                <input required name="piece-price" type="number" step=".01" class="form-control" id="exampleInputPrice" aria-describedby="priceHelp" placeholder="El precio que la pieza">
                                                                <small>El precio de fabricacion de la pieza</small>
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Registrar pieza</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- FURNITURE END -->
                                            <!-- PIECE START -->
                                            <div  class="d-none" id="insert-usr-FAAP">
                                                <!-- FUNNITURE START -->
                                                <div id="insert-frn-FAAP">
                                                    <h6 class="row d-flex p-2 justify-content-center border-bottom-primary">Registar nuevo mueble ensamblado</h6>
                                                    <div class="m-0 row justify-content-center">
                                                        <div class="col-auto align-content-around">
                                                            <form action="../InsertToDB" method="POST">
                                                                <input name="action-perf" type="text" value="insert-furn-assbm" hidden>
                                                                <div class="form-group">
                                                                    <label for="exampleInputName">Nombre</label>
                                                                    <select name="furniture-assemb" class="form-control btn-block" id="examplePieceName">
                                                                        <%for (Furniture elem : listFurnitures) {%>
                                                                        <option value="<%=elem.getName()%>"><%=elem.getName()%></option>
                                                                        <br>
                                                                        <%}%>
                                                                    </select>
                                                                    <small>Puedes revisar la lista de abajo para ver nombres</small>
                                                                </div>
                                                                <button type="submit" class="btn btn-primary">Ensamblar mueble</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <jsp:include page="/Includes/pieces-stock.jsp"></jsp:include>
                            </div>

                        </div>

                        <div class="row">


                            <!-- Collapsable Card Example -->

                        </div>
                        <!-- /.container-fluid -->

                    </div>
                    <!-- End of Main Content -->

                <jsp:include page="/Includes/footer.jsp"></jsp:include>

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>

        <jsp:include page="/Includes/scroll-top-logout-pop-up.jsp"></jsp:include>

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


        <!-- Page level plugins -->
        <script src="${pageContext.request.contextPath}/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="${pageContext.request.contextPath}/js/demo/datatables-demo.js"></script>

        <script src="${pageContext.request.contextPath}/js/MyOwnFunction.js"></script>

    </body>
</html>
