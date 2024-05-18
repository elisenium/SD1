import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Concours {

    private Candidat[] tableCandidats;
    private HashMap<String,Candidat> mapCandidats; //nom --> candidat
    private HashMap<String,Integer> mapVotants; //numero de telephone --> nombre de votes
    private int nombreMaxVotes;

    /**
     * debute un concours
     * @param tableNomsCandidats la table avec les noms des candidats
     * @param nombreMaxVotes le nombre maximal de votes qu'un spectateur peut faire
     * @throws IllegalArgumentException
     *           s'il n'y a pas au moins un vote possible par spectateur
     *           si la table des noms des candidats est null ou est vide
     *           s'il y a des homonymes
     */
    public Concours(String[] tableNomsCandidats, int nombreMaxVotes){
        //TODO
        if (nombreMaxVotes < 1)
            throw new IllegalArgumentException();
        if (tableNomsCandidats == null || tableNomsCandidats.length == 0)
            throw new IllegalArgumentException();

        this.nombreMaxVotes = nombreMaxVotes;
        tableCandidats = new Candidat[tableNomsCandidats.length];
        mapCandidats = new HashMap<String, Candidat>();
        mapVotants = new HashMap<String, Integer>();

        for (int i = 0; i < tableNomsCandidats.length; i++) {
            if (mapCandidats.containsKey(tableNomsCandidats[i]))
                throw new IllegalArgumentException();
            Candidat candidat = new Candidat(tableNomsCandidats[i], i+1);
            tableCandidats[i] = candidat;
            mapCandidats.put(tableNomsCandidats[i], candidat);
        }
    }

    public int getNombreMaxVotes() {
        return nombreMaxVotes;
    }

    /**
     * ajoute 1 vote au candidat dont le nom est passe en parametre
     * a condition que le candidat existe
     * et a condition que le nombre max de votes n'est pas atteint pour le numero de telephone passe en parametre
     * @param numeroTelephone le numero de telephone qui fait le vote
     * @param nomCandidat le nom du candidat qui fait l'objet du vote
     * @return true si le vote a ete fait, false sinon
     * @throws IllegalArgumentException
     *              si le numero de telephone est null
     *              si le nom du candidat est null
     */
    public boolean voterViaNom(String numeroTelephone, String nomCandidat) {
        //TODO
        if (numeroTelephone == null || numeroTelephone.equals(""))
            throw new IllegalArgumentException();
        if (nomCandidat == null || nomCandidat.equals(""))
            throw new IllegalArgumentException();

        if (!mapCandidats.containsKey(nomCandidat))
            return false;

        Integer nombreVotes = 0;
        if (mapVotants.containsKey(numeroTelephone))
            nombreVotes = mapVotants.get(numeroTelephone);

        if (nombreVotes >= nombreMaxVotes)
            return false;

        Candidat candidat = mapCandidats.get(nomCandidat);

        candidat.ajouter1Vote();
        mapCandidats.put(nomCandidat, candidat);
        mapVotants.put(numeroTelephone, ++nombreVotes);
        return true;
    }

    /**
     * ajoute 1 vote au candidat dont le numero est passe en parametre
     * a condition que le candidat existe
     * et a condition que le nombre max de votes n'est pas atteint pour le numero de telephone passe en parametre
     * @param numeroTelephone le numero de telephone qui fait le vote
     * @param numeroCandidat le numero du candidat qui fait l'objet du vote
     * @return true si le vote a ete fait, false sinon
     * @throws IllegalArgumentException si le numero de telephone est null
     */
    public boolean voterViaNumero(String numeroTelephone, int numeroCandidat) {
        //TODO
        if (numeroTelephone == null || numeroTelephone.equals(""))
            throw new IllegalArgumentException();

        if (numeroCandidat < 0 || numeroCandidat > tableCandidats.length)
            return false;

        Integer nombreVotes = 0;
        if (mapVotants.containsKey(numeroTelephone))
            nombreVotes = mapVotants.get(numeroTelephone);

        if (nombreVotes >= nombreMaxVotes)
            return false;

        Candidat candidat = tableCandidats[numeroCandidat-1];

        candidat.ajouter1Vote();
        mapCandidats.put(candidat.getNom(), candidat);
        mapVotants.put(numeroTelephone, ++nombreVotes);
        return true;
    }

    /**
     * construit une table dans laquelle les candidats apparaissent tries selon l'ordre decroissant des nombres de vote
     * @return la table dans laquelle les candidats apparaissent tries selon l'ordre decroissant des nombres de vote
     */
    public Candidat[] classement(){
        // Utilisez les methodes copyOf() et sort() de la classe Arrays !
        // cfr enonce
        //TODO
        Candidat[] classement = Arrays.copyOf(tableCandidats, tableCandidats.length);
        Arrays.sort(classement, new ComparateurCandidats());
        return classement;
    }

    @Override
    // A NE PAS MODIFIER
    public String toString() {
        return "maxVotes=" + nombreMaxVotes +
                "\ntableCandidats=" + Arrays.toString(tableCandidats) +
                "\nmapCandidats=" + mapCandidats +
                "\nmapVotants=" + mapVotants;
    }

}

