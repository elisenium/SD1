public class JeuGuerrier {
	
	public static void main(String[] args) {
		EquipeGuerriers equipe = new EquipeGuerriers(3, 10);

		int pointsDeVieDuMal = 30;
		
		while (pointsDeVieDuMal > 0 || equipe.nombreGuerriersEnVie() != 0) {
			System.out.println();
			System.out.println("L'equipe compte " + equipe.nombreGuerriersEnVie() + " guerriers en vie");

			int lancerDeGuerrier = lancerDe();
			int lancerDeDuMal = lancerDe();

			Guerrier guerrier = equipe.jouer(lancerDeDuMal);
			int numeroGuerrier = guerrier.getNumero()+1;

			pointsDeVieDuMal -= lancerDeGuerrier;
			int pointsVieGuerrier = guerrier.getPointsDeVie();

			System.out.println("Suite au combat entre la creature du mal et le guerrier n°" + numeroGuerrier + ":");
			System.out.println("Le guerrier vient de perdre " + lancerDeDuMal + " points de vie");

			if (pointsVieGuerrier <= 0)
				System.out.println("Le guerrier est mort");
			else {
				System.out.println("Il lui reste " + pointsVieGuerrier + " points de vie");
			}

			System.out.println("La creature du mal vient de perdre " + lancerDeGuerrier + " points de vie");
			if (pointsDeVieDuMal <= 0) {
				System.out.println("La creature du mal est morte");
				break;

			} else {
				System.out.println("Il lui reste " + pointsDeVieDuMal + " points de vie");
			}

			if (equipe.nombreGuerriersEnVie() == 0) {
				System.out.println("Tous les guerriers sont morts");
				break;
			}
		}
	}
	
	public static int lancerDe (){
		double nombreReel;
		nombreReel = Math.random();
		return (int) (nombreReel * 6) + 1;
	}
	
}
