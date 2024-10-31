
package Modelo;
public class Colonia {
    private int idColonia;
    private String nombre;
    private String codigoPostal;
    private String municipio;
    private String estado;

    public Colonia(int idColonia, String nombre, String codigoPostal, String municipio, String estado) {
        this.idColonia = idColonia;
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.municipio = municipio;
        this.estado = estado;
    }

    public Colonia() {
    }
    
    

    public int getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(int idColonia) {
        this.idColonia = idColonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
