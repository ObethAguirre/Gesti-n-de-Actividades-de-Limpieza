
package Modelo;

import java.util.Date;
import java.sql.Time;

public class Actividad {
    private int idActividad;
    private String descripcion;
    private Date fecha;
    private Time hora;
    private String evidenciaImagen;

    // Getters y Setters
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getEvidenciaImagen() {
        return evidenciaImagen;
    }

    public void setEvidenciaImagen(String evidenciaImagen) {
        this.evidenciaImagen = evidenciaImagen;
    }
}
