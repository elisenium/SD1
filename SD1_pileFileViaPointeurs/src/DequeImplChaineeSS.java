public class DequeImplChaineeSS<E> implements Deque<E> {

	private Noeud tete ;
	private Noeud queue ;
	private int taille ;
	
	public DequeImplChaineeSS(){
		tete=null;
		queue=null;
		taille=0;
	}

	public int taille() {
		return this.taille ;
	}

	public boolean estVide() {
		return (taille==0) ;
	}

	public void ajouterEnPremier(E element) {
		Noeud nouveauNoeud = new Noeud(element,null, tete);
		if (estVide()) {
			tete = nouveauNoeud;
			queue = nouveauNoeud;
		} else {
			nouveauNoeud.suivant = tete;
			tete.precedent = nouveauNoeud;
			tete = nouveauNoeud;
		}
		taille++;

	}

	public E retirerPremier() {
		if (estVide())
			throw new DequeVideException();
		Noeud noeudARetirer = tete;

		if (taille == 1) {
			tete = null;
			queue = null;
		} else {
			tete = noeudARetirer.suivant;
			tete.precedent = null;
		}

		taille--;
		return noeudARetirer.element;
	}

	public void ajouterEnDernier(E element) {
		Noeud nouveauNoeud = new Noeud(element,queue, null);

		if (estVide()) {
			tete = nouveauNoeud;
			queue = nouveauNoeud;
		} else {
			nouveauNoeud.precedent = queue;
			queue.suivant = nouveauNoeud;
			queue = nouveauNoeud;
		}
		taille++;
	}

	public E retirerDernier() throws DequeVideException {
		if (estVide())
			throw new DequeVideException();

		Noeud noeudARetirer = queue;

		if (taille == 1) {
			tete = null;
			queue = null;
		} else {
			queue = noeudARetirer.precedent;
			queue.suivant = null;
		}
		taille--;
		return noeudARetirer.element;
	}

	public E premier() throws DequeVideException {
		if (estVide())
			throw new DequeVideException();
		return tete.element;
	}

	public E dernier() throws DequeVideException {
		if (estVide())
			throw new DequeVideException();
		return queue.element;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	// tete 'a' 'b' 'c' queue : ['a','b','c']
	public DequeImplChaineeSS(Object[] table) {
		if(table == null)
			throw new IllegalArgumentException();
		taille = 0 ;
		tete = null ;
		queue = null ;
		if(table.length==0)
			return;
		for (int i = table.length-1; i>=0;i--) {
			this.ajouterTest((E) table[i]) ;
		}
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String toString(){
		String aRenvoyer="";
		Noeud baladeur=tete;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
			}
			aRenvoyer+=baladeur.element;
			if (baladeur.suivant !=null)
				aRenvoyer += " " ;
			baladeur=baladeur.suivant;
		}
		return aRenvoyer;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String parcoursInverse(){
		String aRenvoyer="";
		Noeud baladeur=queue;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
			}
			aRenvoyer+=baladeur.element;
			if (baladeur.precedent !=null)
				aRenvoyer += " " ;
			baladeur=baladeur.precedent;
		}
		return aRenvoyer;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	private void ajouterTest(E element) {
		if (estVide()) {
			tete = new Noeud(element,null,null) ;
			queue = tete ;
		} else {
			tete.precedent = new Noeud(element,null,tete) ;
			tete = tete.precedent;
		}
		taille = taille + 1 ;
	}

	
	// classe interne
	private class Noeud{
		private E element;
		private Noeud precedent;
		private Noeud suivant;

		private Noeud(E element, Noeud precedent, Noeud suivant){
			this.element = element;
			this.precedent = precedent ;
			this.suivant = suivant;
		}
	}

}
