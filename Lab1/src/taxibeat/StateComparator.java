package taxibeat;
/**
 * A class that implements the comparator for our priority queue based on the sum of the distance and the heuristic
 */
import java.util.Comparator;

public class StateComparator implements Comparator<State>{
	/**
	 *  Overriding compare()method of Comparator  
	 */
	public int compare(State s1, State s2) { 
		if (s1.getDistance() + s1.getHeuristic() > s2.getDistance() + s2.getHeuristic()) 
			return 1; 
		else if (s1.getDistance() + s1.getHeuristic() < s2.getDistance() + s2.getHeuristic()) 
			return -1; 
		return 1; 
	} 
}
