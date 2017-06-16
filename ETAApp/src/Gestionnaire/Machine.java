package Gestionnaire;



public class Machine {
	
	private int id = 0;
	private String marque = "";
	private String modele = "";
	private int etat = 0;
	private String dispo;

	
	
	public Machine(int id, String marque, String modele, int etat) {
		super();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.etat = etat;
		if(etat == 0) {
			this.dispo = "Non";
		} else {
			this.dispo = "Oui";
		}
	}

	public int getId() {
		return this.id;
	}

	
	public void setId(int newId) {
		this.id = newId;
	}

	
	public String getMarque() {
		return this.marque;
	}

	
	public void setMarque(String newMarque) {
		this.marque = newMarque;
	}

	
	public String getModele() {
		return this.modele;
	}

	
	public void setModele(String newModele) {
		this.modele = newModele;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getDispo() {
		return dispo;
	}

	public void setDispo(String dispo) {
		this.dispo = dispo;
	}

	@Override
	public String toString() {
		return "Machine [id=" + id + ", marque=" + marque + ", modele=" + modele + ", etat=" + etat + ", dispo=" + dispo
				+ "]";
	}

	

	
	

}