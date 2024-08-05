 
package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usario;
    private String user;
    private String password;
    private String rol;

    public Usuario() {
    }

    public Usuario(int id_usario, String user, String password, String rol) {
        this.id_usario = id_usario;
        this.user = user;
        this.password = password;
        this.rol = rol;
    }

    public int getId_usario() {
        return id_usario;
    }

    public void setId_usario(int id_usario) {
        this.id_usario = id_usario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
