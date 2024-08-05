<%@page import="java.util.List"%>
<%@page import="logica.Pasciente"%>
<%@page import="logica.Odontologo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<%
    List<Pasciente> listaPaci = (List<Pasciente>) request.getSession().getAttribute("listaPacientes");
    List<Odontologo> listaOdonto = (List<Odontologo>) request.getSession().getAttribute("listaOdontos");
%>
<div class="text-center">
    <h1 class="h4 text-gray-900 mb-4">Crear Turno</h1>
</div>

<form class="user" action="SvTurnos" method="POST">

    <div class="form-group">
        <select class="form-control form-control" id="id_paci" name="id_paci" required>
            <option value="">Seleccione Paciente</option>
            <% if (listaPaci != null) { 
                for (Pasciente paci : listaPaci) { %>
                    <option value="<%= paci.getId() %>"><%= paci.getNombre() %> <%= paci.getApellido() %></option>
                <% } 
            } else { %>
                <option value="">No hay pacientes disponibles</option>
            <% } %>
        </select>
    </div>
    
    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="Afeccion" name="afeccion"
               placeholder="Afección" required>
    </div>

    <div class="form-group">
        <select class="form-control form-control" id="id_odonto" name="id_odonto" required>
            <option value="">Seleccione Odontólogo</option>
            <% if (listaOdonto != null) { 
                for (Odontologo odonto : listaOdonto) { %>
                    <option value="<%= odonto.getId() %>"><%= odonto.getNombre() %> <%= odonto.getApellido() %></option>
                <% } 
            } else { %>
                <option value="">No hay odontólogos disponibles</option>
            <% } %>
        </select>
    </div>
    
    <div class="form-group">
        <input type="date" class="form-control form-control-user" id="fecha_turno" name="fecha_turno"
               placeholder="Fecha de Turno" required=""> 
    </div>

    <div class="form-group">
        <input type="time" class="form-control form-control-user" id="hora_turno" name="hora_turno"
               placeholder="Hora de Turno" required=""> 
    </div>

    

    


    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Turno
    </button>

</form>
<%@include file="components/bodyfinal.jsp"%>