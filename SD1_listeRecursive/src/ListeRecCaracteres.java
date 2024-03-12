import java.util.NoSuchElementException;

import java.util.ArrayList;

public class ListeRecCaracteres {

	private NoeudCaractere tete;
	// N'ajoutez pas d'autres attributs
	
	public ListeRecCaracteres() {
		this.tete=null;
	}
	

	
	/**
	 * verifie la presence du caractere passe en parametre dans la liste
	 * @param caractereRecherche
	 * @return true si le caractere est present dans la liste, false sinon
	 */
	public boolean contient(char caractereRecherche){
		return contient(tete, caractereRecherche);
		
	}

	private boolean contient(NoeudCaractere noeudCaractere, char caractereRecherche) {
		if (tete == null)
			return false;
		while (noeudCaractere != null) {
			if (noeudCaractere.caractere == caractereRecherche)
				return true;
			noeudCaractere = noeudCaractere.suivant;
		}
		return false;
	}

	
	/**
	 * calcule le nombre de fois qu'apparait le caractere passe en parametre dans la liste
	 * @param caractereRecherche
	 * @return le nombre d'occurrences du caractere
	 */
	public int nombreOccurrences(char caractereRecherche){
		return nombreOccurences(tete, caractereRecherche);
	
	}

	private int nombreOccurences(NoeudCaractere noeudCaractere, char caractereRecherche) {
		if (tete == null)
			return 0;
		int nombreOccurences = 0;
		while (noeudCaractere != null) {
			if (noeudCaractere.caractere == caractereRecherche)
				nombreOccurences++;
			noeudCaractere = noeudCaractere.suivant;
		}
		return nombreOccurences;
	}

	
	/**
	 * remplace la 1ere occurrences du caractere a remplacer par un nouveau caractere
	 * @param caractereARemplacer le caractere a remplacer
	 * @param nouveauCaractere le nouveau caractere
	 */
	public void remplacer(char caractereARemplacer, char nouveauCaractere){
		remplacer(tete, caractereARemplacer, nouveauCaractere);
	}

	private void remplacer(NoeudCaractere noeudCaractere, char caractereARemplacer, char nouveauCaractere){
		while (noeudCaractere != null) {
			if (noeudCaractere.caractere == caractereARemplacer) {
				noeudCaractere.caractere = nouveauCaractere;
				break;
			}
			noeudCaractere = noeudCaractere.suivant;
		}
	}

	
	/**
	 * remplace toutes les occurrences du caractere a remplacer par un nouveau caractere
	 * @param caractereARemplacer le caractere a remplacer
	 * @param nouveauCaractere le nouveau caractere
	 */
	public void remplacerTout(char caractereARemplacer, char nouveauCaractere){
		remplacerTout(tete, caractereARemplacer, nouveauCaractere);
	}

	private void remplacerTout(NoeudCaractere noeudCaractere, char caractereARemplacer, char nouveauCaractere){

		while (noeudCaractere != null) {
			if (noeudCaractere.caractere == caractereARemplacer) {
				noeudCaractere.caractere = nouveauCaractere;
			}
			noeudCaractere = noeudCaractere.suivant;
		}
	}
	
	
	/**
	 * recherche le plus grand caractere de la liste ('a'<'b'< ...)
	 * @return le plus grand caractere 
	 * @throws NoSuchElementException si la liste est vide
	 */
	public char max() {
		
		return max(tete);
		
		// le plus petit caractere : ' '

		// c'est cette methode qui leve une exception en cas de liste vide!
		// suggestion : la methode recursive (private!) pourrait renvoyer ' ' si le noeud passe en parametre est null
	}

	private char max(NoeudCaractere noeudCaractere) {

		if (noeudCaractere == null)
			throw new NoSuchElementException();
		char max = ' ';
		while (noeudCaractere != null) {
			if (noeudCaractere.caractere > max) {
				max = noeudCaractere.caractere;
			}
			noeudCaractere = noeudCaractere.suivant;
		}

		return max;
	}
	
	
	
	
	/**
	 * cree une arrayList contenant les caracteres de la liste 
	 * L'ordre doit etre respecte (une liste est une structure lineaire)
	 * @return l'arrayList cree
	 */
	public ArrayList<Character> enArrayList(){
		
		return enArrayList(tete);

		// cette methode s'occupe de creer (1x!) l'arraylist et la passe en parametre de la methode recursive
		// l'arrayList est un objet --> passage de parametre par adresse 
		// La methode recursive est une methode void!
		
	}

	private ArrayList<Character> enArrayList(NoeudCaractere noeudCaractere){
		ArrayList<Character> characterArrayList = new ArrayList<>();

		while (noeudCaractere != null) {
			characterArrayList.add(noeudCaractere.caractere);
			noeudCaractere = noeudCaractere.suivant;
		}

		return characterArrayList;

		// cette methode s'occupe de creer (1x!) l'arraylist et la passe en parametre de la methode recursive
		// l'arrayList est un objet --> passage de parametre par adresse
		// La methode recursive est une methode void!

	}

