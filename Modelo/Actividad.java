
package Modelo;

import java.util.Date;

public class Actividad {
    private int idActividad;
    private String descripcion;
    private Date fecha;
    private String hora;
    private String evidenciaImagen;

    public Actividad(int idActividad, String descripcion, Date fecha, String hora, String evidenciaImagen) {
        this.idActividad = idActividad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.evidenciaImagen = evidenciaImagen;
    }

    public Actividad() {
    }
    
    

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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEvidenciaImagen() {
        return evidenciaImagen;
    }

    public void setEvidenciaImagen(String evidenciaImagen) {
        this.evidenciaImagen = evidenciaImagen;
    }
    
}
