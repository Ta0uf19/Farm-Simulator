
package Gestionnaire;

// Start of user code (user defined imports)

// End of user code


public class Machine {
	
	private int id = 0;

	
	private String marque = "";

	
	private String modele = "";

	
	private Boolean etat = Boolean.FALSE;

	// Start of user code (user defined attributes for Machine)

	// End of user code

	
	public Machine() {
		// Start of user code constructor for Machine)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Machine)

	// End of user code
	
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