	/**
	 * verifie si les 2 listes contiennent les memes caracteres et ceci dans le meme ordre
	 * Une liste est une structure LINEAIRE!
	 * @param l la liste a comparer a la liste courante
	 * @return true si les 2 listes sont les memes, false sinon
	 */
	public boolean estEgalA(ListeRecCaracteres l){
		
		return estEgalA(this.tete, l.tete);

		// N'utilisez pas la methode toString()!


	}

	private boolean estEgalA(NoeudCaractere noeud1, NoeudCaractere noeud2){
		if (noeud1 == null && noeud2 == null) return true;
		if (noeud1 == null || noeud2 == null) return false;
		if (noeud1.caractere != noeud2.caractere) return false;

		return estEgalA(noeud1.suivant, noeud2.suivant);

	}
	
	

	

	/**
	 * supprime une fois le caractere passe en parametre
	 * si le caractere se trouve plusieurs fois, c est sa premiere occurrence qui sera supprimee
	 * @param caractereASupprimer
	 * @return true si le caractere etait bien present dans la liste, false sinon
	 */
	
	// VERSION 1
	
	public boolean supprimerPremiereOccurrence(char caractereASupprimer){
		if(!contient(caractereASupprimer))
			return false;
		tete = supprimerPremiereOccurrence(tete,caractereASupprimer);
		return true;		
	}
	
	private NoeudCaractere supprimerPremiereOccurrence(NoeudCaractere noeud, char caractereASupprimer) {
		if(noeud==null)
			return null;
		if(noeud.caractere==caractereASupprimer)
			return noeud.suivant;
		noeud.suivant=supprimerPremiereOccurrence(noeud.suivant, caractereASupprimer);
		return noeud;
	}
	
	// VERSION 2
	
//	public boolean supprimerPremiereOccurrence(char caractereASupprimer) {
//		if(tete == null)
//			return false;
//		if(tete.caractere==caractereASupprimer){
//			tete = tete.suivant;
//			return true;
//		}
//			
//		return this.supprimerPremiereOccurrence(tete, caractereASupprimer) ;
//	}
//	
//	private boolean supprimerPremiereOccurrence(NoeudCaractere noeud, char caractereASupprimer) {
//		if (noeud.suivant == null)
//			return false ;
//		if (noeud.suivant.caractere == caractereASupprimer) {
//			noeud.suivant = noeud.suivant.suivant ;
//			return true ;
//		}
//		return supprimerPremiereOccurrence(noeud.suivant, caractereASupprimer);
//	}
	
	
	

	/**
	 * cree une liste qui est une copie de la liste courante (meme ordre)
	 * @return une copie de la liste courante
	 */
	public ListeRecCaracteres clone(){

		ListeRecCaracteres listeClonee = new ListeRecCaracteres();
		listeClonee.tete = clone(tete);
		return listeClonee;

		// DEFI!
		
		// La methode recursive renvoie un noeud!
		// Lisez attentivement la version 1 de la solution de supprimerPremiereOccurrence
		
	}

	private NoeudCaractere clone(NoeudCaractere noeud){
		if (noeud == null)
			return null;
		NoeudCaractere Noeudclone = new NoeudCaractere(noeud.caractere, null);
		Noeudclone.suivant = clone(noeud.suivant);
		return Noeudclone;
	}

	
	/**
	 * supprime le caractere autant de fois qu'il est present dans la liste
	 * @param caractereASupprimer
	 * @return le nombre de suppressions effectuees
	 */
	public int supprimerToutesLesOccurrences(char caractereASupprimer) {
		int nombreSuppressions = 0;

		while (supprimerPremiereOccurrence(caractereASupprimer)){
			nombreSuppressions++;
		}

		return nombreSuppressions;

		// DEFI!

		// Lisez attentivement la version 1 ou la version 2 de la solution recursive de supprimerPremiereOccurrence
	}


	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public ListeRecCaracteres(char[] tableCaracteres) {
		if(tableCaracteres==null)
			throw new IllegalArgumentException();
		for (int i = tableCaracteres.length-1; i>=0; i--) {
			this.tete=new NoeudCaractere(tableCaracteres[i],tete);
		}
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String toString(){
		String aRenvoyer = "";
		NoeudCaractere baladeur = tete;
		while(baladeur != null) {
			aRenvoyer += " " + baladeur.caractere;
			baladeur = baladeur.suivant;
		}
		return aRenvoyer;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS !!!
	public void remplacerToutParX(){
		NoeudCaractere baladeur = tete;
		while(baladeur != null) {
			baladeur.caractere = 'x';
			baladeur = baladeur.suivant;
		}
	}

	private class NoeudCaractere{
		private char caractere;
		private NoeudCaractere suivant;

		public NoeudCaractere(char caractere, NoeudCaractere suivant){
			this.caractere = caractere;
			this.suivant = suivant;
		}

	}
}
