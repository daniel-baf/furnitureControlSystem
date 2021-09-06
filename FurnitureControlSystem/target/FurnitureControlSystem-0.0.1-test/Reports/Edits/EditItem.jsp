<%@page import="TransactionObjects.PieceByStock"%>
<%@page import="Database.FurniturePieceDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String type = request.getParameter("type");%>
<%ArrayList<PieceByStock> listPieces = new FurniturePieceDAO().selectPieceAndStock(null, false);%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body class="bg-gradient-primary">

        <div class="container">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                        <div class="col-lg-7">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Tablero de edicion</h1>
                                </div>


                                <form class="user" action="${pageContext.request.contextPath}/UpdateOnDB" method="POST">
                                    <input type="hidden" value="<%=type%>" name="update-action">
                                    <%if (type.equals("edit-piece")) {%>
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <label for="pieceId">ID pieza</label>
                                            <input required type="text" class="form-control form-control-user" id="pieceId"
                                                   placeholder="ID de pieza" name="piece-id" value="<%=request.getParameter("id")%>" readonly="readonly">
                                            <small>Este ID generado por el sistema,no modificable</small>
                                        </div>
                                        <div class="col-sm-6">
                                            <label for="examplePieceName">Nombre pieza</label>
                                            <select name="piece-name" class="form-control btn-block" id="select-piece-name">
                                                <%for (PieceByStock elem : listPieces) {
                                                        String selected = elem.getName().equals(request.getParameter("name")) ? "selected" : "";
                                                %>
                                                <option <%=selected%> value="<%=elem.getName()%>"><%=elem.getName()%></option>
                                                <%}%>
                                            </select>
                                            <small>Un nombre de pieza de la lista de piezas</small>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleImputPrice">Precio de la pieza</label>
                                        <input type="number" step=".01" min="0" required class="form-control form-control-user" id="exampleImputPrice"
                                               placeholder="Precio de la pieza" name="piece-price" value="<%=request.getParameter("cost")%>">
                                        <small>El precio que costó ensamblar la pieza</small>
                                    </div>
                                    <%}%>

                                    <input type="submit" value="Aplicar modificaciones" class="btn btn-primary btn-user btn-block">
                                    <hr>
                                    <a href="#" class="btn btn-google btn-user btn-block" onclick="history.go(-1)">
                                        <i class="fab fa-fw"></i> Cancelar Edicion
                                    </a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <jsp:include page="/Includes/pieces-stock.jsp"></jsp:include>
            </div>

        <jsp:include page="/Includes/footer.jsp"></jsp:include>

            <!-- Bootstrap core JavaScript-->
            <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="${pageContext.request.contextPath}/js/demo/chart-area-demo.js"></script>
        <script src="${pageContext.request.contextPath}/js/demo/chart-pie-demo.js"></script>


        <!-- Page level plugins -->
        <script src="${pageContext.request.contextPath}/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="${pageContext.request.contextPath}/js/demo/datatables-demo.js"></script>
        <script src="${pageContext.request.contextPath}/js/demo/datatables-demo.js"></script>

        <script src="${pageContext.request.contextPath}/js/test.js""></script>

    </body>
</html>
