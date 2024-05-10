import java.util.Objects;

public class Emplacement {

    private int numero;
    private Exposant exposant;

    public Emplacement(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public Exposant getExposant() {
        return exposant;
    }

    public void setExposant(Exposant exposant) {
        this.exposant = exposant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emplacement that = (Emplacement) o;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "{" +
                "numero=" + numero +
                ", exposant=" + exposant +
                '}';
    }

}
