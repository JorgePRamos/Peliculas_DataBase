package controller;

import java.io.BufferedWriter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import static com.coti.tools.Esdia.readFloat;
import static com.coti.tools.Esdia.readString;

import static java.lang.System.out;

import model.*;

public class Controller {
    Model m = new Model();
    Filmoteca f = new Filmoteca();
    String ruta = System.getProperty("user.home") +
                                    "/Desktop/Filmot18/";//Raiz carpeta Film18
    public void salidaProg(){

        m.saveFilmsBin( ruta+"peliculas.bin");
        m.saveActorBin(ruta+"actores.bin");
        m.saveDirectorBin(ruta +"directores.bin");

        out.printf("Saliendo del programa...\n");
    }//Fin Metodo salidaProg



    public void arranque(){
        boolean exist;

        File peliculaPath = new File(ruta + "peliculas.bin");//Ruta binario peliculas.
        File actoresPath = new File(ruta + "actores.bin");//Ruta binario actores.
        File directoresPath = new File(ruta + "directores.bin");//Ruta binario directores.
        //----------------------------------------------------------------------------------------------

        if(peliculaPath.exists()){//Comprobamos si existen las los binarios de las Peliculas.
            out.printf("Existe peliculas.bin\n");
           ArrayList<Pelicula> pt = (m.import_PeliBin(ruta + "peliculas.bin"));
           for(Pelicula o : pt){
               m.addFilmToCollection(o);
           }
            out.printf("Importando peliculas desde películas.bin.\nEspere....\n\n");

        }else{//No exixte binario pelicuas.
            out.printf("No existe películas.bin\n");
            out.printf("Importando peliculas desde películas.txt.\nEspere....\n\n");

            String txtPeliPath = ruta + "peliculas.txt";

            m.import_Pelitxt(txtPeliPath);

        }

        //----------------------------------------------------------------------------------------------
        if(actoresPath.exists()){//Comprobamos si existen las los binarios de las Actores.
            System.out.printf("Existe actores.bin\n");
            ArrayList<Actor> pt = (m.import_ActBin(ruta + "actores.bin"));
            for(Actor o : pt){
                m.addActorToCollection(o);

            }
            out.printf("Importando actores desde actores.bin.\nEspere....\n\n");

        }else{//No exixte binario actores.
            out.printf("No existe actores.bin\n");
            out.printf("Importando actores desde actores.txt.\nEspere....\n\n");

            String txtActPath = ruta +"actores.txt";
            m.import_Acttxt(txtActPath);//importar fichero .txt

        }

        //----------------------------------------------------------------------------------------------
        if(directoresPath.exists()){//Comprobamos si existen las los binarios de las Directores.
            System.out.printf("Existe directores.bin\n");
            ArrayList<Director> pt = (m.import_DirBin(ruta + "directores.bin"));

            for(Director o : pt){
                m.addDirectorToCollection(o);
            }

            out.printf("Importando directores desde directores.bin.\nEspere....\n\n");

        }else{//No exixte binario directores.
            out.printf("No existe directores.bin\n");
            out.printf("Importando directores desde directores.txt.\nEspere....\n\n");

           String txtDirecPath = ruta +"directores.txt" ;
            m.import_Directxt(txtDirecPath);//importar fichero .txt

        }


    }//Fin Metodo Arranque

    //----------------------------------------------------------------------------------------------
    public void archivo(){
            archivoHtml();
            archivoCol();
    }

