import java.util.Scanner;

public class GestionBrocante {

	//private static Scanner scanner = new Scanner(System.in);
	private static MonScanner scanner = new MonScanner("SD1_ex_recapitulatifs/src/brocante.txt");

	private static Brocante brocante;

	public static void main(String[] args) {

		System.out.println("**********************");
		System.out.println("gestion d'une brocante");
		System.out.println("**********************");
		System.out.println();

		// configuration de la brocante
		System.out.println("Configuration de la brocante");
		System.out.println("----------------------------");
		System.out.print("Entrez le nombre d'emplacements : ");
		int nombreEmplacements = scanner.nextInt();
		System.out.print("Entrez le nombre de riverains : ");
		int nombreRiverains = scanner.nextInt();
		scanner.nextLine();
		String[] tableRiverains = new String[nombreRiverains];
		for (int i = 0; i < tableRiverains.length; i++) {
			System.out.print("Entrez le nom du riverain " + (i + 1) + " : ");
			String nomRiv  = scanner.nextLine();
			tableRiverains[i] = nomRiv;
		}
		brocante = new Brocante(nombreEmplacements, tableRiverains);
		System.out.println();

		// Phase 1
		System.out.println("Phase 1");
		System.out.println("-------");
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					if (!brocante.emplacementLibre())
						System.out.println("Il n'y a plus d'emplacements libres");
					else
						reserverPhase1();
					break;
				case 2:
					afficherTout();
					break;
			}

		} while (choix >= 1 && choix <= 2);

		brocante.changerPhase();
		System.out.println();
		System.out.println();

		// Phase 2
		System.out.println("Phase 2");
		System.out.println("-------");
		choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					if (!brocante.emplacementLibre())
						System.out.println("Il n'y a plus d'emplacements libres");
					else
						reserverPhase2();
					break;
				case 2:
					afficherTout();
					break;
			}

		} while (choix >= 1 && choix <= 2);

		System.out.println("Fin de la brocante!");
	}

	private static void reserverPhase1() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		if (!brocante.estUnRiverain(nom) || brocante.nombreEmplacementsRiverain(nom) >= 3) {
			System.out.println("Impossible de poursuivre car l'exposant entré n'est pas un riverain ou alors il a déjà 3 emplacements");
			return;
		}

		System.out.print("Entrez l'email : ");
		String email = scanner.nextLine();

		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		scanner.nextLine();

		Exposant exposant = new Exposant(nom, email);
		if (brocante.reserver(exposant, numero)) {
			System.out.println("L'exposant " + nom + " a bien réservé l'emplacement " + numero);
		} else {
			System.out.println("Réservation échouée");
		}
	}

	private static void reserverPhase2() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		if (!brocante.estUnRiverain(nom)) {
			System.out.println("Impossible de poursuivre");
			return;
		}

		System.out.print("Entrez l'email : ");
		String email = scanner.nextLine();

		brocante.changerPhase();
		Exposant exposant = new Exposant(nom, email);
		int numero = brocante.attribuerAutomatiquementEmplacement(exposant);
		if (numero != -1) {
			System.out.println("L'exposant " + nom + " a bien réservé l'emplacement " + numero);
		} else {
			System.out.println("Réservation échouée");
		}
	}

	private static void afficherTout() {
		System.out.println("Emplacements :") ;
		System.out.println("--------------") ;
		System.out.println() ;
		System.out.println(brocante) ;
		//System.out.println() ;
	}
}
