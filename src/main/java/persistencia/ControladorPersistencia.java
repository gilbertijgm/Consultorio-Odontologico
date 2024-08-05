 
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Odontologo;
import logica.Pasciente;
import logica.Secretario;
import logica.Turno;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;

 
public class ControladorPersistencia {
    PersonaJpaController personaJpa = new PersonaJpaController();
    OdontologoJpaController odontoJpa = new OdontologoJpaController();
    PascienteJpaController pacienteJpa = new PascienteJpaController();
    ResponsableJpaController responsableJpa = new ResponsableJpaController();
    SecretarioJpaController secreJpa = new SecretarioJpaController();
    HorarioJpaController horarioJpa = new HorarioJpaController();
    TurnoJpaController turnoJpa = new TurnoJpaController();
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();

    public void crearUsuario(Usuario usuario) {
        usuarioJpa.create(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    public void borrarUser(int id) {
        try {
            usuarioJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuario(int id) {
        return usuarioJpa.findUsuario(id);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuarioJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearOdontos(Odontologo odonto) {
        odontoJpa.create(odonto);
    }

    public List<Odontologo> getOdontos() {
        return odontoJpa.findOdontologoEntities();
    }

    public void borrarOdonto(int id) {
        try {
            odontoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Odontologo traerOdonto(int id) {
        return odontoJpa.findOdontologo(id);
    }

    public void editarOdonto(Odontologo odonto) {
        try {
            odontoJpa.edit(odonto);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearPaciente(Pasciente paciente) {
        pacienteJpa.create(paciente);
    }

    public List<Pasciente> traerPacientes() {
        return pacienteJpa.findPascienteEntities();
    }

    public void borrarPaciente(int id) {
        try {
            pacienteJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pasciente traerPaci(int id) {
        return pacienteJpa.findPasciente(id);
    }

    public void editarPaciente(Pasciente paci) {
        try {
            pacienteJpa.edit(paci);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearSecretario(Secretario secre) {
        secreJpa.create(secre);
    }

    public List<Secretario> getSecretario() {
        return secreJpa.findSecretarioEntities();
    }

    public void borrarSecre(int id) {
        try {
            secreJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Secretario traerSecre(int id) {
        return secreJpa.findSecretario(id);
    }

    public void editSecretario(Secretario secre) {
        try {
            secreJpa.edit(secre);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Odontologo> traerOdontos() {
        return odontoJpa.findOdontologoEntities();
    }

    public List<Pasciente> traerPaci() {
        return pacienteJpa.findPascienteEntities();
    }

    public void crearTurno(Turno turno) {
        turnoJpa.create(turno);
    }

    public List<Turno> getTurnos() {
        return turnoJpa.findTurnoEntities();
    }

    public void borrarTurno(int idTurno) {
        try {
            turnoJpa.destroy(idTurno);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Turno traerTurno(int idTurno) {
        return turnoJpa.findTurno(idTurno);
    }

    public void editarTurno(Turno turno) {
        try {
            turnoJpa.edit(turno);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
 
}
