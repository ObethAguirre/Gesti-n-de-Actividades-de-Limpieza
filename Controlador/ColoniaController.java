
package Controlador;
import FactoryMethod.EntityFactory;
import FactoryMethod.GeneralEntityFactory;
import Modelo.Colonia;
import Modelo.ColoniaDAO;
import java.sql.SQLException;
import java.util.List;

public class ColoniaController {
    private EntityFactory factory;
    private ColoniaDAO coloniaDAO;

    public ColoniaController() throws SQLException {
        this.factory = new GeneralEntityFactory();
        this.coloniaDAO = new ColoniaDAO();
    }
    
    public boolean agregarColonia(String nombre, String codigoPostal, String municipio, String estado) {
    Colonia colonia = (Colonia) factory.crearEntidad("Colonia");
    colonia.setNombre(nombre);
    colonia.setCodigoPostal(codigoPostal);
    colonia.setMunicipio(municipio);
    colonia.setEstado(estado);

    return coloniaDAO.insertarColonia(colonia);
}


    public boolean crearColonia(String nombre, String codigoPostal, String municipio, String estado) {
        Colonia colonia = (Colonia) factory.crearEntidad("Colonia");
        colonia.setNombre(nombre);
        colonia.setCodigoPostal(codigoPostal);
        colonia.setMunicipio(municipio);
        colonia.setEstado(estado);

        return coloniaDAO.insertarColonia(colonia);
    }

    public boolean actualizarColonia(int id, String nombre, String codigoPostal, String municipio, String estado) {
        Colonia colonia = (Colonia) factory.crearEntidad("Colonia");
        colonia.setIdColonia(id);
        colonia.setNombre(nombre);
        colonia.setCodigoPostal(codigoPostal);
        colonia.setMunicipio(municipio);
        colonia.setEstado(estado);

        return coloniaDAO.actualizarColonia(colonia);
    }

    public boolean eliminarColonia(int idColonia) {
        return coloniaDAO.eliminarColonia(idColonia);
    }

    public List<Colonia> listarColonias() {
        return coloniaDAO.listarColonias();
    }
}
