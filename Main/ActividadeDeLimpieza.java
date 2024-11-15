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

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ActividadeDeLimpieza {

    public static void main(String[] args) throws SQLException {

        // Pruebas con AdministradorController
        AdministradorController adminController = new AdministradorController();

        // Agregamos un administrador
        //adminController.agregarAdministrador("admin3", "password123");
        listarAdministradores(adminController);
        
        // Validación de credenciales para login
        if (adminController.validarCredenciales("admin3", "password123")) {
            JOptionPane.showMessageDialog(null, "BIENVENIDO");
            
            ActividadController actividadController = new ActividadController();

            // Seleccionar la imagen y obtener la ruta
            String rutaImagen = seleccionarImagen();
            
            if (rutaImagen != null) {
                // Crear una fecha y hora para la actividad
                Date fecha = new Date(System.currentTimeMillis());
                Time hora = Time.valueOf("10:00:00");
                
                // Agregar la actividad usando la ruta de la imagen
                boolean agregado = actividadController.agregarActividad("Limpieza en el parque", fecha, hora, rutaImagen);
                if (agregado) {
                     JOptionPane.showMessageDialog(null,"Actividad agregada con éxito.");
                } else {
                     JOptionPane.showMessageDialog(null,"Error al agregar la actividad.");
                }
            } else {
                 JOptionPane.showMessageDialog(null,"No se seleccionó ninguna imagen.");
            }

            // Listar actividades
            listarActividades(actividadController);

            // Pruebas con ColoniaController
            ColoniaController coloniaController = new ColoniaController();

            // Agregar una nueva colonia
            coloniaController.agregarColonia("Colonia Centro", "25000", "Saltillo", "Coahuila");
            listarColonias(coloniaController);

            // Pruebas con CuadrillaController
            CuadrillaController cuadrillaController = new CuadrillaController();

            // Agregar una nueva cuadrilla
            cuadrillaController.agregarCuadrilla("Cuadrilla 1");
            listarCuadrillas(cuadrillaController);

            // Pruebas con JefeCuadrillaController
            JefeCuadrillaController jefeCuadrillaController = new JefeCuadrillaController();

            // Agregar un nuevo jefe de cuadrilla
            Cuadrilla cuadrillaAsignada = cuadrillaController.obtenerCuadrilla(1);
            jefeCuadrillaController.agregarJefeCuadrilla("Jefe Juan", cuadrillaAsignada);
            listarJefesCuadrilla(jefeCuadrillaController);

        } else {
            JOptionPane.showMessageDialog(null, "Usuario no existe");
        }
    }

    // Método para seleccionar una imagen
    private static String seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            return archivoSeleccionado.getAbsolutePath();
        }
        return null;
    }

    // Lista todos los administradores
    private static void listarAdministradores(AdministradorController adminController) {
        List<Administrador> administradores = adminController.listarAdministradores();
        System.out.println("\n Lista de administradores:");
        for (Administrador admin : administradores) {
            System.out.println("ID: " + admin.getIdAdministrador() + "\t Usuario: " + admin.getUsuario());
        }
    }

    // Lista todas las actividades
    private static void listarActividades(ActividadController actividadController) {
        List<Actividad> actividades = actividadController.listarActividades();
        System.out.println("\n Lista de actividades:");
        for (Actividad actividad : actividades) {
            System.out.println("ID: " + actividad.getIdActividad() +
                               "\n Descripción: " + actividad.getDescripcion() +
                               "\n Fecha: " + actividad.getFecha() +
                               "\n Hora: " + actividad.getHora() +
                               "\n Imagen: " + actividad.getEvidenciaImagen());
        }
    }

    // Lista todas las colonias
    private static void listarColonias(ColoniaController coloniaController) {
        List<Colonia> colonias = coloniaController.listarColonias();
        System.out.println("\n Lista de colonias:");
        for (Colonia colonia : colonias) {
            System.out.println("ID: " + colonia.getIdColonia() + "\t Nombre: " + colonia.getNombre());
        }
    }

    // Lista todas las cuadrillas
    private static void listarCuadrillas(CuadrillaController cuadrillaController) {
        List<Cuadrilla> cuadrillas = cuadrillaController.listarCuadrillas();
        System.out.println("\n Lista de cuadrillas:");
        for (Cuadrilla cuadrilla : cuadrillas) {
            System.out.println("ID: " + cuadrilla.getIdCuadrilla() + "\t Nombre: " + cuadrilla.getNombreCuadrilla());
        }
    }

    // Lista todos los jefes de cuadrilla
    private static void listarJefesCuadrilla(JefeCuadrillaController jefeCuadrillaController) {
        List<JefeCuadrilla> jefes = jefeCuadrillaController.listarJefesCuadrilla();
        System.out.println("\n Lista de jefes de cuadrilla:");
        for (JefeCuadrilla jefe : jefes) {
            System.out.println("ID: " + jefe.getIdJefe() + "\t Nombre: " + jefe.getNombre());
        }
    }
}
