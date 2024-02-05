/**
 * @author 
 *
 */

public class Parenthesage{
   
	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * 
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a re�u en realite
	 */
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
				System.out.println(messageErreur+". Attendu="+attendu+" re�u="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
			System.out.println(messageErreur+". Attendu="+attendu+" re�u="+recu);
			System.exit(0);			
		}
	}
	
	public static void main(String[] args) {

		assertEquals("test ko : (a+b)", true, verificationParenthesage("(a+b)")); 
		assertEquals("test ko : (a+b]", false, verificationParenthesage("(a+b]")); 
		assertEquals("test ko : (a+b", false, verificationParenthesage("(a+b"));
		assertEquals("test ko :  a+b)", false, verificationParenthesage("a+b)"));
		assertEquals("test ko : if(a==b||a==c){return (tab[i]-1)*2}", true, verificationParenthesage("if(a==b||a==c){return (tab[i]-1)*2}"));
		assertEquals("test ko : if(a==b||a==c{return (tab[i]-1)*2}", false,verificationParenthesage("if(a==b||a==c{return tab[i]-1}")); 
		assertEquals("test ko : if a==b||a==c){return (tab[i]-1)*2}", false, verificationParenthesage("if a==b||a==c){return tab[i]-1}"));
		assertEquals("test ko : if((a==b||a==c){return (tab[i]-1)*2}", false,verificationParenthesage("if((a==b||a==c){return tab[i]-1}"));
		assertEquals("test ko : if(a==b||a==c){return (tab[i)-1)*2}", false,verificationParenthesage("if(a==b||a==c){return tab[i)-1}"));
		assertEquals("test ko : if(a==b||a==c)[return (tab[i]-1)*2}", false,verificationParenthesage("if(a==b||a==c)[return tab[i]-1}"));
		assertEquals("test ko : if(a==b||a==c){return (tab[i]-1)*2}}", false,verificationParenthesage("if(a==b||a==c){return tab[i]-1}}"));	
		assertEquals("test ko : if((a==b||a==c){return (tab[i]-1)*2}", false,verificationParenthesage("if((a==b||a==c){return tab[i]-1}"));
		System.out.println("Tous les tests ont reussi!");
	}
	
	
	
	public static boolean verificationParenthesage(String texte){
		// Cette methode necessite une pile!
		// Reflechissez bien a son contenu!
		Pile<PileDeCaracteres> pileParenthese = new PileImpl<PileDeCaracteres>();

		for (int i = 0; i < texte.length(); i++) {
			char caractere = texte.charAt(i);

			if (caractere == '(' || caractere == '[' || caractere == '{') {
				PileDeCaracteres pileInterne = new PileDeCaracteresImpl();
				pileParenthese.push(pileInterne);
				pileInterne.push(caractere);
			} else if (caractere == ')' || caractere == ']' || caractere == '}') {
				if (pileParenthese.estVide()) {
					return false; // Parenthèse fermante sans parenthèse ouvrante correspondante
				}

				PileDeCaracteres pileInterne = pileParenthese.pop();
				if (pileInterne.estVide() ||
						(caractere == ')' && pileInterne.sommet() != '(') ||
						(caractere == ']' && pileInterne.sommet() != '[') ||
						(caractere == '}' && pileInterne.sommet() != '{')) {
					return false; // Parenthèses de types différents ou pile interne vide
				}
			}
		}

		return pileParenthese.estVide();
	}	 
} 
