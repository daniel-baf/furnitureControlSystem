<%@page import="TransactionObjects.PieceByStock"%>
<%@page import="Domain.FurniturePiece"%>
<%@page import="Database.FurniturePieceDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ArrayList<PieceByStock> pieces = (ArrayList<PieceByStock>) new FurniturePieceDAO().selectPieceAndStock(null, false);%>
<!-- Collapsable Card Example -->
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
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td>Pieza</td>
                        <td>Stock</td>
                    </tr>
                </tfoot>
                <tbody>
                    <%for (PieceByStock piece : pieces) {
                            String lowStock = piece.getStock() <= 10 ? "border-bottom-danger" : "";
                    %>
                    <tr class="<%=lowStock%>">
                        <td><%=piece.getName()%></td>
                        <td style="width: 70px"><%=piece.getStock()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>