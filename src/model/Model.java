package model;
import static java.lang.System.exit;
import static java.lang.System.out;
import model.Filmoteca;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Model {

    Filmoteca f = new Filmoteca();
    String delimiter = "#";


//----------------------------------------------------------------------
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
    //---------------------------------------------------------------------------

    public void  addFilmToCollection(Pelicula nuevaPeli){
        f.addPeliculaFilmoteca(nuevaPeli);
    }

//-------------------------------------------------------------------------------
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
    //---------------------------------------------------------------------------

    public void  addDirectorToCollection(Director nuevoDir){
        f.addDirectorGremio(nuevoDir);
    }
//-------------------------------------------------------------------------------

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
    //---------------------------------------------------------------------------

    public void  addActorToCollection(Actor nuevoActor){
        f.addActorGremio(nuevoActor);
    }
//-------------------------------------------------------------------------------








}//Fin Model
