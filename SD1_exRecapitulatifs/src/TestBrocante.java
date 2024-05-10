import java.util.Arrays;
import java.util.Scanner;

public class TestBrocante {

	private static Scanner scanner = new Scanner(System.in);
	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * 
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a recu en realite
	 */
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
				System.out.println(messageErreur+"\nAttendu="+attendu+"\nRecu="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
			System.out.println(messageErreur+"\nAttendu=\n"+attendu+"\nRecu=\n"+recu);
			System.exit(0);
						
		}
	}
	
	public static void main(String[] args) {
		System.out.println("**************************************");
		System.out.println("Programme Test pour la classe Brocante");
		System.out.println("**************************************");
		int choix = 0;
		do {
			System.out.println("1 -> Tester uniquement la phase 1");
			System.out.println("2 -> Tester la phase 1 et 2");
			System.out.println("3 -> Tester les cas IllegalArgumentException");
			System.out.println("4 -> Tester les cas IllegalStateException");

			System.out.print("Entrez votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {
				case 1:
					testScenarioPhase1();
					break;
				case 2:
					testScenarioPhase1Et2();
					break;
				case 3:
					testIllegalArgumentException();
					break;
				case 4:
					testIllegalStateException();
					break;

				default:
					break;
			}
		} while (choix >= 1 && choix <= 4 );

	}

	private static Brocante testScenarioPhase1() {
		String[] tableRiverains = {"riv1", "riv2", "riv3"};
		Brocante brocante = new Brocante(8, tableRiverains);

		Exposant riv1 = new Exposant("riv1", "riv1@ipl.be");
		Exposant riv2 = new Exposant("riv2", "riv2@ipl.be");
		Exposant riv3 = new Exposant("riv3", "riv3@ipl.be");
		Exposant riv4 = new Exposant("riv4", "riv4@ipl.be");

		Exposant dem1 = new Exposant("dem1", "dem1@ipl.be");
		Exposant dem2 = new Exposant("dem2", "dem2@ipl.be");
		Exposant dem3 = new Exposant("dem3", "dem3@ipl.be");
		Exposant dem4 = new Exposant("dem4", "dem4@ipl.be");


		System.out.println("Test constructeur");
		assertEquals("Constructeur: map riverains ko", "{riv3=0, riv2=0, riv1=0}", brocante.donnerMapRiverains());
		assertEquals("Constructeur: phase ko", 1, brocante.getPhase());
		assertEquals("Constructeur: emplacements ko", "[{numero=0, exposant=null}, {numero=1, exposant=null}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant=null}, {numero=5, exposant=null}, {numero=6, exposant=null}, {numero=7, exposant=null}]",
				brocante.donnerTableEmplacements());

		if(!brocante.donnerPileEmplacementsLibres().equals("null")&&!brocante.donnerPileEmplacementsLibres().equals("[]")){
			System.out.println("test constructeur ko, la pile des emplacements libres doit etre initialisee en debut de phase 2");
			System.out.println("Si la pile est initialisee des la phase 1, elle doit etre mise a jour a chaque reservation d'un riverain");
			System.out.println("Suppression dans une pile --> O(N) --> feu rouge ! (pop() --> O(1))");
			System.exit(0);
		}


		System.out.println("En Phase 1");

		System.out.println("test 1 reservation riverain riv1  emplacement 0");
		assertEquals("test 1 ko on doit accepter la reservation", true, brocante.reserver(riv1, 0));
		assertEquals("test 1 emplacement ko", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant=null}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant=null}, {numero=5, exposant=null}, {numero=6, exposant=null}, {numero=7, exposant=null}]",
				brocante.donnerTableEmplacements());


		System.out.println("test 2 reservation riverain riv4  emplacement 1 (Attention riv4 n'est pas un riverain!)");
		assertEquals("test 2 ko on doit refuser la réservation", false, brocante.reserver(new Exposant("riv4", "riv4@ipl.be"), 1));
		assertEquals("test 2 emplacement ko", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant=null}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant=null}, {numero=5, exposant=null}, {numero=6, exposant=null}, {numero=7, exposant=null}]",
				brocante.donnerTableEmplacements());

		System.out.println("test 3 reservation riverain riv1  emplacement 1");
		assertEquals("test 3 ko on doit accepter la réservation", true, brocante.reserver(new Exposant("riv1", "riv1@ipl.be"), 1));
		assertEquals("test 3 emplacement ko", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant=null}, {numero=5, exposant=null}, {numero=6, exposant=null}, {numero=7, exposant=null}]",
				brocante.donnerTableEmplacements());

		System.out.println("test 4 reservation riverain riv1  emplacement 6");
		assertEquals("test 4 ko on doit accepter la réservation", true, brocante.reserver(new Exposant("riv1", "riv1@ipl.be"), 6));
		assertEquals("test 4 emplacement ko. Vérifiez l'emplacement 6", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant=null}, {numero=5, exposant=null}, {numero=6, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=7, exposant=null}]",
				brocante.donnerTableEmplacements());

		System.out.println("test 5 reservation riverain riv1  emplacement 2 (Attention riv1 a deja 3 emplacements!)");
		assertEquals("test 5 ko on doit refuser la réservation", false, brocante.reserver(new Exposant("riv1","riv1@ipl.be"), 2));
		assertEquals("test 5 emplacement ko.", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant=null}, {numero=5, exposant=null}, {numero=6, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=7, exposant=null}]",
				brocante.donnerTableEmplacements());
		System.out.println("test 6 reservation riverain riv3  emplacement 4");
		assertEquals("test 6 ko on doit accepter la réservation", true, brocante.reserver(new Exposant("riv3", "riv3@ipl.be"), 4));
		assertEquals("test 6 emplacement ko.", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant={nom='riv3', email='riv3@ipl.be'}}, {numero=5, exposant=null}, {numero=6, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=7, exposant=null}]",
				brocante.donnerTableEmplacements());

		System.out.println("test 7 reservation riverain riv3 emplacement 1 (Attention emplacement 1 est deja reserve!)");
		assertEquals("test 7 ko on doit refuser la réservation", false, brocante.reserver(new Exposant("riv3", "riv3@ipl.be"), 1));
		assertEquals("test 6 emplacement ko.", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant={nom='riv3', email='riv3@ipl.be'}}, {numero=5, exposant=null}, {numero=6, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=7, exposant=null}]",
				brocante.donnerTableEmplacements());


		System.out.println("Tous les tests du scenario ont reussi");
		System.out.println();

		return brocante;
	}


	private static void testScenarioPhase1Et2() {
		String[] tableRiverains = {"riv1", "riv2", "riv3"};


		Exposant riv1 = new Exposant("riv1", "riv1@ipl.be");
		Exposant riv2 = new Exposant("riv2", "riv2@ipl.be");
		Exposant riv3 = new Exposant("riv3", "riv3@ipl.be");
		Exposant riv4 = new Exposant("riv4", "riv4@ipl.be");

		Exposant dem1 = new Exposant("dem1", "dem1@ipl.be");
		Exposant dem2 = new Exposant("dem2", "dem2@ipl.be");
		Exposant dem3 = new Exposant("dem3", "dem3@ipl.be");
		Exposant dem4 = new Exposant("dem4", "dem4@ipl.be");



		Brocante brocante = testScenarioPhase1();

		System.out.println("En phase 2");

		brocante.changerPhase();
		assertEquals("Changement de phase ko: Pile des emplacements libres ko", "[{numero=7, exposant=null}, {numero=5, exposant=null}, {numero=3, exposant=null}, {numero=2, exposant=null}]", brocante.donnerPileEmplacementsLibres());

		System.out.println("test 8 attribution automatique emplacement demandeur dem1");
		assertEquals("test 8 ko emplacements renvoyé incorrect", 7, brocante.attribuerAutomatiquementEmplacement(dem1));
		assertEquals("test 8 map riverains ko", "{riv3=1, riv2=0, riv1=3}", brocante.donnerMapRiverains() );
		assertEquals("test 8 emplacement ko.", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant={nom='riv3', email='riv3@ipl.be'}}, {numero=5, exposant=null}, {numero=6, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=7, exposant={nom='dem1', email='dem1@ipl.be'}}]",
				brocante.donnerTableEmplacements());
		assertEquals("test 8 ko: Pile des emplacements libres ko", "[{numero=5, exposant=null}, {numero=3, exposant=null}, {numero=2, exposant=null}]", brocante.donnerPileEmplacementsLibres());


		System.out.println("test 9 attribution automatique emplacement demandeur dem2");
		assertEquals("test 9 ko emplacement renvoyé incorrect", 5, brocante.attribuerAutomatiquementEmplacement(dem2));
		assertEquals("test 9 table emplacement ko. Verfiez emplacement 5", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant=null}, {numero=3, exposant=null}, {numero=4, exposant={nom='riv3', email='riv3@ipl.be'}}, {numero=5, exposant={nom='dem2', email='dem2@ipl.be'}}, {numero=6, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=7, exposant={nom='dem1', email='dem1@ipl.be'}}]", brocante.donnerTableEmplacements());
		assertEquals("test 9 ko: Pile des emplacements libres ko", "[{numero=3, exposant=null}, {numero=2, exposant=null}]", brocante.donnerPileEmplacementsLibres());

		System.out.println("test 10 attribution automatique emplacement demandeur riv1 (plus de restriction en phase 2)");
		assertEquals("test 10 ko emplacement renvoyé incorrect", 3, brocante.attribuerAutomatiquementEmplacement(riv1));
		assertEquals("test 10 table emplacement ko. Verfiez emplacement 3", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant=null}, {numero=3, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=4, exposant={nom='riv3', email='riv3@ipl.be'}}, {numero=5, exposant={nom='dem2', email='dem2@ipl.be'}}, {numero=6, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=7, exposant={nom='dem1', email='dem1@ipl.be'}}]", brocante.donnerTableEmplacements());
		assertEquals("test 10 ko: Pile des emplacements libres ko", "[{numero=2, exposant=null}]", brocante.donnerPileEmplacementsLibres());

		System.out.println("test 11 attribution automatique emplacement demandeur dem3");
		assertEquals("test 11 ko emplacement renvoyé incorrect", 2, brocante.attribuerAutomatiquementEmplacement(dem3));
		assertEquals("test 11 table emplacement ko. Verfiez emplacement 2", "[{numero=0, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=1, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=2, exposant={nom='dem3', email='dem3@ipl.be'}}, {numero=3, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=4, exposant={nom='riv3', email='riv3@ipl.be'}}, {numero=5, exposant={nom='dem2', email='dem2@ipl.be'}}, {numero=6, exposant={nom='riv1', email='riv1@ipl.be'}}, {numero=7, exposant={nom='dem1', email='dem1@ipl.be'}}]", brocante.donnerTableEmplacements());
		assertEquals("test 11 ko: Pile des emplacements libres ko", "[]", brocante.donnerPileEmplacementsLibres());

		System.out.println("test 12 attribution automatique emplacement demandeur dem4 (attention plus de place)");
		assertEquals("test 12 ko emplacement renvoyé incorrect", -1, brocante.attribuerAutomatiquementEmplacement(dem4));


		System.out.println("Tous les tests du scenario ont reussi");
		System.out.println();
	}

	private static void testIllegalArgumentException() {

		Exposant riv3 = new Exposant("riv3", "riv3@ipl.be");
		String[] tableRiverains = {"riv1", "riv2", "riv3"};
		Brocante brocante = new Brocante(8, tableRiverains);
		brocante = new Brocante(8, tableRiverains);
		try {
			System.out.println();
			System.out.println("test 1 reservation riverain riv3  emplacement 8 (Attention pas d'emplacement 8)");
			brocante.reserver(riv3, 8);
			System.out.println("test 1 ko pas d'IllegalArgumentException");
		} catch (IllegalArgumentException e) {

		} catch (Exception e) {
			System.out.println("test 1 ko sortie de table!");
		}

		try {
			System.out.println("test 2 reservation riverain riv3  emplacement -1 (Attention pas d'emplacement -1)");
			brocante.reserver(riv3, -1);
			System.out.println("test 2 ko pas d'IllegalArgumentException");
			System.exit(0);
		} catch (IllegalArgumentException e) {

		} catch (Exception e) {
			System.out.println("test 2 ko sortie de table probable");
			System.exit(0);
		}

		System.out.println("Tous les tests ont reussi");
		System.out.println();
	}


	private static void testIllegalStateException() {

		String[] tableRiverains = {"riv1", "riv2", "riv3"};

		Exposant riv3 = new Exposant("riv3", "riv3@ipl.be");

		Exposant dem1 = new Exposant("dem1", "dem1@ipl.be");
		Brocante brocante = new Brocante(8, tableRiverains);
		System.out.println("En phase 1");

		try{
			System.out.println("test 1 attribution automatique emplacement demandeur dem1 (Attention on est en phase 1)");
			brocante.attribuerAutomatiquementEmplacement(dem1);
			System.out.println("test 1 ko pas d'IllegalStateException");
			System.exit(0);

		}catch(IllegalStateException e){

		}catch(Exception e){
			System.out.println("test 1 ko pas d'IllegalStateException");
			System.exit(0);
		}

		System.out.println("En phase 2");
		brocante.changerPhase();

		try{
			System.out.println("test 2 reservation riverain riv3  emplacement 2 (Attention on est en phase 2)");
			brocante.reserver(riv3, 2);
			System.out.println("test 2 ko il fallait une IllegaStateException");
			System.exit(0);

		}catch(IllegalStateException e){
			// Cas ok => pas d'output
		}catch(Exception e){
			System.out.println("test2 ko il fallait une IllegaStateException");
			System.exit(0);
		}


		System.out.println("Tous les tests ont reussi");
		System.out.println();
		
	}
}

