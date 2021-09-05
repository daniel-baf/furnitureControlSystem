<%@page import="TransactionObjects.BillFurniture"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factura</title>
        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
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
                            <div>
                                <h1 class="h3 mb-4 text-gray-800">Factura</h1>
                            </div>
                        </div>
                        <!-- /.container-fluid -->
                        <div class="container-md">
                            <div class="row justify-content-around">
                                <div class="col-4">
                                    <div>
                                        <p>No. factura automatico</p>
                                    </div>
                                    <div>
                                        <div class="row justify-content-around mb-4">
                                            <form method="GET" action="${pageContext.request.contextPath}/Billing">
                                            <input type="text" value="add-item-to-cart" name="action-perf" hidden="hidden">
                                            <div class="form-group">
                                                <label for="code-item">Codigo</label>
                                                <input required="required" class="form-control" type="number" name="code-item" id="code-item">
                                            </div>
                                            <input id="desocultar" type="submit" value="Agregar" class="btn btn-secondary btn-block mt-3"">
                                        </form>
                                    </div>
                                    <div class="row justify-content-around mb-4">
                                        <form action="${pageContext.request.contextPath}/CheckClientAndRedirect" method="POST">
                                            <div class="form-group">
                                                <label>NIT</label>
                                                <input required="required" class="form-control" type="text" name="client-nit">
                                            </div>
                                            <input id="item-ocultar" type="submit" value="Procesar factura" class="btn btn-primary">
                                        </form>
                                    </div>
                                </div>    
                            </div>
                            <jsp:include page="/Includes/items-on-cart.jsp"></jsp:include>
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
            <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/test.js"></script>
    </body>
</html>
