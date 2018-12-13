import java.util.*; 

/**
 * A class representing a node using its longitude x, latitude y and the street it belongs to.
*/
public class Node extends Point {
	private Street street;
	static ArrayList<Node> nodes = new ArrayList<Node>();
	
	/**
	 * @param x longitude
	 * @param y latitude
	 * @param Street street
	 */
	public Node(double x, double y, Street street) {
		super(x, y);
		this.street = street;
	}

	/**
	 * @return the Street
	 */
	public Street getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(Street street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Node with streetId=" + street.getStreetId() + " streetName= " + street.getStreetName() + ", x=" + getX() + ", y=" + getY();
	}
	
	
}
