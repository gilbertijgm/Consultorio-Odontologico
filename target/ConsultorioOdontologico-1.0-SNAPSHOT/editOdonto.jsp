<%@page import="logica.Odontologo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>


<div class="text-center">
    <h1 class="h4 text-gray-900 mb-4">Editar Odontologo</h1>
</div>

<% Odontologo odonto = (Odontologo) request.getSession().getAttribute("odontoEditar");%>
<form class="user" action="SvEditOdonto" method="POST">
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI" value="<%=odonto.getDni()%>">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" value="<%=odonto.getNombre()%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="datetime" class="form-control form-control-user" id="apellido" name="apellido"
                   placeholder="Apellido" value="<%=odonto.getApellido()%>">
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="telefono" name="telefono"
                   placeholder="Telefono" value="<%=odonto.getTelefono()%>">
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion" 
                   placeholder="Direccion" value="<%=odonto.getDireccion()%>"> 
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha_nac" name="fecha_nac"
                   placeholder="Fecha de Nacimiento" value="<%= (odonto.getFormattedFechaNac() != null) ? odonto.getFormattedFechaNac() : ""%>">
        </div> 
    </div>
    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="especialidad" name="especialidad"
               placeholder="Especialidad" value="<%=odonto.getEspecialidad()%>">
    </div>
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="time" class="form-control form-control-user" id="horario_ini" name="horario_ini"
                   placeholder="Horario de Inicio" value="<%=odonto.getUnHorario().getHorario_inicio()%>">
        </div>
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="time" class="form-control form-control-user" id="horario_fin" name="horario_fin"
                   placeholder="Horario Fin" value="<%=odonto.getUnHorario().getHorario_fin()%>">
        </div>
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar
    </button>

</form>
<%@include file="components/bodyfinal.jsp"%>