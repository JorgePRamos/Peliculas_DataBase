package controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.lang.System.out;
import model.Model;
import model.Pelicula;

public class Controller {
    Model m = new Model();

    public void arranque(){
        boolean exist;
        String ruta = System.getProperty("user.home") +
                                     "Desktop/Filmot18/";//Raiz carpeta Film18



        File peliculaPath = new File(ruta + "películas.bin");//Ruta binario peliculas.
        File actoresPath = new File(ruta + "actores.bin");//Ruta binario actores.
        File directoresPath = new File(ruta + "directores.bin");//Ruta binario directores.
        //----------------------------------------------------------------------------------------------


        if(peliculaPath.exists()){//Comprobamos si existen las los binarios de las Peliculas.
            out.printf("Exixte peliculas.bin\n");

        }else{//No exixte binario pelicuas.
            out.printf("No existe pelicuas.bin\n");
            out.printf("Importando peliculas desde pelicuals.txt.\nEspere....\n");

            Path txtPeliPath = Paths.get(ruta +"peliculas.txt" );
            m.addFilmToCollection(m.import_Pelitxt(txtPeliPath));//importar fichero .txt);




     }
        //----------------------------------------------------------------------------------------------

        if(actoresPath.exists()){//Comprobamos si existen las los binarios de las Actores.
            System.out.printf("Exixte actores.bin\n");

        }else{//No exixte binario actores.
            out.printf("No existe actores.bin\n");
            out.printf("Importando actores desde actores.txt.\nEspere....\n");

            Path txtActPath = Paths.get(ruta + "actores.txt" );
            m.import_Acttxt(txtActPath);//importar fichero .txt

        }
        //----------------------------------------------------------------------------------------------

        if(directoresPath.exists()){//Comprobamos si existen las los binarios de las Directores.
            System.out.printf("Exixte directores.bin\n");

        }else{//No exixte binario directores.
            out.printf("No existe directores.bin\n");
            out.printf("Importando directores desde directores.txt.\nEspere....\n");

           Path  txtDirecPath = Paths.get(ruta +"peliculas.txt" );
            m.import_Directxt(txtDirecPath);//importar fichero .txt

        }
        //----------------------------------------------------------------------------------------------


    }



}
