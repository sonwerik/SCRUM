import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {
    public static void main(String[] args) {
        Apartats ap = new Apartats();
        Scanner sc = new Scanner(System.in);

        int menu;

        do{
            System.out.println();
            System.out.println("\u001B[34m====================================\u001B[0m");
            System.out.println("\u001B[34mSISTEMA DE BOMBERS - MENÚ PRINCIPAL\u001B[0m");
            System.out.println("\u001B[34m====================================\u001B[0m");
            System.out.println("1 - Introduir nou servei.");
            System.out.println("2 - Estat dels parcs.");
            System.out.println("\u001B[31m3 - Sortir del programa\u001B[0m");
            System.out.println("\u001B[34m====================================\u001B[0m");
            System.out.println();

            System.out.print("\u001B[34mSeleccioneu una opció: \u001B[0m");

            //Evitem un runtime error quan s'intenta introduir una dada que no sigui int, en aquest cas.
            try {
                menu = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mOpció no vàlida. Si us plau, introdueix un número.\u001B[0m");
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
                    int opcioParcs;
                    do {
                        System.out.println();
                        System.out.println("\u001B[34m=====================================\u001B[0m");
                        System.out.println("\u001B[34m     ESTAT DELS PARCS - SUBMENÚ\u001B[0m");
                        System.out.println("\u001B[34m=====================================\u001B[0m");
                        System.out.println("1. Dotacions de cada parc");
                        System.out.println("2. Estat dels vehicles");
                        System.out.println("\u001B[33m3. Tornar al menú principal\u001B[0m");
                        System.out.println("\u001B[31m4. Sortir del programa\u001B[0m");
                        System.out.println("\u001B[34m=====================================\u001B[0m");
                        System.out.println();
                        System.out.print("\u001B[34mSelecciona una opció: \u001B[0m");

                        try {
                            opcioParcs = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("\u001B[31mOpció no vàlida. Si us plau, introdueix un número.\u001B[0m");
                            sc.nextLine();
                            opcioParcs = 0;
                            continue;
                        }

                        switch (opcioParcs) {
                            case 1:
                                System.out.println("Mostrant les dotacions disponibles de cada parc...");
                                System.out.println();
                                ap.DotacionsDisponibles();
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
                                System.out.println("\u001B[31mOpció no vàlida.\u001B[0m");
                        }

                    } while (opcioParcs != 3);
                    break;

                case 3:
                    System.out.println("Sortint del programa. Fins aviat!");
                    break;

                default:
                    System.out.println("\u001B[31mOpció no vàlida.\u001B[0m");
            }

        }while (menu != 3);

    }

}
