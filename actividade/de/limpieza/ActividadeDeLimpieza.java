
package actividade.de.limpieza;

import Controlador.AdministradorController;
import Controlador.ActividadController;
import Controlador.ColoniaController;
import Controlador.CuadrillaController;
import Controlador.JefeCuadrillaController;
import Modelo.Administrador;
import Modelo.Actividad;
import Modelo.Colonia;
import Modelo.Cuadrilla;
import Modelo.JefeCuadrilla;
import java.io.File;

import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;

public class ActividadeDeLimpieza {

    public static void main(String[] args) {
        // Pruebas con AdministradorController
        AdministradorController adminController = new AdministradorController();
        
        // Agregar, Listar, Obtener, Actualizar y Eliminar Administrador
        adminController.agregarAdministrador("admin1", "password123");
        listarAdministradores(adminController);

        
        ActividadController actividadController = new ActividadController();

        // Seleccionar la imagen y obtener la ruta
        String rutaImagen = actividadController.seleccionarImagen();

        if (rutaImagen != null) {
            // Agregar la actividad usando la ruta de la imagen
            boolean agregado = actividadController.agregarActividad("Limpieza en el parque", new Date(), "10:00", rutaImagen);
            if (agregado) {
                System.out.println("Actividad agregada con éxito.");
            } else {
                System.out.println("Error al agregar la actividad.");
            }
        } else {
            System.out.println("No se seleccionó ninguna imagen.");
        }
    

        //Listar Actividades
        listarActividades(actividadController);
        
        // Pruebas con ColoniaController
        ColoniaController coloniaController = new ColoniaController();
        
        // Agregar una nueva Colonia
        coloniaController.agregarColonia("Colonia Centro", "25000", "Saltillo", "Coahuila");
        listarColonias(coloniaController);

        // Pruebas con CuadrillaController
        CuadrillaController cuadrillaController = new CuadrillaController();
        
        // Agregar una nueva Cuadrilla
        cuadrillaController.agregarCuadrilla("Cuadrilla 1");
        listarCuadrillas(cuadrillaController);

        // Pruebas con JefeCuadrillaController
        JefeCuadrillaController jefeCuadrillaController = new JefeCuadrillaController();
        
        // Agregar un nuevo Jefe de Cuadrilla
        Cuadrilla cuadrillaAsignada = cuadrillaController.obtenerCuadrilla(1);
        jefeCuadrillaController.agregarJefeCuadrilla("Jefe Juan", cuadrillaAsignada);
        listarJefesCuadrilla(jefeCuadrillaController);
    }

    // Métodos de Listado para cada entidad

    // Lista todos los administradores
    private static void listarAdministradores(AdministradorController adminController) {
        List<Administrador> administradores = adminController.listarAdministradores();
        System.out.println("Lista de administradores:");
        for (Administrador admin : administradores) {
            System.out.println("ID: " + admin.getIdAdministrador() + ", Usuario: " + admin.getUsuario());
        }
    }

// Lista todas las actividades
private static void listarActividades(ActividadController actividadController) {
    List<Actividad> actividades = actividadController.listarActividades();
    System.out.println("Lista de actividades:");
    for (Actividad actividad : actividades) {
        System.out.println("ID: " + actividad.getIdActividad() +
                           ", Descripción: " + actividad.getDescripcion() +
                           ", Fecha: " + actividad.getFecha() +
                           ", Hora: " + actividad.getHora() +
                           ", Imagen: " + actividad.getEvidenciaImagen()); // Mostrar ruta de la imagen
    }
}


    // Lista todas las colonias
    private static void listarColonias(ColoniaController coloniaController) {
        List<Colonia> colonias = coloniaController.listarColonias();
        System.out.println("Lista de colonias:");
        for (Colonia colonia : colonias) {
            System.out.println("ID: " + colonia.getIdColonia() + ", Nombre: " + colonia.getNombre());
        }
    }

    // Lista todas las cuadrillas
    private static void listarCuadrillas(CuadrillaController cuadrillaController) {
        List<Cuadrilla> cuadrillas = cuadrillaController.listarCuadrillas();
        System.out.println("Lista de cuadrillas:");
        for (Cuadrilla cuadrilla : cuadrillas) {
            System.out.println("ID: " + cuadrilla.getIdCuadrilla() + ", Nombre: " + cuadrilla.getNombreCuadrilla());
        }
    }

    // Lista todos los jefes de cuadrilla
    private static void listarJefesCuadrilla(JefeCuadrillaController jefeCuadrillaController) {
        List<JefeCuadrilla> jefes = jefeCuadrillaController.listarJefesCuadrilla();
        System.out.println("Lista de jefes de cuadrilla:");
        for (JefeCuadrilla jefe : jefes) {
            System.out.println("ID: " + jefe.getIdJefe() + ", Nombre: " + jefe.getNombre());
        }
    }
}


