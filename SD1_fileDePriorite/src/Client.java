import java.util.Objects;

public class Client {
    private String nom;
    private Integer priorite;

    public Client(String nom) {
        this.nom = nom;
        priorite = 3;
    }

    public Client(String nom, Integer priorite) {
        this.nom = nom;
        this.priorite = priorite;
    }

    public String getNom() {
        return nom;
    }

    public Integer getPriorite() {
        return priorite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return priorite == client.priorite && Objects.equals(nom, client.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, priorite);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", priorite=" + priorite +
                '}';
    }
}
