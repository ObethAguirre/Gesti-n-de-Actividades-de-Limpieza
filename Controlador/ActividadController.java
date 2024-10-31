package Controlador;

import Modelo.Actividad;
import Modelo.ActividadDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import java.io.File;

public class ActividadController {
    private ActividadDAO actividadDAO;

    public ActividadController() {
        try {
            actividadDAO = new ActividadDAO();
        } catch (SQLException e) {
            System.err.println("Error al inicializar ActividadDAO: " + e.getMessage());
        }
    }

    // Método para seleccionar una imagen y obtener su ruta
    public String seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen de evidencia");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null; // Si no se selecciona ninguna imagen
    }

    // Método para agregar una nueva actividad con la ruta de la imagen
    public boolean agregarActividad(String descripcion, Date fecha, String hora, String evidenciaImagen) {
        Actividad actividad = new Actividad();
        actividad.setDescripcion(descripcion);
        actividad.setFecha(fecha);
        actividad.setHora(hora);
        actividad.setEvidenciaImagen(evidenciaImagen);

        return actividadDAO.crearActividad(actividad);
    }

    // Método para listar todas las actividades
    public List<Actividad> listarActividades() {
        return actividadDAO.listarActividades();
    }
}
