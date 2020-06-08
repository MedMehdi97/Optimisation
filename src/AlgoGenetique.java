import java.util.ArrayList;
import java.util.Random;

public class AlgoGenetique {
    ArrayList<Clavier> populationInitiale=new ArrayList<>(); //Liste Population initial
    Fitness fitness; //Classe permettant de calculer la fitness
    Clavier clavierres; //Clavier résultat de l'algorithme
    double fitnessMax;  //Meilleur fitness retrouvé

    /**
     * Constructeur de la classe
     */
    public AlgoGenetique(){
        fitness=new Fitness();
        int i=0;
        //Génération de la population initiale Taille 25
        while (i<25){
            Clavier clavier=new Clavier(); //Chaque élement correspend à un clavier généré aléatoirement
            clavier.Random(); //Rendre le clavier aléatoire
            populationInitiale.add(clavier); //Ajouter le clavier dans la populationInitial
            i++;
        }
    }

    /**
     * Fonction qui implémente l'algorithme génetique
     * @return
     */
    public Clavier Algo(){
        ArrayList<Clavier> population=populationInitiale; //Population de départ
        int i=0;
        System.out.println("Taille de la population : 25 Probabilité de mutation: 0.03");
        //S'arrêter au bout de 20 itérations
        while (i<20){
            //Evaluer la population et sélectionner les solutions dont la fitness >0.03
            ArrayList<Clavier> populationSelect=new ArrayList<>();
            for (int j=0;j<population.size();j++){
                double fit=fitness.Evaluation(population.get(j)); //Evaluer l'élement
                if (fit >0.03){ //Garder l'élement afin de faires les opérations génétiques
                    population.get(j).setFitness(fit);
                    populationSelect.add(population.get(j));
                }
            }
            //Mutation et génération d'une nouvelle population
            ArrayList<Clavier> nvPopulation=new ArrayList();
            for (int j=0;j<populationSelect.size();j++){
                Clavier clav=populationSelect.get(j);
                //Ajouter quelque solution dans la nouvelle géneration dont la fitness est sup à 0.04
                if (clav.getFitness()>0.04){nvPopulation.add(clav);}
                //Mutation de deux élements de clavier
                Clavier clav2=new Clavier(clav);
                int indice1=generateur(); int indice2=generateur();
                char elm1=clav2.getAlpha(indice1); char elm2=clav2.getAlpha(indice2);
                clav2.setAlpha(indice1,elm2); clav2.setAlpha(indice2,elm1);
                nvPopulation.add(clav2);
            }

            //Vérifier Critères d'arrêt
            fitnessMax=0;
            for (int j=0;j<nvPopulation.size();j++){
                double fit=fitness.Evaluation(nvPopulation.get(j));
                if (fit>fitnessMax) {fitnessMax=fit;
                                        clavierres=new Clavier(nvPopulation.get(j));
                                    }
                if (fit>0.11){//Si une solution à un fitness > 0.11 arrêt de l'algorithme
                    System.out.println("Nombre d'itérations : "+(i+1)+"\n");
                    clavierres.setFitness(fit);
                    return clavierres;
                }
            }
            population=nvPopulation;
            i++;
        }
        System.out.println("Nombre d'itérations : 20\n");
        clavierres.setFitness(fitness.Evaluation(clavierres));
        return clavierres;
    }

    /**
     * Fonction qui génere un nombre entre 0 et 39
     * @return
     */
    int generateur(){
        int borneInf=0;
        int borneSup= 39;
        Random random=new Random();
        return borneInf+random.nextInt(borneSup-borneInf);
    }
}
