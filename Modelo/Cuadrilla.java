
package Modelo;


public class Cuadrilla {
    private int idCuadrilla;
    private String nombreCuadrilla;

    public Cuadrilla(int idCuadrilla, String nombreCuadrilla) {
        this.idCuadrilla = idCuadrilla;
        this.nombreCuadrilla = nombreCuadrilla;
    }

    public Cuadrilla() {
    }
    
    

    public int getIdCuadrilla() {
        return idCuadrilla;
    }

    public void setIdCuadrilla(int idCuadrilla) {
        this.idCuadrilla = idCuadrilla;
    }

    public String getNombreCuadrilla() {
        return nombreCuadrilla;
    }

    public void setNombreCuadrilla(String nombreCuadrilla) {
        this.nombreCuadrilla = nombreCuadrilla;
    }
    
    @Override
public String toString() {
    return this.nombreCuadrilla; // Devuelve el nombre de la cuadrilla
}
}
