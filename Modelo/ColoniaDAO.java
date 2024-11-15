
package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColoniaDAO {

    private Connection connection;

    public ColoniaDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public boolean insertarColonia(Colonia colonia) {
        String sql = "INSERT INTO Colonia (nombre, codigoPostal, municipio, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, colonia.getNombre());
            statement.setString(2, colonia.getCodigoPostal());
            statement.setString(3, colonia.getMunicipio());
            statement.setString(4, colonia.getEstado());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarColonia(Colonia colonia) {
        String sql = "UPDATE Colonia SET nombre = ?, codigoPostal = ?, municipio = ?, estado = ? WHERE idColonia = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, colonia.getNombre());
            statement.setString(2, colonia.getCodigoPostal());
            statement.setString(3, colonia.getMunicipio());
            statement.setString(4, colonia.getEstado());
            statement.setInt(5, colonia.getIdColonia());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarColonia(int idColonia) {
        String sql = "DELETE FROM Colonia WHERE idColonia = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idColonia);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Colonia> listarColonias() {
        List<Colonia> colonias = new ArrayList<>();
        String sql = "SELECT * FROM Colonia";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Colonia colonia = new Colonia();
                colonia.setIdColonia(resultSet.getInt("idColonia"));
                colonia.setNombre(resultSet.getString("nombre"));
                colonia.setCodigoPostal(resultSet.getString("codigoPostal"));
                colonia.setMunicipio(resultSet.getString("municipio"));
                colonia.setEstado(resultSet.getString("estado"));
                colonias.add(colonia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colonias;
    }
}
