
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColoniaDAO {
    private Connection connection;

    public ColoniaDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Método para crear una nueva colonia
    public boolean crearColonia(Colonia colonia) {
        String sql = "INSERT INTO Colonia (nombre, codigoPostal, municipio, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, colonia.getNombre());
            stmt.setString(2, colonia.getCodigoPostal());
            stmt.setString(3, colonia.getMunicipio());
            stmt.setString(4, colonia.getEstado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear colonia: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener una colonia por ID
    public Colonia obtenerColoniaPorId(int idColonia) {
        String sql = "SELECT * FROM Colonia WHERE idColonia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idColonia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Colonia(
                    rs.getInt("idColonia"),
                    rs.getString("nombre"),
                    rs.getString("codigoPostal"),
                    rs.getString("municipio"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener colonia: " + e.getMessage());
        }
        return null;
    }

    // Método para actualizar una colonia
    public boolean actualizarColonia(Colonia colonia) {
        String sql = "UPDATE Colonia SET nombre = ?, codigoPostal = ?, municipio = ?, estado = ? WHERE idColonia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, colonia.getNombre());
            stmt.setString(2, colonia.getCodigoPostal());
            stmt.setString(3, colonia.getMunicipio());
            stmt.setString(4, colonia.getEstado());
            stmt.setInt(5, colonia.getIdColonia());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar colonia: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar una colonia por ID
    public boolean eliminarColonia(int idColonia) {
        String sql = "DELETE FROM Colonia WHERE idColonia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idColonia);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar colonia: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todas las colonias
    public List<Colonia> listarColonias() {
        List<Colonia> colonias = new ArrayList<>();
        String sql = "SELECT * FROM Colonia";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Colonia colonia = new Colonia(
                    rs.getInt("idColonia"),
                    rs.getString("nombre"),
                    rs.getString("codigoPostal"),
                    rs.getString("municipio"),
                    rs.getString("estado")
                );
                colonias.add(colonia);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar colonias: " + e.getMessage());
        }
        return colonias;
    }
}