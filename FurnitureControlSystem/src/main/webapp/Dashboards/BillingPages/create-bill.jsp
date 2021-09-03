<%@page import="java.util.ArrayList"%>
<%@page import="Domain.FurnitureAssembly"%>
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
                                        <form action="${pageContext.request.contextPath}/ProcesBilling" method="POST">
                                            <div class="form-group">
                                                <label>NIT</label>
                                                <input required="required" class="form-control" type="text" name="client-nit">
                                            </div>
                                            <input id="item-ocultar" type="submit" value="Procesar factura" class="btn btn-primary">
                                        </form>
                                    </div>
                                </div>    
                            </div>
                            <div class="col-8">
                                <h3>Items</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th style="width: 60px">Id</th>
                                            <th style="width: 380px">Item</th>
                                            <th style="width: 140px">Precio</th>
                                            <th style="width: 80px">Accion</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Id</th>
                                            <th>Item</th>
                                            <th>Precio</th>
                                            <th>Accion</th>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>Total</td>
                                            <td>Q100.00</td>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <%
                                            try {
                                                ArrayList<FurnitureAssembly> cart = (ArrayList<FurnitureAssembly>) session.getAttribute("buy-cart");
                                                if (!cart.isEmpty()) {
                                                    for (FurnitureAssembly furnitureAssembly : cart) {%>
                                        <tr>
                                            <td><%=furnitureAssembly.getId()%></td>
                                            <td><%=furnitureAssembly.getFurnitureName()%></td>
                                            <td>precio</td>
                                            <td>
                                                <a class="btn btn-danger" href="${pageContext.request.contextPath}/Billing?action-perf=remove-from-cart&id=<%=furnitureAssembly.getId()%>">Quitar</a>
                                            </td>
                                        </tr>
                                        <%}
                                                }
                                            } catch (Exception e) {
                                            }%>

                                    </tbody>
                                </table>
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
            <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/test.js"></script>
    </body>
</html>
