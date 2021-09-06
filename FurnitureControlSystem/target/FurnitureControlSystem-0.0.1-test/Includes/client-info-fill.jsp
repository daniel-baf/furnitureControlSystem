<%@page import="Domain.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Client client = (Client) session.getAttribute("client-bill-user");
    String dep = client.getDepartment() == null ? "" : client.getDepartment();
    String mun = client.getDepartment() == null ? "" : client.getMunicipality();
%>
<div>
    <input name="action-perf" type="text" value="insert-client" hidden>
    <div class="form-group">
        <label for="nit">NIT</label>
        <input required name="nit" type="text" class="form-control" id="nit" aria-describedby="emailHelp" placeholder="Un NIT del cliente" value="<%=client.getNit()%>">
        <small>Solicitale al cliente el nit</small>
    </div>
    <div class="form-group">
        <label for="name">Nombre</label>
        <input required name="name" type="text" min="0" step=".01" class="form-control" id="name" aria-describedby="priceHelp" placeholder="Ingresa el nombre del cliente" value="<%=client.getName()%>">
        <small>El nombre del cliente</small>
    </div>
    <div class="form-group">
        <label for="adress">Dirección</label>
        <input required name="adress" type="text" min="0" step=".01" class="form-control" id="adress" aria-describedby="priceHelp" placeholder="La dirección del cliente" value="<%=client.getAdress()%>">
        <small>Ingresa la dirección donde el cliente recide, puede ser ciudad</small>
    </div>
    <div class="form-group">
        <label for="municipality">Municipalidad</label>
        <input name="municipality" type="text" min="0" step=".01" class="form-control" id="municipality" aria-describedby="priceHelp" placeholder="El municipio no es obligatorio" value="<%=mun%>">
        <small>El precio de venta del mueble</small>
    </div>
    <div class="form-group">
        <label for="department">Departamento</label>
        <input name="department" type="text" min="0" step=".01" class="form-control" id="department" aria-describedby="priceHelp" placeholder="Un nombre de usuario" value="<%=dep%>">
        <small>El precio de venta del mueble</small>
    </div>
</div>