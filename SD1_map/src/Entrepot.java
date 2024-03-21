import java.util.HashMap;
import java.util.LinkedList;

/**
 * classe Entrepôt.
 *
 * @author -------> METTEZ ICI VOS NOM ET PRENOM
 */
public class Entrepot {

	private int nombreHangarsLibres;
	private int nombreHangarsOccupees = 0;
	private HashMap<Integer, Societe> mapSocietes;
	private Hangar[] tableauHangars;
	private LinkedList<String> plaquesVoitures;

	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre de hangars
	 * @throws IllegalArgumentException si le nombre de hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if(nombreHangars<=0)
			throw new IllegalArgumentException();

		nombreHangarsLibres = nombreHangars;
		mapSocietes = new HashMap<>();
		tableauHangars = new Hangar[nombreHangars];
		plaquesVoitures = new LinkedList<>();
	}

	/**
	 * renvoie le nombre d'hangars libres
	 * @return le nombre d'hangars libres
	 */
	public int nombreHangarsLibres(){
		return nombreHangarsLibres;

	}

	/**
	 * renvoie le nombre d'hangars ocupées
	 * @return le nombre d'hangars ocupées
	 */
	public int nombreHangarsOccupees(){
		return nombreHangarsOccupees;

	}


	/**
	 * renvoie le nombre de societes presentes
	 * @return le nombre de societes presentes
	 */
	public int nombreSocietesPresentes(){
		return mapSocietes.size();

	}

	/**
	 * renvoie la societe dont le numero est passe en parametre
	 * @param numeroSociete le numero de la societe
	 * @return la societe recherchee ou null si aucune societe presente ne possede ce numero
	 */
	public Societe getSociete(int numeroSociete){
		return mapSocietes.get(numeroSociete);

	}

	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire :
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * @param numeroSociete le numero de la societe
	 * @param nomSociete le nom de la societe
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 */
	public int attribuerHangar(int numeroSociete, String nomSociete) {
		if (nombreHangarsLibres == 0) return -1;

		Societe societe = new Societe(numeroSociete, nomSociete);

		if (!mapSocietes.containsKey(numeroSociete)) {
			mapSocietes.put(numeroSociete, societe);
		}

		int hangarIndex = numeroSociete % tableauHangars.length;

		while (tableauHangars[hangarIndex] != null) {
			hangarIndex = (hangarIndex + 1) % tableauHangars.length;
		}

		tableauHangars[hangarIndex] = new Hangar(hangarIndex);
		mapSocietes.get(numeroSociete).ajouterHangar(hangarIndex);
		tableauHangars[hangarIndex].setSociete(societe);
		nombreHangarsLibres--;
		nombreHangarsOccupees++;
		return hangarIndex;

		// Pour une meilleure repartition des hangars occupes dans l'entrepot,
		// veuillez suivre les indications donnees dans l'enonce!

	}

	/**
	 * libère un hangar de la societe passée en paramètre
	 * Si la liberation a pu se faire :
	 * la societe est enregistrée comme présente (si pas encore présente)
	 * le hangar lui est retiré
	 * @param numeroHangar le numero de la societe
	 * @return le numero de societe du hangar retiré ou -1 s'il n'a pas été retiré
	 */
	public boolean libererHangar(int numeroHangar) {
		if (numeroHangar < 0 || numeroHangar > tableauHangars.length) {
			return false;
		}
		if (tableauHangars[numeroHangar].getSociete() == null)
			return false;

		int numeroSociete = tableauHangars[numeroHangar].getSociete().getNumeroSociete();
		mapSocietes.get(numeroSociete).retirerHangar(numeroHangar);
		tableauHangars[numeroHangar].setSociete(null);
		return true;
	}

	/**
	 * ajouter une plaque d'immatriculation de voiture.
	 * l'ajout de la plaque échoue si elle est déjà présente
	 * @param numPlaque le numéro de la plaque d'immatriculation
	 * @return true si l'ajout a bien été effectué, false sinon
	 */
	public boolean ajouterPlaque(String numPlaque) {
		if (plaquesVoitures.contains(numPlaque))
			return false;
		return plaquesVoitures.add(numPlaque);
	}


	/**
	 * vérifie si la voiture est autorisée au sein de l'entrepôt
	 * s'il n'y a aucun hangar occupé, il sera impossible de procéder à l'ajout
	 * @param numPlaque le numéro de la plaque d'immatriculation
	 * @return true si elle fait partie des plaques de voitures autorisées, false sinon.
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 */
	public boolean estAutorisee(String numPlaque) throws IllegalArgumentException {
		if (numPlaque == null || numPlaque.isEmpty())
			throw new IllegalArgumentException();
		if (nombreHangarsOccupees == 0)
			return false;

		return plaquesVoitures.contains(numPlaque);
	}


}