    public void archivoHtml() {

        //-------------------------          HTML            ---------------------------------------

        out.printf("Exportado Peliculas en formato Html ---> peliculas.html\n");
        StringBuilder htmlFileSBuilder = new StringBuilder();

        htmlFileSBuilder.append("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "  padding: 5px;\n" +
                "}\n" +
                "th {\n" +
                "  text-align: left;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n");//Head

        htmlFileSBuilder.append("<body>\n" + "<h2>Tabla Peliculas Filmoteca</h2>\n" +
                "<table style=\"width:100%\">");//Body

        htmlFileSBuilder.append("<tr>\n" +
                "    <th>Titulo</th>\n" +
                "    <th>Año</th> \n" +
                "    <th>Duración</th>\n" +
                "\t<th>País</th>\n" +
                "    <th>Direción</th> \n" +
                "    <th>Guión</th>\n" +
                "\t<th>Música</th>\n" +
                "    <th>Fotografía</th> \n" +
                "    <th>Reparto</th>\n" +
                "\t<th>Productora</th>\n" +
                "    <th>Genero</th> \n" +
                "    <th>Simnosis</th> \n" +
                "  </tr>"); //Linea de Campos.

        //-------------------------         HTML_Filas           --------------------------------------
        int n = f.getNpeliculas();
        htmlFileSBuilder.append(m.addHtmlRow());
//   }
        //-------------------------         HTML_Cierre           -----------------------------------
        htmlFileSBuilder.append("</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        escribirFichero(htmlFileSBuilder.toString(), "peliculas.html");

    }

    //----------------------------------------------------------------------------------------------
    public void archivoCol() {
        //-------------------------          Ficheros_Col            -------------------------------
        out.printf("Exportado Directores ---> directores.col\n");
        String tableString = null;
        StringBuilder tableBuilder = new StringBuilder();

//        tableBuilder.append(String.format("%-65s%-65s%-65s%-80s%-50s\n", "Nombre", "Fecha de nacimiento", "Nacionalidad", "Ocupación", "Obras")); //Descomentar para obtener nombres de los campos en directores.col
        tableBuilder.append(m.addColRow());
        escribirFicheroCol(tableBuilder.toString(), "directores.col");

    }
//Fin Metodo Archivo.

    //----------------------------------------------------------------------------------------------
    public void escribirFichero(String contenido,  String nombreFichero){
        String tempFile = ruta + nombreFichero;
        File file = new File(tempFile);
        // if file does exists, then delete and create a new file
        if (file.exists()) {
            try {
                File newFileName = new File(ruta+ " old_" +nombreFichero);
                file.renameTo(newFileName);
                file.createNewFile();
            } catch (Exception e) {
                out.printf("Error en la creación de: peliculas.html\n");
            }
        }
        try {
            OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
            Writer writer = new OutputStreamWriter(outputStream);
            writer.write(contenido);
            writer.close();
        }catch (Exception e){
        out.printf("Error al escribir:  directores.col\n");
        System.out.println(e.toString());
        }

    }//Fin escribir Fichero

    //----------------------------------------------------------------------------------------------
    public void escribirFicheroCol(String cont,  String nombreFichero) {
        Path path = Paths.get(ruta + nombreFichero);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(cont, 0, cont.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

    }//Fin escribit fichero col

    //----------------------------------------------------------------------------------------------
// -------------------           Consultas Peliculas         ---------------------------------------
public void altaPelicula(){

    Scanner sc = new Scanner(System.in);
    Pelicula peliculaIntroducida = new Pelicula();
    String tempArrayList;

     ArrayList<String> direccion = new ArrayList();
        tempArrayList = readString("Introduzca el/los nombres de los directores separados por < , >\n");
        String[] tempDirectores = tempArrayList.split(",");
        for(String t : tempDirectores){
            peliculaIntroducida.addDirector(t);
        }//Conjunto de directores

     ArrayList<String> reparto = new ArrayList();
     tempArrayList = readString("Introduzca los nombres de los miembros del reparto separados por < , >\n");
        String[] tempActores = tempArrayList.split(",");
        for(String t : tempActores){
             peliculaIntroducida.addActor(t);
         }//Conjunto de actores

     String titulo = "-";
        titulo = readString("Introduzca el titulo de la obra.\n");
        peliculaIntroducida.setTitulo(titulo);

     String ano = "-";
        ano = readString("Introduzca el año de producción\n");
        peliculaIntroducida.setAno(ano);

     Float duracion = 0f; //Separados por < . >
         duracion = readFloat("Introduzca la duración\n");
         peliculaIntroducida.setDuracion(duracion);

     String pais = "-";
        pais = readString("Introduzca la nacionalidad\n");
        peliculaIntroducida.setPais(pais);

     String guion = "-";
          guion = readString("Introduzca el autor del guión\n");
        peliculaIntroducida.setGuion(guion);

     String musica = "-";
         musica = readString("Introduzca el autor de la banda sonora\n");
         peliculaIntroducida.setMusica(musica);

     String fotografia = "-";
         fotografia = readString("Introduzca el nombre del director de fotografía\n");
         peliculaIntroducida.setFotografia(fotografia);

     String productora = "-";
        productora = readString("Introduzca el nombre de la productora\n");
        peliculaIntroducida.setProductora(productora);

     String simnosis = "---";
        simnosis = readString("Introduzca la simnosis\n");
        peliculaIntroducida.setSimnosis(simnosis);

    String genero = "---";
         genero = readString("Introduzca el genero\n");
         peliculaIntroducida.setGenero(genero);


    int exixtencia = m.existePelicula(peliculaIntroducida);
    if (exixtencia == 0) {
        m.addFilmToCollection(peliculaIntroducida);
    }

    m.buscarDirectorYañadir(peliculaIntroducida);

    m.buscarRepartoYañadir(peliculaIntroducida);

}//Fin altaPelicula

    //----------------------------------------------------------------------------------------------
    public  void  bajasPelicula(){

        String nombrePeliculaObejetivo;
        nombrePeliculaObejetivo = readString("Introduzca el nombre de la película que desea borrar\n");
        Pelicula peliabuscar = new Pelicula();
        peliabuscar.setTitulo(nombrePeliculaObejetivo);

        if (m.existePelicula(peliabuscar) == 0){
            out.printf("La pelicula %s introducida no se encuentra en la bases de datos.\n", nombrePeliculaObejetivo);
        }else{
            m.borrarPelicula(nombrePeliculaObejetivo);
         }

    }//Fin Metodo Bajas Peliculas

    //----------------------------------------------------------------------------------------------
    public  void modPeliculas(){

        String nombrePeliculaObejetivo;
        nombrePeliculaObejetivo = readString("Introduzca el nombre de la película que desea modificar\n");
        m.modPelicula(nombrePeliculaObejetivo);

    }//Fin Metodo modPeliculas

    //----------------------------------------------------------------------------------------------
   public  void mostrarPelicula(){

       String nombrePeliculaObejetivo;
       nombrePeliculaObejetivo = readString("Introduzca el nombre de la película que desea mostrar\n");
        m.mostrarPelicula(nombrePeliculaObejetivo);

   }//Fin mostrsrPelicula

    //----------------------------------------------------------------------------------------------
    //-------------------       Consultas Directores    --------------------------------------------
    public void altaDirector(){

        Scanner sc = new Scanner(System.in);
        Director directorIntroducida = new Director();
        String tempArrayList;
        ArrayList<String> obras = new ArrayList();


        tempArrayList = readString("Introduzca el/los nombres de las obras separadas por < , >\n");
        String[] tempObras = tempArrayList.split(",");
        for(String t : tempObras){
            directorIntroducida.addObra(t);
        }//Conjunto de obras


        String nombre = "-";
        nombre = readString("Introduzca el nombre del director.\n");
        directorIntroducida.setNombre(nombre);

        String fecha_nac = "-";
        fecha_nac = readString("Introduzca la fecha de nacimiento\n");
        directorIntroducida.setFecha_nac(fecha_nac);


        String nacionalidad = "-";
        nacionalidad = readString("Introduzca la nacionalidad\n");
        directorIntroducida.setNacionalidad(nacionalidad);

        String ocupacion = "-";
        ocupacion = readString("Introduzca la ocupación\n");
        directorIntroducida.setOcupacion(ocupacion);

        int exixtencia = m.existeDirector(directorIntroducida);
        if (exixtencia == 0) {
            m.addDirectorToCollection(directorIntroducida);
        }

       // m.buscarObraYañadir(directorIntroducida);
    }//Fin altaPelicula

    //----------------------------------------------------------------------------------------------
    public  void  bajasDirector(){
        String nombreDirectorObjetivo;

        nombreDirectorObjetivo = readString("Introduzca el nombre del director que desea borrar\n");
        Director directorBuscar = new Director();
        directorBuscar.setNombre(nombreDirectorObjetivo);

        if (m.existeDirector(directorBuscar) == 0){
            out.printf("El director %s introducido no se encuentra en la bases de datos.\n", nombreDirectorObjetivo);
        }else{
            m.borrarDirector(nombreDirectorObjetivo);
        }

    }//Fin Metodo Bajas Peliculas

    //----------------------------------------------------------------------------------------------
    public  void modDirector(){
        String nombreDirectorObejetivo;
        nombreDirectorObejetivo = readString("Introduzca el nombre del director que desea modificar\n");

        m.modDirector(nombreDirectorObejetivo);


    }//Fin Metodo modDirectores

    //----------------------------------------------------------------------------------------------
    //-------------------          Consultas Directores           ----------------------------------
    public void altaActor(){

        Scanner sc = new Scanner(System.in);
        Actor actorIntroducida = new Actor();
        String tempArrayList;
        ArrayList<String> obras = new ArrayList();


        tempArrayList = readString("Introduzca el nombre de las peliculas de altor  separadas por < , >\n");
        String[] tempObras = tempArrayList.split(",");
        for(String t : tempObras){
            actorIntroducida.addFeatured(t);
        }//Conjunto de obras


        String nombre = "-";
        nombre = readString("Introduzca el nombre del actor.\n");
        actorIntroducida.setNombre(nombre);

        String fecha_nac = "-";
        fecha_nac = readString("Introduzca la fecha de nacimiento\n");
        actorIntroducida.setFecha_nac(fecha_nac);


        String nacionalidad = "-";
        nacionalidad = readString("Introduzca la nacionalidad\n");
        actorIntroducida.setNacionalidad(nacionalidad);

        String fechaDebut = "-";
        fechaDebut = readString("Introduzca la fecha de debut\n");
        actorIntroducida.setFecha_debut(fechaDebut);


        int exixtencia = m.existeActor(actorIntroducida);
        if (exixtencia == 0) {
            m.addActorToCollection(actorIntroducida);
        }

    }//Fin alta Actor

    //----------------------------------------------------------------------------------------------
    public  void  bajasActor(){

        String nombreActorObjetivo;
        nombreActorObjetivo = readString("Introduzca el nombre del actor que desea borrar\n");
        Actor actorBuscar = new Actor();
        actorBuscar.setNombre(nombreActorObjetivo);

        if (m.existeActor(actorBuscar) == 0){
            out.printf("El actor %s introducido no se encuentra en la bases de datos.\n", nombreActorObjetivo);
        }else{
            m.borrarActor(nombreActorObjetivo);
        }

    }//Fin Metodo Bajas Actor

    //----------------------------------------------------------------------------------------------
    public  void modActor(){
        String nombreActorObejetivo;
        nombreActorObejetivo = readString("Introduzca el nombre del actor que desea modificar\n");

        m.modActor(nombreActorObejetivo);


    }//Fin Metodo modDirectores

    //----------------------------------------------------------------------------------------------
    public  void mostrarActor(){

        String nombreActorObejetivo;
        nombreActorObejetivo = readString("Introduzca el nombre del actor que desea mostrar\n");
        m.mostrarActor(nombreActorObejetivo);

    }//Fin mostrar Actor

    //----------------------------------------------------------------------------------------------
    public  void  listarPeliculas(){

        m.listaPeliculas();

    }//Fin ListarPeliuclas

    //----------------------------------------------------------------------------------------------
    public  void  listarDirectores(){

        m.listaDirectores();

    }//Fin listarDirector

    //----------------------------------------------------------------------------------------------
    public  void  listarActores(){

        m.listaActores();

    }//Fin listarActores

}//Fin Controller
