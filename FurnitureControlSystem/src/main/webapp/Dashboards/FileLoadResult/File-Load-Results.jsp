<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="TransactionObjects.InsertObjectStatus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList<InsertObjectStatus> insertOK = (ArrayList<InsertObjectStatus>) request.getAttribute("insertOK");
    ArrayList<InsertObjectStatus> insertNoOK = (ArrayList<InsertObjectStatus>) request.getAttribute("insertNoOK");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados carga</title>
    </head>
    <body>
        <h1>RESULTADOS CARGA DATOS</h1>
        <fieldset>
            <h3>Incorrectos: <%=insertNoOK.size()%></h3>

            <table>
                <thead>
                    <tr>
                        <th>
                            -- Linea --
                        </th>
                        <th>
                            -- Linea leida --
                        </th>
                        <th>
                            -- Error --
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" items="${insertNoOK}">
                        <tr>
                            <td>${i.lineError}</td>
                            <td>${i.lineRead}</td>
                            <td>${i.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </fieldset>
        <fieldset>
            <h3>Correctos: <%=insertOK.size()%></h3>
            <table>
                <thead>
                    <tr>
                        <th>
                            -- Linea --
                        </th>
                        <th>
                            -- Linea leida --
                        </th>
                        <th>
                            -- Estado --
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" items="${insertOK}">
                        <tr>
                            <td>${i.lineError}</td>
                            <td>${i.lineRead}</td>
                            <td>${i.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </fieldset>
    </body>
</html>
