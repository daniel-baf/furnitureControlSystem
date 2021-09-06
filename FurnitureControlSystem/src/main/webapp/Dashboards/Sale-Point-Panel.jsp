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
            <jsp:include page="/Includes/Sellpoint/aside-sellpoint.jsp"></jsp:include>
                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">
                    <!-- Main Content -->
                    <div id="content">
                    <jsp:include page="/Includes/top-bar.jsp"></jsp:include>
                        <!-- Begin Page Content -->
                        <div class="container-fluid">
                            <!-- Page Heading -->
                            <h1 class="h3 mb-4 text-gray-800">Dashboard: Punto ventas</h1>

                            <!-- Content Row -->
                            <div class="row">
                                <!-- Earnings (Monthly) Card Example -->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Ganancias (Dia)
                                                    </div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                        $40,000
                                                    </div>
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
                                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Ganancias (Mes)</div>
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
                                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Muy pronto...</div>
                                                    <div class="row no-gutters align-items-center">
                                                        <div class="col-auto">
                                                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="progress progress-sm mr-2">
                                                                <div class="progress-bar bg-info" role="progressbar"
                                                                     style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                                     aria-valuemax="100">
                                                                </div>
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
                                                    <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Piezas con stock bajo</div>
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
                                <div class="col col-lg-5">
                                    <div class="card shadow mb-4">
                                        <!-- Card Header - Dropdown -->
                                        <div
                                            class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                            <div>
                                                <h6 class="m-0 font-weight-bold text-primary">Reportes</h6>
                                            </div>

                                            <div class="dropdown no-arrow">
                                                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                                </a>
                                                <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                                     aria-labelledby="dropdownMenuLink">
                                                    <div class="dropdown-header">Elige una opcion</div>
                                                    <a class="dropdown-item" id="shw-usr-hde-frn-faap">Disponibilidad</a>
                                                    <a class="dropdown-item" id="shw-frn-hde-usr-faap">Piezas</a>
                                                    <div class="dropdown-divider"></div>
                                                    <a class="dropdown-item" id="toggle-result-faap">Limpiar</a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Card Body -->
                                        <div class="card-body">
                                            <form action="../ShowReport" method="GET" class="border-bottom-secondary">
                                                <div>
                                                    <input name="action-perf" type="text" value="get-report" hidden>
                                                    <div>
                                                        <div class="form-group">
                                                            <label for="exampleInputDate">Fecha inicial</label>
                                                            <input name="dateStart" id="exampleInputDate1" type="date" class="form-control" aria-describedby="emailHelp" placeholder="Un nombre de usuario">
                                                            <small>Selecciona la fecha de inicio para el reporte, dejalo vacio para que el reporte sea de toda la vida</small>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="exampleInputDate1">Fecha final</label>
                                                            <input name="dateEnd" id="exampleInputDate" type="date" class="form-control" aria-describedby="emailHelp" placeholder="Un nombre de usuario">
                                                            <small>Selecciona la fecha donde quieres que el repotre termine, dejalo vacio para que el reporte sea de toda la vida</small>
                                                        </div>
                                                    </div>
                                                    <div id="info-for-report" class="form-group">
                                                        <label>NIT cliente</label>
                                                        <input class="form-control"  required="required" type="text" placeholder="El NIT del cliente" name="client-nit">
                                                        <small>Solicitale al cliente el NIT</small>
                                                    </div>
                                                    <div class="container m-3">
                                                        <div class="d-block text-right">
                                                            <h6 class="ali">Selecciona un tipo de reporte</h6>
                                                        </div>
                                                        <div class="form-check">
                                                            <input required class="form-check-input" type="radio" id="report-name-FAAP-choise2" name="report-type-FAAP" value="refunds-client">
                                                            <label class="form-check-label" for="report-name-FAAP-choise2">Devoluciones por cliente</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input required class="form-check-input" type="radio" id="report-name-FAAP-choise3" name="report-type-FAAP" value="show-buys-client-info">
                                                            <label class="form-check-label" for="report-name-FAAP-choise3">Facturas de un cliente</label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group d-block justify-content-end">
                                                        <button type="submit" class="btn btn-primary">Crear reporte</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                                <!-- Pie Chart -->
                                <div class="col-xl-4 col-lg-5">
                                    <div class="card shadow mb-4">
                                        <!-- Card Header - Dropdown -->
                                        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between" id="delete-user-form">
                                            <h6 class="m-0 font-weight-bold text-primary">Registrar devolucion</h6>
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
                                                        <form action="../InsertToDB" method="POST">
                                                            <div class="form-group">
                                                                <input name="action-perf" type="text" value="regist-refund" hidden>
                                                                <label for="exampleInputName">ID factura</label>
                                                                <input name="bill-id" class="form-control btn-block" id="examplePieceName">
                                                                </select>
                                                                <small>La facutra debe existir</small>
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Borrar</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-4 text-center small">
                                                <span class="mr-2">
                                                    <i class="fas fa-circle text-primary"></i> Si el mueble fue comprado hace mas de 7 dias, no se haran devoluciones
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Content Row -->
                            <div class="row">
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
                            </div>
                        </div>

                        <div class="row">
                            <div class="col col-lg-6">
                                <jsp:include page="/Includes/Furnitures-list.jsp"></jsp:include>
                                </div>
                                <div class="col col-lg-6">
                                <jsp:include page="/Includes/pieces-stock.jsp"></jsp:include>
                                </div>
                            </div>
                            <!-- /.container-fluid -->
                        </div>
                        <!-- /.container-fluid -->
                    </div>
                    <!-- End of Main Content -->
                <jsp:include page="/Includes/footer.jsp"></jsp:include>
                </div>
                <!-- End of Content Wrapper -->
            </div>
            <!-- End of Page Wrapper -->
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
        <!-- own config-->
        <script src="${pageContext.request.contextPath}/js/MyOwnFunction.js"></script>

    </body>
</html>
