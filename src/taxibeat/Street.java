package taxibeat;

import java.util.Objects;

/**
 * A class representing a street using its unique id and its name if given.
*/
public class Street {
	private int streetId;
	private String streetName;
	/**
	 * @param streetId
	 * @param streetName
	 */
	public Street(int streetId, String streetName) {
		this.streetId = streetId;
		this.streetName = streetName;
	}
	
	/**
	 * In case there is not a name available for the 
	 * street this constructor is called.
	 * @param streetId
	 */
	public Street(int streetId) {
		this.streetId = streetId;
		this.streetName = "None";
	}

	/**
	 * @return the streetId
	 */
	public int getStreetId() {
		return streetId;
	}
	
	/**
	 * @param streetId is the id of the street to set
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

	@Override
	public String toString() {
		return "Street with streetId = " + streetId + " streetName = " + streetName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(streetId, streetName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		Street other = (Street) obj;
		return streetId == other.streetId && Objects.equals(streetName, other.streetName);
	}
	
	
	
}
