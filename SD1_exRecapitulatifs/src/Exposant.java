
import java.util.Objects;

public class Exposant {
    private String nom;
    private String email;

    public Exposant(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exposant exposant = (Exposant) o;
        return nom.equals(exposant.nom) && email.equals(exposant.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, email);
    }


    @Override
    public String toString() {
        return "{" +
                "nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

