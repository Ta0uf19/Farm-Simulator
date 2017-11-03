package DAO;

import java.sql.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnexionSQL {

	private static String url = "jdbc:mysql://127.0.0.1/eta?autoReconnect=true&useSSL=false";
	private static String user = "root";
	private static String passwd = "*******";
	private static Connection connexion;

	public static Connection getConnexion() {
		if (connexion == null) {
			try {
				connexion = DriverManager.getConnection(url, user, passwd);

			} catch (SQLException e) {
				System.out.println("------ Pas de connexion à la base donnée ! ");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur de connexion BDD");
				alert.setHeaderText("Erreur de connexion avec la base de donnée");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
				System.exit(0);
			}
		}
		
		return connexion;
	}

}
