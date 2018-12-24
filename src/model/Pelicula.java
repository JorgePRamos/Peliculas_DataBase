package model;

import java.util.ArrayList;

public class Pelicula {

    private ArrayList<String> direccion = new ArrayList();
    private  ArrayList<String> reparto = new ArrayList();
    private String titulo;
    private String ano;
    private Float duracion = 0f; //Separados por < . >
    private String pais;
    private String guion;
    private String musica;
    private String fotografia;
    private String productora;
    private String simnosis;

//------------------------------------------------------
//---------        Constructors     --------------------

    public Pelicula() {//Default COnstructor
        this.titulo = "Empty Title";
        this.ano = "DD/MM/YYYY";
        this.duracion = 0f;
        this.pais = "La Tierra";
        this.guion = "Anonimo";
        this.musica = "ACME";
        this.fotografia = "ACME";
        this.productora = "ACME";
        this.fotografia = "ACME";
        this.simnosis = "Blank";
    }
    //------------------------------------------------------
    // -------     Adders and Getters      -----------------

    public ArrayList<String> getDireccion() {
        return direccion;
    }

    public void addDirector(String nuevoDirector) {
       this.direccion.add(nuevoDirector);
    }


    public ArrayList<String> getReparto() {
        return reparto;
    }

    public void addActor(String nuevoActor) {
        this.reparto.add(nuevoActor);
    }



    //------------------------------------------------------
    // -------     Setters and Getters      ----------------

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Float getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGuion() {
        return guion;
    }

    public void setGuion(String guion) {
        this.guion = guion;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getSimnosis() {
        return simnosis;
    }

    public void setSimnosis(String simnosis) {
        this.simnosis = simnosis;
    }
}//Fin clase Pelicula
