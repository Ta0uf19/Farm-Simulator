
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import DAO.DAO;
import Gestionnaire.*;




public class MachineDAO implements DAO<Machine> {

	
	public Machine recuperer(int id) {
		Machine mach = null;
		try {
			Statement stat = connexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT * FROM machine WHERE IdMa=" + id);
			if(resultat.first()) {
				
					mach = new Machine(resultat.getInt("IdMa"), resultat.getString("mqMa"), resultat.getString("mdMa"), resultat.getInt("etatMa"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mach;
	}

	@Override
	public Machine ajouter(Machine objet) {
		try{
			int id=objet.getId();
			String marque=objet.getMarque();
			String modele=objet.getModele();
			int etat = objet.getEtat();
			
			Machine machine = null;
			
		Statement stat = connexion.createStatement();
		int resultat = stat.executeUpdate("INSERT INTO machine VALUES ('"+id+"','"+marque+"','"+modele+"','"+etat+"';");
		
//		if(machine.getString("tyCl").equals("Tracteur"))
//			agr = new Machine(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
//		else
//			agr = new Machine(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("preCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
//	}
		//modifier aussi la table concerné par la machine(tracteur...)
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return null;
	}
	
	@Override
	public List<Machine> recupererTout() {
		List<Machine> liste = new ArrayList<Machine>();
		/*
		 * Récupérer les bottleuses
		 * 
		 * 
		 */
		
		try {
			Statement stat = connexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT * from machine join botteleuse on botteleuse.idMa=machine.idMa");
			while(resultat.next()) {
					Machine machine = new Botteleuse(resultat.getInt("idMa"), resultat.getString("mqMa"), resultat.getString("mdMa"), resultat.getInt("etatMa"), resultat.getString("tyB"));
					liste.add(machine);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*
		 * Récupérer les tracteurs
		 */
		
		
		try {
			Statement stat = connexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT * from machine join tracteur on tracteur.idMa=machine.idMa");
			while(resultat.next()) {
					Machine machine = new Tracteur(resultat.getInt("idMa"), resultat.getString("mqMa"), resultat.getString("mdMa"), resultat.getInt("etatMa"), resultat.getString("capT"));
					liste.add(machine);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*
		 * Récupérer les moisseuneuse
		 */
		try {
			Statement stat = connexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT * from machine join moissonneuse on moissonneuse.idMa=machine.idMa");
			while(resultat.next()) {
					Machine machine = new Moissoneuse(resultat.getInt("idMa"), resultat.getString("mqMa"), resultat.getString("mdMa"), resultat.getInt("etatMa"), resultat.getInt("consoRM"), resultat.getInt("consoFM"), resultat.getInt("tremieM"), resultat.getInt("lgCoupeM"), resultat.getInt("lgRouteM"), resultat.getInt("htM"), resultat.getInt("pdsM"), resultat.getInt("capaciteReservM"));
					liste.add(machine);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	

	@Override
	public Machine modifier(Machine objet) {
		int id=objet.getId();
		String marque=objet.getMarque();
		String modele=objet.getModele();
		int etat=objet.getEtat();
		
		Machine machine = null;
		try{
	Statement stat = connexion.createStatement();
	int resultat = stat.executeUpdate("UPDATE INTO machine VALUES ('"+id+"','"+marque+"','"+modele+"','"+etat+"';");
	return machine;
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return machine;
	}

	@Override
	public void supprimer(Machine objet) {
		// TODO Auto-generated method stub
		
	}

	
	


}
