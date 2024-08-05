<%@page import="logica.Responsable"%>
<%@page import="logica.Pasciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<%
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>

<% if (error != null) {%>
<div class="alert alert-danger" role="alert">
    <%= error%>
</div>
<% } else if (success != null) {%>
<div class="alert alert-success" role="alert">
    <%= success%>
</div>
<% } %>
<div class="text-center">
    <h1 class="h4 text-gray-900 mb-4">Editar Paciente</h1>
</div>
<%
    Pasciente paci = (Pasciente) request.getSession().getAttribute("paciEditar");
    String fechaNac = (String) request.getSession().getAttribute("fechaNac");

    Responsable resp = (Responsable) request.getSession().getAttribute("resp");
    String fechaNacResp = (String) request.getSession().getAttribute("fechaNacResp");
    boolean tieneResponsable = (resp != null);
%>

<form class="user" action="SvEditPaciente" method="POST">
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI" required="" value="<%= paci.getDni() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" required="" value="<%= paci.getNombre() %>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
                   placeholder="Apellido" required="" value="<%= paci.getApellido() %>">
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha_nac" name="fecha_nac"
                   placeholder="Fecha de Nacimiento" required="" value="<%= fechaNac %>"> 
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="telefono" name="telefono"
                   placeholder="Telefono" required="" value="<%= paci.getTelefono() %>">
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion" 
                   placeholder="Direccion" required="" value="<%= paci.getDireccion() %>"> 
        </div> 
        <div class="col-sm-6 mb-3">
            <select class="form-control form-control" id="obraSocial" name="obraSocial">
                <option>Obra Social?</option>
                <option value="true" <%= paci.isObra_social() ? "selected" : "" %>>Si</option>
                <option value="false" <%= !paci.isObra_social() ? "selected" : "" %>>No</option>
            </select>
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="tipo_sangre" name="tipo_sangre" 
                   placeholder="Tipo sangre" required="" value="<%= paci.getTipo_sangre() %>"> 
        </div> 
    </div>

    <!-- Campos para el responsable (inicialmente ocultos) -->
    <div id="camposResponsable" style="display: <%= tieneResponsable ? "block" : "none" %>;">
        <div class="text-center">
            <h1 class="h4 text-gray-900 mb-4">Datos del Responsable</h1>
        </div>
        <div class="form-group">
            <input type="text" class="form-control form-control-user" id="tipo_resp" name="tipo_resp"
                   placeholder="Parentesco" value="<%= tieneResponsable ? resp.getTipo_resp() : "" %>">
        </div>
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" id="dni_resp" name="dni_resp"
                       placeholder="DNI del responsable" value="<%= tieneResponsable ? resp.getDni() : "" %>">
            </div>
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="nombre_resp" name="nombre_resp"
                       placeholder="Nombre del responsable" value="<%= tieneResponsable ? resp.getNombre() : "" %>">
            </div>
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="apellido_resp" name="apellido_resp"
                       placeholder="Apellido del responsable" value="<%= tieneResponsable ? resp.getApellido() : "" %>">
            </div>
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="telefono_resp" name="telefono_resp"
                       placeholder="Telefono del responsable" value="<%= tieneResponsable ? resp.getTelefono() : "" %>">
            </div>
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="direccion_resp" name="direccion_resp"
                       placeholder="Direccion del responsable" value="<%= tieneResponsable ? resp.getDireccion() : "" %>">
            </div>
            <div class="col-sm-6 mb-3">
                <input type="date" class="form-control form-control-user" id="fecha_nac_resp" name="fecha_nac_resp"
                       placeholder="Fecha de Nacimiento del responsable" value="<%= tieneResponsable ? fechaNacResp : "" %>">
            </div>
        </div>
    </div>
           
    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar
    </button>
</form>

<script type="text/javascript">
    function calcularEdad(fechaNacimiento) {
        var fechaNac = new Date(fechaNacimiento);
        var fechaActual = new Date();
        var edad = fechaActual.getFullYear() - fechaNac.getFullYear();
        var mes = fechaActual.getMonth() - fechaNac.getMonth();

        if (mes < 0 || (mes === 0 && fechaActual.getDate() < fechaNac.getDate())) {
            edad--;
        }

        return edad;
    }

    function mostrarCamposResponsable() {
        var fechaNac = document.getElementById("fecha_nac").value;
        if (fechaNac) {
            var edad = calcularEdad(fechaNac);
            var camposResponsable = document.getElementById("camposResponsable");
            if (edad < 18) {
                camposResponsable.style.display = "block";
            } else {
                camposResponsable.style.display = "none";
            }
        }
    }

    // Ejecutar la función al cargar la página
    window.onload = function() {
        mostrarCamposResponsable();
    }

    // Ejecutar la función al cambiar la fecha de nacimiento
    document.getElementById("fecha_nac").addEventListener("change", mostrarCamposResponsable);
</script>
<%@include file="components/bodyfinal.jsp"%>
