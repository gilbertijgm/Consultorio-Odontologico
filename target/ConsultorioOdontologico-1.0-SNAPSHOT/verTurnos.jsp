<%@page import="logica.Turno"%>
<%@page import="logica.Pasciente"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Turnos</h1>


    <!-- DataTales Example -->
    <div class="card shadow mb-4">

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>Afeccion</th>
                            <th>Paciente</th>
                            <th>Odontologo</th>
                            <th>Fecha</th> 
                            <th>Hora</th>
                            <th>Acciones</th>  
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>id</th>
                            <th>Afeccion</th>
                            <th>Paciente</th>
                            <th>Odontologo</th>
                            <th>Fecha</th> 
                            <th>Hora</th> 
                            <th style="width: 210px">Acciones</th> 
                        </tr>
                    </tfoot>
                    <%
                         List<Turno> listaTurno = (List<Turno>) request.getSession().getAttribute("listaTurno");
                        if (listaTurno != null) {
                            for (Turno turno : listaTurno) {
                    %> 
                    <tr>
                        <td><%= turno.getId_turno() %></td>
                        <td><%= turno.getAfeccion() %></td> 
                        <td><%= turno.getPascien().getNombre() %> <%= turno.getPascien().getApellido() %></td>
                        <td><%= turno.getOdonto().getNombre() %> <%= turno.getOdonto().getApellido() %></td>  
                        <td><%= turno.getFechaFormateada() %></td>  
                        <td><%= turno.getHora_turno() %></td> 
                        
                        <td style="display: flex; width: 230px">
                            <form name="eliminar" action="SvElimTurno" method="POST">    
                                <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red;margin-right:5px;">
                                    <i class="fas fa-trash-alt"></i> Eliminar
                                </button>
                                <input type="hidden" name="id" value="<%= turno.getId_turno() %>">
                            </form>
                            <form name="editar" action="SvEditTurno" method="GET">    
                                <button type="submit" class="btn btn-warning btn-user btn-block" style="margin-left:5px;">
                                    <i class="fas fa-pencil-alt"></i> Editar
                                </button>
                                <input type="hidden" name="id" value="<%= turno.getId_turno() %>">
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">No hay turnos registrados</td>
                    </tr>
                    <% }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<%@include file="components/bodyfinal.jsp"%>