
package Controlador;
import Modelo.Administrador;
import Modelo.AdministradorDAO;

import java.sql.SQLException;
import java.util.List;

public class AdministradorController {
    private AdministradorDAO administradorDAO;

    // Constructor que inicializa el DAO
    public AdministradorController() {
        try {
            administradorDAO = new AdministradorDAO();
        } catch (SQLException e) {
            System.err.println("Error al inicializar AdministradorDAO: " + e.getMessage());
        }
    }

    // Método para agregar un nuevo administrador
    public boolean agregarAdministrador(String usuario, String contraseña) {
        Administrador administrador = new Administrador();
        administrador.setUsuario(usuario);
        administrador.setContraseña(contraseña);

        return administradorDAO.crearAdministrador(administrador);
    }

    // Método para obtener un administrador por ID
    public Administrador obtenerAdministrador(int idAdministrador) {
        return administradorDAO.obtenerAdministradorPorId(idAdministrador);
    }

    // Método para actualizar un administrador
    public boolean actualizarAdministrador(int idAdministrador, String usuario, String contraseña) {
        Administrador administrador = new Administrador(idAdministrador, usuario, contraseña);
        return administradorDAO.actualizarAdministrador(administrador);
    }

    // Método para eliminar un administrador por ID
    public boolean eliminarAdministrador(int idAdministrador) {
        return administradorDAO.eliminarAdministrador(idAdministrador);
    }

    // Método para listar todos los administradores
    public List<Administrador> listarAdministradores() {
        return administradorDAO.listarAdministradores();
    }

    // Método para validar las credenciales de inicio de sesión
    public boolean validarCredenciales(String usuario, String contraseña) {
        return administradorDAO.validarCredenciales(usuario, contraseña);
    }
}
