<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="TransactionObjects.InsertObjectStatus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="userArea" scope="session" value="${area}"></c:set>
    <!DOCTYPE html>
<%
    ArrayList<InsertObjectStatus> insertOK = (ArrayList<InsertObjectStatus>) request.getAttribute("insertOK");
    ArrayList<InsertObjectStatus> insertNoOK = (ArrayList<InsertObjectStatus>) request.getAttribute("insertNoOK");
%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Resultados carga</title>
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
                        <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <h1 class="h3 mb-4 text-gray-800">Resultados carga de datos</h1>
                            <p class="mb-4">A continuacion se muestran los datos que se insertaron correctamente. Puedes ocultar la tabla presionando la flecha<</p>

                            <div class="card shadow mb-4">

                                <!-- Card Header - Accordion -->
                                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse"
                                   role="button" aria-expanded="true" aria-controls="collapseCardExample">
                                    <h6 class="m-0 font-weight-bold text-primary">No se lograron insertar: <%=insertNoOK.size()%></h6>
                            </a>
                            <div class="collapse show" id="collapseCardExample">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>No. Linea</th>
                                                    <th>Valor leido</th>
                                                    <th>Estado</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>No. Linea</th>
                                                    <th>Valor leido</th>
                                                    <th>Estado</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <c:forEach var="i" items="${insertNoOK}">
                                                    <tr>
                                                        <td>${i.lineError}</td>
                                                        <td>${i.lineRead}</td>
                                                        <td>${i.status}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card shadow mb-4">

                            <!-- Card Header - Accordion -->
                            <a href="#collapseCardExample1" class="d-block card-header py-3" data-toggle="collapse"
                               role="button" aria-expanded="true" aria-controls="collapseCardExample">
                                <h6 class="m-0 font-weight-bold text-primary">Insertados correctamente: <%=insertOK.size()%></h6>
                            </a>
                            <div class="collapse show" id="collapseCardExample1">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable1" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>No. Linea</th>
                                                    <th>Valor leido</th>
                                                    <th>Estado</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>No. Linea</th>
                                                    <th>Valor leido</th>
                                                    <th>Estado</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <c:forEach var="i" items="${insertOK}">
                                                    <tr>
                                                        <td>${i.lineError}</td>
                                                        <td>${i.lineRead}</td>
                                                        <td>${i.status}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
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