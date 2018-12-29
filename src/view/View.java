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
                    String[] opDirectores = new String[]{"1", "2", "3","4"};
                    String tempDirectores = null;
                    String menuDirectores = (" 1) Altas\n 2) Bajas\n 3) Modificaciones\n 4) Atras\n");
                    int selecDirectores = 0;
                    int salidaDirectores;


                    do {
                        salidaDirectores = 0;
                        System.out.printf("\n-------------------------------------------\nSeleccione la opcion deseada: \n");
                        System.out.printf("%s\n>", menuDirectores);
                        tempDirectores = sc.nextLine();

                        try {
                            selecDirectores = Integer.parseInt(tempDirectores);

                        } catch (Exception e) {
                            System.out.printf("SE PRODUJO UN ERROR EN LA INTRODUCCION DE LOS DATOS\n saliendo.... \n");
                            System.exit(-1);
                        }

                        switch (selecDirectores){
                            case 1:
                                cn.altaDirector();

                                break;
                            case 2:
                                cn.bajasDirector();

                                break;
                            case 3:
                                cn.modDirector();

                                break;
                            case 4:
                                salidaDirectores = 1;

                                break;
                            default:
                                System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                                salidaDirectores= 0;

                        }
                    }while(salidaDirectores == 0);

                    break;
                case 6:
                    String[] opActores = new String[]{"1", "2", "3","4","5"};
                    String tempActores = null;
                    String menuActores = (" 1) Altas\n 2) Bajas\n 3) Modificaciones\n 4) Consultas\n 5) Atras\n");
                    int selecActores = 0;
                    int salidaActores;

                    do {
                        salidaActores = 0;
                        System.out.printf("\n---------------------------------------------------\nSeleccione la opcion deseada: \n");
                        System.out.printf("%s\n>", menuActores);
                        tempActores = sc.nextLine();

                        try {
                            selecActores = Integer.parseInt(tempActores);

                        } catch (Exception e) {
                            System.out.printf("SE PRODUJO UN ERROR EN LA INTRODUCCION DE LOS DATOS\n saliendo.... \n");
                            System.exit(-1);
                        }

                        switch (selecActores){
                            case 1:
                                cn.altaActor();

                                break;
                            case 2:
                                cn.bajasActor();

                                break;
                            case 3:
                                cn.modActor();

                                break;
                            case 4:
                                cn.mostrarActor();

                                break;
                            case 5:
                                salidaActores = 1;

                                break;
                            default:
                                System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                                salidaActores= 0;


                        }
                    }while(salidaActores == 0);

                break;
                case 7:
                    String[] opciones = new String[]{"1", "2", "3"};
                    String tempOpciones = null;
                    String menuOpciones = (" 1) Peiculas\n 2) Directores\n 3) Actores\n 4) Atras\n");
                    int selectOpciones = 0;
                    int salidaOpciones;


                    do {
                        salidaOpciones = 0;
                        System.out.printf("\n---------------------------------------------------\nSeleccione la opcion deseada: \n");
                        System.out.printf("%s\n>", menuOpciones);
                        tempOpciones = sc.nextLine();

                        try {
                            selectOpciones = Integer.parseInt(tempOpciones);

                        } catch (Exception e) {
                            System.out.printf("SE PRODUJO UN ERROR EN LA INTRODUCCION DE LOS DATOS\n saliendo.... \n");
                            System.exit(-1);
                        }

                        switch (selectOpciones){
                            case 1:
                                cn.listarPeliculas();

                                break;
                            case 2:
                                cn.listarDirectores();

                                break;
                            case 3:
                                cn.listarActores();

                                break;
                            case 4:
                                salidaOpciones = 1;

                                break;
                            default:
                                System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                                salidaOpciones= 0;

                        }
                    }while(salidaOpciones == 0);

                    break;
                default:
                    System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                    sal = 0;
                    contador++;

            }
        } while (sal == 0);

    }//Fin runMenu
}//Fin de View.