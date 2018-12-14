package taxibeat;
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
	 * @return the street of the closest point
	 */
	Street closestStreetByPoint() {
		Street minStreet = null;
		double minDistance = 1000000000;
		for (Node currentNode : Node.nodes) {
			if (euclideanDistance(this, currentNode) < minDistance) {
				minStreet = currentNode.getStreet();
				minDistance = euclideanDistance(this, currentNode);
			}
		}
		if (minStreet != null) {
			return minStreet;
		}
		else {
			System.out.println("Error in finding closest street");
			return null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point with x = " + x + ", y = " + y;
	}

}

