import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class Brocante {

    private int phase = 1;

    private Emplacement[] tableEmplacements;
    private HashMap<String, Integer> mapRiverains;
    private ArrayDeque<Emplacement> pileEmplacementsLibres;

    //private String tableRiverains[] 
    //inutile, regardez bien les schemas, cette table n'apparait pas !


    public int getPhase() {
        return phase;
    }

    /**
     * initialise une brocante avec nombre emplacements
     * @param nombreEmplacements le nombre d'emplacements
     * @param tableRiverains la table des riverains
     * @throws IllegalArgumentException si le nombre d'emplacements est negatif ou nul ou si la table des riverains est null
     */
    public Brocante(int nombreEmplacements, String[] tableRiverains) {
        tableEmplacements = new Emplacement[nombreEmplacements];
        mapRiverains = new HashMap<String, Integer>();
        pileEmplacementsLibres = new ArrayDeque<>();

        for (int i = 0; i < nombreEmplacements; i++) {
            Emplacement emplacement = new Emplacement(i);
            tableEmplacements[i] = emplacement;
            pileEmplacementsLibres.add(emplacement);
        }
        for (int i = 1; i <= tableRiverains.length; i++) {
            mapRiverains.put(tableRiverains[i-1], 0);
        }
    }

    /**
     * reserve l'emplacement qui porte le numero passe en parametre au demandeur passe en parametre
     * La reservation reussit si
     *     l'emplacement est libre
     *     le demandeur est bien un riverain
     *     le riverain n'a pas encore 3 emplacements
     * @param demandeur le riverain qui demande un emplacement
     * @param numeroEmplacement le numero de l'emplacement souhaite
     * @return true si la reservation a reussi, false sinon
     * @throws IllegalArgumentException si le numero de l'emplacement n'existe pas
     * @throws IllegalStateException si on n'est pas en phase 1
     */
    public boolean reserver(Exposant demandeur, int numeroEmplacement) {
        if (numeroEmplacement < 0 || numeroEmplacement > tableEmplacements.length)
            throw new IllegalArgumentException();
        if (phase != 1)
            throw new IllegalStateException();

        if (!estUnRiverain(demandeur.getNom()))
            return false;
        if (!estLibre(numeroEmplacement))
            return false;

        int nombreEmplacements = nombreEmplacementsRiverain(demandeur.getNom());
        if (nombreEmplacements >= 3)
            return false;

        pileEmplacementsLibres.remove(tableEmplacements[numeroEmplacement]);
        tableEmplacements[numeroEmplacement].setExposant(demandeur);
        mapRiverains.put(demandeur.getNom(), ++nombreEmplacements);
        return true;

        //Attention pour augmenter le nombre d'emplacements
        //Solution ko:
        //Integer nombreEmplacements = mapRiverains.get(demandeur);
        //mapRiverains.put(demandeur, nombreEmplacements++);
        //Solutions ok:
        //Integer nombreEmplacements = mapRiverains.get(demandeur);
        //mapRiverains.put(demandeur, ++nombreEmplacements);
        //ou:
        //Integer nombreEmplacements = mapRiverains.get(demandeur);
        //nombreEmplacements++;
        //mapRiverains.put(demandeur, nombreEmplacements);
    }

    /**
     * attribue automatiquement un emmplacement libre au demandeur passe en parametre
     * @param demandeur le demandeur d'un emplacement
     * @return le numero de l'emplacement attribue ou -1 si plus d'emplacement libre
     * @throws IllegalStateException si on n'est pas en phase 2
     */
    public int attribuerAutomatiquementEmplacement(Exposant demandeur) {
        if (phase != 2)
            throw new IllegalStateException();
        if (!emplacementLibre())
            return -1;

        Emplacement emplacementAttribue = pileEmplacementsLibres.pop();
        int numeroEmplacement = emplacementAttribue.getNumero();
        tableEmplacements[numeroEmplacement].setExposant(demandeur);
        Integer nombreEmplacements = mapRiverains.get(demandeur.getNom());
        mapRiverains.put(demandeur.getNom(), ++nombreEmplacements);
        return numeroEmplacement;

    }

    /**
     * a comme effet de passer de la phase 1 a la phase 2
     * si deja en phase 2, rien ne doit etre fait
     */
    public void changerPhase() {
        if (phase == 1) phase = 2;
        //Pensez a initialiser la pile!!!

    }

    public boolean estUnRiverain(String nom) {
        return mapRiverains.containsKey(nom);
    }

    public int nombreEmplacementsRiverain(String nom) {
        return mapRiverains.get(nom);
    }

    public boolean estLibre(int numeroEmplacement) {
        return pileEmplacementsLibres.contains(tableEmplacements[numeroEmplacement]);
    }

    public boolean emplacementLibre() {
        return !pileEmplacementsLibres.isEmpty();
    }

    @Override
    public String toString() {
        String aRenvoyer="";
        for (int i = 0; i < tableEmplacements.length; i++) {
            if(tableEmplacements[i].getExposant()==null){
                aRenvoyer +=  ("\n"+i+" : /");
            }else{
                aRenvoyer +=  ("\n"+i+" : "+tableEmplacements[i].getExposant());
            }
        }
        return aRenvoyer;
    }

    //Pour le debug
    public String donnerTableEmplacements() {
        if(tableEmplacements==null)
            return "null";
        return Arrays.toString(tableEmplacements);
    }

    //Pour le debug
    public String donnerMapRiverains() {
        if(mapRiverains==null)
            return "null";
        return mapRiverains.toString();
    }

    //Pour le debug
    public String donnerPileEmplacementsLibres() {
        if(pileEmplacementsLibres==null)
            return "null";
        return pileEmplacementsLibres.toString();
    }
  
}
