package taxibeat;
import java.util.ArrayList;
/**
 * A class representing a state of the world when running the A* algorithm.
*/
public class State extends Node{
	private double distance;
	private double heuristic;
	private State previous;
	private ArrayList<State> myChildren = new ArrayList<State>();
	
	/**
	 * @param x
	 * @param y
	 * @param street
	 * @param distance
	 * @param heuristic
	 * @param isGoal
	 */
	public State(double x, double y, Street street, double distance, boolean isGoal, State previous) {
		super(x, y, street, isGoal);
		this.distance = distance;
		this.heuristic = computeHeuristic();
		this.myChildren = null;
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
				 " distance = " + distance + ", heuristic = " + heuristic + ", isGoal = " + isGoal() ;
	}
	
	/**
	 * Function that takes a state and returns its children which are the closest nodes
	 * (including the nodes in other street if we are in a crossing)
	*/
	public void setChildren(){
		ArrayList<State> children = new ArrayList<State>();
		Point myPoint = new Point(getX(), getY());
		if (Street.pointCrossings.containsKey(myPoint)){
			//System.out.println("I am a crosing");
			for (Street crossingStreet : Street.pointCrossings.get(myPoint)) {
				ArrayList<Node> currentStreet = Street.streetNodes.get(crossingStreet);
				int currentIndex = currentStreet.indexOf(new Node(getX(), getY(), crossingStreet, isGoal()));
				//System.out.println(currentIndex);
				//System.out.println(Street.pointCrossings.get(myPoint));
				if (currentIndex < currentStreet.size()-1){
					Node nextNode = currentStreet.get(currentIndex + 1);
					//System.out.println(nextNode);
					double nextNodeDistance = distance + euclideanDistance(this, nextNode);
					State nextNodeState = new State(nextNode.getX(), nextNode.getY(), nextNode.getStreet(), nextNodeDistance, nextNode.isGoal(), this);
					children.add(nextNodeState);
				}
				if (currentIndex > 0){
					Node prevNode = currentStreet.get(currentIndex - 1); 
					//System.out.println(prevNode);
					double prevNodeDistance = distance + euclideanDistance(this, prevNode);
					State prevNodeState = new State(prevNode.getX(), prevNode.getY(), prevNode.getStreet(), prevNodeDistance, prevNode.isGoal(), this);
					children.add(prevNodeState);
				}
			}
		}
		else {
			//System.out.println("Im not a crossing with index");
			ArrayList<Node> myStreet = Street.streetNodes.get(getStreet());
			//for(int i = 0; i< myStreet.size(); i++) {
			//	System.out.println(myStreet.get(i));
			//}
			int myIndex = myStreet.indexOf(new Node(getX(), getY(), getStreet(), isGoal()));
			//System.out.println(myIndex);
			if (myIndex < myStreet.size()-1){
				Node nextNode = myStreet.get(myIndex + 1);
				//System.out.println(nextNode);
				//System.out.println(nextNode.isGoal());
				double nextNodeDistance = distance + euclideanDistance(this, nextNode);
				State nextNodeState = new State(nextNode.getX(), nextNode.getY(), nextNode.getStreet(), nextNodeDistance, nextNode.isGoal(), this);
				children.add(nextNodeState);
			}
			if (myIndex > 0){
				Node prevNode = myStreet.get(myIndex - 1); 
				//System.out.println(prevNode);
				//System.out.println(prevNode.isGoal());
				double prevNodeDistance = distance + euclideanDistance(this, prevNode);
				State prevNodeState = new State(prevNode.getX(), prevNode.getY(), prevNode.getStreet(), prevNodeDistance, prevNode.isGoal(), this);
				//System.out.println(prevNodeState.isGoal());
				children.add(prevNodeState);
			}
		}
		myChildren = children;
	}

	/**
	 * @return the myChildren
	 */
	public ArrayList<State> getMyChildren() {
		return myChildren;
	}

	/**
	 * @param myChildren the myChildren to set
	 */
	public void setMyChildren(ArrayList<State> myChildren) {
		this.myChildren = myChildren;
	}
	
	/**
	 * @return the euclidean distance to closest available taxi.
	 */
	public double computeHeuristic() {
		double minHeuristic = 1000000;
		for( Taxi taxi : Taxi.taxis) {
			if (euclideanDistance(taxi, this) < minHeuristic) {
				minHeuristic = euclideanDistance(taxi, this);
			}
		}
		return minHeuristic;
	}
	
	

}
