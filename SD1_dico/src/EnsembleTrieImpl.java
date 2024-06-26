import java.util.ArrayList;

public class EnsembleTrieImpl<E> implements EnsembleTrie<E> {

	private Noeud racine;
	private int taille;

	public EnsembleTrieImpl() {
		racine = null;
		taille = 0;
	}

	public boolean estVide() {
		return racine == null;
	}

	public int taille() {
		return taille;
	}

	public E min() {
		if (estVide())
			return null;
		Noeud baladeur = racine;
		while (baladeur.gauche != null)
			baladeur = baladeur.gauche;
		//le min se trouve dans le noeud (feuille) le plus a gauche
		return baladeur.element;
	}

	public boolean contient(E element) {
		if (estVide())
			return false;
		Noeud baladeur = racine;
		while (baladeur != null) {
			if (baladeur.element.equals(element))
				return true;
			if (((Comparable<E>)baladeur.element).compareTo(element) > 0) {
				if (baladeur.gauche == null) return false;
				baladeur = baladeur.gauche;
			}
			if (((Comparable<E>)baladeur.element).compareTo(element) < 0) {
				if (baladeur.droit == null) return false;
				baladeur = baladeur.droit;
			}
		}
		//Lisez bien les explications pour l'utilisation de la methode compareTo() dans l'enonce
		return true;

	}

	public boolean ajouter(E element) {
		if (estVide()) {
			racine = new Noeud(null, element, null);
			taille++;
			return true;
		}

		Noeud noeud = racine;
		while (noeud != null) {
			if (((Comparable<E>)noeud.element).compareTo(element) > 0) {
				if (noeud.gauche == null) {
					noeud.gauche = new Noeud(null, element, null);
					taille++;
					return true;
				}
				noeud = noeud.gauche;
			}

			if (((Comparable<E>)noeud.element).compareTo(element) < 0) {
				if (noeud.droit == null) {
					noeud.droit = new Noeud(null, element, null);
					taille++;
					return true;
				}
				noeud = noeud.droit;
			}
		}
		return false;

	}

	public E predecesseur(E element) {
		if (estVide()) return null;
		ArrayList<E> liste = new ArrayList<E>();
		remplirListe(racine, liste);
		int i = 0;
		while (i < liste.size()) {
			if (liste.get(i).equals(element)) {
				if (i == 0) return null;
				return liste.get(i-1);
			}
			i++;
		}
		//defi !
		return null;
	}

	private void remplirListe(Noeud noeud, ArrayList<E> liste) {
		if (noeud == null) return;
		remplirListe(noeud.gauche, liste);
		liste.add(noeud.element);
		remplirListe(noeud.droit, liste);
	}


	// A NE PAS MODIFIER!!!
	// VA SERVIR POUR LES TESTS!!!
	public String toString() {
		return "[ " + toString(racine) + " ]";
	}

	private String toString(Noeud n) {
		if (n == null)
			return "";
		if (n.gauche == null && n.droit == null)
			return "" + n.element;
		if (n.gauche == null)
			return " [ ] " + n.element + " [ " + toString(n.droit) + " ] ";
		if (n.droit == null)
			return " [ " + toString(n.gauche) + " ] " + n.element + " [ ] ";
		return " [ " + toString(n.gauche) + " ] " + n.element + " [ " + toString(n.droit) + " ] ";
	}

	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS
	// permet de construire l'ensembleTrie de l'enonce ABR1

	public EnsembleTrieImpl(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
		Noeud nG = new Noeud(null, e2, new Noeud(e5));
		Noeud nG1 = new Noeud(new Noeud(e7), e4, new Noeud(e6));
		Noeud nD = new Noeud(nG1, e3, null);
		racine = new Noeud(nG, e1, nD);
		taille = 7;
	}

	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS
	// permet de construire l'ensembleTrie de l'enonce ABR2

	public EnsembleTrieImpl(E e1, E e2, E e3, E e4, E e5) {
		Noeud nG1 = new Noeud(null, e4, new Noeud(e5));
		Noeud nD1 = new Noeud(nG1, e3, null);
		Noeud nD = new Noeud(null, e2, nD1);
		racine = new Noeud(null, e1, nD);
		taille = 5;
	}


	public class Noeud {

		private E element;
		private Noeud gauche;
		private Noeud droit;

		private Noeud(E element) {
			this.element = element;
			this.gauche = null;
			this.droit = null;
		}

		private Noeud(Noeud gauche, E element, Noeud droit) {
			this.element = element;
			this.gauche = gauche;
			this.droit = droit;
		}
	}


}
