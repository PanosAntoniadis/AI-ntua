package taxibeat;

/**
 * A class representing a client in the map using its longitude x, its latitude y and the
 * street that is closest to him.
*/

public class Client extends Point {
	private Street closestStreet;

	/**
	 * @param x
	 * @param y
	 */
	public Client(double x, double y) {
		super(x, y);
		this.closestStreet = closestStreetByPoint();
	}

	/**
	 * @return the closestStreet
	 */
	public Street getClosestStreet() {
		return closestStreet;
	}

	/**
	 * @param closestStreet the closestStreet to set
	 */
	public void setClosestStreet(Street closestStreet) {
		this.closestStreet = closestStreet;
	}

	@Override
	public String toString() {
		return "Client with streetId = " + closestStreet.getStreetId() + " streetName " + closestStreet.getStreetName() + " x = " + getX() + " y = " + getY() + "]";
	}	
}
