import java.util.*;
/**
 * A class representing a taxi in the map using its longitude x, its latitude y and its unique id.
*/
public class Taxi extends Point{
	private int taxiId;
	private Street closestStreet;
	static ArrayList<Taxi> taxis = new ArrayList<Taxi>();
	
	/**
	 * @param x longitude
	 * @param y latitude
	 * @param taxiId unique id
	 */
	public Taxi(double x, double y, int taxiId) {
		super(x, y);
		this.taxiId = taxiId;
		this.closestStreet = closestStreetByPoint();
	}

	/**
	 * @return the taxiId
	 */
	public int getTaxiId() {
		return taxiId;
	}

	/**
	 * @param taxiId the unique id to set
	 */
	public void setTaxiId(int taxiId) {
		this.taxiId = taxiId;
	}

	@Override
	public String toString() {
		return "Taxi with taxiId = " + taxiId + " closest streetId = " + closestStreet.getStreetId() + " streetName " + 
				closestStreet.getStreetName() + " x = " + getX() + " y = " + getY() ;
	}
	
	
}
	
	
