package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Director implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String fecha_nac;
    private String nacionalidad;
    private String ocupacion;
     ArrayList<String> obras = new ArrayList();



    //------------------------------------------------------
//---------        Constructors     --------------------

    public Director() {//Default Constructor
        this.nombre = "Fulanito de Tal";
        this.fecha_nac = "DD/MM/YYYY";
        this.nacionalidad = "La Tierra";
        this.ocupacion = "Blade Runner Retirado";
    }
    //------------------------------------------------------
    // -------     Adders and Getters      -----------------
    public ArrayList<String> getObras() {
        return obras;
    }

    public void addObra(String nuevaObra) {
        this.obras.add(nuevaObra);
    }


    public  void borrarObra(int index){
        this.obras.remove(index);

    }

    //------------------------------------------------------
    // -------     Setters and Getters      ----------------

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



}//Fin clase Director