
package Controlador;
import Modelo.Cuadrilla;
import Modelo.CuadrillaDAO;

import java.sql.SQLException;
import java.util.List;

public class CuadrillaController {
    private CuadrillaDAO cuadrillaDAO;

    public CuadrillaController() {
        try {
            cuadrillaDAO = new CuadrillaDAO();
        } catch (SQLException e) {
            System.err.println("Error al inicializar CuadrillaDAO: " + e.getMessage());
        }
    }

    // Método para agregar una nueva cuadrilla
    public boolean agregarCuadrilla(String nombreCuadrilla) {
        Cuadrilla cuadrilla = new Cuadrilla();
        cuadrilla.setNombreCuadrilla(nombreCuadrilla);

        return cuadrillaDAO.crearCuadrilla(cuadrilla);
    }

    // Método para obtener una cuadrilla por ID
    public Cuadrilla obtenerCuadrilla(int idCuadrilla) {
        return cuadrillaDAO.obtenerCuadrillaPorId(idCuadrilla);
    }

    // Método para actualizar una cuadrilla
    public boolean actualizarCuadrilla(int idCuadrilla, String nombreCuadrilla) {
        Cuadrilla cuadrilla = new Cuadrilla(idCuadrilla, nombreCuadrilla);
        return cuadrillaDAO.actualizarCuadrilla(cuadrilla);
    }

    // Método para eliminar una cuadrilla por ID
    public boolean eliminarCuadrilla(int idCuadrilla) {
        return cuadrillaDAO.eliminarCuadrilla(idCuadrilla);
    }

    // Método para listar todas las cuadrillas
    public List<Cuadrilla> listarCuadrillas() {
        return cuadrillaDAO.listarCuadrillas();
    }
}