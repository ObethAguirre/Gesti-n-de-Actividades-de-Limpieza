
package Controlador;
import Modelo.Cuadrilla;
import Modelo.JefeCuadrilla;
import Modelo.JefeCuadrillaDAO;

import java.sql.SQLException;
import java.util.List;

public class JefeCuadrillaController {
    private JefeCuadrillaDAO jefeCuadrillaDAO;

    public JefeCuadrillaController() {
        try {
            jefeCuadrillaDAO = new JefeCuadrillaDAO();
        } catch (SQLException e) {
            System.err.println("Error al inicializar JefeCuadrillaDAO: " + e.getMessage());
        }
    }

    // Método para agregar un nuevo jefe de cuadrilla
    public boolean agregarJefeCuadrilla(String nombre, Cuadrilla cuadrillaAsignada) {
        JefeCuadrilla jefe = new JefeCuadrilla();
        jefe.setNombre(nombre);
        jefe.setCuadrillaAsignada(cuadrillaAsignada);

        return jefeCuadrillaDAO.crearJefeCuadrilla(jefe);
    }

    // Método para obtener un jefe de cuadrilla por ID
    public JefeCuadrilla obtenerJefeCuadrilla(int idJefe) {
        return jefeCuadrillaDAO.obtenerJefeCuadrillaPorId(idJefe);
    }

    // Método para actualizar un jefe de cuadrilla
    public boolean actualizarJefeCuadrilla(int idJefe, String nombre, Cuadrilla cuadrillaAsignada) {
        JefeCuadrilla jefe = new JefeCuadrilla(idJefe, nombre, cuadrillaAsignada);
        return jefeCuadrillaDAO.actualizarJefeCuadrilla(jefe);
    }

    // Método para eliminar un jefe de cuadrilla por ID
    public boolean eliminarJefeCuadrilla(int idJefe) {
        return jefeCuadrillaDAO.eliminarJefeCuadrilla(idJefe);
    }

    // Método para listar todos los jefes de cuadrilla
    public List<JefeCuadrilla> listarJefesCuadrilla() {
        return jefeCuadrillaDAO.listarJefesCuadrilla();
    }
}