package DAO;
import java.sql.*;

public class Login {
	
	public static Connection connexion = ConnexionSQL.getConnexion();
	
	/*
	 * Méthode pour vérifier les identifiants insérées.
	 * @return boolean
	 */
	
	public boolean isValid(String username, String password) {
		Statement st = null;
		ResultSet query = null;
		try {
			st = connexion.createStatement();
			query = st.executeQuery("SELECT * FROM utilisateur WHERE username='"+username+"' AND password='"+password+"'");
			if(query.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
			
		}
	}
}
