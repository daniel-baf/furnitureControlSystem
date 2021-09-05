<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Page Wrapper -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/login.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Muebleria <sup>Mi</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Panel</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Tus acciones
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-fw fa-cog"></i>
            <span>Reportes</span>
        </a>
        <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Reportes financieros</h6>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#generate-report-faap">Ventas</a>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#generate-report-faap">Devoluciones</a>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#generate-report-faap"">Ganancias</a>
                <h6 class="collapse-header">Trabajadores</h6>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#generate-report-faap">Con más ventas</a>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#generate-report-faap">Con más ganancias</a>
                <h6 class="collapse-header">Muebles</h6>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#generate-report-faap">Más vendido</a>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#generate-report-faap">Menos vendido</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
           aria-expanded="true" aria-controls="collapseUtilities">
            <i class="fas fa-fw fa-wrench"></i>
            <span>Administración</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
             data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Creación</h6>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#insert-usr-FAAP">Nuevo usuario</a>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#insert-frn-FAAP">Tipo de mueble</a>
                <h6 class="collapse-header">Borrar</h6>
                <a class="collapse-item" href="${pageContext.request.contextPath}/Dashboards/Fin-Admin-Panel.jsp#delete-user-form">Usuario</a>
            </div>
        </div>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider">
    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
<!-- End of Sidebar -->