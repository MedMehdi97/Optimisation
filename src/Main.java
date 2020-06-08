import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AlgoGenetique algoGenetique=new AlgoGenetique();
        System.out.println("Algorithme en cours d'exécutions ...");
        Clavier clav=algoGenetique.Algo();
        System.out.println(clav);
        System.out.println("Fitness de la solution : "+clav.getFitness());
    }
}
