<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Domain.Bill"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte ventas</title>
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
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">

                    <jsp:include page="/Includes/top-bar.jsp"></jsp:include>
                        <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <h1 class="h3 mb-2 text-gray-800">Reporte generado</h1>
                            <p class="mb-4">Se muestra el reporte generado con la información de las ventas realizadas durante el periodo de <%=request.getParameter("startDate")%> hasta <%=request.getParameter("endDate")%></p>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <a href="DownloadReports/report-sells-dwnld.jsp?file-name=ventas&bills=${bills}" id="download-report">
                                    <h6 class="m-0 font-weight-bold text-primary">Ventas</h6>
                                </a>

                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" id="report-table-to-download">
                                        <thead>
                                            <tr>
                                                <td>Factura</td>
                                                <td>Id mueble</td>
                                                <td>Mueble</td>
                                                <td>Fecha venta</td>
                                                <td>Precio</td>
                                                <td>NIT</td>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <td>Factura</td>
                                                <td>Id mueble</td>
                                                <td>Mueble</td>
                                                <td>Fecha venta</td>
                                                <td>Precio</td>
                                                <td>NIT</td>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="i" items="${bills}">
                                                <tr>
                                                    <td>${i.code}</td>
                                                    <td>${i.furnitureAssemblyId}</td>
                                                    <td>${i.furnitureName}</td>
                                                    <td>${i.buyDate}</td>
                                                    <td>${i.ammount}</td>
                                                    <td>${i.clientNit}</td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- End of Main Content -->
                <jsp:include page="/Includes/footer.jsp"></jsp:include>
                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->
        <jsp:include page="/Includes/scroll-top-logout-pop-up.jsp"></jsp:include>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>
    </body>
</html>