
package Gestionnaire;



public class Machine {
	
	private int id = 0;
	private String marque = "";
	private String modele = "";
	private Boolean etat = Boolean.FALSE;

	
	
	public Machine(int id, String marque, String modele, Boolean etat) {
		super();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.etat = etat;
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

	
	public Boolean getEtat() {
		return this.etat;
	}

	
	public void setEtat(Boolean newEtat) {
		this.etat = newEtat;
	}

}
