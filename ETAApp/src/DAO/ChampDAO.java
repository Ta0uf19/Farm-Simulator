
package DAO;

import java.sql.*;
import java.util.*;

import Gestionnaire.*;

public class ChampDAO implements DAO<Champ> {
	
	public ChampDAO() {
		super();
	}

	@Override
	public Champ recuperer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Champ ajouter(Champ objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Champ modifier(Champ objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(Champ objet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Champ> recupererTout() {
		List<Champ> champs = new ArrayList<Champ>();
		Champ champ = null;
		DAO<Client> cl = new ClientDAO();
		
		try {
			Statement stat = connexion.createStatement();
			ResultSet resultat = stat.executeQuery("SELECT * FROM champ");
			while(resultat.next()) {
				champ = new Champ(resultat.getInt("idCh"), resultat.getString("adrCh"), resultat.getInt("suCh"), resultat.getString("tyculCh"), resultat.getString("gpslatCh") + ", " + resultat.getString("gpslonCh"), resultat.getString("polyCh"), cl.recuperer(resultat.getInt("idCl")));
				champs.add(champ);
				//System.out.println(champ);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return champs;
	}

	
	


}
