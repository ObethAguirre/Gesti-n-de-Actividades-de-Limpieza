
package Modelo;

public class JefeCuadrilla {
    private int idJefe;
    private String nombre;
    private Cuadrilla cuadrillaAsignada;

    public JefeCuadrilla(int idJefe, String nombre, Cuadrilla cuadrillaAsignada) {
        this.idJefe = idJefe;
        this.nombre = nombre;
        this.cuadrillaAsignada = cuadrillaAsignada;
    }

    public JefeCuadrilla() {
    }
    
    

    public int getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cuadrilla getCuadrillaAsignada() {
        return cuadrillaAsignada;
    }

    public void setCuadrillaAsignada(Cuadrilla cuadrillaAsignada) {
        this.cuadrillaAsignada = cuadrillaAsignada;
    }
    
}
