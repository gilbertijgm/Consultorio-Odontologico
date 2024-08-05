<%@page import="logica.Secretario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<div class="text-center">
    <h1 class="h4 text-gray-900 mb-4">Editar Secretario</h1>
</div>
<%
    Secretario secre = (Secretario) request.getSession().getAttribute("secreEditar");
    String fechaNac = (String) request.getSession().getAttribute("fechaNac");
%>
<form class="user" action="SvEditSecre" method="POST">
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI" required="" value="<%=secre.getDni()%>">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" required="" value="<%=secre.getNombre()%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="datetime" class="form-control form-control-user" id="apellido" name="apellido"
                   placeholder="Apellido" required="" value="<%=secre.getApellido()%>">
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="telefono" name="telefono"
                   placeholder="Telefono" required="" value="<%=secre.getTelefono()%>">
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion" 
                   placeholder="Direccion" required="" value="<%=secre.getDireccion()%>"> 
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha_nac" name="fecha_nac"
                   placeholder="Fecha de Nacimiento" required="" value="<%=fechaNac%>"> 
        </div> 
    </div>
    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="sector" name="sector"
               placeholder="Sector" required="" value="<%=secre.getSector()%>">
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar
    </button>

</form>
<%@include file="components/bodyfinal.jsp"%>