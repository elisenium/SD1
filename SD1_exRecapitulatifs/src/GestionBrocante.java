import java.util.Iterator;
import java.util.Scanner;

public class GestionBrocante {

//	private static Scanner scanner = new Scanner(System.in);
	private static MonScanner scanner = new MonScanner("SD1_exRecapitulatifs/brocante.txt");

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
			System.out.println("3 -> consulter un exposant via son nom");
			System.out.println("4 -> lister tous les exposants");
			System.out.println("5 -> libérer un emplacement");
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
				case 3:
					consulterExposant();
					break;
				case 4:
					listerTousLesExposants();
					break;
				case 5:
					libererEmplacement();
					break;
			}

		} while (choix >= 1 && choix <= 5);

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
			System.out.println("3 -> consulter un exposant via son nom");
			System.out.println("4 -> lister tous les exposants");
			System.out.println("5 -> libérer un emplacement");
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
				case 3:
					consulterExposant();
					break;
				case 4:
					listerTousLesExposants();
					break;
				case 5:
					libererEmplacement();
					break;
			}

		} while (choix >= 1 && choix <= 5);

		System.out.println("Fin de la brocante!");
	}

	private static void reserverPhase1() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		if (!brocante.estUnRiverain(nom) || brocante.nombreEmplacementsRiverain(nom) >= 3) {
			System.out.println("Impossible de poursuivre car l'exposant entré n'est pas un riverain ou alors il a déjà 3 emplacements");
			return;
		}

		Exposant exposant = brocante.getExposant(nom);

		if (exposant.getEmail() == null) {
			System.out.print("Entrez l'email : ");
			String email = scanner.nextLine();
			exposant.setEmail(email);
		}

		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		scanner.nextLine();

		if (brocante.reserver(exposant, numero)) {
			System.out.println("L'exposant " + nom + " a bien réservé l'emplacement " + numero);
		} else {
			System.out.println("Réservation échouée");
		}
	}

	private static void reserverPhase2() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();
		String email;

		if (!brocante.estUnRiverain(nom) || brocante.getExposant(nom).getEmail() == null) {
			System.out.print("Entrez l'email : ");
			email = scanner.nextLine();
		} else {
			email = brocante.getExposant(nom).getEmail();
		}

		Exposant exposant = new Exposant(nom, email);
		brocante.changerPhase();
		int numero = brocante.attribuerAutomatiquementEmplacement(exposant);
		if (numero != -1) {
			System.out.println("L'emplacement " + numero + " a été attribué automatiquement à l'exposant portant le nom \"" + nom + "\"");
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

	private static void consulterExposant() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		Exposant exposant = brocante.getExposant(nom);
		if (brocante.estUnExposant(exposant))
			System.out.println(exposant);
		else
			System.out.println("Le nom entré ne fait pas parti des exposants");
	}

	private static void listerTousLesExposants() {
		Iterator<Exposant> iterator = brocante.tousLesExposants();
		while (iterator.hasNext())
			System.out.println(iterator.next());
	}

	private static void libererEmplacement() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();
		if (brocante.getExposant(nom) == null) {
			System.out.println("Ce n'est pas un exposant répertorié");
			return;
		}
		Exposant exposant = brocante.getExposant(nom);

		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		scanner.nextLine();

		if (brocante.libererEmplacement(exposant, numero)) {
			System.out.println("L'emplacement " + numero + " est désormais libre");
			return;
		}
		System.out.println("Échec de la libération de l'emplacement");
	}
}
