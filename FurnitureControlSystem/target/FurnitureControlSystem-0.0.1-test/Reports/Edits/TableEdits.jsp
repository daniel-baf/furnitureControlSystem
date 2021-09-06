<%@page import="Domain.FurniturePiece"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="session" value="<%request.getAttibute('title')%>"></c:set>
<% String area = (String) session.getAttribute("area");%>
<c:set var="areat" scope="session" value="${area}"></c:set>

    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>${title}</title>
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
            <!-- Sidebar -->
            <c:if test="${areat == '1'}">
                <jsp:include page="/Includes/Factory/aside-factory.jsp"></jsp:include>
            </c:if>
            <c:if test="${areat == '2'}">
                <jsp:include page="/Includes/Sellpoint/aside-sellpoint.jsp"></jsp:include>
            </c:if>
            <c:if test="${areat == '3'}">
                <jsp:include page="/Includes/Financial/financial-sidebar.jsp"></jsp:include>
            </c:if>
            <!-- End of Sidebar -->
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">
                    <jsp:include page="/Includes/top-bar.jsp"></jsp:include>
                        <!-- Begin Page Content -->
                        <div class="container-fluid">
                            <!-- Page Heading -->
                            <h1>${title}</h1>
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Resumen</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <c:forEach var="i" items="${titles}"><th>${i}</th></c:forEach>
                                                    <th>Acciones</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                <c:forEach var="i" items="${titles}"><th>${i}</th></c:forEach>
                                                    <th>Acciones</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                            <c:if test="${type == 'furniture'}">
                                                <c:forEach var="furn" items="${furnitures}">
                                                    <tr>
                                                        <td>${furn.id}</td>
                                                        <td>${furn.furnitureName}</td>
                                                        <td>${furn.assemblyPrice}</td>
                                                        <td>${furn.username}</td>
                                                        <td>${furn.sold}</td>
                                                        <td style="width: 00px">
                                                            <a href="${pageContext.request.contextPath}/DeleteFromDB?action-perf=delete-furn-assemb&id=${furn.id}" class="btn btn-danger">Borrar</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${type == 'pieces'}">
                                                <c:forEach var="piece" items="${pieces}">
                                                    <tr>
                                                        <td>${piece.id}</td>
                                                        <td>${piece.name}</td>
                                                        <td>${piece.cost}</td>
                                                        <td style="width: 170px">
                                                            <a href="${pageContext.request.contextPath}/Reports/Edits/EditItem.jsp?type=edit-piece&id=${piece.id}&name=${piece.name}&cost=${piece.cost}" class="btn btn-primary">Editar</a>
                                                            <a href="${pageContext.request.contextPath}/DeleteFromDB?action-perf=delete-furn-piece&id=${piece.id}" class="btn btn-danger">Borrar</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>
                                <input id="export-CSV" type="button" value="Descargar reporte en formato compatible con excel(CSV)" class="btn btn-block btn-primary">
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
