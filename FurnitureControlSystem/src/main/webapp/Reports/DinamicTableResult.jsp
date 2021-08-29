<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<Object> list;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getParameter("title")%></title>
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
                        <c:set var="report"></c:set>

                            <h1 class="h3 mb-4 text-gray-800"><%=request.getParameter("action-perf")%></h1>

                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <jsp:include page="../Includes/footer.jsp"></jsp:include>

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

        <jsp:include page="../Includes/scroll-top-logout-pop-up.jsp"></jsp:include>

        <!-- Bootstrap core JavaScript-->
        <script src="../Resources/vendor/jquery/jquery.min.js"></script>
        <script src="../Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="../Resources/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="../Resources/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="../Resources/vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="../Resources/js/demo/chart-area-demo.js"></script>
        <script src="../Resources/js/demo/chart-pie-demo.js"></script>
        <!-- own scripts -->
        <script src="../Resources/js/MyOwnFunctions.js"></script>

    </body>
</html>
