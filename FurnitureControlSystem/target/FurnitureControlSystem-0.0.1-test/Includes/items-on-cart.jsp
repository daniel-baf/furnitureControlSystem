<%@page import="TransactionObjects.BillFurniture"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <h3>Items</h3>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th style="width: 60px">Id</th>
                <th style="width: 380px">Item</th>
                <th style="width: 140px">Precio</th>
                <th style="width: 90px">Accion</th>
            </tr>
        </thead>
        <tbody>
            <%
                double total = 0;
                try {
                    ArrayList<BillFurniture> cart = (ArrayList<BillFurniture>) session.getAttribute("buy-cart");
                    if (!cart.isEmpty()) {
                        for (BillFurniture furn : cart) {%>
            <tr class="item-on-cart">
                <td><%=furn.getId()%></td>
                <td><%=furn.getFurniture()%></td>
                <td><%=furn.getSellPrice()%></td>
                <td>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/Billing?action-perf=remove-from-cart&id=<%=furn.getId()%>">Quitar</a>
                </td>
            </tr>
            <%total += furn.getSellPrice();}}} catch (Exception e) {}%>

        </tbody>
        <tfoot>
            <tr>
                <th>Id</th>
                <th>Item</th>
                <th>Precio</th>
            </tr>
            <tr>
                <td></td>
                <td>Total</td>
                <td><%=total%></td>
            </tr>
        </tfoot>
    </table>
</div>