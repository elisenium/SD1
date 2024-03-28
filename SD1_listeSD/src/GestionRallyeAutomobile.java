import java.util.Scanner;

public class GestionRallyeAutomobile {
    private static MonScanner scanner = new MonScanner("SD1_listeSD/src/pilotes.txt");

    //private static Scanner scanner = new Scanner(System.in);
    private static RallyeAutomobile rallyeAutomobile;

    public static void main(String[] args) {
        System.out.println("**************************************");
        System.out.println("         Rallye Automobile      ");
        System.out.println("**************************************");
        System.out.println();
        System.out.print("Entrez le nombre de pilotes : ");
        int nombrePilotes = scanner.nextInt();

        String[] lesPilotesAuto = new String[nombrePilotes];
        for (int i = 1; i <= nombrePilotes; i++) {
            System.out.print("Entrez le nom du pilote " + i + " : ");
            String nomPilote = scanner.next();
            System.out.println();
            lesPilotesAuto[i - 1] = nomPilote;
        }

        rallyeAutomobile = new RallyeAutomobile(lesPilotesAuto);

        int choix = 0;
        do {
            System.out.println();
            System.out.println("1 -> Afficher toute la course");
            System.out.println("2 -> Afficher le pilote en tête");
            System.out.println("3 -> Enregistrer un dépassement");
            System.out.println("4 -> Retirer un pilote de la course");
            System.out.println("5 -> Donner la position d’un pilote (encore dans la course)");
            System.out.println("6 -> Faire franchir la ligne d’arrivée au pilote de tête");
            System.out.println();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    afficherCourse();
                    break;
                case 2:
                    afficherPiloteEnTete();
                    break;
                case 3:
                    enregistrerDepassement();
                    break;
                case 4:
                    retirerPilote();
                    break;
                case 5:
                    donnerPosition();
                    break;
                case 6:
                    faireFranchirLigneDArrivee();
                    break;
                default:
                    break;
                }
        } while (choix >= 1 && choix <= 6 );
    }

    public static void afficherCourse() {
        System.out.println("Voici le classement de la course : ");
        System.out.println(rallyeAutomobile.toString());
        //TODO afficher le bon classement
    }

    public static void afficherPiloteEnTete() {
        if (rallyeAutomobile.donnerPiloteEnTete() == null) {
            System.out.println("La course est terminée");
        } else {
            System.out.println("Le pilote en tête est : " + rallyeAutomobile.donnerPiloteEnTete());
        }
    }

    public static void enregistrerDepassement() {
        System.out.print("Quel pilote effectuera le dépassement ? ");
        String pilote = scanner.next();
        System.out.println();
        if (rallyeAutomobile.depasser(pilote)) {
            System.out.println("Dépassement enregistré !");
        } else {
            System.out.println("Échec du dépassement...");
        }

    }

    public static void retirerPilote() {
        System.out.print("Quel pilote souhaitez-vous retirer de la course ? : ");
        String pilote = scanner.next();
        System.out.println();
        if (rallyeAutomobile.supprimer(pilote)) {
            System.out.println(pilote + " a été retiré de la course");
        } else {
            System.out.println("Impossible de retirer ce pilote.");
            System.out.println("Il a déjà été retiré ou il ne fait pas parti des pilotes en course.");
        }

    }

    public static void donnerPosition() {
        System.out.print("Vous souhaitez connaitre la position de quel pilote ? :");
        String pilote = scanner.next();
        System.out.println();
        int positionPilote = rallyeAutomobile.getPosition(pilote);
        if (positionPilote != -1) {
            System.out.println("Le pilote " + pilote + " est " + positionPilote + "e dans le classement");
        } else {
            System.out.println("Ce pilote ne fait pas parti de la course.");
        }
    }

    public static void faireFranchirLigneDArrivee() {
        if (rallyeAutomobile.donnerPiloteEnTete() == null) {
            System.out.println("La course est terminée");
        } else {
            System.out.println("Le pilote " + rallyeAutomobile.donnerPiloteEnTete() + " a franchi la ligne d'arrivée");
            rallyeAutomobile.franchirLigneDArrivee();
        }
    }

}
