package model;
import model.Filmoteca;
import java.io.FileInputStream;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.coti.tools.Esdia.readFloat;
import static com.coti.tools.Esdia.readString;
import java.util.Collections;
import java.util.Comparator;
import static java.lang.System.*;


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
                peli.setGuion(tempString[5]);
                peli.setMusica(tempString[6]);
                peli.setFotografia(tempString[7]);
                peli.setProductora(tempString[9]);
                peli.setGenero(tempString[10]);
                peli.setSimnosis(tempString[11]);

                String[] actoresArray = tempString[8].split("\t");
                for(String t : actoresArray){
                    peli.addActor(t);
                }//Conjunto de actores

                String[] directoresArray = tempString[4].split("\t");
                for(String t : directoresArray){
                    peli.addDirector(t);
                }//Conjunto de directores.

                f.addPeliculaFilmoteca(peli);
            }
        } catch(FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
            throw new RuntimeException(e);
        }

        /*
        int n = 0;
        for (Pelicula pl : f.estanteria){
            out.printf("**********Estoy intentando modifica el padrino**********\n");
            out.printf("***********     Este es <%s>      ***********\n", pl.getTitulo());
            String titulo = "El padrino";
            String pelicualActual = pl.getTitulo();
            if(titulo.equals(pl.getTitulo())){
                out.printf("************    Encontre al padrino %d  *********\n", n);
                pl.setTitulo("El tontino2");
            }
            n++;

        }
        */
    }//Fin Metodo importPeli

    //------------------------------------------------------------------------------------
    public void  addFilmToCollection(Pelicula nuevaPeli){
        f.addPeliculaFilmoteca(nuevaPeli);
    }
    //------------------------------------------------------------------------------------
    public void  modPelicula(String nombrePelicula){
        Scanner sc = new Scanner(System.in);
        String[] opMod = new String[]{"1", "2", "3","4","5","6","7","8","9","10","11","12"};
        String tempOpciones = null;
        String menuMod = (" 1) Titulo\n 2) Año\n 3) Duración\n 4) País\n 5) Dirección\n 6) Guion\n 7) Música\n 8) Fotogrfía\n 9) Reparto\n 10) Productora\n 11) Género\n 12) Simnosis\n");
        int selecPeliculas = 0;
        int salidaPeliculas;
        String mod =null;

        for (Pelicula pl : f.estanteria) {
            if (nombrePelicula.equals(pl.getTitulo())) {


                do {
                    salidaPeliculas = 0;
                    System.out.printf("\n-------------------------------------------\nSeleccione la opcion de modificación deseada: \n");
                    System.out.printf("%s\n>", menuMod);
                    tempOpciones = sc.nextLine();

                    try {
                        selecPeliculas = Integer.parseInt(tempOpciones);

                    } catch (Exception e) {
                        System.out.printf("SE PRODUJO UN ERROR EN LA INTRODUCCION DE LOS DATOS\n saliendo.... \n");
                        System.exit(-1);
                    }

                    switch (selecPeliculas) {
                        case 1:
                            mod = readString("Introduzca el nuevo título:   ");
                            pl.setTitulo(mod);
                            break;
                        case 2:
                            mod = readString("Introduzca el nuevo año:   ");
                            pl.setAno(mod);
                            break;
                        case 3:
                            mod = readString("Introduzca la nueva duración:   ");
                            float fmod = Float.parseFloat(mod);
                            pl.setDuracion(fmod);
                            break;
                        case 4:
                            mod = readString("Introduzca el nuevo País:   ");
                            pl.setPais(mod);
                            break;
                        case 5:
                            mod = readString("Introduzca el/los nombres de los directores separados por < , >\n");
                            String[] tempDirectores = mod.split(",");
                            pl.borrarTodaDireccion();
                            for(String t : tempDirectores){
                                pl.addDirector(t);
                            }//Conjunto de directores
                            break;
                        case 6:
                            mod = readString("Introduzca el nuevo autor del guión:   ");
                            pl.setGuion(mod);
                            break;
                        case 7:
                            mod = readString("Introduzca el nuevo autor de la banda sonora:   ");
                            pl.setMusica(mod);
                            break;
                        case 8:
                            mod = readString("Introduzca el nuevo director de fotografía:   ");
                            pl.setFotografia(mod);
                            break;
                        case 9:
                            mod = readString("Introduzca los nombres de los actores separados por < , >\n");
                            String[] tempActores = mod.split(",");
                            pl.borrarTodaCasting();
                            for(String t : tempActores){
                                pl.addActor(t);
                            }//Conjunto de directores
                            break;
                        case 10:
                            mod = readString("Introduzca la nueva productora:   ");
                            pl.setProductora(mod);
                            break;
                        case 11:
                            mod = readString("Introduzca el nuevo Género:   ");
                            pl.setGenero(mod);
                            break;
                        case 12:
                            mod = readString("Introduzca la nueva simnosis:   ");
                            pl.setSimnosis(mod);
                            break;

                        default:
                            System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                            salidaPeliculas = 0;


                    }
                } while (salidaPeliculas == 0);
            }
        }


    }//Fin modificacion
    //------------------------------------------------------------------------------------

    public  void  mostrarPelicula(String peliObjetivo){

        String arrayString = null;
        String tableString = null;
        StringBuilder arrayStringBuilder = new StringBuilder();
        StringBuilder tableBuilder = new StringBuilder();

        tableBuilder.append(String.format(
                "|  %-55s  |  %-30s  |  %-30s  |  %-40s  |  %-50s  |  %-60s  |  %-50s  |  %-40s  |  %-110s  |  %-40s  |  %-40s  |  %-300s  |\n","Titulo", "Año", "Duración", "Pais", "Dirección", "Guión", "Música", "Fotografía", "Reparto", "Productora", "Genero", "Simnosis")); //Linea de Campos.
        tableString = tableBuilder.toString();
        out.printf("%s", tableString);

        for (Pelicula peliTemp : f.estanteria) {
            if (peliObjetivo.equals(peliTemp.getTitulo())) {

                    arrayStringBuilder.append(String.format(
                            "|  %-55s  |  %-30s  |  %-30.2f  |  %-40s  |  %-50s  |  %-60s  |  %-50s  |  %-40s  |  %-110s  |  %-40s  |  %-40s  |  %-300s  |\n", peliTemp.getTitulo(), peliTemp.getAno(), peliTemp.getDuracion(), peliTemp.getPais(), peliTemp.getDireccion(),
                            peliTemp.getGuion(), peliTemp.getMusica(), peliTemp.getFotografia(), peliTemp.getReparto(), peliTemp.getProductora(), peliTemp.getGenero(), peliTemp.getSimnosis()));
                }
                arrayString = arrayStringBuilder.toString();


        }
        out.printf("%s", arrayString);
    }//Fin Metodo mostrarPelicula


    //------------------------------------------------------------------------------------
    public void borrarPelicula(String peliculaObjetivo) {

        int indexPelicula = buscarPelicula(peliculaObjetivo);
        //buscamos actores
        for (String ta : f.estanteria.get(indexPelicula).reparto) {
            int x = 0;
            for ( Actor ac : f.gremio_actor){
                if(ta.equals(ac.getNombre())){
                    ac.borrarFeature(x);

                }
                x++;
            }
        }

        //buscamos directores
        for (String da : f.estanteria.get(indexPelicula).direccion) {
            int y = 0;
            for ( Director dir : f.gremio_dir){
                if(da.equals(dir.getNombre())){
                    dir.borrarObra(y);

                }
                y++;
            }
        }
        //borramos Pelicula
        f.borrarPeliculaFilmoteca(indexPelicula);
        out.printf("La pelicula y sus relaciones han sido borradas\n");


    }
    //------------------------------------------------------------------------------------
    public int buscarPelicula(String peliculaObjetivo){
        int index = 0;
        for(Pelicula pl : f.getFilmoteca()){
            if(peliculaObjetivo.equals(pl.getTitulo())){

                out.printf("Pelicula Encontrada, con index %d \n", index);


            }
            index++;

        }

        return  index;
    }


    //------------------------------------------------------------------------------------
    public boolean existePelicula(Pelicula peliculaObjetivo){

        boolean exitencia = false;

            for(Pelicula pl : f.getFilmoteca()){
                if(peliculaObjetivo.getTitulo().equals(pl.getTitulo())){

                    out.printf("Pelicula Encontrada\n");
                    exitencia = true;
                }

            }

            return exitencia;
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
                dir.setOcupacion(tempString[3]);

                String[] obrasArray = tempString[4].split("\t");
                for(String t : obrasArray){
                dir.addObra(t);
                }//Conjunto deobras

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
       public boolean buscarDirectorYañadir(Pelicula peliculaObjetivo){

        boolean exitencia = false;


        for (String nombre : peliculaObjetivo.direccion){
            for(Director dir : f.gremio_dir){
                if(nombre.equals(dir.getNombre())){

                    out.printf("Director encontrado\n");
                    out.printf("Añadiendo %s a sus obras...\n", peliculaObjetivo.getTitulo());
                    dir.addObra(peliculaObjetivo.getTitulo());
                    exitencia = true;
                }

            }

            if (exitencia == false){
                out.printf("No se encontró el director\nAñadiendo %s a directores...\n", nombre);

                Director nuevoDirector = new Director();
                 nuevoDirector.setNombre(nombre);
                addDirectorToCollection(nuevoDirector);
            }
        }



        return exitencia;
       }
    //------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------

    public int buscarDirector(String nombreObjetivo){
        int index = 0;
        for(Director dr : f.getGremio_dir()){
            if(nombreObjetivo.equals(dr.getNombre())){

                out.printf("Director encontrado, index %d\n", index);


            }
            index++;

        }

        return  index;
    }
    //------------------------------------------------------------------------------------

    public boolean existeDirector(Director nombre){

        boolean exitencia = false;

        for(Director dr : f.getGremio_dir()){
            if(nombre.getNombre().equals(dr.getNombre())){

                out.printf("Director encontrado\n");
                exitencia = true;
            }

        }

        return exitencia;
    }


    //------------------------------------------------------------------------------------
    public void borrarDirector(String directorObjetivo) {

        boolean borrable = true;
        int indexDirector = buscarDirector(directorObjetivo);
        //Buscamos Peliculas
        for (String ta : f.gremio_dir.get(indexDirector).obras) {
            int x = 0;
            for ( Pelicula ac : f.estanteria){
                if(ta.equals(ac.getTitulo())){

                borrable = false;
                }
                x++;
            }
        }


        //borramos director si no tiene relaciones
        if(borrable) {
            f.borrarDirectorGremio(indexDirector);
            out.printf("El director ha sido borrado\n");
        }else{
            out.printf("El director no se pudo borrar debido a sus relaciones");
        }


    }
    //------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------
    public void  modDirector(String nombreDirector){
        Scanner sc = new Scanner(System.in);
        String[] opMod = new String[]{"1", "2", "3"};
        String tempOpciones = null;
        String menuMod = ("1) Fecha Nacimiento\n 2) Nacionalidad\n 3) Ocupación\n");
        int selecPeliculas = 0;
        int salidaPeliculas;
        String mod =null;

        for (Director pl : f.gremio_dir) {
            if (nombreDirector.equals(pl.getNombre())) {


                do {
                    salidaPeliculas = 0;
                    System.out.printf("\n-------------------------------------------\nSeleccione la opcion de modificación deseada: \n");
                    System.out.printf("%s\n>", menuMod);
                    tempOpciones = sc.nextLine();

                    try {
                        selecPeliculas = Integer.parseInt(tempOpciones);

                    } catch (Exception e) {
                        System.out.printf("SE PRODUJO UN ERROR EN LA INTRODUCCION DE LOS DATOS\n saliendo.... \n");
                        System.exit(-1);
                    }

                    switch (selecPeliculas) {
                        case 1:
                            mod = readString("Introduzca la nueva fecha de nacimiento:   ");
                            pl.setFecha_nac(mod);
                            break;
                        case 2:
                            mod = readString("Introduzca la nueva nacionalidad:   ");
                            pl.setNacionalidad(mod);
                            break;
                        case 3:
                            mod = readString("Introduzca la ocupacion:   ");
                            pl.setOcupacion(mod);
                            break;


                        default:
                            System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                            salidaPeliculas = 0;


                    }
                } while (salidaPeliculas == 0);
            }
        }


    }//Fin modificacion
    //------------------------------------------------------------------------------------
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

                String[] pelisArray = tempString[4].split("\t");
                for(String t : pelisArray){
                   act.addFeatured(t);
                }//Conjunto de trabajos

                f.addActorGremio(act);
            }


        } catch(FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }//Fin Metodo import_Acttxt

    //------------------------------------------------------------------------------------
    public void borrarActor(String actorObjetivo) {

        boolean borrable = true;
        int indexActor = buscarActor(actorObjetivo);
        //Buscamos Peliculas
        for (String ta : f.gremio_actor.get(indexActor).feature) {
            int x = 0;
            for ( Pelicula ac : f.estanteria){
                if(ta.equals(ac.getTitulo())){

                    borrable = false;
                }
                x++;
            }
        }


        //borramos director si no tiene relaciones
        if(borrable) {
            f.borrarActorGremio(indexActor);
            out.printf("El actor ha sido borrado\n");
        }else{
            out.printf("El actor no se pudo borrar debido a sus relaciones");
        }


    }
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    public void  modActor(String nombreActor){
        Scanner sc = new Scanner(System.in);
        String[] opMod = new String[]{"1", "2", "3"};
        String tempOpciones = null;
        String menuMod = ("1) Fecha Nacimiento\n 2) Nacionalidad\n 3) Fecha Debut\n");
        int selecPeliculas = 0;
        int salidaPeliculas;
        String mod =null;

        for (Actor pl : f.getGremio_actor()) {
            if (nombreActor.equals(pl.getNombre())) {


                do {
                    salidaPeliculas = 0;
                    System.out.printf("\n-------------------------------------------\nSeleccione la opcion de modificación deseada: \n");
                    System.out.printf("%s\n>", menuMod);
                    tempOpciones = sc.nextLine();

                    try {
                        selecPeliculas = Integer.parseInt(tempOpciones);

                    } catch (Exception e) {
                        System.out.printf("SE PRODUJO UN ERROR EN LA INTRODUCCION DE LOS DATOS\n saliendo.... \n");
                        System.exit(-1);
                    }

                    switch (selecPeliculas) {
                        case 1:
                            mod = readString("Introduzca la nueva fecha de nacimiento:   ");
                            pl.setFecha_nac(mod);
                            break;
                        case 2:
                            mod = readString("Introduzca la nueva nacionalidad:   ");
                            pl.setNacionalidad(mod);
                            break;
                        case 3:
                            mod = readString("Introduzca la nueva fecha de debut:   ");
                            pl.setFecha_debut(mod);
                            break;


                        default:
                            System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                            salidaPeliculas = 0;


                    }
                } while (salidaPeliculas == 0);
            }
        }


    }//Fin modificacion Actor
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------

    public  void  mostrarActor(String actorObjetivo){

        String arrayString = null;
        String tableString = null;
        StringBuilder arrayStringBuilder = new StringBuilder();
        StringBuilder tableBuilder = new StringBuilder();
        ArrayList<Pelicula> obrasOrdenadas = new ArrayList();
        tableBuilder.append(String.format(
                "|  %-55s  |  %-30s  |  %-30s  |  %-40s  |  %-50s  |  %-60s  |  %-50s  |  %-40s  |  %-110s  |  %-40s  |  %-40s  |  %-300s  |\n","Titulo", "Año", "Duración", "Pais", "Dirección", "Guión", "Música", "Fotografía", "Reparto", "Productora", "Genero", "Simnosis")); //Linea de Campos.
        tableString = tableBuilder.toString();
        out.printf("%s", tableString);

        for (Pelicula peliTemp : f.estanteria) {
            for(String act : peliTemp.reparto) {
                if (actorObjetivo.equals(act)) {
                    obrasOrdenadas.add(peliTemp);

                }
            }
        }

        Collections.sort(obrasOrdenadas, new Comparator<Pelicula>(){

            @Override
            public int compare(Pelicula o1, Pelicula o2) {
                return o1.getAno().compareTo(o2.getAno());
            }

        });
            for(Pelicula p : obrasOrdenadas) {
                arrayStringBuilder.append(String.format(
                        "|  %-55s  |  %-30s  |  %-30.2f  |  %-40s  |  %-50s  |  %-60s  |  %-50s  |  %-40s  |  %-110s  |  %-40s  |  %-40s  |  %-300s  |\n", p.getTitulo(), p.getAno(), p.getDuracion(), p.getPais(), p.getDireccion(),
                        p.getGuion(), p.getMusica(), p.getFotografia(), p.getReparto(), p.getProductora(), p.getGenero(), p.getSimnosis()));
            }
        arrayString = arrayStringBuilder.toString();
        out.printf("%s", arrayString);
    }//Fin Metodo mostrarPelicula


    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    public void  addActorToCollection(Actor nuevoActor){
        f.addActorGremio(nuevoActor);
    }
    //------------------------------------------------------------------------------------
    public boolean buscarRepartoYañadir(Pelicula peliculaObjetivo){

        boolean exitencia = false;

        for (String nombre : peliculaObjetivo.reparto){
            for(Actor ac : f.gremio_actor){
                if(nombre.equals(ac.getNombre())){

                    out.printf("Actor encontrado\n");
                    out.printf("Añadiendo %s a sus Trabajos...\n", peliculaObjetivo.getTitulo());
                    ac.addFeatured(peliculaObjetivo.getTitulo());
                    exitencia = true;
                }

            }
            if (exitencia == false){
                out.printf("No se encontró el actor\nAñadiendo %s a Actores...\n", nombre);

                Actor nuevoActor = new Actor();
                nuevoActor.setNombre(nombre);
                addActorToCollection(nuevoActor);
            }
        }



        return exitencia;
    }
    //------------------------------------------------------------------------------------

    public int buscarActor(String nombreObjetivo){
        int index = 0;
        for(Actor ac : f.getGremio_actor()){
            if(nombreObjetivo.equals(ac.getNombre())){

                out.printf("Actpr encontrado, index %d\n", index);


            }
            index++;

        }

        return  index;
    }

    //------------------------------------------------------------------------------------
    public boolean existeActor(Actor nombre){

        boolean exitencia = false;

        for(Actor ac : f.getGremio_actor()){
            if(nombre.getNombre().equals(ac.getNombre())){

                out.printf("Actor encontrado\n");
                exitencia = true;
            }

        }

        return exitencia;
    }

    //------------------------------------------------------------------------------------

    public  String addHtmlRow(){
        String arrayString;
        StringBuilder arrayStringBuilder = new StringBuilder();

        for(Pelicula peliTemp : f.estanteria){

            arrayStringBuilder.append(String.format("<TR>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%.1f min</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"//7
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "<TD>%s</TD>"
                            + "</TR>", peliTemp.getTitulo(), peliTemp.getAno(), peliTemp.getDuracion(), peliTemp.getPais(), peliTemp.getDireccion(),
                             peliTemp.getGuion(), peliTemp.getMusica(), peliTemp.getFotografia(), peliTemp.getReparto(), peliTemp.getProductora(), peliTemp.getGenero(), peliTemp.getSimnosis()));
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

    public void listaPeliculas(){

        String arrayString = null;
        String tableString = null;
        StringBuilder arrayStringBuilder = new StringBuilder();
        StringBuilder tableBuilder = new StringBuilder();
        ArrayList<Pelicula> obrasOrdenadas = new ArrayList();


        tableBuilder.append(String.format(
                "|  %-55s  |  %-30s  |  %-30s  |  %-40s  |  %-40s  |\n","Titulo", "Año", "Duración", "Pais", "Genero")); //Linea de Campos.
        tableString = tableBuilder.toString();
        out.printf("%s", tableString);



        for (Pelicula peliTemp : f.estanteria) {

                    obrasOrdenadas.add(peliTemp);



        }

        Collections.sort(obrasOrdenadas, new Comparator<Pelicula>(){

            @Override
            public int compare(Pelicula o1, Pelicula o2) {
                return o1.getTitulo().compareTo(o2.getTitulo());
            }

        });
        for(Pelicula p : obrasOrdenadas) {
            arrayStringBuilder.append(String.format(
                    "|  %-55s  |  %-30s  |  %-30.2f  |  %-40s  |  %-40s  |\n", p.getTitulo(), p.getAno(), p.getDuracion(), p.getPais(), p.getDireccion(),
                    p.getGuion(), p.getMusica(), p.getFotografia(), p.getReparto(), p.getProductora(), p.getGenero(), p.getSimnosis()));
        }
        arrayString = arrayStringBuilder.toString();
        out.printf("%s", arrayString);

    }

    // ------------------------------------------------------------------------------------

    public void listaDirectores(){

        String arrayString = null;
        String tableString = null;
        StringBuilder arrayStringBuilder = new StringBuilder();
        StringBuilder tableBuilder = new StringBuilder();
        ArrayList<Director> directoresOrdenadas = new ArrayList();



        tableBuilder.append(String.format(
                "|  %-55s  |  %-50s  |  %-50s  |  %-50s  |\n","Nombre", "Fecha de nacimiento", "Nacionalidad", "Ocupación")); //Linea de Campos.

        tableString = tableBuilder.toString();
        out.printf("%s", tableString);



        for (Director directorTemp : f.gremio_dir) {

            directoresOrdenadas.add(directorTemp);



        }

        Collections.sort(directoresOrdenadas, new Comparator<Director>(){

            @Override
            public int compare(Director o1, Director o2) {
                return o1.getNacionalidad().compareTo(o2.getNacionalidad());
            }
                   });
        Collections.sort(directoresOrdenadas, new Comparator<Director>(){

            @Override
            public int compare(Director o1, Director o2) {
                return o1.getFecha_nac().compareTo(o2.getFecha_nac());
            }
        });



        for(Director p : directoresOrdenadas) {
            arrayStringBuilder.append(String.format(
                    "|  %-55s  |  %-50s  |  %-50s  |  %-50s  |\n",p.getNombre(), p.getFecha_nac(), p.getNacionalidad(), p.getOcupacion()));
        }
        arrayString = arrayStringBuilder.toString();
        out.printf("%s", arrayString);

    }

    // ------------------------------------------------------------------------------------

    public void listaActores(){

        String arrayString = null;
        String tableString = null;
        StringBuilder arrayStringBuilder = new StringBuilder();
        StringBuilder tableBuilder = new StringBuilder();
        ArrayList<Actor> actoresOrdenadas = new ArrayList();


        tableBuilder.append(String.format(
                "|  %-55s  |  %-50s  |  %-50s  |  %-50s  |\n","Nombre", "Fecha de nacimiento", "Nacionalidad", "Año Debut")); //Linea de Campos.

        tableString = tableBuilder.toString();
        out.printf("%s", tableString);



        for (Actor actTemp : f.gremio_actor) {

            actoresOrdenadas.add(actTemp);



        }


        Collections.sort(actoresOrdenadas, new Comparator<Actor>(){

            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.getFecha_debut().compareTo(o2.getFecha_debut());
            }

        });
        Collections.sort(actoresOrdenadas, new Comparator<Actor>(){

            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }

        });


        for(Actor p : actoresOrdenadas) {
            arrayStringBuilder.append(String.format(
                    "|  %-55s  |  %-50s  |  %-50s  |  %-50s  |\n",p.getNombre(), p.getFecha_nac(), p.getNacionalidad(), p.getFecha_debut()));
        }
        arrayString = arrayStringBuilder.toString();
        out.printf("%s", arrayString);

    }




}//Fin Model
