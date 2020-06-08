import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Fitness {
    //Hashmap des bigrammes avec leurs fréquences selon le fichier freqBigrammes.txt
    HashMap<String,Integer> FrequencesBigrammes=new HashMap<>();
    //Somme des fréquences du fichier
    int SommeFrequence=0;

    /**
     * Constructeur de la classe Fitness
     */
    public Fitness(){
        try {
            //Lecture du fichier freqBigrammes.txt renomé en test.txt
            BufferedReader bufferedReader=new BufferedReader(new FileReader("test.txt"));
            String line=bufferedReader.readLine();
            int i=0;
            String Alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            //Lecture du fichier et mettre les informations dans une hashMap
            while ((line=bufferedReader.readLine())!=null){
                int j=0;
                String[] Nombre=line.split("\\s");
                for (int k=1;k<27;k++){
                    //Génération du bigramme
                    StringBuilder bigramme=new StringBuilder("");
                    bigramme.append(Alphabet.charAt(i));
                    bigramme.append(Alphabet.charAt(j));
                    //Ajout du bigramme et ça fréquence dans le hashMap
                    FrequencesBigrammes.put(bigramme.toString(),Integer.valueOf(Nombre[k]));
                    SommeFrequence+=Integer.valueOf(Nombre[k]); //Somme de tous les fréquences du fichier freqBigrammes.txt
                    j++;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Veuillez placer le fichier test.txt dans le même répertoire du jar" );
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calcul de la fintness d'un clavier (élement de la population)
     * @param clavier
     * @return
     */
    public double Evaluation(Clavier clavier){
        double result=0;
        int sommeFreqclav=0; //Ensemble des fréquences des bigrammes disponible dans le clavier
        for (int i=0;i<40;i++){
            if (i%10!=9 && clavier.getAlpha(i)!=' '&& clavier.getAlpha(i+1)!=' '){
                String bigramme=clavier.getBigramme(i);
                sommeFreqclav+=FrequencesBigrammes.get(bigramme);
            }
        }
        //Evaluer le clavier en divisant ses fréquences sur le nbr de fréquences total
        result=(double)sommeFreqclav/(double)SommeFrequence;
        return result;
    }
}
