import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ABRDEntiers implements Iterable<Integer> {

	private NoeudEntier racine;

	public ABRDEntiers() {
		racine = null;
	}

	public boolean estVide() {
		return racine == null;
	}


	public int nombrePositifsVI(){
		//CONTRAINTE : cette methode doit etre iterative
		//Utilisez l'iterateur
		int nombrePositifs = 0;
		for (int entier : this) {
			if (entier > 0)
				nombrePositifs++;
			else break;
			// OU
			// return nombrePositifs
		}
		return nombrePositifs;
	}

	public int nombrePositifsVR(){
		//CONTRAINTE : cette methode doit etre recursive
		return nombrePositifsVR(racine);
	}

	private int nombrePositifsVR(NoeudEntier noeudEntier) {
		if (noeudEntier == null)
			return 0;
		if (noeudEntier.entier > 0)
			return 1 + nombrePositifsVR(noeudEntier.gauche) + nombrePositifsVR(noeudEntier.droit);
		return nombrePositifsVR(noeudEntier.gauche) + nombrePositifsVR(noeudEntier.droit);
	}

	public boolean auMoins1PositifVI() {
		//CONTRAINTE : cette methode doit etre iterative
		//Utilisez l'iterateur
		//N'utilisez pas une methode nombrePositifs()!
		for (int entier : this) {
			if (entier > 0)
				return true;
			// Si la file commence par des entiers négatifs, le reste est négatif
			// Si on a parcouru tous les entiers positifs et que l'entier est négatif,
				// les entiers suivant sont d'office négatif donc on sort de la boucle
			else break;
			// OU
			// return false;
		}
		return false;
	}

	public boolean auMoins1PositifVR() {
		//CONTRAINTE : cette methode doit etre recursive
		//N'utilisez pas une methode nombrePositifs()!
		return auMoins1PositifVR(racine);
	}

	private boolean auMoins1PositifVR(NoeudEntier noeudEntier) {
		if (noeudEntier == null)
			return false;
		if (noeudEntier.entier > 0)
			return true;
		return auMoins1PositifVR(noeudEntier.droit);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterateur();
	}

	private class Iterateur implements Iterator<Integer> {
		private ArrayDeque<Integer> fileDEntiers;

		public Iterateur () {
			fileDEntiers = new ArrayDeque<Integer>();
			if(!estVide())
				remplirFile(racine);
		}

		private void remplirFile (NoeudEntier n) {
			if (n == null)
				return;
			remplirFile(n.droit);
			fileDEntiers.addLast(n.entier);
			remplirFile(n.gauche);


			// Mettez les instructions suivantes dans le bon ordre
			// pour permettre d'iterer les entiers par ordre decroissant

			/*
			remplirFile(n.gauche);
			remplirFile(n.droit);
			fileDEntiers.addLast(n.entier);
			*/
		}

		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		public Integer next () {
			if(!hasNext())
				throw new NoSuchElementException();
			return fileDEntiers.removeFirst();
		}

	}

	public class NoeudEntier {

		private int entier;
		private NoeudEntier gauche;
		private NoeudEntier droit;

		private NoeudEntier (int entier) {
			this.entier = entier;
			this.gauche = null;
			this.droit = null;
		}

		private NoeudEntier (NoeudEntier g, int entier, NoeudEntier d) {
			this.entier = entier;
			this.gauche = g;
			this.droit = d;
		}
	}

	// pour les tests

	public ABRDEntiers (int entier) {
		this.racine = new NoeudEntier(entier) ;
	}

	// pour les tests - attention ne verifie pas si tri ABR respecte!
	public ABRDEntiers (ABRDEntiers filsGauche, int entier, ABRDEntiers filsDroit) {
		this.racine = new NoeudEntier(filsGauche.racine, entier, filsDroit.racine);

	}
}
