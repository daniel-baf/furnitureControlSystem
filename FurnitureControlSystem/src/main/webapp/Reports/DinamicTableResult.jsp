<%@page import="Domain.Earning"%>
<%@page import="Domain.Bill"%>
<%@page import="Domain.Refund"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String reportType = (String) request.getAttribute("report-type-FAAP");
    String tableTitle = (String) request.getAttribute("tableTitle");
    String reportMsg = (String) request.getAttribute("reportMsg");
    reportMsg = reportMsg == null ? " indefinido" : reportMsg;
    String[] thTitles = (String[]) request.getAttribute("thTitles");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reportes</title>
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
                            <p class="mb-4">Se muestra el reporte generado con la información de ${reportMsg}</p>

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">${tableTitle}</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <c:forEach var="ir" items="${thTitles}">
                                                    <th>${ir}</th>
                                                    </c:forEach>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <c:forEach var="ir" items="${thTitles}">
                                                    <th>${ir}</th>
                                                    </c:forEach>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:set var="ir1" scope="request" value="${reportType}"></c:set>
                                            <c:if test="${ir1 == 'sells'}">
                                                <%ArrayList<Bill> bills = (ArrayList<Bill>) request.getAttribute("list");
                                                    for (Bill b : bills) {%>
                                                <tr>
                                                    <%=b%>
                                                    <td><%=b.getCode()%></td>
                                                    <td><%=b.getFurnitureAssemblyId()%></td>
                                                    <td><%=b.getFurnitureName()%></td>
                                                    <td><%=b.getBuyDate()%></td>
                                                    <td><%=b.getAmmount()%></td>
                                                    <td><%=b.getClientNit()%></td>
                                                </tr>
                                                <%}%>
                                            </c:if>
                                            <c:if test="${ir1 == 'refunds'}">
                                                <%ArrayList<Refund> refunds = (ArrayList<Refund>) request.getAttribute("list");
                                                    for (Refund r : refunds) {%>
                                                <tr>
                                                    <td><%=r.getBillID()%></td>
                                                    <td><%=r.getFurnitureName()%></td>
                                                    <td><%=r.getRefundDate()%></td>
                                                    <td><%=r.getBuyDate()%></td>
                                                    <td><%=r.getRefund()%></td>
                                                    <td><%=r.getClientNit()%></td>
                                                    <td><%=r.getPriceAssembly()%></td>
                                                    <td><%=r.getMoneyLost()%></td>
                                                </tr>
                                                <%}%>
                                            </c:if>
                                            <c:if test="${ir1 == 'earnings'}">
                                                <%ArrayList<Earning> earnings = (ArrayList<Earning>) request.getAttribute("list");
                                                    int i = 0;
                                                    for (Earning e : earnings) {
                                                %>
                                                <tr class="${trClass}">
                                                    <td><%=e.getBillId()%></td>
                                                    <td><%=e.getItem()%></td>
                                                    <td><%=e.getSellAmmount()%></td>
                                                    <td><%=e.getSellDate()%></td>
                                                    <td><%=e.getEarning()%></td>
                                                    <td><%=e.getFurnitureState()%></td>
                                                </tr>
                                                <%}%>
                                            </c:if>
                                            <c:if test="${ir1 == 'most-profits-user'}">
                                                most-profits-user
                                                <%ArrayList<Bill> bills2 = (ArrayList<Bill>) request.getAttribute("try");
                                                    for (Bill b : bills2) {%>
                                                <tr>
                                                    <td><%=b.getCode()%></td>
                                                    <td><%=b.getFurnitureAssemblyId()%></td>
                                                    <td><%=b.getFurnitureName()%></td>
                                                    <td><%=b.getBuyDate()%></td>
                                                    <td><%=b.getAmmount()%></td>
                                                    <td><%=b.getClientNit()%></td>
                                                </tr>
                                                <%}%>
                                                <c:forEach var="it" items="${bills2}">
                                                <td>${it.code}</td>
                                                <td>${it.furnitureAssemblyId}</td>
                                                <td>${it.furnitureName}</td>
                                                <td>${it.buyDate}</td>
                                                <td>${it.ammount}</td>
                                                <td>${t.clientNit}</td>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${ir1 == 'most-sold-furniture'}">
                                            most-sold-furniture
                                        </c:if>
                                        <c:if test="${ir1 == 'less-sold-furniture'}">
                                            less-sold-furniture
                                        </c:if>
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
