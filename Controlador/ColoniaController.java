
package Controlador;
import Modelo.Colonia;
import Modelo.ColoniaDAO;

import java.sql.SQLException;
import java.util.List;

public class ColoniaController {
    private ColoniaDAO coloniaDAO;

    public ColoniaController() {
        try {
            coloniaDAO = new ColoniaDAO();
        } catch (SQLException e) {
            System.err.println("Error al inicializar ColoniaDAO: " + e.getMessage());
        }
    }

    // Método para agregar una nueva colonia
    public boolean agregarColonia(String nombre, String codigoPostal, String municipio, String estado) {
        Colonia colonia = new Colonia();
        colonia.setNombre(nombre);
        colonia.setCodigoPostal(codigoPostal);
        colonia.setMunicipio(municipio);
        colonia.setEstado(estado);

        return coloniaDAO.crearColonia(colonia);
    }

    // Método para obtener una colonia por ID
    public Colonia obtenerColonia(int idColonia) {
        return coloniaDAO.obtenerColoniaPorId(idColonia);
    }

    // Método para actualizar una colonia
    public boolean actualizarColonia(int idColonia, String nombre, String codigoPostal, String municipio, String estado) {
        Colonia colonia = new Colonia(idColonia, nombre, codigoPostal, municipio, estado);
        return coloniaDAO.actualizarColonia(colonia);
    }

    // Método para eliminar una colonia por ID
    public boolean eliminarColonia(int idColonia) {
        return coloniaDAO.eliminarColonia(idColonia);
    }

    // Método para listar todas las colonias
    public List<Colonia> listarColonias() {
        return coloniaDAO.listarColonias();
    }
}