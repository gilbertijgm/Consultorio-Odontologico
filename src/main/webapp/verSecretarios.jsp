<%@page import="logica.Secretario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Secretarios</h1>


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
                            <th>telefono</th> 
                            <th>Sector</th>
                            <th>Acciones</th>  
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>DNI</th>
                            <th>Nombre</th>       
                            <th>Apellido</th> 
                            <th>telefono</th> 
                            <th>Sector</th>
                            <th style="width: 210px">Acciones</th> 
                        </tr>
                    </tfoot>
                    <%
                        List<Secretario> listaSecre = (List) request.getSession().getAttribute("listaSecre");
                    %>
                    <tbody>
                        <% for (Secretario secre : listaSecre) {%>  
                        <tr>

                            <th><%=secre.getDni() %></th>
                            <th><%=secre.getNombre() %></th>       
                            <th><%=secre.getApellido() %></th> 
                            <th><%=secre.getTelefono() %></th> 
                            <th><%=secre.getSector() %></th> 
                            <td style="display: flex; width: 230px">
                                <form name="eliminar" action="SvElimSecre" method="POST">    
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red;margin-right:5px;">
                                        <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%= secre.getId() %>">
                                </form>
                                <form name="editar" action="SvEditSecre" method="GET">    
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="margin-left:5px;">
                                        <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="<%= secre.getId()%>">
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