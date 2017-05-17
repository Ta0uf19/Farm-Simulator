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

public class HomeController extends MainController {

	
	@FXML private Label label;
	private boolean fullScreen = false;
	
	/*
	 * Déconnexion
	 */
	public void logout(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
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
	
	/*
	 * Navigation menu - méthode
	 */
	
	public void menuClient(ActionEvent event) {
		Parent contenu;
		try {
			contenu = FXMLLoader.load(getClass().getResource("Client.fxml"));
			this.setNodeHome(contenu);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void menuMap(ActionEvent event) {
		Parent contenu;
		try {
			contenu = FXMLLoader.load(getClass().getResource("Map.fxml"));
			System.out.println(event.getSource() + " Taget: " + event.getTarget());
			this.setNodeHome(contenu);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.setText(LoginController.getSession());
	}
}
