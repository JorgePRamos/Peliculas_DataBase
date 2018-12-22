package model;

import java.util.ArrayList;

public class Actor {

    private  String nombre;
    private  String fecha_nac;
    private String nacionalidad;
    private  String fecha_debut;
    private ArrayList<Pelicula> feature = new ArrayList();


    public ArrayList<Pelicula> getFeatures() {
        return feature;
    }

    public void addFeatured(Pelicula nuevaActuacion) {
        this.feature.add(nuevaActuacion);
        }


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

    public String getFecha_debut() {
        return fecha_debut;
    }

    public void setFecha_debut(String fecha_debut) {
        this.fecha_debut = fecha_debut;
    }

    }

