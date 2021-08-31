<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Domain.Refund"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    String description = (String) request.getAttribute("rep-desc");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facturas</title>
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
                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Reporte generado</h1>
                        <%=description%> <a id="download-table-csv"href="#">descargar CSV</a>.

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
                                                <th>Devuelto</th>
                                                <th>Comprado</th>
                                                <th>Reembolso</th>
                                                <th>NIT</th>
                                                <th>Perdida</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Factura</th>
                                                <th>Item</th>
                                                <th>Devuelto</th>
                                                <th>Comprado</th>
                                                <th>Reembolso</th>
                                                <th>NIT</th>
                                                <th>Perdida</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="refund" items="${refunds}">
                                                <tr>
                                                    <td>${refund.billID}</td>
                                                    <td>${refund.furnitureName}</td>
                                                    <td>${refund.refundDate}</td>
                                                    <td>${refund.buyDate}</td>
                                                    <td>${refund.refund}</td>
                                                    <td>${refund.clientNit}</td>
                                                    <td>${refund.moneyLost}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->
            </div>
            <!-- End of Content Wrapper -->
        </div>

        <script src="../js/MyOwnFunction.js"></script>

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
