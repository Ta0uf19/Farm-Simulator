
package Gestionnaire;

import Gestionnaire.Machine;

public class Moissoneuse extends Machine {
	
	private int consoR = 0;
	private int consoF = 0;
	private int tremie = 0;
	private int lgCoupe = 0;
	private int lgRoute = 0;
	private int hauteur = 0;
	private int poids = 0;
	private int vitesse = 0;

	
	public Moissoneuse(int id, String marque, String modele, Boolean etat, int consoR, int consoF, int tremie,
			int lgCoupe, int lgRoute, int hauteur, int poids, int vitesse) {
		super(id, marque, modele, etat);
		
		this.consoR = consoR;
		this.consoF = consoF;
		this.tremie = tremie;
		this.lgCoupe = lgCoupe;
		this.lgRoute = lgRoute;
		this.hauteur = hauteur;
		this.poids = poids;
		this.vitesse = vitesse;
	}



	public int getConsoR() {
		return this.consoR;
	}

	
	public void setConsoR(int newConsoR) {
		this.consoR = newConsoR;
	}

	
	public int getConsoF() {
		return this.consoF;
	}

	
	public void setConsoF(int newConsoF) {
		this.consoF = newConsoF;
	}

	
	public int getTremie() {
		return this.tremie;
	}

	
	public void setTremie(int newTremie) {
		this.tremie = newTremie;
	}

	
	public int getLgCoupe() {
		return this.lgCoupe;
	}

	
	public void setLgCoupe(int newLgCoupe) {
		this.lgCoupe = newLgCoupe;
	}

	
	public int getLgRoute() {
		return this.lgRoute;
	}

	
	public void setLgRoute(int newLgRoute) {
		this.lgRoute = newLgRoute;
	}

	
	public int getHauteur() {
		return this.hauteur;
	}

	
	public void setHauteur(int newHauteur) {
		this.hauteur = newHauteur;
	}

	
	public int getPoids() {
		return this.poids;
	}

	
	public void setPoids(int newPoids) {
		this.poids = newPoids;
	}

	
	public int getVitesse() {
		return this.vitesse;
	}

	
	public void setVitesse(int newVitesse) {
		this.vitesse = newVitesse;
	}

}
