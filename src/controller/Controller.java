package controller;

import java.io.BufferedWriter;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.lang.System.out;
import model.Model;
import model.Filmoteca;
import model.Pelicula;

public class Controller {
    Model m = new Model();
    Filmoteca f = new Filmoteca();
    String ruta = System.getProperty("user.home") +
                                    "/Desktop/Filmot18/";//Raiz carpeta Film18
    //Path rutaCarpeta = Paths.get(ruta);
    public void salidaProg(){
      //  m.saveBin( fimoteca, rutaCarpeta);//crear filmoteca constructor ect
        m.saveFilmsBin( f.getFilmoteca(), ruta+"peliculas.bin");
        m.saveActorBin( f.getGremio_actor(), ruta+"actores.bin");
        m.saveDirectorBin( f.getGremio_dir(), ruta+"directores.bin");
        out.printf("Saliendo del programa...\n");
    }//Fin Metodo salidaProg



    public void arranque(){
        boolean exist;
        /*String ruta = System.getProperty("user.home") +
                                     "Desktop/Filmot18/";//Raiz carpeta Film18*///remove if not used lately



        File peliculaPath = new File(ruta + "peliculas.bin");//Ruta binario peliculas.
        File actoresPath = new File(ruta + "actores.bin");//Ruta binario actores.
        File directoresPath = new File(ruta + "directores.bin");//Ruta binario directores.
        //----------------------------------------------------------------------------------------------


        if(peliculaPath.exists()){//Comprobamos si existen las los binarios de las Peliculas.
            out.printf("Existe peliculas.bin\n");
            m.import_PeliBin(ruta + "peliculas.bin");

        }else{//No exixte binario pelicuas.
            out.printf("No existe pelicuas.bin\n");
            out.printf("Importando peliculas desde pelicuals.txt.\nEspere....\n");

          //  Path txtPeliPath = Paths.get("peliculas.txt" );
            String txtPeliPath = ruta + "peliculas.txt";

            m.import_Pelitxt(txtPeliPath);




     }
        //----------------------------------------------------------------------------------------------

        if(actoresPath.exists()){//Comprobamos si existen las los binarios de las Actores.
            System.out.printf("Existe actores.bin\n");

        }else{//No exixte binario actores.
            out.printf("No existe actores.bin\n");
            out.printf("Importando actores desde actores.txt.\nEspere....\n");

            String txtActPath = ruta +"actores.txt";
            m.import_Acttxt(txtActPath);//importar fichero .txt

        }
        //----------------------------------------------------------------------------------------------

        if(directoresPath.exists()){//Comprobamos si existen las los binarios de las Directores.
            System.out.printf("Existe directores.bin\n");

        }else{//No exixte binario directores.
            out.printf("No existe directores.bin\n");
            out.printf("Importando directores desde directores.txt.\nEspere....\n");

           String txtDirecPath = ruta +"peliculas.txt" ;
            m.import_Directxt(txtDirecPath);//importar fichero .txt

        }
        //----------------------------------------------------------------------------------------------


    }//Fin Metodo Arranque


    //---------------------------------------------------------------------------------------------
public void archivo(){


        //--------  HTML    -------

        //--------  HTML_FORMATO    -------
        /*for(Pelicula model : f.estanteria) {
        System.out.println(model.getTitulo());
    }*/
        out.printf("Exportando Peliculas en formato Html a ---> peliculas.html\n");
        StringBuilder htmlFileSBuilder = new StringBuilder();
        htmlFileSBuilder.append("<!DOCTYPE html>\n" + "<html>\n"+"<head>\n" +
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
            "    <th>Guión</th> \n" +
            "    <th>Música</th>\n" +
            "\t<th>Fotografía</th>\n" +
            "    <th>Productora</th> \n" +
            "    <th>Simnosis</th>\n" +
            "\t<th>Dirección</th>\n" +
            "    <th>Reparto</th> \n" +
            "  </tr>"); //Linea de Campos.

    //--------  HTML_Filas   -------

    int n =    f.getNpeliculas();
    //for(int x=0;x<n;x++){
    htmlFileSBuilder.append(m.addHtmlRow());
//   }
    //--------  HTML_Cierre   -------

      htmlFileSBuilder.append("</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>");






escribirFichero(htmlFileSBuilder.toString(), "peliculas.html");








    //--------  Ficheros_COL    -------
    out.printf("Exportando Directores  a ---> directores.col\n");


}//Fin Metodo Archivo.



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

    out.printf("Error al escribir:  peliculas.html\n");
    System.out.println(e.toString());
}
    }

    }//Fin Controller
