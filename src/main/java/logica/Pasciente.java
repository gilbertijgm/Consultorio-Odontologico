 
package logica;
 
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Pasciente extends Persona implements Serializable {
//    private int id_pasciente;
    private boolean obra_social;
    private String tipo_sangre;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Responsable unResponsable;
    @OneToMany(mappedBy="pascien")
    private List<Turno> listTurno;

    public Pasciente() {
    }

    public Pasciente(boolean obra_social, String tipo_sangre, Responsable unResponsable, List<Turno> listTurno, int id, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(id, dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.obra_social = obra_social;
        this.tipo_sangre = tipo_sangre;
        this.unResponsable = unResponsable;
        this.listTurno = listTurno;
    }

   

//    public int getId_pasciente() {
//        return id_pasciente;
//    }
//
//    public void setId_pasciente(int id_pasciente) {
//        this.id_pasciente = id_pasciente;
//    }

    public boolean isObra_social() {
        return obra_social;
    }

    public void setObra_social(boolean obra_social) {
        this.obra_social = obra_social;
    }

    public String getTipo_sangre() {
        return tipo_sangre;
    }

    public void setTipo_sangre(String tipo_sangre) {
        this.tipo_sangre = tipo_sangre;
    }

    public Responsable getUnResponsable() {
        return unResponsable;
    }

    public void setUnResponsable(Responsable unResponsable) {
        this.unResponsable = unResponsable;
    }

    public List<Turno> getListTurno() {
        return listTurno;
    }

    public void setListTurno(List<Turno> listTurno) {
        this.listTurno = listTurno;
    }

    
    
}
