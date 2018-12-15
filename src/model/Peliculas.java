package model;

import java.util.ArrayList;

public class Peliculas {

    private ArrayList<Director> direccion = new ArrayList();
    private  ArrayList<Actor> reparto = new ArrayList();

    private String titulo;
    private String ano;
    private Float duracion = 0f;//con punto 1.2
    private String pais;
    //private String direcion;//combertir a coleccion?¿?
    private String guion;
    private String musica;
    private String fotografia;
    //private String reparto;//coleccion¿?¿?
    private String productora;
    private String simnosis;

    public ArrayList<Director> getDireccion() {
        return direccion;
    }

    public void setDireccion(ArrayList<Director> direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Actor> getReparto() {
        return reparto;
    }

    public void setReparto(ArrayList<Actor> reparto) {
        this.reparto = reparto;
    }

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
}