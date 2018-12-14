import java.util.*; 

/**
 * A class representing a node using its longitude x, latitude y and the street it belongs to.
*/
public class Node extends Point {
	private Street street;
	static ArrayList<Node> nodes = new ArrayList<Node>();
	
	/**
	 * @param x longitude
	 * @param y latitude
	 * @param Street street
	 */
	public Node(double x, double y, Street street) {
		super(x, y);
		this.street = street;
	}

	/**
	 * @return the Street
	 */
	public Street getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(Street street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Node with streetId=" + street.getStreetId() + " streetName= " + street.getStreetName() + ", x=" + getX() + ", y=" + getY();
	}
	
	/**
	 * 
	 * @param currentStreet a list containing all the points in the same street
	 * @return the closes point between the points in the input list
	 */
	public Node getClosestNode(ArrayList<Node> currentStreet) {
		Node closestNode = null;
		double minDistance = 1000000;
		for( Node currentNode : currentStreet) {
			if ((euclideanDistance(this, currentNode) < minDistance) && !currentNode.equals(this)){
				minDistance = euclideanDistance(this, currentNode);
				closestNode = currentNode;
			}
		}
		return closestNode;
	}
	
	public double computeHeuristic() {
		double minHeuristic = 1000000;
		for( Taxi taxi : Taxi.taxis) {
			if (euclideanDistance(taxi, this) < minHeuristic) {
				minHeuristic = euclideanDistance(taxi, this);
			}
		}
		return minHeuristic;
	}
	
	public boolean isTaxi() {
		for( Taxi taxi : Taxi.taxis) {
			if ((taxi.getX() == getX()) && (taxi.getY() == getY())) {
				return true;
			}
		}
		return false;
	}
	
	
}
