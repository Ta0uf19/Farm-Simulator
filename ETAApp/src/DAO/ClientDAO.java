
package DAO;

import java.sql.*;
import java.util.*;

import Gestionnaire.*;

public class ClientDAO implements DAO<Client> {

	public ClientDAO() {
		super();
	}
	/*
	 * Récupérer objet par identifiant
	 * @see DAO.DAO#recuperer(int)
	 */
	public Client recuperer(int id) {
		
		Client agr = null;
		try {
			Statement stat = connexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT * FROM client WHERE IdCl=" + id);
			if(resultat.first()) {
				if(resultat.getString("tyCl").equals("Coopérative"))
					agr = new Client(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
				else
					agr = new Client(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("preCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agr;
	}
	@Override
	public int ajouter(Client c) {
			Statement stat;
			try {
				stat = connexion.createStatement();
				stat.executeUpdate("INSERT INTO client VALUES (NULL, "
						+ "'"+ c.getTypeCl() +"', '"+c.getNom()+"', '"+c.getPrenom()+"', '"+c.getTelephone()+"', '"+c.getAdresse()+"');");
				
				return 1;
			} catch (SQLException e) {
			
				e.printStackTrace();
				
				return 0;
			}
	}
	@Override
	public int modifier(Client c) {
		Statement stat;
		try {
			stat = connexion.createStatement();
			stat.executeUpdate("UPDATE client SET tyCl='"+c.getTypeCl()+"', nomCl='"+c.getNom()+"', preCl='"+c.getPrenom()+"', telCl='"+c.getTelephone()+"', adrCl='"+c.getAdresse()+"' WHERE IdCl="+c.getId()+";");		
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public void supprimer(Client objet) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * Récupérer la liste de toutes les clients;
	 */
	@Override
	public List<Client> recupererTout() {
		List<Client> liste = new ArrayList<Client>();
		Client agr = null;
		try {
			Statement stat = connexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT * FROM client");
			while(resultat.next()) {
				
				if(resultat.getString("tyCl").equals("Coopérative"))
					agr = new Client(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
				else
					agr = new Client(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("preCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
			
				//agr = new Client(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("preCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
				liste.add(agr);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	public Client recupererParNom(String nom) {
		
		Client cl = null;
		try {
			Statement stat = connexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT * FROM client WHERE nomCl='"+nom+"'");
			if(resultat.next()) {
				if(resultat.getString("tyCl").equals("Coopérative"))
					cl = new Client(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
				else
					cl = new Client(resultat.getInt("IdCl"), resultat.getString("nomCl"), resultat.getString("preCl"), resultat.getString("adrCl"), resultat.getString("telCl"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cl;
	}

	



}
