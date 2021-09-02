<%@page import="Domain.FurniturePiece"%>
<%@page import="Domain.FurniturePiece"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="type" scope="session" value="<%request.getAttibute('type')%>"></c:set>
<c:set var="titles" scope="session" value="<%request.getAttibute('titles')%>"></c:set>
<c:set var="title" scope="session" value="<%request.getAttibute('title')%>"></c:set>

    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>${title}</title>
        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
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

        <script src="js/TableCSVExporter.js"></script>

        <script>
            let dataTable = document.getElementById("dataTable");

            new TableCSVExporter(dataTable, true).convertToCSV();

            $("#export-CSV").on("click", function () {
                const exporter = new TableCSVExporter(dataTable);
                const csvOutput = exporter.convertToCSV();
                const csvBlob = new Blob([csvOutput], {type: "text/csv"});
                const blobUrl = URL.createObjectURL(csvBlob);
                const anchorElement = document.createElement("a");

                anchorElement.href = blobUrl;
                anchorElement.download = "reporte-exportar.csv";
                anchorElement.click();

                setTimeout(() => {
                    URL.revokeObjectURL(blobUrl);
                }, 500);
            });
        </script>
    </body>
</html>
