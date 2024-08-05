package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.ControladorPersistencia;

public class Controlador {

    ControladorPersistencia controlPersis = new ControladorPersistencia();

    public void crearUsuario(String user, String password, String rol) {

        Usuario usuario = new Usuario();

        usuario.setUser(user);
        usuario.setPassword(password);
        usuario.setRol(rol);

        controlPersis.crearUsuario(usuario);
    }

    public List<Usuario> getUsuarios() {
        return controlPersis.getUsuarios();
    }

    public void borrarUser(int id) {
        controlPersis.borrarUser(id);
    }

    public Usuario traerUsuario(int id) {
        return controlPersis.traerUsuario(id);
    }

    public void editarUsuario(Usuario usu) {
        controlPersis.editarUsuario(usu);
    }

    public void crearOdontos(String dni, String nombre, String apellido, Date fechaNac, String telefono, String direccion, String especialidad,
            String horarioInicio, String horarioFin) {
        
        Horario horario = new Horario();
        horario.setHorario_inicio(horarioInicio);
        horario.setHorario_fin(horarioFin);

        Odontologo odonto = new Odontologo();

        odonto.setDni(dni);
        odonto.setNombre(nombre);
        odonto.setApellido(apellido);
        odonto.setFecha_nac(fechaNac);
        odonto.setTelefono(telefono);
        odonto.setDireccion(direccion);
        odonto.setEspecialidad(especialidad);
        odonto.setUnHorario(horario);
        controlPersis.crearOdontos(odonto);
    }

    public List<Odontologo> getOdontos() {
        return controlPersis.getOdontos();
    }

    public void borrarOdonto(int id) {
        controlPersis.borrarOdonto(id);
    }

    public Odontologo traerOdonto(int id) {
        return controlPersis.traerOdonto(id);
    }

    public void editarOdonto(Odontologo odonto) {
        controlPersis.editarOdonto(odonto);
    }

    public void crearPacientes(String dniPac, String nombrePac, String apellidoPac, Date fechaNacPac, String telefonoPac, String direccionPac, boolean obraSocPac, String tipo_sangrePac,
            String tipoResp, String dniResp, String nombreResp, String apellidoResp, String telefonoResp, String direccionResp, Date fechaNacResp) {
        
        Responsable resp = new Responsable();
        resp.setTipo_resp(tipoResp);
        resp.setDni(dniResp);
        resp.setNombre(nombreResp);
        resp.setApellido(apellidoResp);
        resp.setFecha_nac(fechaNacResp);
        resp.setTelefono(telefonoResp);
        resp.setDireccion(direccionResp);
        
        
        Pasciente paciente = new Pasciente();
        
        paciente.setDni(dniPac);
        paciente.setNombre(nombrePac);
        paciente.setApellido(apellidoPac);
        paciente.setFecha_nac(fechaNacPac);
        paciente.setTelefono(telefonoPac);
        paciente.setDireccion(direccionPac);
        paciente.setObra_social(obraSocPac);
        paciente.setTipo_sangre(tipo_sangrePac);
        paciente.setUnResponsable(resp);
        
        controlPersis.crearPaciente(paciente);
    }

    public void crearPaciente(String dniPac, String nombrePac, String apellidoPac, Date fechaNacPac, String telefonoPac, String direccionPac, boolean obraSocPac, String tipo_sangrePac) {
         Pasciente paciente = new Pasciente();
        
        paciente.setDni(dniPac);
        paciente.setNombre(nombrePac);
        paciente.setApellido(apellidoPac);
        paciente.setFecha_nac(fechaNacPac);
        paciente.setTelefono(telefonoPac);
        paciente.setDireccion(direccionPac);
        paciente.setObra_social(obraSocPac);
        paciente.setTipo_sangre(tipo_sangrePac);
       
        
        controlPersis.crearPaciente(paciente);
    }

    public List<Pasciente> getPacientes() {
        return controlPersis.traerPacientes();
    }

    public void borrarPaciente(int id) {
        controlPersis.borrarPaciente(id);
    }

    public Pasciente traerPaci(int id) {
        return controlPersis.traerPaci(id);
    }

    public void editarPaciente(Pasciente paci) {
        controlPersis.editarPaciente(paci);
    }

    public void crearSecretario(String dni, String nombre, String apellido, String telefono, String direccion, String sector, Date fechaNac) {
        Secretario secre = new Secretario();
        
        secre.setDni(dni);
        secre.setNombre(nombre);
        secre.setApellido(apellido);
        secre.setTelefono(telefono);
        secre.setDireccion(direccion);
        secre.setSector(sector);
        secre.setFecha_nac(fechaNac);
        
        controlPersis.crearSecretario(secre);
    }

    public List<Secretario> getSecretarios() {
        return controlPersis.getSecretario();
    }

    public void borrarSecre(int id) {
        controlPersis.borrarSecre(id);
    }

    public Secretario traerSecre(int id) {
        return controlPersis.traerSecre(id);
    }

    public void editSecretario(Secretario secre) {
        controlPersis.editSecretario(secre);
    }

    public void crearTurno(String id_paci, String id_odonto, String afeccion, String hora_turno, Date fechaTurno) {
        int idPaci = Integer.parseInt(id_paci);
        int idOdon = Integer.parseInt(id_odonto);
        
        Turno turno = new Turno();
        
        turno.setPascien(controlPersis.traerPaci(idPaci));
        turno.setOdonto(controlPersis.traerOdonto(idOdon));
        turno.setAfeccion(afeccion);
        turno.setHora_turno(hora_turno);
        turno.setFecha_turno(fechaTurno);
        
        controlPersis.crearTurno(turno);
    }

    public List<Turno> getTurnos() {
        return controlPersis.getTurnos();
    }

    public void borrarTurno(int idTurno) {
        controlPersis.borrarTurno(idTurno);
    }

    public Turno traerTurno(int idTurno) {
        return controlPersis.traerTurno(idTurno);
    }

    public void editarTurno(Turno turno) {
        controlPersis.editarTurno(turno);
    }

    public boolean comprobarIngreso(String user, String pass) {
        boolean ingreso = false;
        
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        listaUsuario = controlPersis.getUsuarios();
        
        for (Usuario usuario : listaUsuario) {
            if (usuario.getUser().equals(user)) {
                if (usuario.getPassword().equals(pass)) {
                    ingreso = true;
                }else{
                    ingreso = false;
                }
            }
        }
        return ingreso;
    }

     

    

    
 


}
