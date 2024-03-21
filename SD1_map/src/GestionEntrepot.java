
public class GestionEntrepot {
    //private static Scanner scanner = new Scanner(System.in);
    private static MonScanner scanner = new MonScanner("SD1_map/src/commandes.txt");
    private static Entrepot entrepot;

    public static void main(String[] args) {
        System.out.println("*********************");
        System.out.println("Gestion d'un entrepot");
        System.out.println("*********************");
        System.out.println();
        System.out.print("Entrez le nombre d'hangars : ");
        int nombreHangars = scanner.nextInt();
        entrepot = new Entrepot(nombreHangars);
        int choix = 0;
        do {
            System.out.println();
            System.out.println("1 -> attribuer un hangar");
            System.out.println("2 -> lister les hangars d'une societe");
            System.out.println("3 -> libérer un hangar");
            System.out.println("4 -> ajouter un véhicule à une société");
            System.out.println("5 -> vérifier une plaque d’immatriculation");
            System.out.println("6 -> mettre momentanément un hangar (non utilisé) hors service/en service");
            System.out.println("7 -> quitter");
            System.out.println();
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    attribuerUnHangar();
                    break;
                case 2:
                    listerLesHangars();
                    break;
                case 3:
                    libererUnHangar();
                    break;
                case 4:
                    ajouterVehiculeSociete();
                    break;
                case 5:
                    verifierPlaque();
                    break;
                case 6:
                    mettreHangarHorsService();
                    break;
                case 7:
                    break;
            }

        } while (choix!=7);

        System.out.println("Fin");
    }

    private static void attribuerUnHangar() {
        if (entrepot.nombreHangarsLibres()==0) {
            System.out.println("Désolé, tous les hangars sont occupés !");
        } else {
            System.out.print("Entrez le numéro de la societe : ");
            int numeroSociete = scanner.nextInt();
            Societe societe = entrepot.getSociete(numeroSociete);
            String nomSociete;

            if (societe == null){
                System.out.print("Entrez le nom de la société : ");
                nomSociete = scanner.next();
            } else {
                nomSociete = societe.getNom();
            }

            System.out.println();
            int numeroHangar = entrepot.attribuerHangar(numeroSociete,nomSociete);
            System.out.println("Le numéro du hangar attribué : " + numeroHangar);
        }
    }

    private static void listerLesHangars() {
        System.out.print("Entrez le numéro de la societe : ");
        int numeroSociete = scanner.nextInt();
        Societe societe = entrepot.getSociete(numeroSociete);
        if (societe == null) {
            System.out.println("Cette société n'existe pas");
            return;
        }

        System.out.println("La société " + societe.getNom() + " possède les hangars suivants : " + societe.lesHangars());
    }

    private static void libererUnHangar() {
        if (entrepot.nombreHangarsOccupees() == 0) {
            System.out.println("Désolé, tous les hangars sont libres !");
        } else {
            System.out.print("Entrez le numero de la societe : ");
            int numeroSociete = scanner.nextInt();
            Societe societe = entrepot.getSociete(numeroSociete);
            String nomSociete;

            if (societe == null) {
                System.out.println("Cette société n'existe pas");
                return;
            } else {
                nomSociete = societe.getNom();
            }

            System.out.println();
            if (societe.lesHangars().equals("[]")) {
                System.out.println("Cette société ne possède aucun hangar.");
                return;
            }
            System.out.println("Voici les hangars que la société " + nomSociete + " possède : " + societe.lesHangars());
            System.out.println("Entrez le numéro de hangar que vous souhaitez libérer : ");

            int numeroHangar = scanner.nextInt();
            if (!entrepot.libererHangar(numeroHangar)) {
                System.out.println("Ce hangar n'a pas pu être retiré.");
            } else {
                System.out.println("Le hangar n°" + numeroHangar + " appartenant à la société " + nomSociete + " a été libéré");
                if (!societe.lesHangars().equals("[]"))
                    System.out.println(societe.getNom() + " possède à présent le(s) hangar(s) suivant(s) : " + societe.lesHangars());
            }
        }
    }

    private static void ajouterVehiculeSociete() {
        System.out.println("Entrez le numéro de la société : ");
        int numeroSociete = scanner.nextInt();
        Societe societe = entrepot.getSociete(numeroSociete);

        System.out.println("Entrez le numéro de plaque d'immatriculation de la voiture : ");
        String numPlaque = scanner.next();
        System.out.println();

        if (!entrepot.ajouterPlaque(numPlaque) || !societe.ajouterVoiture(numPlaque)) {
            System.out.println("Erreur, la voiture n'a pas été ajoutée.");
            return;
        }
        System.out.println("La voiture " + numPlaque + " a bien été enregistrée pour la société " + societe.getNom());
    }

    private static void verifierPlaque() {
        System.out.println("Entrez le numéro de société : ");
        int numSociete = scanner.nextInt();

        System.out.println("Entrer le numéro de la plaque d'immatriculation à vérifier : ");
        String numPlaque = scanner.next();
        Societe societe = entrepot.getSociete(numSociete);
        System.out.println(societe);

        if (!societe.lesVoitures().contains(numPlaque)) {
            System.out.println("Cette voiture n'appartient pas à cette société.");
        }
        if (societe.lesHangars().equals("[]")) {
            System.out.println("Cette société n'a aucun hangar. Elle ne peut donc plus entrer dans l'entrepôt.");
            return;
        }

        System.out.println();
        if (!entrepot.estAutorisee(numPlaque)) {
            System.out.println("Cette voiture n'est pas autorisée à entrer dans l'entrepôt");
            return;
        }
        System.out.println("Cette voiture est autorisée à entrer dans l'entrepôt.");

    }

    private static void mettreHangarHorsService() {
        //TODO
    }

}
