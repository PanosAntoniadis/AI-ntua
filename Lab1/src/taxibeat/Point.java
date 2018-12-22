package taxibeat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * A class representing a point in the map using its longitude x and its latitude y.
*/
public class Point {
	private double x;
	private double y;
	
	/**
	 * @param x longitude
	 * @param y latitude
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x longitude to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y latitude to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * @param p1 
	 * @param p2
	 * @return euclidean distance of the two input points
	 */
	public static double euclideanDistance(Point p1, Point p2) {
		return Math.sqrt((Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2)));
	}
	
	/**
	 * @param lat1 the latitude of the first point
	 * @param lon1 the longitude of the first point
	 * @param lat2 the latitude of the second point
	 * @param lon2 the longitude of the second point
	 * @return the haversine formula as described here https://rosettacode.org/wiki/Haversine_formula
	 */
	public static final double R = 6372.8; // In kilometers
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return round(R * c, 3);
    }
    
	/**
	 * @return the Node of the closest point
	 */
	Node closestNodeByPoint() {
		Node minNode = null;
		double minDistance = 1000000000;
		for (Node currentNode : Node.nodes) {
			if (haversine(y, x, currentNode.getY(), currentNode.getX()) < minDistance) {
				minNode = currentNode;
				minDistance = haversine(y, x, currentNode.getY(), currentNode.getX());
			}
		}
		if (minNode != null) {
			return minNode;
		}
		else {
			System.out.println("Error in finding closest node");
			return null;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Point other = (Point) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}

	public static double round(double value, int places) {
	    double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}
	
	@Override
	public String toString() {
		return "Point with x = " + x + ", y = " + y;
	}

}

