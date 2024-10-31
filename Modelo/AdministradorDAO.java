
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {
    private Connection connection;

    public AdministradorDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Método para crear un nuevo administrador
    public boolean crearAdministrador(Administrador administrador) {
        String sql = "INSERT INTO Administrador (usuario, contraseña) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, administrador.getUsuario());
            stmt.setString(2, administrador.getContraseña());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear administrador: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener un administrador por ID
    public Administrador obtenerAdministradorPorId(int idAdministrador) {
        String sql = "SELECT * FROM Administrador WHERE idAdministrador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAdministrador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Administrador(
                    rs.getInt("idAdministrador"),
                    rs.getString("usuario"),
                    rs.getString("contraseña")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener administrador: " + e.getMessage());
        }
        return null;
    }

    // Método para actualizar un administrador
    public boolean actualizarAdministrador(Administrador administrador) {
        String sql = "UPDATE Administrador SET usuario = ?, contraseña = ? WHERE idAdministrador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, administrador.getUsuario());
            stmt.setString(2, administrador.getContraseña());
            stmt.setInt(3, administrador.getIdAdministrador());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar administrador: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un administrador por ID
    public boolean eliminarAdministrador(int idAdministrador) {
        String sql = "DELETE FROM Administrador WHERE idAdministrador = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAdministrador);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar administrador: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todos los administradores
    public List<Administrador> listarAdministradores() {
        List<Administrador> administradores = new ArrayList<>();
        String sql = "SELECT * FROM Administrador";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Administrador administrador = new Administrador(
                    rs.getInt("idAdministrador"),
                    rs.getString("usuario"),
                    rs.getString("contraseña")
                );
                administradores.add(administrador);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar administradores: " + e.getMessage());
        }
        return administradores;
    }

    // Método para validar el inicio de sesión de un administrador
    public boolean validarCredenciales(String usuario, String contraseña) {
        String sql = "SELECT * FROM Administrador WHERE usuario = ? AND contraseña = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Devuelve true si hay una coincidencia
        } catch (SQLException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
            return false;
        }
    }
}