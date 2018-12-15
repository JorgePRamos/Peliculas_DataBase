package model;

import java.util.ArrayList;

public class Director {
    private String nombre;
    private String fecha_nac;
    private String nacionalidad;
    private String ocupacion;
    private ArrayList<Peliculas> obras = new ArrayList();


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }






    public ArrayList<model.Peliculas> getObras() {
        return obras;
    }

    public void setObras(ArrayList<model.Peliculas> obras) {
        this.obras = obras;
    }
}
