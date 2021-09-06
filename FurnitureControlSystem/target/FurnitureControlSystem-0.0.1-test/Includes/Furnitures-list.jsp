<%@page import="Database.FurnitureDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Domain.Furniture"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ArrayList<Furniture> furnitures = (ArrayList<Furniture>) new FurnitureDAO().selectFurnitures();%>
<!-- Collapsable Card Example -->
<div class="card shadow mb-4" id="inventory-furniture-stock-div">
    <!-- Card Header - Accordion -->
    <a href="#collapseInventoryFurniture" class="d-block card-header py-3" data-toggle="collapse"
       role="button" aria-expanded="true" aria-controls="collapseCardExample">
        <h6 id="table-stock-furniture"class="m-0 font-weight-bold text-primary">Inventario muebles</h6>
    </a>
    <!-- Card Content - Collapse -->
    <div class="collapse show" id="collapseInventoryFurniture">
        <div class="card-body">
            <p>Puedes hacer click en los titulos para ordenar de mayor a menor o bice versa</p>
            <table class="table table-bordered" id="dataTable1" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <td>Mueble</td>
                        <td>Precio</td>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td>Mueble</td>
                        <td>Precio</td>
                    </tr>
                </tfoot>
                <tbody>
                    <%for (Furniture furn : furnitures) {%>
                    <tr>
                        <td><%=furn.getName()%></td>
                        <td style="width: 70px"><%=furn.getSellPrice()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>