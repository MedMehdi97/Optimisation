import java.util.ArrayList;
import java.util.Random;

public class Clavier {
    //List permettant de stocker les caractères du clavier
    ArrayList<Character> clavier=new ArrayList<>();
    //Fitness du clavier
    Double fitness;

    /**
     * Constructeur de la classe Clavier
     */
    public Clavier(){
        clavier=new ArrayList<>();
        for (int i=0;i<40;i++){
            clavier.add(' ');
        }
    }

    /**
     * Constructeur de la classe Clavier  partir d'un autre clavier
     * @param clav
     */
    public Clavier(Clavier clav){
            for (int i=0;i<40;i++){
                char car=clav.getAlpha(i);
                this.clavier.add(car);
            }
    }

    /**
     * Modification d'un élement du tableau
     * @param emp
     * @param alpha
     */
    public void setAlpha(int emp,char alpha){
        this.clavier.set(emp,alpha);
    }

    /**
     * Génerer un clavier aléatoirement
     */
    public void Random(){
            String Alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            ArrayList listIndice=new ArrayList();
            for (int i=0;i<26;i++){
                int indice=generateur();
                while (listIndice.contains(indice)){
                    indice=generateur();
                }
                listIndice.add(indice);
                this.clavier.set(indice,Alphabet.charAt(i));
            }
    }

    /**
     * Retourne le contenu d'une case
     * @param indice
     * @return
     */
    public char getAlpha(int indice){
        return this.clavier.get(indice);
    }

    /**
     * Retourne le bigramme à partir d'un indice en concaténant l'element suivant
     * @param indice
     * @return
     */
    public String getBigramme(int indice){
        StringBuilder bigramme=new StringBuilder("");
        bigramme.append(clavier.get(indice));
        bigramme.append(clavier.get(indice+1));
        return bigramme.toString();
    }

    /**
     * Générer un nombre aléatoirement entre 0 et 39
     * @return
     */
    int generateur(){
        int borneInf=0;
        int borneSup= 39;
        Random random=new Random();
        return borneInf+random.nextInt(borneSup-borneInf);
    }

    /**
     * Fonction setFitness
     * @param fitness
     */
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    /**
     * Fonction getFitness
     * @return
     */
    public Double getFitness() {
        return this.fitness;
    }

    /**
     * Fonction to String
     * @return
     */
    @Override
    public String toString() {
        StringBuilder text=new StringBuilder("");
        for (int i=0;i<40;i++){
            text.append(clavier.get(i));
            if (i%10==9){text.append("\n");} else {text.append("\t|\t");}
        }
        return text.toString();
    }

}
