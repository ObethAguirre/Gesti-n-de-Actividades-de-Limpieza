package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {

    private Connection connection;

    public ActividadDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public boolean insertarActividad(Actividad actividad) {
        String sql = "INSERT INTO Actividad (descripcion, fecha, hora, evidenciaImagen) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, actividad.getDescripcion());
            statement.setDate(2, (Date) actividad.getFecha());
            statement.setTime(3, actividad.getHora());
            statement.setString(4, actividad.getEvidenciaImagen());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarActividad(Actividad actividad) {
        String sql = "UPDATE Actividad SET descripcion = ?, fecha = ?, hora = ?, evidenciaImagen = ? WHERE idActividad = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, actividad.getDescripcion());
            statement.setDate(2, (Date) actividad.getFecha());
            statement.setTime(3, actividad.getHora());
            statement.setString(4, actividad.getEvidenciaImagen());
            statement.setInt(5, actividad.getIdActividad());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarActividad(int idActividad) {
        String sql = "DELETE FROM Actividad WHERE idActividad = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idActividad);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Actividad> listarActividades() {
        List<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT * FROM Actividad";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Actividad actividad = new Actividad();
                actividad.setIdActividad(resultSet.getInt("idActividad"));
                actividad.setDescripcion(resultSet.getString("descripcion"));
                actividad.setFecha(resultSet.getDate("fecha"));
                actividad.setHora(resultSet.getTime("hora"));
                actividad.setEvidenciaImagen(resultSet.getString("evidenciaImagen"));
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividades;
    }
}
