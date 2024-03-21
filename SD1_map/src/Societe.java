import java.util.HashSet;

public class Societe {

	private int numeroSociete;
	private String nom;
	private HashSet<Integer> lesHangars;
	private HashSet<String> voituresSociete;

	/**
	 * construit une societe sans hangar
	 * @param numeroSociete son numero
	 * @param nom son nom
	 * @throws IllegalArgumentException si le numero de la societe est negatif ou nul 
	 *                                  si le nom est null ou ""
	 */
	public Societe(int numeroSociete, String nom) {
		if(numeroSociete<=0)
			throw new IllegalArgumentException();
		if(nom == null || nom.equals(""))
			throw new IllegalArgumentException();
		this.numeroSociete = numeroSociete;
		this.nom = nom;
		lesHangars = new HashSet<>();
		voituresSociete = new HashSet<>();

	}


	/**
	 * ajoute un hangar si celui-ci n'y est pas encore
	 * @param numeroHangar le numero du hangar ajoute
	 * @return
	 */
	public boolean ajouterHangar(int numeroHangar){
		if (lesHangars.contains(numeroHangar))
			return false;
		return lesHangars.add(numeroHangar);
	}

	/**
	 * retire un hangar si celui-ci n'y est pas encore
	 * @param numeroHangar le numero du hangar ajoute
	 * @return
	 */
	public boolean retirerHangar(int numeroHangar){
		if (!lesHangars.contains(numeroHangar))
			return false;
		return lesHangars.remove(numeroHangar);
	}

	/**
	 * ajoute une voiture de société si celle-ci n'y est pas encore
	 * @param numPlaque le numero de la plaque d'immatriculation ajoute
	 * @return
	 */
	public boolean ajouterVoiture(String numPlaque){
		if (voituresSociete.contains(numPlaque))
			return false;
		return voituresSociete.add(numPlaque);
	}

	/**
	 * renvoie un String avec les hangars occupes par la societe
	 * @return
	 */
	public String lesHangars() {
		return lesHangars.toString();
		// A NE PAS MODIFIER --> VA SERVIR POUR LES TESTS
	}

	public HashSet<String> lesVoitures() {
		return voituresSociete;
	}

	public int getNumeroSociete() {
		return numeroSociete;
	}
	
	public String getNom() {
		return nom;
	}
	

	@Override
	public String toString() {
		return "Societe [numeroSociete=" + numeroSociete + ", nom=" + nom
				+ ", lesHangars=" + lesHangars + ", voituresSocietes=" + voituresSociete + "]";
	}


	@Override
	public int hashCode() {
		return numeroSociete;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Societe other = (Societe) obj;
		if (numeroSociete != other.numeroSociete)
			return false;
		return true;
	}
	
	
	
	
	

	
	
	
	
	
}
