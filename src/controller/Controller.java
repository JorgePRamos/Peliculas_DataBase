package controller;

import java.io.File;

public class Controller {

    public void arranque(){
        boolean exist;
    String ruta = System.getProperty("user.home") +
                    "Desktop/Filmot18/";
        File pelicula = new File(ruta + "películas.bin");
        File actores = new File(ruta + "actores.bin");
        File directores = new File(ruta + "directores.bin");

        if(pelicula.exists()){
            System.out.printf("Exixte peliculas.bin\n");

        }else{
            System.out.printf("No existe pelicuas.bin\n");
            //importar fichero texto

        }

        if(actores.exists()){
            System.out.printf("Exixte actores.bin\n");

        }else{
            System.out.printf("No existe actores.bin\n");
            //importar fichero texto

        }
        if(directores.exists()){
            System.out.printf("Exixte directores.bin\n");

        }else{
            System.out.printf("No existe directores.bin\n");
            //importar fichero texto

        }

    }

}
