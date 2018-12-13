/**
 * A class representing a node using its longitude x, latitude y and the street it belongs to.
*/
public class Node extends Point {
	Street street;

	/**
	 * @param x longitude
	 * @param y latitude
	 * @param Street street
	 */
	public Node(int x, int y, Street street) {
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
	
}
