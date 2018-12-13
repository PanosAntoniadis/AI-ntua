/**
 * A class representing a client in the map using its longitude x, its latitude y and the
 * street that is closest to him.
*/
public class Client extends Point {
	Street clientStreet;

	/**
	 * @param x
	 * @param y
	 */
	public Client(double x, double y) {
		super(x, y);
		this.clientStreet = closestStreet(x, y);
	}

	/**
	 * @return the clientStreet
	 */
	public Street getClientStreet() {
		return clientStreet;
	}

	/**
	 * @param clientStreet the clientStreet to set
	 */
	public void setClientStreet(Street clientStreet) {
		this.clientStreet = clientStreet;
	}

	@Override
	public String toString() {
		return "Client with streetId=" + clientStreet.streetId + " streetName " + clientStreet.streetName + " x= " + x + " y= " + y + "]";
	}
	
	Street closestStreet(double x, double y) {
		Street minStreet = null;
		double minDistance = 1000000000;
		for (Node currentNode : Node.nodes) {
			if (Point.euclideanDistance(this, currentNode) < minDistance) {
				minStreet = currentNode.getStreet();
			}
		}
		if (minStreet != null) {
			return minStreet;
		}
		else {
			System.out.println("Error in finding closest street to client");
			return null;
		}
	}
	
	
	
	
}
