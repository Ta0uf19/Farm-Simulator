package Geometry;

import java.io.Serializable;

/**
 * Une classe pour représenter une coordonnée GPS.
 * @author BE
 * @version 1.0
 */
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;
	private double x, y; // coordonnées GPS

	/**
	 * @param x
	 * latitude
	 * @param y
	 * longitude
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return
	 * la latitude
	 */
	public double x() {
		return this.x;
	}
	
	/**
	 * @return
	 * la longitude
	 */
	public double y() {
		return this.y;
	}
	
	/**
	 * @return
	 * les coordonnées sous la forme (latitude, longitude)
	 */
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
