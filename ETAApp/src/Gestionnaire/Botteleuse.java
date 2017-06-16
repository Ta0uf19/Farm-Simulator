
package Gestionnaire;

import Gestionnaire.Machine;


public class Botteleuse extends Machine {
	
	private String type = "";


	public Botteleuse(int id, String marque, String modele, int etat, String type) {
		super(id, marque, modele, etat);
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}


	public void setType(String newType) {
		this.type = newType;
	}

}
