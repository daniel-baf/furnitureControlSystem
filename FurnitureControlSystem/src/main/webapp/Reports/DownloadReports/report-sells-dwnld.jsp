<%@page import="java.util.ArrayList, Domain.Bill"%>
<%@page contentType="application/vnd.ms-excel" %>
<%
    String filename = (String) request.getAttribute("report-title");
    ArrayList<Bill> bills = (ArrayList<Bill>) request.getAttribute("bills");
    response.setHeader("Content-Disposition", "attachemte;filename" + filename);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Descarga reporte</title>
    </head>
    <body>
        <h1><%=request.getParameter("report-title")%></h1>
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" id="report-table-to-download">
            <thead>
                <tr>
                    <td>Factura</td>
                    <td>Id mueble</td>
                    <td>Mueble</td>
                    <td>Fecha venta</td>
                    <td>Precio</td>
                    <td>NIT</td>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <td>Factura</td>
                    <td>Id mueble</td>
                    <td>Mueble</td>
                    <td>Fecha venta</td>
                    <td>Precio</td>
                    <td>NIT</td>
                </tr>
            </tfoot>
            <tbody>
                <tr>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                </tr>
                <tr>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                </tr>
                <tr>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                    <td>a</td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
