import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Cirque {
	
	// suivez l'implementation imposee dans l'enonce

	private String[] tableReservations; //table des reservations
	private HashMap<String, HashSet<Integer>> mapEnfants; //cle = enfant, valeur = ensemble de ses places

	/**
	 * initialise un spectacle de cirque
	 * @param nombreTotalPlaces le nombre total de places disponibles
	 * @param tablePrenoms la table avec les prenoms des enfants du village
	 * @throws IllegalArgumentException si un des parametres ne permet pas d'initialiser le spectacle :
	 *         il faut au moins une place
	 *         la table des prenoms ne peut etre null ou vide
	 *         les prenoms ne peuvent etre null ou vides
	 *         il ne peut y avoir des doublons dans la table des prenoms
	 */
	public Cirque(int nombreTotalPlaces, String[] tablePrenoms){
		if (nombreTotalPlaces < 1)
			throw new IllegalArgumentException();
		if (tablePrenoms.length == 0)
			throw new IllegalArgumentException();

		tableReservations = new String[nombreTotalPlaces];
		mapEnfants = new HashMap<String, HashSet<Integer>>();

		for (int i = 0; i < nombreTotalPlaces; i++) {
			tableReservations[i] = null;
		}

		for (int i = 0; i < tablePrenoms.length; i++) {
			if (tablePrenoms[i] == null || tablePrenoms[i].equals("") || mapEnfants.containsKey(tablePrenoms[i]))
				throw new IllegalArgumentException();

			HashSet<Integer> ensemblePlaces = new HashSet<Integer>();
			mapEnfants.put(tablePrenoms[i], ensemblePlaces);
		}
		// pour verifier la presence de doublons dans la table des prenoms :
		// verifier si les ajouts dans le map se font bien
		// Dans un map, chaque cle est unique !

	}


	/**
	 * reserve une ou plusieurs places
	 * la reservation reussit si toutes les places demandees sont libres
	 * ATTENTION : PAS de reservation partielle (tout ou rien)
	 * @param prenom le prenom de l'enfant qui demande des places
	 * @param ensemblePlacesDemandees l'ensemble avec les numeros des places demandees
	 * @return true si la reservation a reussi, false sinon
	 * @throws IllegalArgumentException si le prenom est null ou vide
	 * 								 ou si l'enfant n'est pas un enfant du village
	 *                               ou si l'ensemble est null ou vide
	 *                               ou si l'enseble contient un numero de place inexistant
	 */
	public boolean reserver(String prenom, HashSet<Integer> ensemblePlacesDemandees){
		if (prenom == null || prenom.equals(""))
			throw new IllegalArgumentException();
		if (!estUnEnfantDuVillage(prenom))
			throw new IllegalArgumentException();
		if (ensemblePlacesDemandees.isEmpty() || ensemblePlacesDemandees == null || ensemblePlacesDemandees.equals(""))
			throw new IllegalArgumentException();

		HashSet<Integer> toutesTablesReservations = new HashSet<Integer>();
		for (int i = 0; i < tableReservations.length; i++) {
			toutesTablesReservations.add(i);
		}

		if (!toutesTablesReservations.containsAll(ensemblePlacesDemandees))
			return false;

		for (Integer placesDemandee : ensemblePlacesDemandees) {
			if (tableReservations[placesDemandee] != null)
				return false;
			tableReservations[placesDemandee] = prenom;
		}

		mapEnfants.put(prenom, ensemblePlacesDemandees);
		return true;

	}


	/**
	 * renvoie une table contenant les places reservees par un enfant
	 * cette table doit etre triee selon l'ordre croissant des numeros de place
	 * la table sera de dimension 0, si l'enfant n'a aucune reservation
	 * @param prenom le prenom de l'enfant
	 * @return la table avec les places reservees par un enfant
	 * @throws IllegalArgumentException si le prenom est null ou vide
	 * 								 ou si l'enfant n'est pas un enfant du village
	 */
	public int[] placesReservees (String prenom) {
		if (prenom == null || prenom.equals(""))
			throw new IllegalArgumentException();
		if (!estUnEnfantDuVillage(prenom))
			throw new IllegalArgumentException();

		HashSet<Integer> placesEnfant = mapEnfants.get(prenom);

		int[] placesReserveesEnfant;
		if (placesEnfant.isEmpty())
			placesReserveesEnfant = new int[0];
		else
			placesReserveesEnfant = new int[placesEnfant.size()];

		int j = 0;
		for (Integer i : placesEnfant) {
			placesReserveesEnfant[j] = i;
			j++;
		}

		Arrays.sort(placesReserveesEnfant);
		return placesReserveesEnfant;
		// Pour trier la table, utilisez la methode static sort() de la classe Arrays

	}

	public boolean estUnEnfantDuVillage(String enfant) {
		return mapEnfants.containsKey(enfant);
	}


	/**
	 * renvoie la table des reservations et le map
	 */
	public String toString(){
		// vous pouvez modifier cette methode comme vous voulez
		// MAIS cette methode ne sera pas evaluee
		// ne perdez pas de temps sur des affichages!
		if(tableReservations ==null)
			return "attention table des reservations est null";
		if(mapEnfants==null)
			return "attention mapEnfants est null";
		return "table des reservations :\n"+Arrays.toString(tableReservations)+"\nmapEnfants :\n"+mapEnfants.toString();
	}
	
}
