package InterfaceClient;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application  {
	public void start(Stage window) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/InterfaceClient/view/Login.fxml"));
			Scene scene = new Scene(root);
			window.setTitle("Entreprise de Travaux Agricoles");
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
}