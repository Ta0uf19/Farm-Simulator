package InterfaceClient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class MainController implements Initializable {
	
	@FXML public BorderPane pane;
	@FXML public AnchorPane anchorpane;
	
	public void setNode(Node node) {
		pane.getChildren().clear();
		//pane.getChildren().add(node);
		pane.setCenter(node);
		/*System.out.println(pane.prefWidthProperty());
		pane.prefWidthProperty().bind(pane.widthProperty());
		System.out.println(pane.prefWidthProperty());*/

		/*
		 * Transition
		 * */
		FadeTransition ft = new FadeTransition(Duration.millis(1500));
	    ft.setNode(node);
	    ft.setFromValue(0.5);
	    ft.setToValue(1);
	    ft.setCycleCount(1);
	    ft.setAutoReverse(false);
	    ft.play();
	}
	
	public void setNodeHome(Node node) {
		//anchorpane.getChildren().clear();
		pane.setCenter(node);
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	
	
}
