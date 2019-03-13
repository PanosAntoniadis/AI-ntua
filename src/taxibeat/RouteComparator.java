package taxibeat;
/**
 * A class that implements the comparator for our tree set based on the cost of each route.
 */
import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {
	/**
	 *  Overriding compare()method of Comparator  
	 */
	public int compare(Route r1, Route r2) { 
		if (r1.getCost() > r2.getCost()) {
			return 1;
		}
		else if (r1.getCost() < r2.getCost()) {
			return -1;
		}
		return 1;
	}
}
