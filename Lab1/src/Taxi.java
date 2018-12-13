/**
 * A class representing a taxi in the map using its longitude x, its latitude y and its unique id.
*/	
public class Taxi extends Point{
	int taxiId;

	/**
	 * @param x
	 * @param y
	 * @param taxiId
	 */
	public Taxi(double x, double y, int taxiId) {
		super(x, y);
		this.taxiId = taxiId;
	}

	/**
	 * @return the taxiId
	 */
	public int getTaxiId() {
		return taxiId;
	}

	/**
	 * @param taxiId the taxiId to set
	 */
	public void setTaxiId(int taxiId) {
		this.taxiId = taxiId;
	}
	
}
	
	
