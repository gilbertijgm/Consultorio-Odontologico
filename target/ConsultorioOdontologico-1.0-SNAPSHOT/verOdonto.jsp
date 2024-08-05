<%@page import="logica.Horario"%>
<%@page import="logica.Odontologo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Odontologos</h1>


    <!-- DataTales Example -->
    <div class="card shadow mb-4">

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>

                            <th>DNI</th>
                            <th>Nombre</th>       
                            <th>Apellido</th> 
                            <th>Especialidad</th> 
                            <th>Horario Inicio</th> 
                            <th>Horario Fin</th> 
                            <th>Acciones</th>  
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>DNI</th>
                            <th>Nombre</th>       
                            <th>Apellido</th> 
                            <th>Especialidad</th> 
                            <th>Horario Inicio</th> 
                            <th>Horario Fin</th> 
                            <th style="width: 210px">Acciones</th> 
                        </tr>
                    </tfoot>
                    <%
                        List<Odontologo> listaOdontos = (List) request.getSession().getAttribute("listaOdontos");
                    %>
                    <tbody>
                        <% for (Odontologo odonto : listaOdontos) {%>  
                        <tr>

                            <th><%=odonto.getDni() %></th>
                            <th><%=odonto.getNombre()%></th>       
                            <th><%=odonto.getApellido()%></th> 
                            <th><%=odonto.getEspecialidad()%></th> 
                            <th>
                            <%
                                Horario horario = odonto.getUnHorario();
                                if (horario != null) {
                                    out.print(horario.getHorario_inicio());
                                }
                            %>
                            </th> 
                            <th>
                            <%
                                if (horario != null) {
                                    out.print(horario.getHorario_fin());
                                }
                            %>
                            </th> 
                            <td style="display: flex; width: 230px">
                                <form name="eliminar" action="SvElimOdonto" method="POST">    
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red;margin-right:5px;">
                                        <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%= odonto.getId()%>">
                                </form>
                                <form name="editar" action="SvEditOdonto" method="GET">    
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="margin-left:5px;">
                                        <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="<%= odonto.getId()%>">
                                </form>
                            </td>
                        </tr>
                        <%}%> 
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<%@include file="components/bodyfinal.jsp"%>