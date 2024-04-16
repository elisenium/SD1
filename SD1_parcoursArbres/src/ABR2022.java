import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ABR2022 implements Iterable<Integer> {

	private NoeudEntier racine;

	public ABR2022() {
		racine = null;
	}

	public boolean estVide() {
		return racine == null;
	}


	public int nombreNegatifsVI(){
		int compteur = 0;
		for (int entier : this) {
			if (entier < 0)
				compteur++;
			else {
				break;
			}
		}
		//CONTRAINTE : cette methode doit etre iterative
		return compteur;
	}

	public int nombreNegatifsVR(){
		//CONTRAINTE : cette methode doit etre recursive
		return nombreNegatifsVR(racine);
	}

	private int nombreNegatifsVR(NoeudEntier noeud){
		if (noeud == null)
			return 0;
		if (noeud.entier < 0) {
			return 1 + nombreNegatifsVR(noeud.droit) + nombreNegatifsVR(noeud.gauche);
		}
		return nombreNegatifsVR(noeud.gauche);
	}

	public boolean tousPositifsVI() {
		for (int entier : this) {
			if (entier < 0)
				return false;
			else {
				//ou else break;
				return true;
			}
		}
		//CONTRAINTE : cette methode doit etre iterative
		//N'utilisez pas une methode nombrePositifs()!
		return true;
	}

	public boolean tousPositifsVR() {
		//CONTRAINTE : cette methode doit etre recursive
		//N'utilisez pas une methode nombrePositifs()!
		return tousPositifsVR(racine);
	}

	private boolean tousPositifsVR(NoeudEntier noeud) {
		if (noeud == null)
			return true;
		if (noeud.entier < 0)
			return false;

		return tousPositifsVR(noeud.gauche);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterateur();
	}

	private class Iterateur implements Iterator<Integer> {
		private ArrayDeque<Integer> fileDEntiers;

		public Iterateur () {
			fileDEntiers = new ArrayDeque<Integer>();
			remplirFile(racine);
		}

		private void remplirFile (NoeudEntier n) {
			if (n == null) return;
			remplirFile(n.gauche);
			fileDEntiers.addLast(n.entier);
			remplirFile(n.droit);
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

	public ABR2022 (int entier) {
		this.racine = new NoeudEntier(entier) ;
	}

	// pour les tests - attention ne verifie pas si tri ABR respecte!
	public ABR2022 (ABR2022 filsGauche, int entier, ABR2022 filsDroit) {
		this.racine = new NoeudEntier(filsGauche.racine, entier, filsDroit.racine);

	}
}
