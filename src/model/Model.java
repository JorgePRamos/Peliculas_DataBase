package model;
import static java.lang.System.exit;
import static java.lang.System.out;
import model.Filmoteca;
import java.io.FileInputStream;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    Filmoteca f = new Filmoteca();
    String delimiter = "#";


    //------------------------------------------------------------------------------------
    //------------- Funciones Peliculas
    public void import_Pelitxt(String path){
        String[] lines = new String[100];
        Scanner scanner;
try{
        scanner  = new Scanner(new File(path));
        String line = null;
        int x = 0;
        while (scanner.hasNextLine()) {
            Pelicula peli = new Pelicula();//Def Constructor
            line = scanner.nextLine();
            String [] tempString = line.split(delimiter);
            peli.setTitulo(tempString[0]);
            peli.setAno(tempString[1]);
            peli.setDuracion(Float.parseFloat(tempString[2]));
            peli.setPais(tempString[3]);
            peli.setGuion(tempString[4]);
            peli.setMusica(tempString[5]);
            peli.setFotografia(tempString[6]);
            peli.setProductora(tempString[7]);
            peli.setSimnosis(tempString[8]);
            peli.addActor(tempString[9]);
            peli.addDirector(tempString[10]);

            f.addPeliculaFilmoteca(peli);

        }
} catch(FileNotFoundException e) {
    System.err.println("Caught FileNotFoundException: " + e.getMessage());
    throw new RuntimeException(e);
}



          //  return peli;
        }
//-----------------------------------------------------------------------------------

    public void  addFilmToCollection(Pelicula nuevaPeli){
        f.addPeliculaFilmoteca(nuevaPeli);
    }

//-----------------------------------------------------------------------------------
    //------------- Funciones Directores

    public Director import_Directxt( String path){
        String[] lines = new String[100];
        BufferedReader br = null;
        Director dir  =  new Director();//Def Constructor
        try{
            br = new BufferedReader(new FileReader(path));
            String line = null;
            int x = 0;
            while ((line = br.readLine()) != null) {
                String [] tempString = line.split(delimiter);


                dir.setNombre(tempString[0]);
                dir.setFecha_nac(tempString[1]);
                dir.setNacionalidad(tempString[2]);
                dir.setOcupacion(tempString[3]);
                dir.addObra(tempString[4]);


            }
        } catch(FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
            throw new RuntimeException(e);
        } catch(IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }



        return dir;
    }
//------------------------------------------------------------------------------------
    public void  addDirectorToCollection(Director nuevoDir){
        f.addDirectorGremio(nuevoDir);
    }
//-----------------------------------------------------------------------------------

    //------------- Funciones Actores

    public Actor import_Acttxt(String path){
        String[] lines = new String[100];
        BufferedReader br = null;
        Actor act  =  new Actor();//Def Constructor
        try{
            br = new BufferedReader(new FileReader(path));
            String line = null;
            int x = 0;
            while ((line = br.readLine()) != null) {
                String [] tempString = line.split(delimiter);
                act.setNombre(tempString[0]);
                act.setFecha_nac(tempString[1]);
                act.setNacionalidad((tempString[2]));
                act.setFecha_debut(tempString[3]);
                act.addFeatured(tempString[4]);//son multiples elementos hacer funcion que lo lea separado por \t




            }
        } catch(FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
            throw new RuntimeException(e);
        } catch(IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }



        return act;
    }
//------------------------------------------------------------------------------------


    public void  addActorToCollection(Actor nuevoActor){
        f.addActorGremio(nuevoActor);
    }
//-----------------------------------------------------------------------------------

public  String addHtmlRow(){
        String arrayString;
        StringBuilder arrayStringBuilder = new StringBuilder();

        for(Pelicula peliTemp : f.estanteria){
            arrayStringBuilder.append(String.format("<TR>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%.1f min1</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"//7
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "</TR>", peliTemp.getTitulo(), peliTemp.getAno(), peliTemp.getDuracion(), peliTemp.getPais(), peliTemp.getGuion(),
                    peliTemp.getMusica(), peliTemp.getFotografia(), peliTemp.getProductora(), peliTemp.getSimnosis(), peliTemp.getDireccion(), peliTemp.getReparto()));
        }
arrayString = arrayStringBuilder.toString();
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
            //bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(fos);
            for (Pelicula peliTemp : f.estanteria) {
                oos.writeObject(peliTemp);
            }
            oos.close();

        } catch (IOException e) {
              System.out.println("No fue posible guardar peliculas.bin\n");
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

//----------------------------------------------------------------------------------
    public ArrayList<Pelicula> import_PeliBin(String path){
        ObjectInputStream ois = null;
        FileInputStream fis;
        ArrayList<Pelicula> listaTemp = null;
        try {
            fis = new FileInputStream(path);
             ois = new ObjectInputStream(fis);
        }catch (Exception e){

            out.printf("NECESITA CONTROL DE ERRORES\n");
        }
        //int[] ia = (int[]) (ois.readObject());
        try{
       listaTemp =(ArrayList<Pelicula>)  (ois.readObject());
            ois.close();

        }catch (Exception e){}
        return listaTemp;
    }
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
            System.out.println("No fue posible guardar actores.bin\n");
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
            System.out.println("No fue posible guardar directores.bin\n");
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
