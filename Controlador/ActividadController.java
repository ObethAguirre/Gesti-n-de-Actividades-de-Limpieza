package Controlador;
import FactoryMethod.EntityFactory;
import FactoryMethod.GeneralEntityFactory;
import Modelo.Actividad;
import Modelo.ActividadDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class ActividadController {
    private EntityFactory factory;
    private ActividadDAO actividadDAO;

    public ActividadController() throws SQLException {
        this.factory = new GeneralEntityFactory();
        this.actividadDAO = new ActividadDAO();
    }
    
    public boolean agregarActividad(String descripcion, Date fecha, Time hora, String evidenciaImagen) {
    Actividad actividad = (Actividad) factory.crearEntidad("Actividad");
    actividad.setDescripcion(descripcion);
    actividad.setFecha(fecha);
    actividad.setHora(hora);
    actividad.setEvidenciaImagen(evidenciaImagen);

    return actividadDAO.insertarActividad(actividad);
}


    public boolean crearActividad(String descripcion, Date fecha, Time hora, String evidenciaImagen) {
        Actividad actividad = (Actividad) factory.crearEntidad("Actividad");
        actividad.setDescripcion(descripcion);
        actividad.setFecha(fecha);
        actividad.setHora(hora);
        actividad.setEvidenciaImagen(evidenciaImagen);

        return actividadDAO.insertarActividad(actividad);
    }

    public boolean actualizarActividad(int id, String descripcion, Date fecha, Time hora, String evidenciaImagen) {
        Actividad actividad = (Actividad) factory.crearEntidad("Actividad");
        actividad.setIdActividad(id);
        actividad.setDescripcion(descripcion);
        actividad.setFecha(fecha);
        actividad.setHora(hora);
        actividad.setEvidenciaImagen(evidenciaImagen);

        return actividadDAO.actualizarActividad(actividad);
    }

    public boolean eliminarActividad(int idActividad) {
        return actividadDAO.eliminarActividad(idActividad);
    }

    public List<Actividad> listarActividades() {
        return actividadDAO.listarActividades();
    }
}
