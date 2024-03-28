import java.util.HashSet;
import java.util.Iterator;

public class RallyeAutomobile {

    ListeSDImpl<String> pilotes;
    ListeSDImpl<String> depart;

    ListeSDImpl<String> classement;
    HashSet<String> ensembleDisqualifies;

    public RallyeAutomobile(String[] lesPilotes) {
       pilotes = new ListeSDImpl<>();
       depart = new ListeSDImpl<>();
       classement = new ListeSDImpl<>();
       ensembleDisqualifies = new HashSet<>();

        for (String pilote : lesPilotes) {
            pilotes.insererEnQueue(pilote);
            depart.insererEnQueue(pilote);
        }
    }

    public String donnerPiloteEnTete() {
        return pilotes.premier();
    }

    public boolean supprimer(String pilote) {
        if (pilote == null || pilote.equals(""))
            throw new IllegalArgumentException();

        if (!ensembleDisqualifies.add(pilote))
            return false;

        if (!depart.supprimer(pilote))
            return false;
        pilotes.supprimer(pilote);
        return true;
    }

    public boolean estDisqualifie(String pilote) {
        if (pilote == null || pilote.equals(""))
            throw new IllegalArgumentException();

        return ensembleDisqualifies.contains(pilote);
    }

    public boolean aTermineCourse(String pilote) {
        if (pilote == null || pilote.equals(""))
            throw new IllegalArgumentException();

        return classement.contient(pilote);
    }

    public boolean pasEnCourse(String pilote) {
        return !depart.contient(pilote);
    }

    public int getPosition(String pilote) {
        if (pilote == null || pilote.equals(""))
            throw new IllegalArgumentException();
        int index = 1;

        for (String piloteSuivant : pilotes) {
            if (estDisqualifie(piloteSuivant)) {
                index += 0;
            }
            if (piloteSuivant.equals(pilote))
                return index;
            index++;
        }

        return -1;
    }

    public boolean depasser(String pilote) {
        if (estDisqualifie(pilote) || aTermineCourse(pilote))
            return false;
        String piloteDepasse = pilotes.donnerPrecedent(pilote);
        pilotes.permuter(pilote, piloteDepasse);
        return true;
    }

    public void franchirLigneDArrivee() {
        classement.insererEnQueue(donnerPiloteEnTete());
        pilotes.supprimer(donnerPiloteEnTete());
    }

    public String toString() {
        String toString = "";
        int index = 1;
        for (String pilote : pilotes) {
            if (ensembleDisqualifies.contains(pilote)) {
                toString += "";
            } else {
                toString += index + " - " + pilote + "\n";
                index++;
            }
        }
        return toString;
    }
}
