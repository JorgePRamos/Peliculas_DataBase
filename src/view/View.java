package view;
import  java.util.Scanner;
import controller.Controller;
import static java.lang.System.out;
import  java.io.PrintStream;

public class View {

    Scanner sc = new Scanner(System.in);
    Controller  cn = new Controller();

    public void View(String menu) {

        String[] opDisponibles = new String[]{"1", "2", "3", "4", "5", "6", "7", "0"};
        String temp;
        int op = 0;
        int sal;
        int contador = 0;


        do {
           // System.out.printf("Asi se depura: %d\n", contador);
             sal = 0;
            System.out.printf("\n-------------------------------------------\nSeleccione la opcion deseada: \n");
            System.out.printf("%s\n>", menu);
            temp = sc.nextLine();

            try {
                op = Integer.parseInt(temp);

            } catch (Exception e) {
                System.out.printf("SE PRODUJO UN ERROR EN LA INTRODUCCION DE LOS DATOS\n saliendo \n");
                System.exit(1);
            }


            switch (op) {
                case 1:
                    cn.arranque();
                    break;

                case 2:
                   cn.salidaProg();
                   sal = 1;
                    break;

                case 3:
                     cn.archivo();
                    break;

                case 4:
                    String[] opPeliculas = new String[]{"1", "2", "3","4","5"};
                    String tempPeliculas = null;
                    String menuPelicuals = (" 1) Altas\n 2) Bajas\n 3) Modificaciones\n 4) Consultas\n 5) Atras\n");
                    int selecPeliculas = 0;
                    int salidaPeliculas;


                    do {
                        // System.out.printf("Asi se depura: %d\n", contador);
                        salidaPeliculas = 0;
                        System.out.printf("\n-------------------------------------------\nSeleccione la opcion deseada: \n");
                        System.out.printf("%s\n>", menuPelicuals);
                        tempPeliculas = sc.nextLine();

                        try {
                            selecPeliculas = Integer.parseInt(tempPeliculas);

                        } catch (Exception e) {
                            System.out.printf("SE PRODUJO UN ERROR EN LA INTRODUCCION DE LOS DATOS\n saliendo.... \n");
                            System.exit(-1);
                        }

                        switch (selecPeliculas){
                            case 1:
                                cn.altaPelicula();
                                break;
                            case 2:
                                cn.bajasPelicula();
                                break;
                            case 3:
                                cn.modPeliculas();
                                break;
                            case 4:
                                cn.mostrarPelicula();
                                break;
                            case 5:
                                salidaPeliculas = 1;
                                break;

                                default:
                                    System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                                    salidaPeliculas= 0;


                        }
                    }while(salidaPeliculas == 0);
                    break;

                case 5:
                  //  cn.directores();
                    break;
                case 6:
                  //  cn.actores();
                    break;
                case 7:
                 //   cn.listados();
                    break;


                default:
                    System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                    sal = 0;
                    contador++;

            }
        } while (sal == 0);


    }
}
