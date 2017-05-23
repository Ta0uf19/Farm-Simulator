package InterfaceClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController extends MainController {

	
	@FXML private Label label;
	private boolean fullScreen = false;
	
	/*
	 * Déconnexion
	 */
	public void logout(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			this.setNode(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Option - Full screen
	 */
	public void setfullScreen(ActionEvent event) {
		Stage screen = (Stage) ((Node) event.getSource()).getScene().getWindow();
		if(!fullScreen) {
			screen.setFullScreen(true);
			screen.show();
			fullScreen = true;
		}
		else {
			screen.setFullScreen(false);
			screen.show();
			fullScreen = false;
		}
	}
	
	/**
	 * Navigation menu
	 */
	
	public void menuClient(ActionEvent event) {
		loadFXML("Client.fxml", event);
	}
	
	public void menuMap(ActionEvent event) {
		loadFXML("Map.fxml", event);
	}
	
	public void menuAccueil(ActionEvent event) {
		loadFXML("Home.fxml", event);
	}
	public void menuOrder(ActionEvent event) {
		loadFXML("Order.fxml", event);
	}
	public void menuMachine(ActionEvent event) {
		loadFXML("Machine.fxml", event);
	}
	/**
	 * Charger le fichier et l'ajouter dans le noeud de la page - avec event 
	 */
	public void loadFXML(String file, ActionEvent event) {
		Parent contenu;
		try {
			contenu = FXMLLoader.load(getClass().getResource(file));
			this.setNodeHome(contenu);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Charger le fichier et l'ajouter dans le noeud de la page - sans event
	 */
	public void loadFXML(String file) {
		loadFXML(file, null);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/*
		 * set page d'accueil
		 */
		label.setText(LoginController.getSession());
		loadFXML("Home.fxml");
		
		
	}
}
