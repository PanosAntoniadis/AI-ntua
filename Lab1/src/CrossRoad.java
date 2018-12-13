import java.io.*; 
import java.util.*; 

/**
 * A class representing a crossroad using its longitude x, latitude y and the streets that crosses.
*/
public class CrossRoad extends Point{
	ArrayList<Street> crossStreets;

	/**
	 * @param x
	 * @param y
	 * @param crossStreets
	 */
	public CrossRoad(int x, int y, ArrayList<Street> crossStreets) {
		super(x, y);
		this.crossStreets = crossStreets;
	}

	/**
	 * @return the crossStreets
	 */
	public ArrayList<Street> getCrossStreets() {
		return crossStreets;
	}

	/**
	 * @param crossStreets the crossStreets to set
	 */
	public void setCrossStreets(ArrayList<Street> crossStreets) {
		this.crossStreets = crossStreets;
	}
	
	
}
