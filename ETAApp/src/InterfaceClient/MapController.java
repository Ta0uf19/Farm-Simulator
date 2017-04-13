package InterfaceClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import com.lynden.gmapsfx.shapes.ArcBuilder;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import netscape.javascript.JSObject;


public class MapController implements Initializable, MapComponentInitializedListener {

	    @FXML private GoogleMapView googleMapView;

	    private GoogleMap map;

		@Override
		public void initialize(URL url, ResourceBundle rb) {
			//googleMapView.addMapInializedListener(() -> configureMap());
			googleMapView.addMapInializedListener(this);
			
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
            .zoom(14).mapType(MapTypeIdEnum.SATELLITE);
               
	        map = googleMapView.createMap(mapOptions);
	        
	        LatLong poly1 = new LatLong(47.9792325, -1.4950258);
	        LatLong poly2 = new LatLong(47.9789237, -1.4936632);
	        LatLong poly3 = new LatLong(47.9791607, -1.4934593);
	        LatLong poly4 = new LatLong(47.9791284, -1.4922577);
	        LatLong poly5 = new LatLong(47.9791859, -1.4919573);
	        LatLong poly6 = new LatLong(47.9815666, -1.4923677);
	        LatLong poly7 = new LatLong(47.9815684, -1.4928934);
	        LatLong poly8 = new LatLong(47.9816079, -1.4930624);
	        LatLong poly9 = new LatLong(47.9816348, -1.4933681);
	        LatLong poly10 = new LatLong(47.9815989, -1.4938188);
	        LatLong poly11 = new LatLong(47.9815989, -1.4942721);
	        LatLong poly12 = new LatLong(47.9816312, -1.4946851);
	        LatLong poly13 = new LatLong(47.9805684, -1.4949238);
	        LatLong[] pAry = new LatLong[]{poly1, poly2, poly3, poly4, poly5, poly6, poly7, poly8, poly9, poly10, poly11, poly12, poly13};
	        MVCArray pmvc = new MVCArray(pAry);
	        
	        Polygon arc = new Polygon(new PolygonOptions()
	                .paths(pmvc)
	                .strokeColor("blue")
	                .fillColor("lightBlue")
	                .fillOpacity(0.3)
	                .strokeWeight(2)
	                .editable(false));
	        
	        LatLong poly14 = new LatLong(47.9792325, -1.4950258);

	        map.addMapShape(arc);
	        /*
	         * Modifier arc
	         * 
	         */
	        /*map.addUIEventHandler(arc, UIEventType.click, (JSObject obj) -> {
	            arc.setEditable(!arc.getEditable());
	        });*/
	        LatLong centreBle = new LatLong(47.980005, -1.4934);
	        MarkerOptions markerOptions = new MarkerOptions().animation(Animation.DROP).icon(MarkerImageFactory.createMarkerImage(baseDir()+"InterfaceClient/marker.png", "png"));
	        markerOptions.position(centreBle);
	        Marker mark = new Marker(markerOptions);
	        map.addMarker(mark);
	        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
	        infoWindowOptions.content("<b>Nom de la culture</b> Blé <br/>"
	                                + "<b>Adresse</b> Le Paveil, Amanlis <br/>"
	                                + "<b>Surface</b> 56800 m² <br/>"
	                                + "<b>Client</b> Client1");
	        
	        InfoWindow clientInfoWindow = new InfoWindow(infoWindowOptions);
	        /*
	         * Ouvrir la boîte avec click
	         */
	        map.addUIEventHandler(mark, UIEventType.click, (JSObject obj) -> { 
	        	clientInfoWindow.open(map, mark);
	        });
	        System.out.println("distance" + centreBle.distanceFrom(poly14));
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
}
