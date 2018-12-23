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
            System.out.printf("Asi se depura: %d\n", contador);
             sal = 1;
            System.out.printf("Seleccione la opcion deseada: \n");
            System.out.printf("%s\n", menu);
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
                    break;

                case 3:
                     cn.archivo();
                    break;

                case 4:
                   // cn.peliculas();
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
                    sal = 0;
                    System.out.printf("No se encontró la opcion solicitada\n Reintentando...\n \n");
                    contador++;
                    break;
            }
        } while (sal == 0);

    }
}
