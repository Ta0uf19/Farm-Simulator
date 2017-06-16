
package Gestionnaire;

import Gestionnaire.Machine;


public class Tracteur extends Machine {
	
	private String capacite;

	public Tracteur(int id, String marque, String modele, int etat, String capacite) {
		super(id, marque, modele, etat);
		this.capacite = capacite;
	}


	public String getCapacite() {
		return this.capacite;
	}

	
	public void setCapacite(String newCapacite) {
		this.capacite = newCapacite;
	}

}
