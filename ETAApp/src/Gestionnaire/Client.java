
package Gestionnaire;


public class Client {
	
	private Integer id;
	private String adresse;
	private String telephone;
	private String typeCl;
	private String nom;
	private String prenom = null;

	/*
	 * Ajout d'un client agr
	 */
	public Client(Integer id, String nom, String prenom, String adresse, String telephone) {
		super();
		this.id = id;
		this.adresse = adresse;
		this.telephone = telephone;
		this.prenom = prenom;
		this.nom = nom;
		this.typeCl = "Agriculteur";
	}
	/*
	 * Ajout d'une coopérative
	 */
	public Client(Integer id, String nom, String adresse, String telephone) {
		super();
		this.id = id;
		this.adresse = adresse;
		this.telephone = telephone;
		this.nom = nom;
		this.typeCl = "Coopérative";
	}

	public int getId() {
		return this.id;
	}

	
	public void setId(int newId) {
		this.id =newId;
	}

	
	public String getAdresse() {
		return this.adresse;
	}
	
	public void setAdresse(String newAdresse) {
		this.adresse = newAdresse;
	}

	
	public String getTelephone() {
		return this.telephone;
	}

	
	public void setTelephone(String newTelephone) {
		this.telephone = newTelephone;
	}
	public String getTypeCl() {
		return typeCl;
	}
	public void setTypeCl(String typeCl) {
		this.typeCl = typeCl;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
