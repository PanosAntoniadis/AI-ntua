package taxibeat;
import java.util.*;
/**
 * A class representing a node using its longitude x,its latitude y and the street it belongs to.
*/
public class Node extends Point {
	private Street street;
	private boolean available;
	private boolean isGoal;
	/**
	 * A list that contains all the nodes in the map.
	 */
	static ArrayList<Node> nodes = new ArrayList<Node>();
	
	/**
	 * @param x longitude
	 * @param y latitude
	 * @param Street street
	 */
	public Node(double x, double y, Street street, boolean isGoal) {
		super(x, y);
		this.street = street;
		this.available = true;
		this.isGoal = isGoal;
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


	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
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
		return "Node with streetId = " + street.getStreetId() + " streetName = " + street.getStreetName() + " x = " + getX() + ", y = " + getY() + " goal " + isGoal;
	}
	
	/**
	 * 
	 * @param currentStreet a list containing all the points in the same street
	 * @return the closest point among the points in the input list
	 */
	public Node getClosestNode(ArrayList<Node> currentStreet) {
		if (currentStreet.size() == 0) {
			System.out.println("Street is empty");
			return null;
		}
		Node closestNode = null;
		double minDistance = 1000000;
		for( Node currentNode : currentStreet) {
			if ((euclideanDistance(this, currentNode) < minDistance) && !currentNode.equals(this) && currentNode.isAvailable()){
				minDistance = euclideanDistance(this, currentNode);
				closestNode = currentNode;
			}
		}
		System.out.println(closestNode);
		if (closestNode != null) {
			closestNode.setAvailable(false);
		}
		return closestNode;
	}
	
	
	/**
	 * @return check if a node is a taxi 
	 */
	public boolean isTaxi() {
		for( Taxi taxi : Taxi.taxis) {
			if ((taxi.getX() == getX()) && (taxi.getY() == getY())) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(available, isGoal, street);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node other = (Node) obj;
		return available == other.available && isGoal == other.isGoal && Objects.equals(street, other.street);
	}

	
	
	
	
}
