
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuadrillaDAO {
    private Connection connection;

    public CuadrillaDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Método para crear una nueva cuadrilla
    public boolean crearCuadrilla(Cuadrilla cuadrilla) {
        String sql = "INSERT INTO Cuadrilla (nombreCuadrilla) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cuadrilla.getNombreCuadrilla());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear cuadrilla: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener una cuadrilla por ID
    public Cuadrilla obtenerCuadrillaPorId(int idCuadrilla) {
        String sql = "SELECT * FROM Cuadrilla WHERE idCuadrilla = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cuadrilla(
                    rs.getInt("idCuadrilla"),
                    rs.getString("nombreCuadrilla")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cuadrilla: " + e.getMessage());
        }
        return null;
    }

    // Método para actualizar una cuadrilla
    public boolean actualizarCuadrilla(Cuadrilla cuadrilla) {
        String sql = "UPDATE Cuadrilla SET nombreCuadrilla = ? WHERE idCuadrilla = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cuadrilla.getNombreCuadrilla());
            stmt.setInt(2, cuadrilla.getIdCuadrilla());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cuadrilla: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar una cuadrilla por ID
    public boolean eliminarCuadrilla(int idCuadrilla) {
        String sql = "DELETE FROM Cuadrilla WHERE idCuadrilla = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCuadrilla);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cuadrilla: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todas las cuadrillas
    public List<Cuadrilla> listarCuadrillas() {
        List<Cuadrilla> cuadrillas = new ArrayList<>();
        String sql = "SELECT * FROM Cuadrilla";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cuadrilla cuadrilla = new Cuadrilla(
                    rs.getInt("idCuadrilla"),
                    rs.getString("nombreCuadrilla")
                );
                cuadrillas.add(cuadrilla);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cuadrillas: " + e.getMessage());
        }
        return cuadrillas;
    }
}