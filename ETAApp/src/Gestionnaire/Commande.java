
package Gestionnaire;

import java.util.HashSet;


public class Commande {
	
	private int cout = 0;
	public Champ champs = null;
	private String date = null;
	public Client client = null;
	private int id = 0;
	public HashSet<Machine> machines = new HashSet<Machine>();

	
	public Commande(int id, Client client,  Champ champs, int cout, String date) {
		super();
	}
	
	public int getCout() {
		return this.cout;
	}

	
	public void setCout(int newCout) {
		this.cout = newCout;
	}

	
	public Champ getChamps() {
		return this.champs;
	}

	
	/*public void setChamps(Champ newChamps) {
		if (this.champs != null) {
			this.champs.set(null);
		}
		this.champs.set(this);
	}
*/
	
	public String getDate() {
		return this.date;
	}

	
	public void setDate(String newDate) {
		this.date = newDate;
	}

	
	public Client getClient() {
		return this.client;
	}

	
	public void setClient(Client newClient) {
		this.client = newClient;
	}

	
	public int getId() {
		return this.id;
	}

	
	public void setId(int newId) {
		this.id = newId;
	}

	
	public HashSet<Machine> getMachines() {
		return this.machines;
	}

}
