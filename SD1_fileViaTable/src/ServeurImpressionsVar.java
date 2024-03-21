import java.util.ArrayDeque;

public class ServeurImpressionsVar{
	// TODO
	private ArrayDeque<DemandeImpression>[] tableFilesDAttente;

	/**
	 * construit une table avec 10 FileAttenteImpression
	 */
	public ServeurImpressionsVar() {
		//TODO
		tableFilesDAttente = new ArrayDeque[10];
		for (int i = 0; i < tableFilesDAttente.length; i++) {
			tableFilesDAttente[i] = new ArrayDeque<DemandeImpression>();
		}

	}

	/**
	 * verifie si toutes les files sont vides
	 * @return true si toutes les files sont vides, false sinon
	 */
	public boolean estVide(){
		//TODO
		return tableFilesDAttente.length == 0;
	}

	/**
	 * ajoute la demande d impression en fin de la file de priorite correspondante
	 * @param demande la demande a ajouter
	 * @throws IllegalArgumentException si la demande est null
	 */
	public void ajouter(DemandeImpression demande){
		//TODO
		if (demande == null || demande.equals("")) throw new IllegalArgumentException();

		tableFilesDAttente[demande.getPriorite()].add(demande);
	}

	/**
	 * retire l'impression en tete de file de priorite la plus haute qui est non vide
	 * @return l'impression qui a ete retiree
	 * @throws FileVideException si aucune demande d impression dans la file
	 */
	public DemandeImpression retirer()throws FileVideException{
		//TODO
		return null;
	}

}