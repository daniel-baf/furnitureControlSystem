<%@page import="Domain.FurniturePieceCuantity"%>
<%@page import="Domain.FurniturePiece"%>
<%@page import="Database.FurniturePieceDAO"%>
<%@page import="TransactionObjects.PieceByStock"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ArrayList<PieceByStock> pieces = (ArrayList<PieceByStock>) new FurniturePieceDAO().selectPieceAndStock(null, false);
    ArrayList<FurniturePieceCuantity> picesRecet = (ArrayList<FurniturePieceCuantity>) session.getAttribute("furn-recet");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo mueble</title>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/MyOwnCss.css">

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
                            <h1 class="h3 mb-4 text-gray-800">Elige las piezas</h1>
                            <div class="container-lg">
                                <div class="row">
                                    <div class="col col-4">
                                        <!-- COLOCAR EL NOMBRE DEL MUEBLE -->
                                        <h4>Elige las piezas que usa</h4>
                                        <h5> <%=request.getAttribute("furnName")%> Q<%=request.getParameter("furnPrice")%></h5>
                                    <div class="card-body">
                                        <p>Puedes hacer click en los titulos para ordenar de mayor a menor o bice versa</p>
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <td>Pieza</td>
                                                    <td style="width: 30px">Cn</td>
                                                    <td style="width: 80px">Accion</td>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <td>Pieza</td>
                                                    <td>Cn</td>
                                                    <td>Accion</td>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <%for (FurniturePieceCuantity fpc : picesRecet) {%>
                                                <tr>
                                                    <td><%=fpc.getFurnPiece().getName()%></td>
                                                    <td><%=fpc.getCuantity()%></td>
                                                    <td>
                                                        <form action="${pageContext.request.contextPath}/AddFurnAndCuantity" method="POST">
                                                            <input type="text" hidden="hidden" value="remove-item-to-recet" name="action-perf">
                                                            <input type="text" hidden="hidden" value="<%=fpc.getFurnPiece().getName()%>" name="piece-name">
                                                            <input type="text" hidden="hidden" value="<%=request.getParameter("furnPrice")%>" name="furnPrice">
                                                            <input type="text" hidden="hidden" value="<%=request.getParameter("furnName")%>" name="furnName">
                                                            <input type="submit" value="QUITAR" class="btn btn-dark">
                                                        </form>
                                                    </td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                    </div>

                                    <form action="${pageContext.request.contextPath}/CreateNewFurniture" method="POST">
                                        <input type="text" hidden="hidden" value="<%=request.getParameter("furnPrice")%>" name="furnPrice">
                                        <input type="text" hidden="hidden" value="<%=request.getParameter("furnName")%>" name="furnName">
                                        <input type="submit" value="Confirmar nuevo mueble" class="btn btn-primary">
                                    </form>

                                </div>
                                <div class="col col-8">
                                    <!--  INCLUIR TABLA PARA LOS UTEMS QUE USA -->
                                    <div>
                                        <div class="card shadow mb-4" id="inventory-pieces-stock-div">
                                            <!-- Card Header - Accordion -->
                                            <a href="#collapseInventoryPieces" class="d-block card-header py-3" data-toggle="collapse"
                                               role="button" aria-expanded="true" aria-controls="collapseCardExample">
                                                <h6 id="table-stock-pieces"class="m-0 font-weight-bold text-primary">Inventario Piezas</h6>
                                            </a>
                                            <!-- Card Content - Collapse -->
                                            <div class="collapse show" id="collapseInventoryPieces">
                                                <div class="card-body">
                                                    <p>Puedes hacer click en los titulos para ordenar de mayor a menor o bice versa</p>
                                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                        <thead>
                                                            <tr>
                                                                <td>Pieza</td>
                                                                <td>Strock</td>
                                                                <td style="width: 80px">Accion</td>
                                                            </tr>
                                                        </thead>
                                                        <tfoot>
                                                            <tr>
                                                                <td>Pieza</td>
                                                                <td>Cantidad</td>
                                                                <td>Accion</td>
                                                            </tr>
                                                        </tfoot>
                                                        <tbody>
                                                            <%for (PieceByStock piece : pieces) {
                                                                    String lowStock = piece.getStock() <= 10 ? "border-bottom-danger" : "";
                                                            %>
                                                            <tr class="<%=lowStock%>">
                                                                <td><%=piece.getName()%></td>
                                                                <td style="width: 70px"><%=piece.getStock()%></td>
                                                                <td style="width: 100px">
                                                                    <form action="${pageContext.request.contextPath}/AddFurnAndCuantity" method="POST">
                                                                        <input type="text" hidden="hidden" value="add-item-to-recet" name="action-perf">
                                                                        <input type="text" hidden="hidden" value="<%=piece.getName()%>" name="piece-name">
                                                                        <input type="text" hidden="hidden" value="<%=request.getParameter("furnPrice")%>" name="furnPrice">
                                                                        <input type="text" hidden="hidden" value="<%=request.getParameter("furnName")%>" name="furnName">
                                                                        <input type="number" placeholder="cantidad" class="form-control" name="cuantity" required="required">
                                                                        <input type="submit" value="AGREGAR" class="btn btn-dark">
                                                                    </form>
                                                                </td>
                                                            </tr>
                                                            <%}%>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
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
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="js/sb-admin-2.min.js"></script>
    </body>
</html>
