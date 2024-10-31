
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JefeCuadrillaDAO {
    private Connection connection;

    public JefeCuadrillaDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Método para crear un nuevo jefe de cuadrilla
    public boolean crearJefeCuadrilla(JefeCuadrilla jefe) {
        String sql = "INSERT INTO JefeCuadrilla (nombre, idCuadrilla) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, jefe.getNombre());
            stmt.setInt(2, jefe.getCuadrillaAsignada().getIdCuadrilla());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear jefe de cuadrilla: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener un jefe de cuadrilla por ID
    public JefeCuadrilla obtenerJefeCuadrillaPorId(int idJefe) {
        String sql = "SELECT * FROM JefeCuadrilla WHERE idJefe = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idJefe);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cuadrilla cuadrilla = new CuadrillaDAO().obtenerCuadrillaPorId(rs.getInt("idCuadrilla"));
                return new JefeCuadrilla(
                    rs.getInt("idJefe"),
                    rs.getString("nombre"),
                    cuadrilla
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener jefe de cuadrilla: " + e.getMessage());
        }
        return null;
    }
    // Método para actualizar un jefe de cuadrilla
    public boolean actualizarJefeCuadrilla(JefeCuadrilla jefe) {
        String sql = "UPDATE JefeCuadrilla SET nombre = ?, idCuadrilla = ? WHERE idJefe = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, jefe.getNombre());
            stmt.setInt(2, jefe.getCuadrillaAsignada().getIdCuadrilla());
            stmt.setInt(3, jefe.getIdJefe());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar jefe de cuadrilla: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un jefe de cuadrilla por ID
    public boolean eliminarJefeCuadrilla(int idJefe) {
        String sql = "DELETE FROM JefeCuadrilla WHERE idJefe = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idJefe);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar jefe de cuadrilla: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todos los jefes de cuadrilla
    public List<JefeCuadrilla> listarJefesCuadrilla() {
        List<JefeCuadrilla> jefes = new ArrayList<>();
        String sql = "SELECT * FROM JefeCuadrilla";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            CuadrillaDAO cuadrillaDAO = new CuadrillaDAO(); // Para obtener la cuadrilla asignada
            while (rs.next()) {
                Cuadrilla cuadrilla = cuadrillaDAO.obtenerCuadrillaPorId(rs.getInt("idCuadrilla"));
                JefeCuadrilla jefe = new JefeCuadrilla(
                    rs.getInt("idJefe"),
                    rs.getString("nombre"),
                    cuadrilla
                );
                jefes.add(jefe);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar jefes de cuadrilla: " + e.getMessage());
        }
        return jefes;
    }
}
