
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<div class="text-center">
    <h1 class="h4 text-gray-900 mb-4">Editar Usuario</h1>
</div>
<% Usuario usu = (Usuario)request.getSession().getAttribute("usuEditar");%>

<form class="user" action="SvEditUsuario" method="POST">
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user" id="user" name="user"
                   placeholder="Nombre de Usuario" value="<%=usu.getUser()%>">
        </div>
        
        <div class="col-sm-6 mb-3 ">
            <input type="password" class="form-control form-control-user" id="password" name="password"
                   placeholder="Contraseña" value="<%=usu.getPassword()%>">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="rol" name="rol"
                   placeholder="Rol" value="<%=usu.getRol()%>">
        </div> 
        
        
    <button class="btn btn-primary btn-user btn-block" type="submit">
        Guardar
    </button>

</form>
<%@include file="components/bodyfinal.jsp"%>