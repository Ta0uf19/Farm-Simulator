package InterfaceClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsLeg;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;

import DAO.ChampDAO;
import DAO.ClientDAO;
import DAO.DAO;
import Geometry.JSONmanager;
import Geometry.Point;
import Gestionnaire.Champ;
import Gestionnaire.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import netscape.javascript.JSObject;


public class MapController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {
		

		/*
		 * last edit: 16/04/17
		 */
	    @FXML private GoogleMapView googleMapView;
	    @FXML private TextField champ1;
	    @FXML private TextField champ2;
	    @FXML private Label labelDistance;
	    @FXML private TextField champClient;
	    @FXML private Label labelErrorClient;
	    
	    private GoogleMap map;
	    protected DirectionsRenderer directionsRenderer = null;
	    // Travel mode
	    private TravelModes travel = TravelModes.DRIVING;
	    //ZOOM
	    private static final int ZOOM = 12;
	    //
	    public static String showMapClient =null;
	    
	    
		@Override
		public void initialize(URL url, ResourceBundle rb) {
			googleMapView.addMapInializedListener(this);
			
		}
		
		public void showDistance(ActionEvent e) {
			if(!champ1.getText().isEmpty() && !champ2.getText().isEmpty()) {
				DirectionsService directionsService = new DirectionsService();
		        DirectionsRequest request = new DirectionsRequest(champ1.getText(), champ2.getText(), travel);
		        directionsRenderer = new DirectionsRenderer(false, googleMapView.getMap(), googleMapView.getDirec());
		        directionsService.getRoute(request, this, directionsRenderer);
			}
		}
		public void mapReset() {
			 map.clearMarkers();
		
		}
		@Override
		public void mapInitialized() {

	        MapOptions mapOptions = new MapOptions();
	        mapOptions.center(new LatLong(47.970793, -1.448495))
            .overviewMapControl(false)
            .panControl(true)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(true)
            .zoom(ZOOM).mapType(MapTypeIdEnum.SATELLITE);
	        map = googleMapView.createMap(mapOptions);
	        
	        /*
	         * Nouvelle version 17/04 - récupérer les champs depuis bdd.
	         */
	        DAO<Champ> champ = new ChampDAO();
	        List<Champ> champs = champ.recupererTout();
	        Client client = (new ClientDAO()).recupererParNom(showMapClient);
	        
	      
	        if(client == null && showMapClient == null) {
	        	 /*
		         * Ajouter toutes les champs des clients
		         */
		        for(Champ ch : champs) {
		        	addShapeMarkChamp(ch);
		        }
	        }
	        else {
	        	/*
	        	 * Afficher les champs d'un seul Client
	        	 */
	        	for(Champ ch : champs) {
		        	if(ch.getClients().getNom().toLowerCase().equals(showMapClient)) {
		        			addShapeMarkChamp(ch);
			        }
		
		        }
	        }
	        /*
	         * Si le client n'existe pas
	         */
	        if(client == null && showMapClient != null) {
    			labelErrorClient.setText("Le client n'existe pas \n Vérifiez le nom");
	        }
	        showMapClient = null;
	        

	       
	       
		}
		/**
		 * Ajouter le champ et la mark et l'InfoWindow
		 * @param champ
		 */
		private void addShapeMarkChamp(Champ ch) {
			
			String iconpath  = (baseDir()+"InterfaceClient/marker.png").replaceAll(" ", "%20");
			
		    JSONmanager points = new JSONmanager();
		    MarkerOptions markerOptions = new MarkerOptions().animation(Animation.DROP).icon(MarkerImageFactory.createMarkerImage(iconpath, "png"));
		    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
		    InfoWindow clientInfoWindow = new InfoWindow(infoWindowOptions);
		       
			Point[] point = points.read(ch.getPolygone());
        	List<LatLong> pAry = new ArrayList<LatLong>();
	        for(Point p : point) {
	        	pAry.add(new LatLong(p.x(), p.y()));
	        }
	        MVCArray pmvc = new MVCArray(pAry.toArray());
	        Polygon arc = new Polygon(new PolygonOptions()
	                .paths(pmvc)
	                .strokeColor("blue")
	                .fillColor("lightBlue")
	                .fillOpacity(0.3)
	                .strokeWeight(2)
	                .editable(false));
	        map.addMapShape(arc);

	       
	        LatLong centreMarker = new LatLong(ch.getCenterLat(), ch.getCenterLong());
	        markerOptions.position(centreMarker);
	        Marker mark = new Marker(markerOptions);

	        map.addUIEventHandler(mark, UIEventType.click, (JSObject obj) -> { 
	          clientInfoWindow.setContent(("<h3><b>Propriétaire</b></h3> "+ch.getClients().getNom()+" "+ch.getClients().getPrenom()+"<br/>"
	        		+ "<h3><b>Nom de la culture</b></h3> "+ch.getType()+" <br/>"
                    + "<h3><b>Adresse</b></h3> "+ch.getAdresse()+" <br/>"
                    + "<h3><b>Surface</b></h3> "+ch.getSurface()+" m² <br/>"
                    ));
	        	clientInfoWindow.open(map, mark);
	        });
	        map.addMarker(mark);
		}
		
		/*
		 * Option - src path
		 */
		private String baseDir() {
	        String baseDir = "";
	        try {
	            baseDir = new File(".").getCanonicalPath();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        baseDir = baseDir.replace('\\','/');
	        baseDir = "file:///" + baseDir + "/src/";
	        return baseDir;
	    }

		@Override
		public void directionsReceived(DirectionsResult results, DirectionStatus status) {
			/*
			 * Calcul de la distance
			 */
			if(status.equals(DirectionStatus.OK)){
	            
	            DirectionsLeg e = results.getRoutes().get(0).getLegs().get(0);
	            GeocodingService gs = new GeocodingService();
	        
	            labelDistance.setText("Distance: " + e.getDistance().getText());
	        }
		}
		
		public void showMapClient() {
			showMapClient = champClient.getText().toLowerCase();
			mapInitialized();
		}
		
		/*
		 * Adresse to latLong
		 
		public void getLatLongFromAdress(String address) {
			  GeocodingService geocodingService = new GeocodingService();
			  //LatLong latLong = null;
			  geocodingService.geocode(address, (GeocodingResult[] results, GeocoderStatus status) -> {
				  LatLong latLong = null;  
				  if( status == GeocoderStatus.ZERO_RESULTS) {
		                return;
		            } else if( results.length > 1 ) {
		                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
		            } else {
		                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
		            }
				  map.setCenter(latLong);
				 
		        });
			//return latLong;
		}*/
}
