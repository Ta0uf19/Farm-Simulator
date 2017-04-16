package Geometry;

import Geometry.Point;

/**
 * Une classe pour encoder et décoder au format JSON<br>
 * un tableau de points (coordonnées GPS)<br>
 * {"polygon":[[48.752110999990684,-3.013601999994632],[48.750417999985736,-3.013448999997121]]}
 * @author BE
 * @version 1.0
 */
public class JSONmanager {
	
	/**
	 * Décode une chaîne au format JSON
	 * @param s
	 * la chaîne de caractère au format JSON
	 * @return
	 * le tableau de points
	 */
	public static Point[] read(String s) {
		JSONTokener tokener = new JSONTokener(s);
		JSONObject  r       = new JSONObject(tokener);
		JSONArray   ar      = (JSONArray) r.get("polygon");
		Point[]     t       = new Point[ar.length()];
		
		for (int i = 0; i < ar.length(); i++) {
			JSONArray elt = (JSONArray) ar.get(i);
			t[i] = new Point(elt.getDouble(0), elt.getDouble(1));
		}
		
		return t;
	}
	
	/**
	 * Encode une chaîne au format JSON
	 * @param t
	 * le tableau de points
	 * @return
	 * la chaîne de caractère au format JSON
	 */
	public static String write(Point[] t) {
		JSONStringer stringer = new JSONStringer();
		stringer.object();
		stringer.key("polygon");
		stringer.array();
		
		for (int j = 0; j < t.length; j++) {
			stringer.array();
			stringer.value(t[j].x());
			stringer.value(t[j].y());
			stringer.endArray();
		}
		stringer.endArray();
		stringer.endObject();
	
		return stringer.toString();
	}
}
