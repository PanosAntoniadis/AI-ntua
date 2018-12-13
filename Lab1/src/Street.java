/**
 * A simple class representing a street using its unique id and its name if given.
*/
public class Street {
	int streetId;
	String streetName;
	
	/**
	 * @return the streetId
	 */
	public int getStreetId() {
		return streetId;
	}
	
	/**
	 * @param streetId is the if of the street to set
	 */
	public void setStreetId(int streetId) {
		this.streetId = streetId;
	}
	
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName is the name of the street to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

}
