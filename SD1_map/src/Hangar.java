
public class Hangar {
	
	private int numeroHangar;
	private Societe societe;
	private boolean horsService;
	
	public Hangar(int numeroHangar) {
		this.numeroHangar = numeroHangar;
		this.horsService = false;
	}


	public int getNumeroHangar() {
		return numeroHangar;
	}
	
	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public boolean estHorsService() {
		return horsService;
	}

	public void setHorsService(boolean horsService) {
		if (this.societe != null)
			return;

		this.horsService = horsService;
	}

	@Override
	public String toString() {
		return "Hangar [numeroHangar=" + numeroHangar + ", societe=" + societe + "]";
	}	
	
}
