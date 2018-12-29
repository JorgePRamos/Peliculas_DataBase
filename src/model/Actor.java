package model;
import java.util.ArrayList;
import java.io.Serializable;

public class Actor implements Serializable{
    private static final long serialVersionUID = 1L;
    private  String nombre;
    private  String fecha_nac;
    private String nacionalidad;
    private  String fecha_debut;
    ArrayList<String> feature = new ArrayList();

    //----------------------------------------------------------------------------------------------
//---------------------          Constructors             ------------------------------------------

    public Actor() {//Default Constructor
        this.nombre = "Fulanito de Tal";
        this.fecha_nac = "DD/MM/YYYY";
        this.nacionalidad = "La Tierra";
        this.fecha_debut = "DD/MM/YYYY";
    }

    //----------------------------------------------------------------------------------------------
    //---------------------          Adders and Getters             --------------------------------
    public ArrayList<String> getFeatures() {
        return feature;
    }

    public void addFeatured(String nuevaActuacion) {
        this.feature.add(nuevaActuacion);
    }

    public  void borrarFeature(int index){
        this.feature.remove(index);

    }

    //----------------------------------------------------------------------------------------------
    //---------------------          Setters and Getters            --------------------------------
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

}//Fin clase Actor