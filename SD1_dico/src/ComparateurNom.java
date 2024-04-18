import java.util.Comparator;

public class ComparateurNom implements Comparator<Etudiant> {

    public int compare(Etudiant etudiant1, Etudiant etudiant2) {
        return etudiant1.getNom().compareTo(etudiant2.getNom());
    }
}
