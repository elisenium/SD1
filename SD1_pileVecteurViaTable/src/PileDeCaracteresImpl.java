// implementation d'une pile en utilisant un tableau de taille variable redimensionnable

/**
 * @author
 *
 */

public class PileDeCaracteresImpl implements PileDeCaracteres{

	private char[] table; 			// ne modifiez pas cet identifiant, va servir pour les tests
	private int nombreCaracteres; 	// taille logique
								  	//ne mofifiez pas cet identifiant, va servir pour les tests

	
	public PileDeCaracteresImpl(){
		table = new char[4];
		nombreCaracteres = 0;
	}

	
	public PileDeCaracteresImpl(int capacite){
		if (capacite <= 0)
			throw new IllegalArgumentException("la taille physique ne peut etre negative ou nulle");
		table = new char[capacite];
		nombreCaracteres = 0;
	}

	
	public int taille(){
		return nombreCaracteres;
	}

	
	public boolean estVide(){
		return nombreCaracteres == 0;
	}

	
	public void push(char c){
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
		if (nombreCaracteres == table.length)
			agrandirTable();
		table[nombreCaracteres] = c;
		nombreCaracteres++;
	}


	public char pop() throws PileVideException{
		if (estVide()) throw new PileVideException();
		char elem = table[nombreCaracteres-1];
		nombreCaracteres--;
		return elem;
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
	}


	public char sommet() throws PileVideException{
		if (estVide()) throw new PileVideException();
		return table[nombreCaracteres-1];
		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
	}

	public void agrandirTable() {
		char temp[] = new char[table.length * 2];
		for (int i = 0; i < nombreCaracteres; i++) {
			temp[i] = table[i];
		}
		table = temp;
	}

} 
