package taxibeat;
/**
 * A class representing a state of the world when running the A* algorithm.
*/
public class State extends Node{
	private double distance;
	private double heuristic;
	private State previous;
	
	/**
	 * @param x
	 * @param y
	 * @param street
	 * @param distance
	 * @param heuristic
	 * @param isGoal
	 */
	public State(double x, double y, Street street, double distance, State previous) {
		super(x, y, street);
		this.distance = distance;
		this.heuristic = computeHeuristic();
		this.previous = previous;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the heuristic
	 */
	public double getHeuristic() {
		return heuristic;
	}

	/**
	 * @param heuristic the heuristic to set
	 */
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}

	/**
	 * @return the previous
	 */
	public State getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(State previous) {
		this.previous = previous;
	}

	@Override
	public String toString() {
		return "State with x = " + getX() + " y = " + getY() + " street id " + getStreet().getStreetId() + " street name " + getStreet().getStreetName() +
				 " distance = " + distance + ", heuristic = " + heuristic  ;
	}
	
	/**
	 * @return the distance to client.
	 */
	public double computeHeuristic() {
		return haversine(getY(), getX(), TaxiBeat.client.getY(), TaxiBeat.client.getX());
	}
}
