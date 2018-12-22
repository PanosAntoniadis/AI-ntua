package taxibeat;

import java.util.ArrayList;

public class Route {
	ArrayList<State> route = new ArrayList<State>();
	double cost;
	Taxi taxi;
	
	
	public Route(ArrayList<State> route, double cost, Taxi taxi) {
		this.route = route;
		this.cost = cost;
		this.taxi = taxi;
	}
	
	
}
