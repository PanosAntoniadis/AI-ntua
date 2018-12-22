package taxibeat;

import java.util.ArrayList;
import java.util.TreeSet;

public class Route {
	private ArrayList<State> route = new ArrayList<State>();
	private double cost;
	private Taxi taxi;
	public static TreeSet<Route> routes = new TreeSet<Route>(new RouteComparator());
	
	public Route(ArrayList<State> route, double cost, Taxi taxi) {
		this.route = route;
		this.cost = cost;
		this.taxi = taxi;
	}


	/**
	 * @return the route
	 */
	public ArrayList<State> getRoute() {
		return route;
	}


	/**
	 * @param route the route to set
	 */
	public void setRoute(ArrayList<State> route) {
		this.route = route;
	}


	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}


	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}


	/**
	 * @return the taxi
	 */
	public Taxi getTaxi() {
		return taxi;
	}


	/**
	 * @param taxi the taxi to set
	 */
	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}
	
	
	
	
}
