<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Domain.Earning"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    String description = (String) request.getAttribute("rep-desc");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ganancias</title>
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">
            <jsp:include page="/Includes/Financial/financial-sidebar.jsp"></jsp:include>
                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">
                    <!-- Main Content -->
                    <div id="content">
                    <jsp:include page="/Includes/top-bar.jsp"></jsp:include>
                        <!-- Main Content -->
                        <div id="content">
                            <!-- Begin Page Content -->
                            <div class="container-fluid">

                                <!-- Page Heading -->
                                <h1 class="h3 mb-2 text-gray-800">Reporte generado</h1>
                            <%=description%>

                            <!-- DataTales Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Devoluciones</h6>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Factura</th>
                                                    <th>Item</th>
                                                    <th>Trabajador</th>
                                                    <th>Ingreso</th>
                                                    <th>Fecha</th>
                                                    <th>Ganancia</th>
                                                    <th>Estado</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Factura</th>
                                                    <th>Item</th>
                                                    <th>Trabajador</th>
                                                    <th>Ingreso</th>
                                                    <th>Fecha</th>
                                                    <th>Ganancia</th>
                                                    <th>Estado</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <c:forEach var="earn" items="${earnings}">
                                                    <c:set var="tmp" value="${tmp + earn.sellAmmount}"></c:set>
                                                    <c:set var="tmp1" value="${tmp1 + earn.earning}"></c:set>
                                                        <tr>
                                                            <td>${earn.billId}</td>
                                                        <td>${earn.item}</td>
                                                        <td>${earn.worker}</td>
                                                        <td>${earn.sellAmmount}</td>
                                                        <td>${earn.sellDate}</td>
                                                        <td>${earn.earning}</td>
                                                        <td style="width: 90px">
                                                            <c:if test="${earn.furnitureState == '0'}">
                                                                Disponible
                                                            </c:if>
                                                            <c:if test="${earn.furnitureState == '1'}">
                                                                Vendido
                                                            </c:if>
                                                            <c:if test="${earn.furnitureState == '2'}">
                                                                Devuelto
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <input id="export-CSV" type="button" value="Descargar reporte en formato compatible con excel(CSV)" class="btn btn-block btn-primary">\
                                </div>
                            </div>
                            <div class="row">
                                <!-- Earnings (Monthly) Card Example -->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Ingresos por venta <br><%=request.getParameter("dateStart")%> - <%=request.getParameter("dateEnd")%></div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">Q${tmp}</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Earnings (Annual) Card Example -->
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-success shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                        Ganancias<br> <%=request.getParameter("dateStart")%> - <%=request.getParameter("dateEnd")%></div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">Q${tmp1}</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
        <script src="${pageContext.request.contextPath}/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Page level custom scripts -->
        <script src="${pageContext.request.contextPath}/js/demo/datatables-demo.js"></script>
        <script src="${pageContext.request.contextPath}/js/TableCSVExporter.js"></script>
        <script src="${pageContext.request.contextPath}/js/TableDownload.js"></script>
    </body>
</html>