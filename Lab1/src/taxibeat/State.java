package taxibeat;
import java.util.ArrayList;
/**
 * A class representing a state of the world when running the A* algorithm.
*/
public class State extends Node{
	private double distance;
	private double heuristic;
	private boolean isGoal;
	private ArrayList<State> myChildren = new ArrayList<State>();
	
	/**
	 * @param x
	 * @param y
	 * @param street
	 * @param distance
	 * @param heuristic
	 * @param isGoal
	 */
	public State(double x, double y, Street street, double distance, double heuristic, boolean isGoal) {
		super(x, y, street);
		this.distance = distance;
		this.heuristic = computeHeuristic();
		this.isGoal = isGoal;
		this.myChildren = null; 
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
	 * @return the isGoal
	 */
	public boolean isGoal() {
		return isGoal;
	}

	/**
	 * @param isGoal the isGoal to set
	 */
	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}

	@Override
	public String toString() {
		return "State with x " + getX() + " y " + getY() + " street id " + getStreet().toString() 
				+ " distance= " + distance + ", heuristic=" + heuristic + ", isGoal=" + isGoal ;
	}
	
	/**
	 * Function that takes a state and returns /
	 * 
	 
	public ArrayList<State> getMyChildren(){
		Street myStreet = getStreet();
		Node closestNode = getClosestNode(Street.streetNodes.get(myStreet));
		//State closestNodeState = new State(closestNode.getX(), closestNode.getY(), distance + euclideanDistance(this, closestNode),
			//	closestNode.computeHeuristic(), closestNode.isTaxi());
	}*/
	
	
	
}
