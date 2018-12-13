/**
 * A class representing a node using its longitude x, latitude y and the street id it belongs to.
*/
public class Node extends Point {
	int nodeId;

	/**
	 * @param x
	 * @param y
	 * @param nodeId
	 */
	public Node(int x, int y, int nodeId) {
		super(x, y);
		this.nodeId = nodeId;
	}

	/**
	 * @return the nodeId
	 */
	public int getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
}
