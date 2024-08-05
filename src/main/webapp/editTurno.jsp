<%@page import="logica.Turno"%>
<%@page import="java.util.List"%>
<%@page import="logica.Pasciente"%>
<%@page import="logica.Odontologo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<div class="text-center">
    <h1 class="h4 text-gray-900 mb-4">Editar Turno</h1>
</div>
<%
    Turno turno = (Turno) request.getSession().getAttribute("turnoEditar");
    String fechaTurno = (String) request.getSession().getAttribute("fechaTurno");

    List<Pasciente> listaPaci = (List<Pasciente>) request.getSession().getAttribute("listaPacientes");
    List<Odontologo> listaOdonto = (List<Odontologo>) request.getSession().getAttribute("listaOdontos");
%>
<form class="user" action="SvEditTurno" method="POST">

    <div class="form-group">
        <select class="form-control form-control" id="id_paci" name="id_paci" required>
            <option value="">Seleccione Paciente</option>
            <% for (Pasciente paci : listaPaci) {%>
            <option value="<%= paci.getId()%>" <%= (turno.getPascien().getId() == paci.getId() ? "selected" : "")%> >
                <%= paci.getNombre()%> <%= paci.getApellido()%>
            </option>
            <% }%>
        </select>
    </div>

    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="Afeccion" name="afeccion"
               placeholder="Afección" required value="<%= turno.getAfeccion()%>">
    </div>

    <div class="form-group">
        <select class="form-control form-control" id="id_odonto" name="id_odonto" required>
            <option value="">Seleccione Odontólogo</option>
            <% for (Odontologo odonto : listaOdonto) {%>
            <option value="<%= odonto.getId()%>" <%= (turno.getOdonto().getId() == odonto.getId() ? "selected" : "")%> >
                <%= odonto.getNombre()%> <%= odonto.getApellido()%>
            </option>
            <% }%>
        </select>
    </div>

    <div class="form-group">
        <input type="date" class="form-control form-control-user" id="fecha_turno" name="fecha_turno"
               placeholder="Fecha de Turno" required="" value="<%= fechaTurno%>"> 
    </div>

    <div class="form-group">
        <input type="time" class="form-control form-control-user" id="hora_turno" name="hora_turno"
               placeholder="Hora de Turno" required="" value="<%= turno.getHora_turno()%>"> 
    </div>

    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar
    </button>

</form>
<%@include file="components/bodyfinal.jsp"%>