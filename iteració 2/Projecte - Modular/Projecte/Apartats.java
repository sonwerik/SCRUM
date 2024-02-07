import java.util.InputMismatchException;
import java.util.Scanner;
public class Apartats {
    public final String colorBlau = "\u001B[34m";
    public final String colorVermell = "\u001B[31m";
    public final String colorVerd = "\u001B[32m";
    public final String colorGroc = "\u001B[33m";
    public final String finalDeColor = "\u001B[0m";
    public final String espai = "\n";
    private final int vehiclesAutobomba = 1; // (B)
    private final int vehiclesAutoescala = 2; // (E)
    private final int ambulancies = 3; // (A)
    private final int vehiclesSalvament = 4; // (S)
    private final int[][] vehiclesDelsParcs;
    private final int[][] totalVehiclesParcs;
    private final boolean[][] disponibilitatVehiclesDelsParcs;
    private int coordenadaIncidenciaX = 0;
    private int coordenadaIncidenciaY = 0;
    private int opcioMenu = 0;
    private final int quantitatParcs = 5;
    public final Scanner sc = new Scanner(System.in);

    public Apartats(){
        totalVehiclesParcs = new int[][] {
                {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4},
                {1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4},
                {1,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4},
                {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4},
                {1,1,1,1,1,1,1,1,2,2,2,3,3,3,3,3,3,3,4,4,4}
        };
        vehiclesDelsParcs = new int[][] {
                {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4},
                {1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4},
                {1,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4},
                {1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4},
                {1,1,1,1,1,1,1,1,2,2,2,3,3,3,3,3,3,3,4,4,4}
        };
        disponibilitatVehiclesDelsParcs = new boolean[][] {
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
        };
    }
    public String[][] mapaParcBombers (){
        return new String[][] {
                {"P","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","P","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","P","O","O","O","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","P","O","O","O","O","O","O"},
                {"O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"},
                {"O","O","P","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O","O"}
        };
    }
    public void imprimirMapaBombers (int index, int[][] coordenadesParcs){
        System.out.println("Representació de mapa amb els parcs de bombers més propers: ");
        System.out.println("Esquerra a dreta seguint ordre de files: Parc 1, Parc 2, Parc 3, Parc 4 i Parc 5");
        String[][] mapaParcBombers = mapaParcBombers();
        for (int i = 0; i < mapaParcBombers.length; i++) {
            System.out.println();
            for (int j = 0; j < mapaParcBombers[i].length; j++) {
                if (mapaParcBombers[i][j].equals("P")) {
                    mapaParcBombers[i][j] = colorBlau + "P" + finalDeColor;
                    coordenadesParcs[index][0] = i;
                    coordenadesParcs[index][1] = j;
                    index++;
                }
                System.out.print(mapaParcBombers[i][j] + " ");
            }
        }
        System.out.println(espai);
    }
    public int coordenadaHoritzontalIncidencia(){
        boolean comprovarDada = false;
        do{
            System.out.print(colorBlau + "Introdueix la coordenada x: " + finalDeColor);
            try {
                coordenadaIncidenciaX = sc.nextInt();
                comprovarDada = true;
            } catch (InputMismatchException e) {
                System.out.println(colorVermell + "Opció no vàlida. Si us plau, introdueix un número." + finalDeColor);
                sc.nextLine();
                coordenadaIncidenciaX = 0;
            }
        }while(!comprovarDada);
        return coordenadaIncidenciaX;
    }
    public int coordenadaVerticalIncidencia(int coordenadaIncidenciaY, boolean comprovarDada){
        do{
            System.out.print(colorBlau + "Introdueix la coordenada y: " + finalDeColor);
            try {
                coordenadaIncidenciaY = sc.nextInt();
                comprovarDada = true;
            } catch (InputMismatchException e) {
                System.out.println(colorVermell + "Opció no vàlida. Si us plau, introdueix un número." + finalDeColor);
                sc.nextLine();
                coordenadaIncidenciaY = 0;
                comprovarDada = false;
                continue;
            }
        }while(!comprovarDada);
        return coordenadaIncidenciaY;
    }
    public void textAmbLesCoordenadesIncidencia(int coordenadaIncidenciaX, int coordenadaIncidenciaY){
        System.out.println(espai + "Coordenades de l'incident: (" + coordenadaIncidenciaX + "," + coordenadaIncidenciaY + ")" + espai);
    }
    public int[] buscarElsParcsMesPropersSegonsIncidencia(int index, int[][] coordenadesParcs, int[] aproximitatParcs, int parcMesProper, int coordenadaIncidenciaX, int coordenadaIncidenciaY){
        while(index != quantitatParcs){
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
        return aproximitatParcs;
    }
    public int[] ordenarElsParcsMesPropersSegonsIncidencia(int[] aproximitatParcs, String[] nomParcs){
        for (int i = 0; i < quantitatParcs - 1; i++) {
            for (int j = 0; j < quantitatParcs - i - 1; j++) {
                if (aproximitatParcs[j] > aproximitatParcs[j + 1]) {
                    int tempAproximitatParcs = aproximitatParcs[j];
                    aproximitatParcs[j] = aproximitatParcs[j + 1];
                    aproximitatParcs[j + 1] = tempAproximitatParcs;
                    String tempNomParcs = nomParcs[j];
                    nomParcs[j] = nomParcs[j + 1];
                    nomParcs[j + 1] = tempNomParcs;
                }
            }
        }
        return aproximitatParcs;
    }
    public int[] aproximitatDelsParcs(int index, int[][] coordenadesParcs, int[] aproximitatParcs, int coordenadaIncidenciaX, int coordenadaIncidenciaY, String[] nomParcs){
        int parcMesProper = Integer.MAX_VALUE;
        int[] aproximitatDelsParcs = buscarElsParcsMesPropersSegonsIncidencia(index, coordenadesParcs, aproximitatParcs, parcMesProper, coordenadaIncidenciaX, coordenadaIncidenciaY);
        aproximitatDelsParcs = ordenarElsParcsMesPropersSegonsIncidencia(aproximitatParcs, nomParcs);
        return  aproximitatDelsParcs;
    }
    public void imprimirOrdreParcsSegonsIncidencia(int[] aproximitatParcs, String[] nomParcs, int indexParc){
        System.out.println("Parcs ordenats segons aproximació del incident registrat: " + espai);
        for (int i = 0; i < aproximitatParcs.length; i++) {
            System.out.print(nomParcs[i] + " " + espai);
        }
        System.out.println(espai + colorVerd + "El parc més proper és el " + nomParcs[indexParc] + finalDeColor);
    }
    public int guardarElParcMesProper(String[] nomParcs, int indexParc, int guardarParc){
        if(nomParcs[indexParc].equals("Parc 1")) guardarParc = 0;
        if(nomParcs[indexParc].equals("Parc 2")) guardarParc = 1;
        if(nomParcs[indexParc].equals("Parc 3")) guardarParc = 2;
        if(nomParcs[indexParc].equals("Parc 4")) guardarParc = 3;
        if(nomParcs[indexParc].equals("Parc 5")) guardarParc = 4;
        return guardarParc;
    }
    public void textMenuTipusEmergencia(){
        System.out.println(espai + colorBlau + "=====================================" + finalDeColor);
        System.out.println(colorBlau + "         TIPUS D'EMERGÈNCIA" + finalDeColor);
        System.out.println(colorBlau + "=====================================" + finalDeColor + espai);
        System.out.println("1.- Incendi petit");
        System.out.println("2.- Incendi gran");
        System.out.println("3.- Accident de cotxe");
        System.out.println("4.- Fuita de gas");
        System.out.println("5.- Ascensor amb persones atrapades");
        System.out.println("6.- Despreniment de façana" + espai);
    }
    public void tipusDeCasEmergencia(){
        if(opcioMenu == 1 || opcioMenu == 2 ||opcioMenu == 3 ||opcioMenu == 4){
            System.out.println("És un cas de prioritat màxima." + espai);
        } else if (opcioMenu == 5 || opcioMenu == 6){
            System.out.println("És un cas de prioritat mitjana." + espai);
        }
    }
    public int menuTipusEmergencia(boolean comprovarDada){
        do{
            textMenuTipusEmergencia();
            System.out.print(colorBlau + "Indiqui el tipus d'emergència: " + finalDeColor);
            try {
                opcioMenu = sc.nextInt();
                comprovarDada = true;
            } catch (InputMismatchException e) {
                System.out.println(colorVermell + "Opció no vàlida. Si us plau, introdueix un número." + finalDeColor);
                sc.nextLine();
                opcioMenu = 0;
                comprovarDada = false;
            }
        }while(!comprovarDada);
        System.out.println();
        return opcioMenu;
    }
    public int indexParcModificatPerEmergencies(String[] nomParcs, int indexParc, int guardarParc){
        if(nomParcs[indexParc].equals("Parc 1")) guardarParc = -1;
        if(nomParcs[indexParc].equals("Parc 2")) guardarParc = 0;
        if(nomParcs[indexParc].equals("Parc 3")) guardarParc = 1;
        if(nomParcs[indexParc].equals("Parc 4")) guardarParc = 2;
        if(nomParcs[indexParc].equals("Parc 5")) guardarParc = 3;
        return guardarParc;
    }
    public boolean enviarVehicleAutobomba(int i, int j, boolean autobombaDisponible, String[] nomParcs, int indexParc, int guardarParc, int vehicleAutobomba){
        if (vehiclesDelsParcs[i][j] == vehiclesAutobomba) {
            if (disponibilitatVehiclesDelsParcs[i][j] && !autobombaDisponible) {
                autobombaDisponible = true;
                System.out.println(colorVerd + "Enviat un vehicle autobomba del " + nomParcs[indexParc] + finalDeColor);
                if (j < vehicleAutobomba) {
                    if(i == guardarParc){
                        vehiclesDelsParcs[i][j] = 0;
                        disponibilitatVehiclesDelsParcs[i][j] = false;
                    }
                }
            }
        }
        return autobombaDisponible;
    }
    public boolean enviarVehicleAmbulancia(int i, int j, boolean ambulanciaDisponible, String[] nomParcs, int indexParc, int guardarParc, int vehicleAmbulancia){
        if (vehiclesDelsParcs[i][j] == ambulancies) {
            if (disponibilitatVehiclesDelsParcs[i][j] && !ambulanciaDisponible) {
                ambulanciaDisponible = true;
                System.out.println(colorVerd + "Enviada una ambulància del " + nomParcs[indexParc] + finalDeColor);
                if (j < vehicleAmbulancia) {
                    if(i == guardarParc){
                        vehiclesDelsParcs[i][j] = 0;
                        disponibilitatVehiclesDelsParcs[i][j] = false;
                    }
                }
            }
        }
        return ambulanciaDisponible;
    }
    public boolean enviarVehicleAutoescala(int i, int j, boolean autoescalaDisponible, String[] nomParcs, int indexParc, int guardarParc, int vehicleAutoescala){
        if(vehiclesDelsParcs[i][j] == vehiclesAutoescala){
            if (disponibilitatVehiclesDelsParcs[i][j] && !autoescalaDisponible) {
                autoescalaDisponible = true;
                System.out.println(colorVerd + "Enviat un vehicle autoescala del " + nomParcs[indexParc] + finalDeColor);
                if(j < vehicleAutoescala){
                    if(i == guardarParc){
                        vehiclesDelsParcs[i][j] = 0;
                        disponibilitatVehiclesDelsParcs[i][j] = false;
                    }
                }
            }
        }
        return autoescalaDisponible;
    }
    public boolean enviarVehicleSalvament(int i, int j, boolean salvamentDisponible, String[] nomParcs, int indexParc, int guardarParc, int vehicleSalvament){
        if(vehiclesDelsParcs[i][j] == vehiclesSalvament){
            if (disponibilitatVehiclesDelsParcs[i][j] && !salvamentDisponible) {
                salvamentDisponible = true;
                System.out.println(colorVerd + "Enviat un vehicle de salvament del " + nomParcs[indexParc] + finalDeColor);
                if(j < vehicleSalvament){
                    if(i == guardarParc){
                        vehiclesDelsParcs[i][j] = 0;
                        disponibilitatVehiclesDelsParcs[i][j] = false;
                    }
                }
            }
        }
        return salvamentDisponible;
    }
    public int buscarVehiclesEnElSeguentParcDisponible(String[] nomParcs, int indexParc){
        System.out.print(colorVermell + "No s'han trobat un o més dels vehicles al " + nomParcs[indexParc] + finalDeColor);
        indexParc++;
        System.out.println(colorVermell + ", buscant disponibilitat al " + nomParcs[indexParc] + finalDeColor);
        return indexParc;
    }
    public void llistatDeCasosEmergencia(int guardarParc, int indexParc, String[] nomParcs){
        boolean autobombaDisponible = false; boolean ambulanciaDisponible = false; boolean autoescalaDisponible = false; boolean salvamentDisponible = false;
        switch (opcioMenu) {
            case 1:
                System.out.println("Es necessita un vehicle autobomba i una ambulància." + espai);
                processarPrimerCasIncidencia(guardarParc, autobombaDisponible, ambulanciaDisponible, nomParcs, indexParc);
                break;
            case 2:
                System.out.println("Es necessita un vehicle autobomba, una ambulància, un vehicle autoescala i un vehicle de salvament." + espai);
                processarSegonCasIncidencia(guardarParc, autobombaDisponible, ambulanciaDisponible, autoescalaDisponible, salvamentDisponible, nomParcs, indexParc);
                break;
            case 3, 4, 5:
                System.out.println("Es necessita un vehicle de salvament i una ambulància." + espai);
                processarTercerQuartCinqueCasIncidencia(guardarParc, ambulanciaDisponible, salvamentDisponible, nomParcs, indexParc);
                break;
            case 6:
                System.out.println("Es necessita un vehicle autoescala i un vehicle de salvament." + espai);
                processarSiseCasIncidencia(guardarParc, autoescalaDisponible, salvamentDisponible, nomParcs, indexParc);
                break;
            default: System.out.println(colorVermell + "Opció no vàlida." + finalDeColor);
        }
    }
    public int canviarParcPerBuscarDisponibilitat(int guardarParc, String[] nomParcs, int indexParc){
        guardarParc = indexParcModificatPerEmergencies(nomParcs, indexParc, guardarParc);
        guardarParc++;
        return guardarParc;
    }
    public void processarPrimerCasIncidencia(int guardarParc, boolean autobombaDisponible, boolean ambulanciaDisponible, String[] nomParcs, int indexParc){
        while(guardarParc <= quantitatParcs){
            int vehicleAutobomba = Integer.MAX_VALUE; int vehicleAmbulancia = Integer.MAX_VALUE;
            System.out.println(colorVermell + "Buscant vehicles disponibles..." + finalDeColor);
            for (int i = 0; i < vehiclesDelsParcs.length; i++) {
                for (int j = 0; j < vehiclesDelsParcs[i].length; j++) {
                    if (i == guardarParc) {
                        autobombaDisponible = enviarVehicleAutobomba(i, j, autobombaDisponible, nomParcs, indexParc, guardarParc, vehicleAutobomba);
                        ambulanciaDisponible = enviarVehicleAmbulancia(i, j, ambulanciaDisponible, nomParcs, indexParc, guardarParc, vehicleAmbulancia);
                    }
                }
            }
            if(autobombaDisponible && ambulanciaDisponible) break;
            indexParc = buscarVehiclesEnElSeguentParcDisponible(nomParcs, indexParc);
            if(indexParc == quantitatParcs){
                System.out.println(colorVermell + "No hi ha vehicles disponibles." + finalDeColor);
                break;
            }
            guardarParc = canviarParcPerBuscarDisponibilitat(guardarParc, nomParcs, indexParc);
        }
    }
    public void processarSegonCasIncidencia(int guardarParc, boolean autobombaDisponible, boolean ambulanciaDisponible, boolean autoescalaDisponible, boolean salvamentDisponible, String[] nomParcs, int indexParc){
        while(guardarParc <= quantitatParcs){
            int vehicleAutobomba = Integer.MAX_VALUE; int vehicleAmbulancia = Integer.MAX_VALUE; int vehicleAutoescala = Integer.MAX_VALUE; int vehicleSalvament = Integer.MAX_VALUE;
            System.out.println(colorVermell + "Buscant vehicles disponibles..." + finalDeColor);
            for (int i = 0; i < vehiclesDelsParcs.length; i++) {
                for (int j = 0; j < vehiclesDelsParcs[i].length; j++) {
                    if(i == guardarParc){
                        autobombaDisponible = enviarVehicleAutobomba(i, j, autobombaDisponible, nomParcs, indexParc, guardarParc, vehicleAutobomba);
                        ambulanciaDisponible = enviarVehicleAmbulancia(i, j, ambulanciaDisponible, nomParcs, indexParc, guardarParc, vehicleAmbulancia);
                        autoescalaDisponible = enviarVehicleAutoescala(i, j, autoescalaDisponible, nomParcs, indexParc, guardarParc, vehicleAutoescala);
                        salvamentDisponible = enviarVehicleSalvament(i, j, salvamentDisponible, nomParcs, indexParc, guardarParc, vehicleSalvament);
                    }
                }
            }
            if(autobombaDisponible && ambulanciaDisponible && autoescalaDisponible && salvamentDisponible) break;
            indexParc = buscarVehiclesEnElSeguentParcDisponible(nomParcs, indexParc);
            if(indexParc == quantitatParcs){
                System.out.println(colorVermell + "No hi ha vehicles disponibles." + finalDeColor);
                break;
            }
            guardarParc = canviarParcPerBuscarDisponibilitat(guardarParc, nomParcs, indexParc);
        }
    }
    public void processarTercerQuartCinqueCasIncidencia(int guardarParc, boolean ambulanciaDisponible, boolean salvamentDisponible, String[] nomParcs, int indexParc){
        while (guardarParc <= quantitatParcs){
            int vehicleAmbulancia = Integer.MAX_VALUE; int vehicleSalvament = Integer.MAX_VALUE;
            System.out.println(colorVermell + "Buscant vehicles disponibles..." + finalDeColor);
            for (int i = 0; i < vehiclesDelsParcs.length; i++) {
                for (int j = 0; j < vehiclesDelsParcs[i].length; j++) {
                    if(i == guardarParc){
                        ambulanciaDisponible = enviarVehicleAmbulancia(i, j, ambulanciaDisponible, nomParcs, indexParc, guardarParc, vehicleAmbulancia);
                        salvamentDisponible = enviarVehicleSalvament(i, j, salvamentDisponible, nomParcs, indexParc, guardarParc, vehicleSalvament);
                    }
                }
            }
            if(salvamentDisponible && ambulanciaDisponible) break;
            indexParc = buscarVehiclesEnElSeguentParcDisponible(nomParcs, indexParc);
            if(indexParc == quantitatParcs){
                System.out.println(colorVermell + "No hi ha vehicles disponibles." + finalDeColor);
                break;
            }
            guardarParc = canviarParcPerBuscarDisponibilitat(guardarParc, nomParcs, indexParc);
        }
    }
    public void processarSiseCasIncidencia(int guardarParc, boolean autoescalaDisponible, boolean salvamentDisponible, String[] nomParcs, int indexParc){
        while(guardarParc <= quantitatParcs){
            int vehicleAutoescala = Integer.MAX_VALUE; int vehicleSalvament = Integer.MAX_VALUE;
            System.out.println(colorVermell + "Buscant vehicles disponibles..." + finalDeColor);
            for (int i = 0; i < vehiclesDelsParcs.length; i++) {
                for (int j = 0; j < vehiclesDelsParcs[i].length; j++) {
                    if(i == guardarParc){
                        autoescalaDisponible = enviarVehicleAutoescala(i, j, autoescalaDisponible, nomParcs, indexParc, guardarParc, vehicleAutoescala);
                        salvamentDisponible = enviarVehicleSalvament(i, j, salvamentDisponible, nomParcs, indexParc, guardarParc, vehicleSalvament);
                    }
                }
            }
            if(autoescalaDisponible && salvamentDisponible) break;
            indexParc = buscarVehiclesEnElSeguentParcDisponible(nomParcs, indexParc);
            if(indexParc == quantitatParcs){
                System.out.println(colorVermell + "No hi ha vehicles disponibles." + finalDeColor);
                break;
            }
            guardarParc = canviarParcPerBuscarDisponibilitat(guardarParc, nomParcs, indexParc);
        }
    }
    public void opcioDeTornarAlMenuPrincipal(){
        do{
            System.out.println(espai + colorGroc + "1. Tornar al menu principal." + finalDeColor);
            System.out.println(colorVermell + "2. Sortir del programa" + finalDeColor + espai);
            System.out.print(colorBlau + "Selecciona una opció: " + finalDeColor);
            try {
                opcioMenu = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(colorVermell + "Opció no vàlida. Si us plau, introdueix un número." + finalDeColor);
                sc.nextLine();
                opcioMenu = 0;
                continue;
            }
            if(opcioMenu == 2){
                System.out.println("Sortint del programa...");
                System.exit(0);
            } else if(opcioMenu == 1){
                System.out.println("Tornant al menu principal...");
            } else{
                System.out.println(colorVermell + "Opció no vàlida." + finalDeColor);
            }
        }while(opcioMenu != 1);
    }
    public void iniciarServei(){
        int espaiPerCoordenades = 2;
        int [][] coordenadesParcs = new int[quantitatParcs][espaiPerCoordenades];
        String [] nomParcs = {"Parc 1", "Parc 2", "Parc 3", "Parc 4", "Parc 5"};
        int [] aproximitatParcs = new int[quantitatParcs];
        boolean comprovarDada = false;
        int indexParc = 0; int guardarParc = 0; int index = 0;
        imprimirMapaBombers(index, coordenadesParcs);
        coordenadaIncidenciaX = coordenadaHoritzontalIncidencia();
        coordenadaIncidenciaY = coordenadaVerticalIncidencia(coordenadaIncidenciaY, comprovarDada);
        textAmbLesCoordenadesIncidencia(coordenadaIncidenciaX, coordenadaIncidenciaY);
        aproximitatParcs = aproximitatDelsParcs(index, coordenadesParcs, aproximitatParcs, coordenadaIncidenciaX, coordenadaIncidenciaY, nomParcs);
        imprimirOrdreParcsSegonsIncidencia(aproximitatParcs, nomParcs, indexParc);
        guardarParc = guardarElParcMesProper(nomParcs, indexParc, guardarParc);
        opcioMenu = menuTipusEmergencia(comprovarDada);
        tipusDeCasEmergencia();
        llistatDeCasosEmergencia(guardarParc, indexParc, nomParcs);
        opcioDeTornarAlMenuPrincipal();
    }
    public void textEstatDelsVehicles(int contadorParcs, int contadorVehiclesBomba, int contadorVehiclesBombaNo, int contadorVehiclesAutoescala, int contadorVehiclesAutoescalaNo, int contadorAmbulancies, int contadorAmbulanciesNo, int contadorVehiclesSalvament, int contadorVehiclesSalvamentNo){
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
    }
    public void estatDelsVehicles(){
        int contadorVehiclesBomba = 0; int contadorVehiclesAutoescala = 0; int contadorAmbulancies = 0; int contadorVehiclesSalvament = 0;
        int contadorVehiclesBombaNo = 0; int contadorVehiclesAutoescalaNo = 0; int contadorAmbulanciesNo = 0; int contadorVehiclesSalvamentNo = 0;
        int contadorParcs = 1;
        for (int i = 0; i < totalVehiclesParcs.length; i++) {
            for (int j = 0; j < totalVehiclesParcs[i].length; j++) {
                if (totalVehiclesParcs[i][j] == vehiclesAutobomba) {
                    if(disponibilitatVehiclesDelsParcs[i][j]) contadorVehiclesBomba++;
                    else contadorVehiclesBombaNo++;

                }if (totalVehiclesParcs[i][j]== vehiclesAutoescala) {
                    if(disponibilitatVehiclesDelsParcs[i][j]) contadorVehiclesAutoescala++;
                    else contadorVehiclesAutoescalaNo++;

                }if (totalVehiclesParcs[i][j] == ambulancies) {
                    if(disponibilitatVehiclesDelsParcs[i][j]) contadorAmbulancies++;
                    else contadorAmbulanciesNo++;

                }if (totalVehiclesParcs[i][j] == vehiclesSalvament) {
                    if(disponibilitatVehiclesDelsParcs[i][j]) contadorVehiclesSalvament++;
                    else contadorVehiclesSalvamentNo++;
                }
            }
            textEstatDelsVehicles(contadorParcs, contadorVehiclesBomba, contadorVehiclesBombaNo, contadorVehiclesAutoescala, contadorVehiclesAutoescalaNo, contadorAmbulancies, contadorAmbulanciesNo, contadorVehiclesSalvament, contadorVehiclesSalvamentNo);
            System.out.println();
            contadorVehiclesBomba = 0; contadorVehiclesAutoescala = 0; contadorAmbulancies = 0; contadorVehiclesSalvament = 0;
            contadorVehiclesBombaNo = 0; contadorVehiclesAutoescalaNo = 0; contadorAmbulanciesNo = 0; contadorVehiclesSalvamentNo = 0;
            contadorParcs++;
        }
        opcioDeTornarAlMenuPrincipal();
    }
    public void dotacionsDisponibles() {
        for (int i = 0; i < totalVehiclesParcs.length; i++) {
            int contadorVehiclesBomba = 0, contadorVehiclesAutoescala = 0, contadorAmbulancies = 0, contadorVehiclesSalvament = 0;

            for (int j : totalVehiclesParcs[i]) {
                if (j == vehiclesAutobomba) contadorVehiclesBomba++;
                else if (j == vehiclesAutoescala) contadorVehiclesAutoescala++;
                else if (j == ambulancies) contadorAmbulancies++;
                else if (j == vehiclesSalvament) contadorVehiclesSalvament++; }

            System.out.printf("\n\u001B[34m=====================================\u001B[0m\n" +
                    "\u001B[34m          DOTACIONS PARC %d \u001B[0m\n" +
                    "\u001B[34m=====================================\u001B[0m\n" +
                    "Vehicles autobomba: %d\n" +
                    "Vehicles autoescala: %d\n" +
                    "Ambulàncies: %d\n" +
                    "Vehicles de salvament: %d\n", i + 1, contadorVehiclesBomba, contadorVehiclesAutoescala, contadorAmbulancies, contadorVehiclesSalvament);
        }
        opcioDeTornarAlMenuPrincipal();
    }
}