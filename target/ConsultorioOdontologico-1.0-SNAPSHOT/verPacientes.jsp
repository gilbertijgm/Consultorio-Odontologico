<%@page import="logica.Pasciente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Pacientes</h1>


    <!-- DataTales Example -->
    <div class="card shadow mb-4">

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>

                            <th>DNI</th>
                            <th>Nombre Paciente</th>
                            <th>Apellido Paciente</th>
                            <th>Tipo Sangre</th> 
                            <th>Obra Social</th> 
                            <th>Telefono Paciente</th> 
                            <th>nombre Responsable</th> 
                            <th>Acciones</th>  
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>DNI</th>
                            <th>Nombre Paciente</th>  
                            <th>Apellido Paciente</th>
                            <th>Tipo Sangre</th> 
                            <th>Obra Social</th> 
                            <th>Telefono Paciente</th> 
                            <th>nombre Responsable</th> 
                            <th style="width: 210px">Acciones</th> 
                        </tr>
                    </tfoot>
                    <%
                        List<Pasciente> listaPacientes = (List<Pasciente>) request.getSession().getAttribute("listaPacientes");
                        if (listaPacientes != null) {
                            for (Pasciente paci : listaPacientes) {
                    %> 
                    <tr>
                        <td><%= paci.getDni()%></td>
                        <td><%= paci.getNombre()%></td> 
                        <td><%= paci.getApellido()%></td>
                        <td><%= paci.getTipo_sangre()%></td>  
                        <td><%= paci.isObra_social() == true ? "Si" : "No" %></td>  
                        <td><%= paci.getTelefono()%></td> 
                        <td>
                            <%= paci.getUnResponsable() != null ? paci.getUnResponsable().getNombre() : "Sin Responsable"%>
                        </td>
                        <td style="display: flex; width: 230px">
                            <form name="eliminar" action="SvElimPaciente" method="POST">    
                                <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red;margin-right:5px;">
                                    <i class="fas fa-trash-alt"></i> Eliminar
                                </button>
                                <input type="hidden" name="id" value="<%= paci.getId()%>">
                            </form>
                            <form name="editar" action="SvEditPaciente" method="GET">    
                                <button type="submit" class="btn btn-warning btn-user btn-block" style="margin-left:5px;">
                                    <i class="fas fa-pencil-alt"></i> Editar
                                </button>
                                <input type="hidden" name="id" value="<%= paci.getId()%>">
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">No hay pacientes registrados</td>
                    </tr>
                    <% }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<%@include file="components/bodyfinal.jsp"%>