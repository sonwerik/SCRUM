import java.util.InputMismatchException;
import java.util.Scanner;
public class Apartats {
    private final int vehiclesAutobomba = 1; // (B)
    private final int vehiclesAutoescala = 2; // (E)
    private final int ambulancies = 3; // (A)
    private final int vehiclesSalvament = 4; // (S)
    private final int vehiclesCatalogatsC = 5; // (C)
    private final int[][] vehiclesDelsParcs;
    private final int[][] totalVehiclesParcs;
    private final boolean[][] disponibilitatVehiclesDelsParcs;
    private int opcioMenu = 0;
    private final int quantitatParcs = 5;
    Scanner sc = new Scanner(System.in);
    public Apartats(){

        totalVehiclesParcs = new int[][] {

                {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5},
                {1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5},
                {1,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,5,5,5,5,5,5},
                {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5},
                {1,1,1,1,1,1,1,1,2,2,2,3,3,3,3,3,3,3,4,4,4,4,5,5,5,5}
        };

        vehiclesDelsParcs = new int[][] {

                {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5},
                {1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5},
                {1,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,5,5,5,5,5,5},
                {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5},
                {1,1,1,1,1,1,1,1,2,2,2,3,3,3,3,3,3,3,4,4,4,4,5,5,5,5}
        };

        disponibilitatVehiclesDelsParcs = new boolean[][] {

                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
        };
    }
    public void iniciarServei(){

        //Per comparar els parcs i quedar-se amb el més proper
        int parcMesProper = Integer.MAX_VALUE;

        System.out.println("Representació de mapa amb els parcs de bombers més propers: ");
        System.out.println("Esquerra a dreta seguint ordre de files: Parc 1, Parc 2, Parc 3, Parc 4 i Parc 5");

        //Matriu, de moment només amb els parcs.
        String [][] mapaParcBombers = {

                {"P","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","P","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","P","O","O","O","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","P","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"},
                {"O","O","P","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"}
        };
        //Matriu especial per agafar les coordenades dels parcs.
        int [][] coordenadesParcs = new int[quantitatParcs][2];

        //Index que ajudará a agafar les coordenades.
        int index = 0;

        //Array amb el nom dels parcs.
        String [] nomParcs = {"Parc 1", "Parc 2", "Parc 3", "Parc 4", "Parc 5"};
        int [] aproximitatParcs = new int[quantitatParcs];

        //Mapa amb els parcs i els vehicles.
        for (int i = 0; i < mapaParcBombers.length; i++) {
            System.out.println();
            for (int j = 0; j < mapaParcBombers[i].length; j++) {
                switch (mapaParcBombers[i][j]) {
                    case "P" -> {

                        //Cambiar el color amb ASCI per estètica.
                        mapaParcBombers[i][j] = "\u001B[34mP\u001B[0m";

                        //Omplim l'array de les coordenades amb la localització dels parcs que hi ha.
                        coordenadesParcs[index][0] = i;
                        coordenadesParcs[index][1] = j;
                        index++;
                    }
                }
                System.out.print(mapaParcBombers[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println();

        //Reset de l'index, el torno a utilitzar per fer les distàncies entre punt d'incidència i parcs.
        index = 0;

        int coordenadaIncidenciaX = 0;
        int coordenadaIncidenciaY = 0;
        boolean comprovarDada = false;
        //Només avançarà si s'introdueix un int. Mateix procediment que en el menú.
        do{
            System.out.print("\u001B[34mIntrodueix la coordenada x: \u001B[0m");
            try {
                coordenadaIncidenciaX = sc.nextInt();
                comprovarDada = true;
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mOpció no vàlida. Si us plau, introdueix un número.\u001B[0m");
                sc.nextLine();
                coordenadaIncidenciaX = 0;
                comprovarDada = false;
                continue;
            }
        }while(!comprovarDada);
        comprovarDada = false;
        do{
            System.out.print("\u001B[34mIntrodueix la coordenada y: \u001B[0m");
            try {
                coordenadaIncidenciaY = sc.nextInt();
                comprovarDada = true;
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mOpció no vàlida. Si us plau, introdueix un número.\u001B[0m");
                sc.nextLine();
                coordenadaIncidenciaY = 0;
                comprovarDada = false;
                continue;
            }
        }while(!comprovarDada);
        comprovarDada = false;

        System.out.println("\nCoordenades de l'incident: (" + coordenadaIncidenciaX + "," + coordenadaIncidenciaY + ")");

        //Fa la diferència entre el punt d'incidència i els altres parcs. Omple una array amb les distàncies.
        while(index != 5){
            for (int i = 0; i < coordenadesParcs.length; i++) {
                int parcX = 0;
                int parcY = 0;
                for (int j = 0; j < coordenadesParcs[i].length; j++) {
                    parcX = coordenadesParcs[index][0];
                    parcY = coordenadesParcs[index][1];
                }
                aproximitatParcs[i] = Math.abs(coordenadaIncidenciaX - parcX) + Math.abs(coordenadaIncidenciaY - parcY);
                if(parcMesProper > aproximitatParcs[i]){
                    parcMesProper = aproximitatParcs[i];
                }
                index++;
            }
        }

        //Ordena l'array de més proper a més gran i ho relaciona amb el nom dels parcs corresponents.
        for (int i = 0; i < quantitatParcs - 1; i++) {

            for (int j = 0; j < quantitatParcs - i - 1; j++) {

                if (aproximitatParcs[j] > aproximitatParcs[j + 1]) {

                    int tempPoints = aproximitatParcs[j];
                    aproximitatParcs[j] = aproximitatParcs[j + 1];
                    aproximitatParcs[j + 1] = tempPoints;

                    String tempTeam = nomParcs[j];
                    nomParcs[j] = nomParcs[j + 1];
                    nomParcs[j + 1] = tempTeam;
                }
            }
        }
        //Array amb els parcs ordenats segons aproximació.
        System.out.println("Parcs ordenats segons aproximació del incident registrat: ");
        System.out.println();
        for (int i = 0; i < aproximitatParcs.length; i++) {
            System.out.print(nomParcs[i] + " ");
            System.out.println();
        }

        System.out.println();

        int indexParc = 0;
        System.out.println("\u001B[32mEl parc més proper és el " + nomParcs[indexParc] + "\u001B[0m");

        int guardarParc = 0;
        if(nomParcs[indexParc].equals("Parc 1")) guardarParc = 0;
        if(nomParcs[indexParc].equals("Parc 2")) guardarParc = 1;
        if(nomParcs[indexParc].equals("Parc 3")) guardarParc = 2;
        if(nomParcs[indexParc].equals("Parc 4")) guardarParc = 3;
        if(nomParcs[indexParc].equals("Parc 5")) guardarParc = 4;


        do{
            //Seleccionem el tipus d'emergència.
            System.out.println();
            System.out.println("\u001B[34m=====================================\u001B[0m");
            System.out.println("\u001B[34m         TIPUS D'EMERGÈNCIA\u001B[0m");
            System.out.println("\u001B[34m=====================================\u001B[0m");
            System.out.println();

            System.out.println("1.- Incendi petit");
            System.out.println("2.- Incendi gran");
            System.out.println("3.- Accident de cotxe");
            System.out.println("4.- Fuita de gas");
            System.out.println("5.- Ascensor amb persones atrapades");
            System.out.println("6.- Despreniment de façana");
            System.out.println();

            System.out.print("\u001B[34mIndiqui el tipus d'emergència: \u001B[0m");
            try {
                opcioMenu = sc.nextInt();
                comprovarDada = true;
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mOpció no vàlida. Si us plau, introdueix un número.\u001B[0m");
                sc.nextLine();
                opcioMenu = 0;
                comprovarDada = false;
                continue;
            }
        }while(!comprovarDada);

        System.out.println();

        //Disponibilitat de vehicles. Aquests booleans són per asegurar que només s'agafa la quantitat desitjada.
        boolean autobombaDisponible = false;
        boolean ambulanciaDisponible = false;
        boolean autoescalaDisponible = false;
        boolean salvamentDisponible = false;

        if(opcioMenu == 1 || opcioMenu == 2 ||opcioMenu == 3 ||opcioMenu == 4){

            System.out.println("És un cas de prioritat màxima.");
            System.out.println();
        } else if (opcioMenu == 5 || opcioMenu == 6){

            System.out.println("És un cas de prioritat mitjana.");
            System.out.println();
        }
        //Busquem els vehicles tenint en conte l'ordre de priotitat i anem adjudicant vehicles segons el cas seleccionat.
        switch (opcioMenu) {

            case 1:
                //Tots els comentaris d'aquest cas apliquen als altres cinc restants, és el mateix mètode. 
                System.out.println("Es necessita un vehicle autobomba i una ambulància.");
                System.out.println();
                while(guardarParc <= quantitatParcs && indexParc < 5){

                    int primerVehicle1 = Integer.MAX_VALUE;
                    int segonVehicle1 = Integer.MAX_VALUE;

                    System.out.println("\u001B[31mBuscant vehicles disponibles...\u001B[0m");
                    for (int i = 0; i < vehiclesDelsParcs.length; i++) {
                        for (int j = 0; j < vehiclesDelsParcs[i].length; j++) {
                            if (i == guardarParc) {
                                //Mirem disponibilitat, si n'hi ha modifiquem la matriu dels vehicles i la de desponibilitat.
                                if (vehiclesDelsParcs[i][j] == vehiclesAutobomba) {
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !autobombaDisponible) {
                                        autobombaDisponible = true;
                                        System.out.println("\u001B[32mEnviat un vehicle autobomba del " + nomParcs[indexParc] + "\u001B[0m");
                                        if (j < primerVehicle1) {
                                            primerVehicle1 = j;
                                            if(i == guardarParc && j == primerVehicle1){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                                if (vehiclesDelsParcs[i][j] == ambulancies) {
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !ambulanciaDisponible) {
                                        ambulanciaDisponible = true;
                                        System.out.println("\u001B[32mEnviada una ambulància del " + nomParcs[indexParc] + "\u001B[0m");
                                        if (j < segonVehicle1) {
                                            segonVehicle1 = j;
                                            if(i == guardarParc && j == segonVehicle1){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(autobombaDisponible && ambulanciaDisponible){
                        //Si no tallem el bucle quan hem trobat els vehicles disponibles que volem peta el programa. Safety first.
                        break;
                    }
                    //Si no trobem vehicles disponibles en el primer parc adjudicat, passarà al següent.
                    System.out.print("\u001B[31mNo s'han trobat un o més dels vehicles al " + nomParcs[indexParc] + " \u001B[0m");
                    indexParc++;
                    System.out.println("\u001B[31m, buscant disponibilitat al " + nomParcs[indexParc] + "\u001B[0m");

                    //Si s'esgoten les opcions es trenca el bucle perque no peti el programa.
                    if(indexParc == 5){
                        System.out.println("\u001B[31mNo hi ha vehicles disponibles.\u001B[0m");
                        break;
                    }
                    //Per agafar bé l'índex prèviament ordenat basant-nos en el nom del Parc.
                    if(nomParcs[indexParc].equals("Parc 1")) guardarParc = -1;
                    if(nomParcs[indexParc].equals("Parc 2")) guardarParc = 0;
                    if(nomParcs[indexParc].equals("Parc 3")) guardarParc = 1;
                    if(nomParcs[indexParc].equals("Parc 4")) guardarParc = 2;
                    if(nomParcs[indexParc].equals("Parc 5")) guardarParc = 3;
                    //Li he restat -1 a tots els índex anteriors perque aquest ++ sigui precís.
                    guardarParc++;
                }
                break;

            case 2:
                System.out.println("Es necessita un vehicle autobomba, una ambulància un vehicle autoescala i un vehicle de salvament.");
                System.out.println();
                while(guardarParc <= quantitatParcs){

                    int primerVehicle2 = Integer.MAX_VALUE;
                    int segonVehicle2 = Integer.MAX_VALUE;
                    int tercerVehicle2 = Integer.MAX_VALUE;
                    int quartVehicle2 = Integer.MAX_VALUE;

                    System.out.println("\u001B[31mBuscant vehicles disponibles...\u001B[0m");
                    for (int i = 0; i < vehiclesDelsParcs.length; i++) {
                        for (int j = 0; j < vehiclesDelsParcs[i].length; j++) {
                            if(i == guardarParc){
                                if(vehiclesDelsParcs[i][j] == vehiclesAutobomba){
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !autobombaDisponible) {
                                        autobombaDisponible = true;
                                        System.out.println("\u001B[32mEnviat un vehicle autobomba del " + nomParcs[indexParc] + "\u001B[0m");
                                        if(j < primerVehicle2){
                                            primerVehicle2 = j;
                                            if(i == guardarParc && j == primerVehicle2){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                                if(vehiclesDelsParcs[i][j] == ambulancies){
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !ambulanciaDisponible) {
                                        ambulanciaDisponible = true;
                                        System.out.println("\u001B[32mEnviada una ambulància del " + nomParcs[indexParc] + "\u001B[0m");
                                        if(j < segonVehicle2){
                                            segonVehicle2 = j;
                                            if(i == guardarParc && j == segonVehicle2){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                                if(vehiclesDelsParcs[i][j] == vehiclesAutoescala){
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !autoescalaDisponible) {
                                        autoescalaDisponible = true;
                                        System.out.println("\u001B[32mEnviat un vehicle autoescala del " + nomParcs[indexParc] + "\u001B[0m");
                                        if(j < tercerVehicle2){
                                            tercerVehicle2 = j;
                                            if(i == guardarParc && j == tercerVehicle2){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                                if(vehiclesDelsParcs[i][j] == vehiclesSalvament){
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !salvamentDisponible) {
                                        salvamentDisponible = true;
                                        System.out.println("\u001B[32mEnviat un vehicle de salvament del " + nomParcs[indexParc] + "\u001B[0m");
                                        if(j < quartVehicle2){
                                            quartVehicle2 = j;
                                            if(i == guardarParc && j == quartVehicle2){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(autobombaDisponible && ambulanciaDisponible && autoescalaDisponible && salvamentDisponible){
                        break;
                    }
                    System.out.print("\u001B[31mNo s'han trobat un o més dels vehicles al " + nomParcs[indexParc] + " \u001B[0m");
                    indexParc++;
                    System.out.println("\u001B[31m, buscant disponibilitat al " + nomParcs[indexParc] + "\u001B[0m");

                    if(indexParc == 5){
                        System.out.println("\u001B[31mNo hi ha vehicles disponibles.\u001B[0m");
                        break;
                    }

                    if(nomParcs[indexParc].equals("Parc 1")) guardarParc = -1;
                    if(nomParcs[indexParc].equals("Parc 2")) guardarParc = 0;
                    if(nomParcs[indexParc].equals("Parc 3")) guardarParc = 1;
                    if(nomParcs[indexParc].equals("Parc 4")) guardarParc = 2;
                    if(nomParcs[indexParc].equals("Parc 5")) guardarParc = 3;

                    guardarParc++;
                }
                break;

            case 3, 4, 5:
                System.out.println("Es necessita un vehicle de salvament i una ambulància.");
                System.out.println();
                while (guardarParc <= quantitatParcs){

                    int primerVehicle3 = Integer.MAX_VALUE;
                    int segonVehicle3 = Integer.MAX_VALUE;

                    System.out.println("\u001B[31mBuscant vehicles disponibles...\u001B[0m");
                    for (int i = 0; i < vehiclesDelsParcs.length; i++) {
                        for (int j = 0; j < vehiclesDelsParcs[i].length; j++) {
                            if(i == guardarParc){
                                if(vehiclesDelsParcs[i][j] == vehiclesSalvament){
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !salvamentDisponible) {
                                        salvamentDisponible = true;
                                        System.out.println("\u001B[32mEnviat un vehicle de salvament del " + nomParcs[indexParc] + "\u001B[0m");
                                        if(j < primerVehicle3){
                                            primerVehicle3 = j;
                                            if(i == guardarParc && j == primerVehicle3){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                                if(vehiclesDelsParcs[i][j] == ambulancies){
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !ambulanciaDisponible) {
                                        ambulanciaDisponible = true;
                                        System.out.println("\u001B[32mEnviada una ambulància del " + nomParcs[indexParc] + "\u001B[0m");
                                        if(j < segonVehicle3){
                                            segonVehicle3 = j;
                                            if(i == guardarParc && j == segonVehicle3){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(salvamentDisponible && ambulanciaDisponible){
                        break;
                    }
                    System.out.print("\u001B[31mNo s'han trobat un o més dels vehicles al " + nomParcs[indexParc] + " \u001B[0m");
                    indexParc++;
                    System.out.println("\u001B[31m, buscant disponibilitat al " + nomParcs[indexParc] + "\u001B[0m");

                    if(indexParc == 5){
                        System.out.println("\u001B[31mNo hi ha vehicles disponibles.\u001B[0m");
                        break;
                    }

                    if(nomParcs[indexParc].equals("Parc 1")) guardarParc = -1;
                    if(nomParcs[indexParc].equals("Parc 2")) guardarParc = 0;
                    if(nomParcs[indexParc].equals("Parc 3")) guardarParc = 1;
                    if(nomParcs[indexParc].equals("Parc 4")) guardarParc = 2;
                    if(nomParcs[indexParc].equals("Parc 5")) guardarParc = 3;

                    guardarParc++;
                }
                break;

            case 6:
                System.out.println("Es necessita un vehicle autoescala i un vehicle de salvament.");
                System.out.println();
                while(guardarParc <= quantitatParcs){

                    int primerVehicle4 = Integer.MAX_VALUE;
                    int segonVehicle4 = Integer.MAX_VALUE;

                    System.out.println("\u001B[31mBuscant vehicles disponibles...\u001B[0m");
                    for (int i = 0; i < vehiclesDelsParcs.length; i++) {
                        for (int j = 0; j < vehiclesDelsParcs[i].length; j++) {
                            if(i == guardarParc){
                                if(vehiclesDelsParcs[i][j] == vehiclesAutoescala){
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !autoescalaDisponible) {
                                        autoescalaDisponible = true;
                                        System.out.println("\u001B[32mEnviat un vehicle autoescala del " + nomParcs[indexParc] + "\u001B[0m");
                                        if(j < primerVehicle4){
                                            primerVehicle4 = j;
                                            if(i == guardarParc && j == primerVehicle4){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                                if(vehiclesDelsParcs[i][j] == vehiclesSalvament){
                                    if (disponibilitatVehiclesDelsParcs[i][j] && !salvamentDisponible) {
                                        salvamentDisponible = true;
                                        System.out.println("\u001B[32mEnviada un vehicle de salvament del " + nomParcs[indexParc] + "\u001B[0m");
                                        if(j < segonVehicle4){
                                            segonVehicle4 = j;
                                            if(i == guardarParc && j == segonVehicle4){
                                                vehiclesDelsParcs[i][j] = 0;
                                                disponibilitatVehiclesDelsParcs[i][j] = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(autoescalaDisponible && salvamentDisponible){
                        break;
                    }
                    System.out.print("\u001B[31mNo s'han trobat un o més dels vehicles al " + nomParcs[indexParc] + " \u001B[0m");
                    indexParc++;
                    System.out.println("\u001B[31m, buscant disponibilitat al " + nomParcs[indexParc] + "\u001B[0m");

                    if(indexParc == 5){
                        System.out.println("\u001B[31mNo hi han vehicles disponibles.\u001B[0m");
                        break;
                    }

                    if(nomParcs[indexParc].equals("Parc 1")) guardarParc = -1;
                    if(nomParcs[indexParc].equals("Parc 2")) guardarParc = 0;
                    if(nomParcs[indexParc].equals("Parc 3")) guardarParc = 1;
                    if(nomParcs[indexParc].equals("Parc 4")) guardarParc = 2;
                    if(nomParcs[indexParc].equals("Parc 5")) guardarParc = 3;

                    guardarParc++;
                }
                break;

            default:
                System.out.println("\u001B[31mOpció no vàlida.\u001B[0m");
        }

        do{
            System.out.println();
            System.out.println("\u001B[33m1. Tornar al menu principal.\u001B[0m");
            System.out.println("\u001B[31m2. Sortir del programa\u001B[0m");
            System.out.println();
            System.out.print("\u001B[34mSelecciona una opció: \u001B[0m");
            try {
                opcioMenu = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mOpció no vàlida. Si us plau, introdueix un número.\u001B[0m");
                sc.nextLine();
                opcioMenu = 0;
                continue;
            }
            if(opcioMenu == 2){
                System.out.println("Sortint del programa...");
                System.exit(0);
            }
            else if(opcioMenu == 1){
                System.out.println("Tornant al menu principal...");
            }
            else{
                System.out.println("\u001B[31mOpció no vàlida.\u001B[0m");
                System.out.println();
            }
        }while(opcioMenu != 1);
    }
    //Estats dels vehicles. Si están disponibles per ocupar-se d'una incidència o no.
    public void estatDelsVehicles(){

        int contadorVehiclesBomba = 0;
        int contadorVehiclesAutoescala = 0;
        int contadorAmbulancies = 0;
        int contadorVehiclesSalvament = 0;
        int contadorVehiclesC = 0;

        int contadorVehiclesBombaNo = 0;
        int contadorVehiclesAutoescalaNo = 0;
        int contadorAmbulanciesNo = 0;
        int contadorVehiclesSalvamentNo = 0;
        int contadorVehiclesCNo = 0;

        int contadorParcs = 1;

        for (int i = 0; i < totalVehiclesParcs.length; i++) {
            for (int j = 0; j < totalVehiclesParcs[i].length; j++) {
                if (totalVehiclesParcs[i][j] == vehiclesAutobomba) {
                    if(disponibilitatVehiclesDelsParcs[i][j]){
                        contadorVehiclesBomba++;
                    }
                    else{
                        contadorVehiclesBombaNo++;
                    }
                }
                if (totalVehiclesParcs[i][j]== vehiclesAutoescala) {
                    if(disponibilitatVehiclesDelsParcs[i][j]){
                        contadorVehiclesAutoescala++;
                    }
                    else{
                        contadorVehiclesAutoescalaNo++;
                    }
                }
                if (totalVehiclesParcs[i][j] == ambulancies) {
                    if(disponibilitatVehiclesDelsParcs[i][j]){
                        contadorAmbulancies++;
                    }
                    else{
                        contadorAmbulanciesNo++;
                    }
                }
                if (totalVehiclesParcs[i][j] == vehiclesSalvament) {
                    if(disponibilitatVehiclesDelsParcs[i][j]){
                        contadorVehiclesSalvament++;
                    }
                    else{
                        contadorVehiclesSalvamentNo++;
                    }
                }
                if (totalVehiclesParcs[i][j] == vehiclesCatalogatsC) {
                    if(disponibilitatVehiclesDelsParcs[i][j]){
                        contadorVehiclesC++;
                    }
                    else{
                        contadorVehiclesCNo++;
                    }
                }
            }
            System.out.println("\u001B[34m=====================================\u001B[0m");
            System.out.println("\u001B[34m   DISPONIBILITAT VEHICLES PARC " + contadorParcs + "\u001B[0m");
            System.out.println("\u001B[34m=====================================\u001B[0m");

            System.out.println("Vehicles autobomba disponibles: " + contadorVehiclesBomba);
            if(contadorVehiclesBombaNo > 0)System.out.println("\u001B[31mVehicles autobomba NO disponibles: \u001B[0m" + contadorVehiclesBombaNo);

            System.out.println("Vehicles autoescala disponibles: " + contadorVehiclesAutoescala);
            if(contadorVehiclesAutoescalaNo > 0)System.out.println("\u001B[31mVehicles autoescala NO disponibles: \u001B[0m" + contadorVehiclesAutoescalaNo);

            System.out.println("Ambulàncies disponibles: " + contadorAmbulancies);
            if(contadorAmbulanciesNo > 0)System.out.println("\u001B[31mAmbulàncies NO disponibles: \u001B[0m" + contadorAmbulanciesNo);

            System.out.println("Vehicles de salvament disponibles: " + contadorVehiclesSalvament);
            if(contadorVehiclesSalvamentNo > 0)System.out.println("\u001B[31mVehicles de salvament NO disponibles: \u001B[0m" + contadorVehiclesSalvamentNo);

            System.out.println("Vehicles C disponibles: " + contadorVehiclesC);
            if(contadorVehiclesCNo> 0)System.out.println("\u001B[31mVehicles C NO disponibles: \u001B[0m" + contadorVehiclesCNo);

            System.out.println();

            //Fem un reset de tots els contadors.
            contadorVehiclesBomba = 0;
            contadorVehiclesAutoescala = 0;
            contadorAmbulancies = 0;
            contadorVehiclesSalvament = 0;
            contadorVehiclesC = 0;

            contadorVehiclesBombaNo = 0;
            contadorVehiclesAutoescalaNo = 0;
            contadorAmbulanciesNo = 0;
            contadorVehiclesSalvamentNo = 0;
            contadorVehiclesCNo = 0;

            contadorParcs++;
        }
        do{
            System.out.println();
            System.out.println("\u001B[33m1. Tornar al menu principal.\u001B[0m");
            System.out.println("\u001B[31m2. Sortir del programa\u001B[0m");
            System.out.println();
            System.out.print("\u001B[34mSelecciona una opció: \u001B[0m");
            try {
                opcioMenu = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mOpció no vàlida. Si us plau, introdueix un número.\u001B[0m");
                sc.nextLine();
                opcioMenu = 0;
                continue;
            }
            if(opcioMenu == 2){
                System.out.println("Sortint del programa...");
                System.exit(0);
            }
            else if(opcioMenu == 1){
                System.out.println("Tornant al menu principal...");
            }
            else{
                System.out.println("\u001B[31mOpció no vàlida.\u001B[0m");
            }
        }while(opcioMenu != 1);
    }
    //Quantital total (que no disponible o no) de vehicles registrats en cada parc.
    public void DotacionsDisponibles(){

        int contadorVehiclesBomba = 0;
        int contadorVehiclesAutoescala = 0;
        int contadorAmbulancies = 0;
        int contadorVehiclesSalvament = 0;
        int contadorVehiclesC = 0;
        int contadorParcs = 1;

        for (int[] totalVehiclesParcs : totalVehiclesParcs) {
            for (int i : totalVehiclesParcs) {
                if (i == vehiclesAutobomba) {
                    contadorVehiclesBomba++;
                } else if (i == vehiclesAutoescala) {
                    contadorVehiclesAutoescala++;
                } else if (i == ambulancies) {
                    contadorAmbulancies++;
                } else if (i == vehiclesSalvament) {
                    contadorVehiclesSalvament++;
                } else if (i == vehiclesCatalogatsC) {
                    contadorVehiclesC++;
                }
            }

            System.out.println();
            System.out.println("\u001B[34m=====================================\u001B[0m");
            System.out.println("\u001B[34m          DOTACIONS PARC " + contadorParcs + " \u001B[0m");
            System.out.println("\u001B[34m=====================================\u001B[0m");
            System.out.println("Vehicles autobomba: " + contadorVehiclesBomba);
            System.out.println("Vehicles autoescala: " + contadorVehiclesAutoescala);
            System.out.println("Ambulàncies: " + contadorAmbulancies);
            System.out.println("Vehicles de salvament: " + contadorVehiclesSalvament);
            System.out.println("Vehicles C: " + contadorVehiclesC);

            contadorVehiclesBomba = 0;
            contadorVehiclesAutoescala = 0;
            contadorAmbulancies = 0;
            contadorVehiclesSalvament = 0;
            contadorVehiclesC = 0;

            contadorParcs++;
        }
        do{
            System.out.println();
            System.out.println("\u001B[33m1. Tornar al menu principal.\u001B[0m");
            System.out.println("\u001B[31m2. Sortir del programa\u001B[0m");
            System.out.println();
            System.out.print("\u001B[34mSelecciona una opció: \u001B[0m");
            try {
                opcioMenu = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mOpció no vàlida. Si us plau, introdueix un número.\u001B[0m");
                sc.nextLine();
                opcioMenu = 0;
                continue;
            }

            if(opcioMenu == 2){
                System.out.println("Sortint del programa...");
                System.exit(0);
            }
            else if(opcioMenu == 1){
                System.out.println("Tornant al menu principal...");
            }
            else{
                System.out.println("\u001B[31mOpció no vàlida.\u001B[0m");
            }
        }while(opcioMenu != 1);
    }
}