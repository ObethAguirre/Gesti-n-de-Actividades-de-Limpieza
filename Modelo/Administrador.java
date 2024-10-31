
package Modelo;

public class Administrador {
    private int idAdministrador;
    private String usuario;
    private String contraseña;

    
    
    public Administrador(int idAdministrador, String usuario, String contraseña) {
        this.idAdministrador = idAdministrador;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    public Administrador() {
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
