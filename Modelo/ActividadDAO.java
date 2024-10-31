package Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {
    private Connection connection;

    public ActividadDAO() throws SQLException {
        // Obtener la conexión de la clase DatabaseConnection
        connection = DatabaseConnection.getConnection();
    }

    // Método para crear una nueva actividad en la base de datos
    public boolean crearActividad(Actividad actividad) {
        String sql = "INSERT INTO Actividad (descripcion, fecha, hora, evidenciaImagen) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, actividad.getDescripcion());
            stmt.setDate(2, new java.sql.Date(actividad.getFecha().getTime()));
            stmt.setString(3, actividad.getHora());
            stmt.setString(4, actividad.getEvidenciaImagen());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear actividad: " + e.getMessage());
            return false;
        }
    }

    // Método para leer una actividad por ID
    public Actividad obtenerActividadPorId(int idActividad) {
        String sql = "SELECT * FROM Actividad WHERE idActividad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idActividad);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Actividad(
                    rs.getInt("idActividad"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha"),
                    rs.getString("hora"),
                    rs.getString("evidenciaImagen")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener actividad: " + e.getMessage());
        }
        return null;
    }

    // Método para actualizar una actividad existente
    public boolean actualizarActividad(Actividad actividad) {
        String sql = "UPDATE Actividad SET descripcion = ?, fecha = ?, hora = ?, evidenciaImagen = ? WHERE idActividad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, actividad.getDescripcion());
            stmt.setDate(2, new java.sql.Date(actividad.getFecha().getTime()));
            stmt.setString(3, actividad.getHora());
            stmt.setString(4, actividad.getEvidenciaImagen());
            stmt.setInt(5, actividad.getIdActividad());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar actividad: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar una actividad por ID
    public boolean eliminarActividad(int idActividad) {
        String sql = "DELETE FROM Actividad WHERE idActividad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idActividad);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar actividad: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todas las actividades
    public List<Actividad> listarActividades() {
        List<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT * FROM Actividad";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Actividad actividad = new Actividad(
                    rs.getInt("idActividad"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha"),
                    rs.getString("hora"),
                    rs.getString("evidenciaImagen")
                );
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar actividades: " + e.getMessage());
        }
        return actividades;
    }
}
