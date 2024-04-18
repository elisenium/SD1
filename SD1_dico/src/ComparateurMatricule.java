import java.util.Comparator;

public class ComparateurMatricule implements Comparator<Etudiant> {

    public int compare(Etudiant etudiant1, Etudiant etudiant2) {
        return etudiant1.compareTo(etudiant2);
    }
}
