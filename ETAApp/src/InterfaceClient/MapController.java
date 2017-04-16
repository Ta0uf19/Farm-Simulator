package InterfaceClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
import com.lynden.gmapsfx.service.directions.DirectionsSteps;
import com.lynden.gmapsfx.service.directions.Distance;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.shapes.ArcBuilder;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;

import DAO.*;
import Geometry.JSONmanager;
import Geometry.Point;
import Gestionnaire.Champ;
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
	    
	    private GoogleMap map;
	    protected DirectionsRenderer directionsRenderer = null;
	    // Travel mode
	    private TravelModes travel = TravelModes.DRIVING;
	    //ZOOM
	    private static final int ZOOM = 12;
	    
	    
		@Override
		public void initialize(URL url, ResourceBundle rb) {
			//googleMapView.addMapInializedListener(() -> configureMap());
			googleMapView.addMapInializedListener(this);
			
		}
		
		public void showDistance(ActionEvent e) {
			if(!champ1.getText().isEmpty() && !champ2.getText().isEmpty()) {
				DirectionsService directionsService = new DirectionsService();
		        DirectionsRequest request = new DirectionsRequest(champ1.getText(), champ2.getText(), travel);
		        directionsRenderer = new DirectionsRenderer(false, googleMapView.getMap(), googleMapView.getDirec());
		        directionsService.getRoute(request, this, directionsRenderer);
		       // directionsService.t
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
	         * Nouvelle version 16/04 - récupérer les champs depuis bdd.
	         */
	        JSONmanager points = new JSONmanager();
	        DAO<Champ> champ = new ChampDAO();
	        List<Champ> champs = champ.recupererTout();
	        List<Marker> markers = new ArrayList<Marker>();
	        MarkerOptions markerOptions = new MarkerOptions().animation(Animation.BOUNCE).icon(MarkerImageFactory.createMarkerImage(baseDir()+"InterfaceClient/marker.png", "png"));
	        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
	        infoWindowOptions.content("Loading");
	        InfoWindow clientInfoWindow = new InfoWindow(infoWindowOptions);
	        
	        for(Champ ch : champs) {
	        	
	        	Point[] point = points.read(ch.getPolygone());
	        	List<LatLong> pAry = new ArrayList<LatLong>();
		        for(Point p : point) {
		        	pAry.add(new LatLong(p.x(), p.y()));
		        }
		        MVCArray pmvc = new MVCArray(pAry.toArray());
		        Polygon arc = new Polygon(new PolygonOptions()
		                .paths(pmvc)
		                .strokeColor("red")
		                .fillColor("lightBlue")
		                .fillOpacity(0.3)
		                .strokeWeight(2)
		                .editable(false));
		        map.addMapShape(arc);

		       
		        LatLong centreMarker = new LatLong(ch.getCenterLat(), ch.getCenterLong());
		        System.out.println(ch.getCenterLat());
		        markerOptions.position(centreMarker);
		        Marker mark = new Marker(markerOptions);
		        markers.add(mark);

		        map.addUIEventHandler(mark, UIEventType.click, (JSObject obj) -> { 
		          clientInfoWindow.setContent(("<h3><b>Propriétaire</b></h3> "+ch.getClients().getNom()+" "+ch.getClients().getPrenom()+"<br/>"
		        		+ "<h3><b>Nom de la culture</b></h3> "+ch.getType()+" <br/>"
                        + "<h3><b>Adresse</b></h3> "+ch.getAdresse()+" <br/>"
                        + "<h3><b>Surface</b></h3> "+ch.getSurface()+" m² <br/>"
                        ));
		        	clientInfoWindow.open(map, mark);
		        });
		        
	        }
	      
	        map.addMarkers(markers);

	       
	       
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
