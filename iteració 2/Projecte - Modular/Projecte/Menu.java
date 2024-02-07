import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {
    Apartats ap = new Apartats();
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Apartats ap = new Apartats();
        Scanner sc = new Scanner(System.in);
        int menu;

        do{
            System.out.println();
            System.out.println(ap.colorBlau + "====================================" + ap.finalDeColor);
            System.out.println(ap.colorBlau + "SISTEMA DE BOMBERS - MENÚ PRINCIPAL" + ap.finalDeColor);
            System.out.println(ap.colorBlau + "====================================" + ap.finalDeColor);
            System.out.println("1 - Introduir nou servei.");
            System.out.println("2 - Estat dels parcs.");
            System.out.println(ap.colorVermell + "3 - Sortir del programa" + ap.finalDeColor);
            System.out.println(ap.colorBlau + "====================================" + ap.finalDeColor);
            System.out.println();
            System.out.print(ap.colorBlau + "Seleccioneu una opció: " + ap.finalDeColor);
            try {
                menu = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ap.colorVermell + "Opció no vàlida. Si us plau, introdueix un número." + ap.finalDeColor);
                sc.nextLine();
                menu = 0;
                continue;
            }

            switch (menu){

                case 1:
                    System.out.println("Introduint un nou servei...");
                    System.out.println();
                    ap.iniciarServei();
                    break;

                case 2:
                    do {
                        System.out.println();
                        System.out.println(ap.colorBlau + "=====================================" + ap.finalDeColor);
                        System.out.println(ap.colorBlau + "     ESTAT DELS PARCS - SUBMENÚ" + ap.finalDeColor);
                        System.out.println(ap.colorBlau + "=====================================" + ap.finalDeColor);
                        System.out.println("1. Dotacions de cada parc");
                        System.out.println("2. Estat dels vehicles");
                        System.out.println(ap.colorGroc + "3. Tornar al menú principal" + ap.finalDeColor);
                        System.out.println(ap.colorVermell + "4. Sortir del programa" + ap.finalDeColor);
                        System.out.println(ap.colorBlau + "=====================================" + ap.finalDeColor);
                        System.out.println();
                        System.out.print(ap.colorBlau + "Selecciona una opció: " + ap.finalDeColor);

                        try {
                            menu = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println(ap.colorVermell + "Opció no vàlida. Si us plau, introdueix un número." + ap.finalDeColor);
                            sc.nextLine();
                            menu = 0;
                            continue;
                        }

                        switch (menu) {
                            case 1:
                                System.out.println("Mostrant les dotacions disponibles de cada parc...");
                                System.out.println();
                                ap.dotacionsDisponibles();
                                break;
                            case 2:
                                System.out.println("Mostrant estats dels vehicles...");
                                System.out.println();
                                ap.estatDelsVehicles();
                                break;
                            case 3:
                                System.out.println("Tornant al menú principal.");
                                break;
                            case 4:
                                System.out.println("Sortint del programa. Fins aviat!");
                                System.exit(0);
                            default:
                                System.out.println(ap.colorVermell + "Opció no vàlida." + ap.finalDeColor);
                        }

                    } while (menu != 3);
                    menu = 0;
                    break;

                case 3:
                    System.out.println("Sortint del programa. Fins aviat!");
                    break;

                default:
                    System.out.println(ap.colorVermell + "Opció no vàlida." + ap.finalDeColor);
            }

        }while (menu != 3);

    }

}
