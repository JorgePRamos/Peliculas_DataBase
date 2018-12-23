package model;
import static java.lang.System.exit;
import static java.lang.System.out;
import model.Filmoteca;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    Filmoteca f = new Filmoteca();
    String delimiter = "#";


    //------------------------------------------------------------------------------------
    //------------- Funciones Peliculas
    public Pelicula import_Pelitxt(Path path){
        List<String> lines = null;
        Pelicula peli  =  new Pelicula();//Def Constructor
    try {
        lines = Files.readAllLines(path);
    }catch (Exception e){
        out.printf("Error al leer el fichero de texto [pelicuals.txt]\n");
        exit(-1);
        }

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
  //          addActor(Actor nuevoActor = tempString[9]);
//            addDirector(Actor nuevoDirector = tempString[10]);

    }



            return peli;
        }
//-----------------------------------------------------------------------------------

    public void  addFilmToCollection(Pelicula nuevaPeli){
        f.addPeliculaFilmoteca(nuevaPeli);
    }

//-----------------------------------------------------------------------------------
    //------------- Funciones Directores

    public Director import_Directxt(Path path){
        List<String> lines = null;
        Director dir  =  new Director();//Def Constructor
        try {
            lines = Files.readAllLines(path);
        }catch (Exception e){
            out.printf("Error al leer el fichero de texto [directores.txt]\n");
            exit(-1);
        }

        for(String ln : lines){
            String [] tempString = ln.split(delimiter);


            dir.setNombre(tempString[0]);
            dir.setFecha_nac(tempString[1]);
            dir.setNacionalidad(tempString[2]);
            dir.setOcupacion(tempString[3]);

            //         dir.addObra(Pelicula nuevoPeliculas = tempString[4]);

        }



        return dir;
    }
//------------------------------------------------------------------------------------

    public void  addDirectorToCollection(Director nuevoDir){
        f.addDirectorGremio(nuevoDir);
    }
//-----------------------------------------------------------------------------------

    //------------- Funciones Actores

    public Actor import_Acttxt(Path path){
        List<String> lines = null;
        Actor act  =  new Actor();//Def Constructor
        try {
            lines = Files.readAllLines(path);
        }catch (Exception e){
            out.printf("Error al leer el fichero de texto [actores.txt]\n");
            exit(-1);
        }

        for(String ln : lines){
            String [] tempString = ln.split(delimiter);


            act.setNombre(tempString[0]);
            act.setFecha_nac(tempString[1]);
            act.setNacionalidad((tempString[2]));
            act.setFecha_debut(tempString[3]);
            //          addPelicula(Pelicula nuevoPelicula = tempString[4]);

        }



        return act;
    }
//------------------------------------------------------------------------------------

    public void  addActorToCollection(Actor nuevoActor){
        f.addActorGremio(nuevoActor);
    }
//-----------------------------------------------------------------------------------

public  String[] addHtmlRow(){
        String [] arrayString =  new String[f.getNpeliculas()];
        for(Pelicula peliTemp : f.getFilmoteca()){
            String.format("<TR>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%f</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"//7
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "</TR>", peliTemp.getTitulo(), peliTemp.getAno(), peliTemp.getDuracion(), peliTemp.getPais(), peliTemp.getGuion(),
                    peliTemp.getMusica(), peliTemp.getFotografia(), peliTemp.getProductora(), peliTemp.getSimnosis(), peliTemp.getDireccion(), peliTemp.getReparto());
        }

    return arrayString;
}//addHtmlRow

//-----------------------------------------------------------------------------------

    public void saveFilmsBin(ArrayList<Pelicula> binFilmsList, String direccion ) {
        Path rutaCarpeta = Paths.get(direccion);
        //Path p = path al file;
        //Filmoteca es la clase que vamos a escribir
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(rutaCarpeta.toFile());
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(binFilmsList);
            oos.close();

        } catch (IOException e) {
              System.out.println("no fue posible guardar el file");
            System.out.println(e.toString());

        }
        /*finally {
            if(null != oos){
                try {
                    oos.close();
                   }catch (IOException e) {
                    Logger.getLogger(Filmoteca.class.getName().log(Level.SEVERE,null, e));
                    //Sustituir por un error normal Da ERROR
                }
            }
        }*/
    }//Fin Metodo saveFilmsBin

//-----------------------------------------------------------------------------------


    public void saveActorBin(ArrayList<Actor> binFilmsList, String direccion ) {
        Path rutaCarpeta = Paths.get(direccion);
        //Path p = path al file;
        //Filmoteca es la clase que vamos a escribir
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(rutaCarpeta.toFile());
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(binFilmsList);
            oos.close();

        } catch (IOException e) {
            System.out.println("no fue posible guardar el file");
            System.out.println(e.toString());

        }
        /*finally {
            if(null != oos){
                try {
                    oos.close();
                   }catch (IOException e) {
                    Logger.getLogger(Filmoteca.class.getName().log(Level.SEVERE,null, e));
                    //Sustituir por un error normal Da ERROR
                }
            }
        }*/
    }//Fin Metodo saveActorBin

//-----------------------------------------------------------------------------------

    public void saveDirectorBin(ArrayList<Director> binFilmsList, String direccion ) {
        Path rutaCarpeta = Paths.get(direccion);
        //Path p = path al file;
        //Filmoteca es la clase que vamos a escribir
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(rutaCarpeta.toFile());
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(binFilmsList);
            oos.close();

        } catch (IOException e) {
            System.out.println("no fue posible guardar el file");
            System.out.println(e.toString());

        }
        /*finally {
            if(null != oos){
                try {
                    oos.close();
                   }catch (IOException e) {
                    Logger.getLogger(Filmoteca.class.getName().log(Level.SEVERE,null, e));
                    //Sustituir por un error normal Da ERROR
                }
            }
        }*/
    }//Fin Metodo saveDirectorBin

//-----------------------------------------------------------------------------------



}//Fin Model
