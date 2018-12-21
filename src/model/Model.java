package model;
import static java.lang.System.out;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class Model {
    String delimiter = "#";

    public void import_txt(File path){

        Pelicula peli  =  new Pelicula();//Def Constructor

        List<String> lines = Files.readAllLines(path);


        for(String ln : lines){
            String [] tempString = ln.split(delimiter);


            peli.setTitulo(tempString[0]);
            peli.setAno(tempString[1]);
            peli.setDuracion(Float.parseFloat(tempString[2]));
            peli.setPais(tempString[3]);
            peli.setGuion(tempString[4]);
            peli.setMusica(tempString[5]);
            peli.setFotografia(tempString[6]);
            peli.setProductora(tempString[7]);
            peli.setSimnosis(tempString[8]);
            //fata repart<> 9 tab
            //falta diraccion <> 10 tab

    }





}
