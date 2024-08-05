
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>
<div class="text-center">
    <h1 class="h4 text-gray-900 mb-4">Crear Usuario</h1>
</div>
<form class="user" action="SvUsuarios" method="POST">
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user" id="user" name="user"
                   placeholder="Nombre de Usuario">
        </div>
        <div class="col-sm-6 mb-3 ">
            <input type="password" class="form-control form-control-user" id="password" name="password"
                   placeholder="Contraseña">
        </div>
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="rol" name="rol"
                   placeholder="Rol">
        </div> 
        
        
    <button class="btn btn-primary btn-user btn-block" type="submit">
        Crear Usuario
    </button>

</form>
<%@include file="components/bodyfinal.jsp"%>