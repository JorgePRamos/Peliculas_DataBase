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
    // -------------    Funciones Peliculas    -------------------------------------------

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

    }//Fin Metodo importPeli

    //------------------------------------------------------------------------------------
    public void  addFilmToCollection(Pelicula nuevaPeli){
        f.addPeliculaFilmoteca(nuevaPeli);
    }



    //------------------------------------------------------------------------------------
    //-------------     Funciones Directores       ---------------------------------------
    public void import_Directxt( String path){
        String[] lines = new String[100];
        Scanner scanner;
        try{

            scanner  = new Scanner(new File(path));
            String line = null;
            int x = 0;

            while (scanner.hasNextLine()) {
                Director dir = new Director();//Def Constructor
                line = scanner.nextLine();
                String [] tempString = line.split(delimiter);

                dir.setNombre(tempString[0]);
                dir.setFecha_nac(tempString[1]);
                dir.setNacionalidad(tempString[2]);
                dir.setNacionalidad(tempString[3]);
                dir.addObra(tempString[4]);

                f.addDirectorGremio(dir);
            }
        } catch(FileNotFoundException e) {
        System.err.println("Caught FileNotFoundException: " + e.getMessage());
        throw new RuntimeException(e);
         }
    }//Fin Metodo import_Directxt

    //------------------------------------------------------------------------------------
    public void  addDirectorToCollection(Director nuevoDir){
        f.addDirectorGremio(nuevoDir);
    }

    //------------------------------------------------------------------------------------
    //-------------     Funciones Actores       ------------------------------------------
    public void import_Acttxt(String path){

        String[] lines = new String[100];
        Scanner scanner;

        try{

            scanner  = new Scanner(new File(path));
            String line = null;
            int x = 0;

            while (scanner.hasNextLine()) {
                Actor act = new Actor();//Def Constructor
                line = scanner.nextLine();
                String [] tempString = line.split(delimiter);

                act.setNombre(tempString[0]);
                act.setFecha_nac(tempString[1]);
                act.setNacionalidad((tempString[2]));
                act.setFecha_debut(tempString[3]);
                act.addFeatured(tempString[4]);

                f.addActorGremio(act);
            }


        } catch(FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }//Fin Metodo import_Acttxt

    //------------------------------------------------------------------------------------
    public void  addActorToCollection(Actor nuevoActor){
        f.addActorGremio(nuevoActor);
    }

    //------------------------------------------------------------------------------------

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

    //------------------------------------------------------------------------------------
    public void saveFilmsBin(String direccion ) {

        out.printf("Estoy en SaveFilsmBin\n");
        ArrayList<Pelicula> tempArraylist = new ArrayList();

        for(Pelicula pl : f.estanteria){
            tempArraylist.add(pl);
            for (Pelicula i : tempArraylist) {
            out.printf("////////    %s\n",i.getTitulo());
            }
        }

        File binFilePeli= new File(direccion);
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;
        try { binFilePeli.createNewFile();

            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(binFilePeli)));
            oos.writeObject(tempArraylist);
            oos.close();

        } catch (IOException e) {
            out.println("No fue posible guardar peliculas.bin\n");
            out.println(e.toString());

        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }//Fin Metodo saveFilmsBin

    //------------------------------------------------------------------------------------
    public ArrayList<Pelicula> import_PeliBin(String path){

        ObjectInputStream ois = null;
        FileInputStream fis;
        BufferedInputStream bis;
        Pelicula peliculaTemp = new Pelicula();
        ArrayList<Pelicula> listaTemp = new ArrayList();

         try {
             fis = new FileInputStream(new File(path));
             bis = new BufferedInputStream(fis);
             ois = new ObjectInputStream(bis);

             listaTemp = (ArrayList<Pelicula>)  ois.readObject();
             ois.close();
            out.printf("** %s\n", peliculaTemp.getTitulo());
         }catch (Exception e){
        System.out.println(e.toString());
         }finally {
            try {
               ois.close();
              } catch (IOException e) {
                System.out.println(e.toString());
            }catch (NullPointerException e){
                e.printStackTrace();
             }
         }
        return listaTemp;
    }//Fin Metodo PeliBin

    // ------------------------------------------------------------------------------------
    public void saveActorBin( String direccion ) {

        out.printf("Estoy en SaveActorsBin\n");
        ArrayList<Actor> tempArraylist = new ArrayList();

        for (Actor pl : f.gremio_actor) {
            tempArraylist.add(pl);
            for (Actor i : tempArraylist) {

                out.printf("////////    %s\n", i.getNombre());


            }
        }

        File binFilePeli = new File(direccion);
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;
        try {
            binFilePeli.createNewFile();

            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(binFilePeli)));
            oos.writeObject(tempArraylist);
            oos.close();

        } catch (IOException e) {

            out.println("No fue posible guardar peliculas.bin\n");
            out.println(e.toString());
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }//Fin Metodo saveActorBin

    // ------------------------------------------------------------------------------------
    public ArrayList<Actor> import_ActBin(String path){

        ObjectInputStream ois = null;
        FileInputStream fis;
        BufferedInputStream bis;
        Actor peliculaTemp = new Actor();
        ArrayList<Actor> listaTemp = new ArrayList();

        try {

            fis = new FileInputStream(new File(path));
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            listaTemp = (ArrayList<Actor>)  ois.readObject();
            ois.close();
            out.printf("** %s\n", peliculaTemp.getNombre());
        }catch (Exception e){

            System.out.println(e.toString());
        }finally {

            try {
                ois.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        return listaTemp;
    }
    // ------------------------------------------------------------------------------------
    public void saveDirectorBin(String direccion){

        out.printf("Estoy en SaveDirectorBin\n");
        ArrayList<Director> tempArraylist = new ArrayList();

        for (Director pl : f.gremio_dir) {
            tempArraylist.add(pl);
            for (Director d : tempArraylist) {
                out.printf("////////    %s\n", d.getNombre());
            }
        }

        File binFileDir = new File(direccion);
        FileOutputStream fosd;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;

        try {
            binFileDir.createNewFile();
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(binFileDir)));
            oos.writeObject(tempArraylist);
            oos.close();

        } catch (IOException e) {
            out.println("No fue posible guardar directores.bin\n");
            out.println(e.toString());

        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }//Fin Metodo saveDirectorBin

    // ------------------------------------------------------------------------------------
    public ArrayList<Director> import_DirBin(String path){

        ObjectInputStream ois = null;
        FileInputStream fis;
        BufferedInputStream bis;
        Director peliculaTemp = new Director();
                ArrayList<Director> listaTemp = new ArrayList();

        try {
            fis = new FileInputStream(new File(path));
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            listaTemp = (ArrayList<Director>)  ois.readObject();

            ois.close();
            out.printf("** %s\n", peliculaTemp.getNombre());

        }catch (Exception e){
            System.out.println(e.toString());

        }finally {
            try {
                ois.close();

            } catch (IOException e) {
                System.out.println(e.toString());

            }catch (NullPointerException e){
                e.printStackTrace();

            }
        }

        return listaTemp;
    }
    // ------------------------------------------------------------------------------------

}//Fin Model
