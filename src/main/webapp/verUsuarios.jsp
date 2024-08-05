
<%@page import="logica.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Usuarios</h1>


    <!-- DataTales Example -->
    <div class="card shadow mb-4">

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>

                            <th>Usuario</th>
                            <th>Rol</th>       
                            <th>Acciones</th> 
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>

                            <th>Usuario</th>
                            <th>Rol</th>
                            <th style="width: 210px">Acciones</th> 
                        </tr>
                    </tfoot>
                    <%
                        List<Usuario> listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios");
                    %>
                    <tbody>
                        <% for (Usuario user : listaUsuarios) {%>  
                        <tr>

                            <td><%= user.getUser()%></td>
                            <td><%= user.getRol()%></td>
                            <td style="display: flex; width: 230px">
                                <form name="eliminar" action="SvElimUsuarios" method="POST">    
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color:red;margin-right:5px;">
                                        <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%= user.getId_usario()%>">
                                </form>
                                <form name="editar" action="SvEditUsuario" method="GET">    
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="margin-left:5px;">
                                        <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="<%= user.getId_usario()%>">
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