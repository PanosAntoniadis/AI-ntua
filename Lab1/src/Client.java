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
		this.closestStreet = closestStreetByPoint(this);
	}

	/**
	 * @return the clientStreet
	 */
	public Street getClientStreet() {
		return closestStreet;
	}

	/**
	 * @param clientStreet the clientStreet to set
	 */
	public void setClientStreet(Street clientStreet) {
		this.closestStreet = clientStreet;
	}

	@Override
	public String toString() {
		return "Client with streetId=" + closestStreet.getStreetId() + " streetName " + closestStreet.getStreetName() + " x= " + getX() + " y= " + getY() + "]";
	}
	
	
}
