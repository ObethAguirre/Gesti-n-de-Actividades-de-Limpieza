
package FactoryMethod;
import Modelo.Actividad;
import Modelo.Colonia;
import Modelo.Cuadrilla;
import Modelo.JefeCuadrilla;

public class GeneralEntityFactory extends EntityFactory {
    @Override
    public Object crearEntidad(String tipo) {
        switch (tipo) {
            case "Actividad":
                return new Actividad();
            case "Colonia":
                return new Colonia();
            case "Cuadrilla":
                return new Cuadrilla();
            case "JefeCuadrilla":
                return new JefeCuadrilla();
            default:
                throw new IllegalArgumentException("Tipo de entidad no reconocido: " + tipo);
        }
    }
}
