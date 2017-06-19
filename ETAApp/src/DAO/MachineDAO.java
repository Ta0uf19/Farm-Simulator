
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
	public int ajouter(Machine m) {
		try{
		
		Statement stat = connexion.createStatement();
		stat.executeUpdate("INSERT INTO machine VALUES (NULL, '"+m.getMarque()+"' , '"+m.getModele()+"' , '"+m.getEtat()+"');", Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stat.getGeneratedKeys();
		if (rs.next()) {
            String idMa = rs.getString(1);
            
            if(m instanceof Botteleuse) {
				Botteleuse b = (Botteleuse) m;
				stat.executeUpdate("INSERT INTO botteleuse VALUES (NULL, "
						+ "'"+ b.getType()+"', '"+idMa+"');");
			}
			if(m instanceof Tracteur) {
				Tracteur b = (Tracteur) m;
				stat.executeUpdate("INSERT INTO tracteur VALUES (NULL, "
						+ "'"+ b.getCapacite()+"', '"+idMa+"');");
			}
			if(m instanceof Moissoneuse) {
				Moissoneuse mo = (Moissoneuse) m;
				stat.executeUpdate("INSERT INTO moissonneuse VALUES (NULL,"
						+ "'"+ mo.getLgCoupe() +"',"
						+ "'"+ mo.getConsoR() +"',"
						+ "'"+ mo.getConsoF() +"',"
						+ "'"+ mo.getTremie() +"',"
						+ "'"+ mo.getLgRoute() +"',"
						+ "'"+ mo.getHauteur() +"',"
						+ "'"+ idMa +"',"
						+ "'"+ mo.getPoids() +"',"
						+ "'"+ mo.getCapaciteReserve() +"');");
			}
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
		return 0;
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
	public int modifier(Machine m) {
		
		int resultat = 0;
		try{
			Statement stat = connexion.createStatement();
			stat.executeUpdate("UPDATE machine SET "
					+ "mqMa='"+ m.getMarque() +"',"
					+ "mdMa='" + m.getModele() + "',"
					+ "etatMa='"+m.getEtat()+"' WHERE idMa='"+m.getId()+"';");
			if(m instanceof Botteleuse) {
					Botteleuse b = (Botteleuse) m;
					stat.executeUpdate("UPDATE botteleuse SET "
							+ "tyB='"+ b.getType()+"' WHERE idMa='"+b.getId()+"';");
			}
			if(m instanceof Tracteur) {
				Tracteur b = (Tracteur) m;
				stat.executeUpdate("UPDATE tracteur SET "
						+ "capT='"+ b.getCapacite()+"' WHERE idMa='"+b.getId()+"';");
			}
			if(m instanceof Moissoneuse) {
				Moissoneuse mo = (Moissoneuse) m;
				stat.executeUpdate("UPDATE moissonneuse SET "
						+ "lgCoupeM='"+ mo.getLgCoupe() +"',"
						+ "consoRM='"+ mo.getConsoR() +"',"
						+ "consoFM='"+ mo.getConsoF() +"',"
						+ "tremieM='"+ mo.getTremie() +"',"
						+ "lgRouteM='"+ mo.getLgRoute() +"',"
						+ "htM='"+ mo.getHauteur() +"',"
						+ "pdsM='"+ mo.getPoids() +"',"
						+ "capaciteReservM='"+ mo.getCapaciteReserve() +"'"
						+ " WHERE idMa='"+mo.getId()+"';");
			}
			
			
			resultat = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultat = 0;
		}
		
		return resultat;
	}

	@Override
	public void supprimer(Machine objet) {
		// TODO Auto-generated method stub
		
	}

	
	


}
