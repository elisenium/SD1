import java.util.Arrays;
import java.util.HashMap;


public class Parking {
	private HashMap<String, Proprietaire> mapVoitures;

	/**
	 * construit un map vide
	 */
	public Parking(){
		mapVoitures = new HashMap<>();
	
	}

	/**
	 * ajoute une voiture a condition que celle-ci ne soit pas deja presente
	 * @param plaque la plaque de la voiture a ajouter
	 * @param proprietaire le proprietaire de la voiture a ajouter
	 * @return true si la voiture n'etait pas encore presente, false sinon
	 */
	public boolean ajouterVoiture(String plaque, Proprietaire proprietaire){
		if (mapVoitures.containsKey(plaque))
			return false;
		mapVoitures.put(plaque, proprietaire);
		mapVoitures.
		return true;

	}


	/**
	 * verifie si la voiture est autorisee car presente dans le map
	 * @param plaque la plaque de la voiture a verifier
	 * @return true si la voiture est presente dans le map, false sinon
	 */
	public boolean voitureAutorisee(String plaque){
		return mapVoitures.containsKey(plaque);

	}
	
	/**
	 * renvoie le proprietaire de la voiture
	 * @param plaque la plaque de la voiture recherchee
	 * @return le proprietaire ou null si la plaque n'est pas dans le map
	 */
	public Proprietaire donnerProprietaire(String plaque){
		return mapVoitures.get(plaque);

	}

	/**
	 * retire une voiture a condition que celle-ci soit presente
	 * @param plaque la plaque de la voiture a ajouter
	 * @return true si la voiture etait presente, false sinon
	 */
	public boolean retirerVoiture(String plaque){
		if (!mapVoitures.containsKey(plaque))
			return false;
		mapVoitures.remove(plaque);
		return true;

	}


	/**
	 * remplit une table avec les plaques des voitures autorisees
	 * cette table doit etre triee par ordre alphabetique
	 * @return une table avec les plaques de voitures autorisees
	 */
	public String[] tableTrieePlaques(){

		// pistes :
		// utilisez la methode keySet() !
		// pour trier une table, utilisez la methode static sort de la classe Arrays
		// cette methode trie la table passee en parametre !

		String[] tableTrieeString = mapVoitures.keySet().toArray(new String[0]);

		Arrays.sort(tableTrieeString);
		return tableTrieeString;

	}
	
	public String toString(){
		return mapVoitures.toString();
	}
}
