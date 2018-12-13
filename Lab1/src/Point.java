/**
 * A class representing a point in the map using its longitude x and its latitude y.
*/
public class Point {
	double x;
	double y;
	
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
	
	

	
}

