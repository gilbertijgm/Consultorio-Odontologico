<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<% 
String error = (String) request.getAttribute("error");
String success = (String) request.getAttribute("success");
%>

<% if (error != null) { %>
    <div class="alert alert-danger" role="alert">
        <%= error %>
    </div>
<% } else if (success != null) { %>
    <div class="alert alert-success" role="alert">
        <%= success %>
    </div>
<% } %>
<div class="text-center">
    <h1 class="h4 text-gray-900 mb-4">Crear Paciente</h1>
</div>
<form class="user" action="SvPaciente" method="POST">
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user" id="dni" name="dni"
                   placeholder="DNI" required="" >
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                   placeholder="Nombre" required="">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
                   placeholder="Apellido"  required="">
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="date" class="form-control form-control-user" id="fecha_nac" name="fecha_nac"
                   placeholder="Fecha de Nacimiento" required=""> 
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="telefono" name="telefono"
                   placeholder="Telefono" required="">
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="direccion" name="direccion" 
                   placeholder="Direccion" required=""> 
        </div> 
        <div class="col-sm-6 mb-3">
            <select class="form-control form-control"  id="obraSocial" name="obraSocial">
                <option >Obra Social?</option>
                <option value="true">Si</option>
                <option value="false">No</option>
            </select>
        </div> 
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="tipo_sangre" name="tipo_sangre" 
                   placeholder="Tipo sangre" required=""> 
        </div> 
    </div>


    <!-- Campos para el responsable (inicialmente ocultos) -->
    <div id="camposResponsable" style="display: none;">
        <div class="text-center">
            <h1 class="h4 text-gray-900 mb-4">Datos del Responable</h1>
        </div>
        <div class="form-group">
            <input type="text" class="form-control form-control-user" id="tipo_resp" name="tipo_resp"
                   placeholder="Parentesco"  >
        </div>
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" id="dni_resp" name="dni_resp"
                       placeholder="DNI del responsable"  >
            </div>
            <div class="col-sm-6 mb-3 ">
                <input type="text" class="form-control form-control-user" id="nombre_resp" name="nombre_resp"
                       placeholder="Nombre del responsable" >
            </div>
            <div class="col-sm-6 mb-3">
                <input type="datetime" class="form-control form-control-user" id="apellido_resp" name="apellido_resp"
                       placeholder="Apellido del responsable"  >
            </div> 
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="telefono_resp" name="telefono_resp"
                       placeholder="Telefono del responsable" >
            </div> 
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="direccion_resp" name="direccion_resp" 
                       placeholder="Direccion del responsable" > 
            </div> 
            <div class="col-sm-6 mb-3">
                <input type="date" class="form-control form-control-user" id="fecha_nac_resp" name="fecha_nac_resp"
                       placeholder="Fecha de Nacimiento del responsable" > 
            </div> 
        </div>
    </div>
    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Paciente
    </button>

</form>

<script type="text/javascript">
    function calcularEdad() {
        // Obtener la fecha de nacimiento ingresada por el usuario
        var fechaNacimiento = new Date(document.getElementById("fecha_nac").value);
 
        // Obtener la fecha actual
        var fechaActual = new Date();

        // Calcular la edad comparando años
        var edad = fechaActual.getFullYear() - fechaNacimiento.getFullYear();
        var mesActual = fechaActual.getMonth();
        var mesNacimiento = fechaNacimiento.getMonth();

        // Ajustar la edad si el mes actual es menor que el mes de nacimiento
        if (mesActual < mesNacimiento || (mesActual === mesNacimiento && fechaActual.getDate() < fechaNacimiento.getDate())) {
            edad--;
        }

        // Determinar si el paciente es menor de edad (menor de 18 años)
        var esMenorEdad = (edad < 18);
         
        // Mostrar u ocultar campos del responsable según sea necesario
        var camposResponsable = document.getElementById("camposResponsable");
        camposResponsable.style.display = esMenorEdad ? "block" : "none";
    }

    // Llamar a la función al cambiar la fecha de nacimiento
    document.getElementById("fecha_nac").addEventListener("change", calcularEdad);
</script>
<%@include file="components/bodyfinal.jsp"%>