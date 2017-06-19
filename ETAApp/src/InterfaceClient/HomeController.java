package InterfaceClient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HomeController implements Initializable {
	@FXML WebView weather;
	
    WebEngine webEngine;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		webEngine = weather.getEngine();
		webEngine.setUserStyleSheetLocation(getClass().getResource("/InterfaceClient/view/weather.css").toExternalForm());
		webEngine.load("https://www.meteoblue.com/en/weather/widget/three/rez%c3%a9_france_2983770?geoloc=fixed&nocurrent=0&noforecast=0&days=4&tempunit=CELSIUS&windunit=KILOMETER_PER_HOUR&layout=bright");
		
	}
	
}
