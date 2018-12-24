package taxibeat;
/**
 * The Taxibeat program implements an application finds
 * the closest available taxi given as input your geographical
 * location.
 *
 * @author Antoniadis Panagiotis
 * @author Bazotis Nikos
 * @since   2018-12-18 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class TaxiBeat {
	public static Client client;
	/**
	 * A map that for each point (x,y) it gives us all the neighboring points.
	 */
	public static HashMap<Point, ArrayList<Node>> map = new HashMap<Point, ArrayList<Node>>();
	/**
	 * The queue that keeps the states that have not yet extended.
	 */
	public static TreeSet<State> searchQueue;
	/**
	 * A set that keeps all the states that have been extended.
	 */
	public static ArrayList<Node> closedSet;
	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		/**
		 * Define the path of the csv files to read and csv separator (comma).
		 */
		String nodesFile = "./nodes.csv";
		String taxisFile = "./taxis.csv";
		String clientFile = "./client.csv";
		String line = "";
		String cvsSplitBy = ",";
		String headerLine;
		String[] fields;

		/**
		 * Read nodes.csv file and keep all nodes in a list.
		 */
		try (BufferedReader br = new BufferedReader(new FileReader(nodesFile))) {
			Street street;
			headerLine = br.readLine();
			while ((line = br.readLine()) != null) {
				/**
				 * Use comma as separator
				 */
				fields = line.split(cvsSplitBy);
				/**
				 * Check if current node provides the name of its street.
				 */
				if (fields.length == 4) {
					street = new Street(Integer.parseInt(fields[2]), fields[3]);
				}
				else {
					street = new Street(Integer.parseInt(fields[2]));
				}
				Node node = new Node(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]), street);
				Node.nodes.add(node);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * Read taxis.csv file and keep all taxis in a list.
		 */
		try (BufferedReader br = new BufferedReader(new FileReader(taxisFile))) {
			headerLine = br.readLine();
			while ((line = br.readLine()) != null) {
				/**
				 * Use comma as separator
				 */
				fields = line.split(cvsSplitBy);
				Taxi taxi = new Taxi(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]), Integer.parseInt(fields[2]));
				Taxi.taxis.add(taxi);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * Read client.csv file and create 
		 */
		try (BufferedReader br = new BufferedReader(new FileReader(clientFile))) {
			headerLine = br.readLine();
			line = br.readLine();
			/**
			 * Use comma as separator
			 */
			fields = line.split(cvsSplitBy);
			client = new Client(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * For each node add to the map its children nodes.
		 */
		map = new HashMap<Point, ArrayList<Node>>();
		int i;
		Point currentPoint;
		Street currentStreet;
		for( i = 0; i < Node.nodes.size(); i++) {
			currentPoint = new Point(Node.nodes.get(i).getX(), Node.nodes.get(i).getY());
			currentStreet = Node.nodes.get(i).getStreet();
			if (!map.containsKey(currentPoint)) {
				/**
				 * This point is new at the map so just make a new entry with its children.
				 */
				ArrayList<Node> myChildren = new ArrayList<Node>();
				if ( i > 0 && Node.nodes.get(i-1).getStreet().equals(currentStreet) ) {
					myChildren.add(Node.nodes.get(i-1));
				}
				if ( i < Node.nodes.size()-1 && Node.nodes.get(i+1).getStreet().equals(currentStreet) ) {
					myChildren.add(Node.nodes.get(i+1));
				}
				map.put(currentPoint, myChildren);
			}
			else {
				/**
				 * This point is already in the map because of a crossing.
				 */
				if ( i > 0 && Node.nodes.get(i-1).getStreet().equals(currentStreet) ) {
					map.get(currentPoint).add(Node.nodes.get(i-1));
				}
				if ( i < Node.nodes.size()-1 && Node.nodes.get(i+1).getStreet().equals(currentStreet) ) {
					map.get(currentPoint).add(Node.nodes.get(i+1));
				}
			}
		} 

		KmlWriter kmlWriter = new KmlWriter("best-route.kml");
		kmlWriter.initializeFile("taxi routes");
		kmlWriter.defineStyle("green", "ff009900", 4);
		kmlWriter.defineStyle("red", "ff0000ff", 4);

		/**
		 * For each taxi compute the shortest route
		 */
		Node clientNode = client.getClosestNode(); 
		double clientDistance = Point.haversine(client.getY(), client.getX(), clientNode.getY(), clientNode.getX());
		Node targetNode;
		Point targetPoint;
		Taxi currentTaxi;
		State startState, targetState, taxiState;
		double initialDistance;
		boolean found;
		ArrayList<State> pathTaxi;
		Iterator<State> iterator;
		boolean add;
		double childDistance;
		ArrayList<State> remove = new ArrayList<State>();
		for(i = 0; i < Taxi.taxis.size(); i++) {	
			/**
			 * Create initial state for our algorithm.
			 */
			currentTaxi = Taxi.taxis.get(i);
			initialDistance = Point.haversine(currentTaxi.getClosestNode().getY(), currentTaxi.getClosestNode().getX(), currentTaxi.getY(), currentTaxi.getX());
			taxiState = new State(currentTaxi.getX(), currentTaxi.getY(), currentTaxi.getClosestNode().getStreet(), 0, null);
			pathTaxi = new ArrayList<State>();
			pathTaxi.add(taxiState);
			startState = new State(currentTaxi.getClosestNode().getX(), currentTaxi.getClosestNode().getY(), currentTaxi.getClosestNode().getStreet(), initialDistance, pathTaxi);
			/**
			 * Define two data structures that will be used for A* algorithm.
			 */
			/**
			 * Add first state to the route.
			 */
			searchQueue = new TreeSet<State>(new StateComparator());
			closedSet = new ArrayList<Node>();
			/**
			 * The closest node to the current taxi is the root of the graph.
			 */
			searchQueue.add(startState);
			found = false;
			while(!searchQueue.isEmpty() && !found) {
				
			if (searchQueue.first().getX() == clientNode.getX() && searchQueue.first().getY() == clientNode.getY()) {
					/**
					 * We reached the node of the client so break.
					 */
					found = true;
					break;
				}
				targetState = searchQueue.pollFirst();
				targetNode = new Node(targetState.getX(), targetState.getY(), targetState.getStreet());
				targetPoint = new Point(targetState.getX(), targetState.getY());
				if (closedSet.contains(targetNode)) {
					continue;
				}

				for (Node child : map.get(targetPoint)) {
					if(!closedSet.contains(child)) {
						add = true;
						remove.clear();
						childDistance = Point.haversine(targetState.getY(), targetState.getX(), child.getY(), child.getX());
						ArrayList<State> path = new ArrayList<State>();
						path.addAll(targetState.getPath());
						path.add(targetState);
						iterator = searchQueue.iterator();
						while (iterator.hasNext()) {
							State curr = null;
							curr = iterator.next();
							if (curr.getX() == child.getX() && curr.getY() == child.getY()) {
								if (curr.getDistance() > Point.round(targetState.getDistance() + childDistance, 3)) {
									remove.add(curr);
								}
								if (curr.getDistance() < Point.round(targetState.getDistance() + childDistance, 3)) {
									add = false;
								}
							
							}
						}
						if (add) {
							searchQueue.removeAll(remove);
							searchQueue.add(new State(child.getX(), child.getY(), child.getStreet(), Point.round(targetState.getDistance() + childDistance, 3), path));
						}
						
					}
				
				}
				
				iterator = searchQueue.iterator();
				boolean exists = false;
				ArrayList<State> prev = new ArrayList<State>();
				while (iterator.hasNext()) {
					State curr = iterator.next();
					if (curr.getX() == targetState.getX() && curr.getY() == targetState.getY()) {
						exists = true;
					}
				}
				if (!exists) {
					closedSet.add(targetNode); 
				}

			}
			

			State finalState = searchQueue.pollFirst();
			if (found) {
				System.out.println("For taxi " + i + " all went OK" + "\n");
			}
			else {
				System.out.println("Couldn't reach client from taxi " + i + "\n");
			}
			/**
			 * Add taxi to the route.
			 */
			Route.routes.add(new Route(finalState.getPath(), Point.round(finalState.getDistance() + clientDistance,3), currentTaxi));	
			State alternativeState;
			while(!searchQueue.isEmpty() && finalState.getDistance() + finalState.getHeuristic() == searchQueue.first().getDistance() + searchQueue.first().getHeuristic()) {
				alternativeState = searchQueue.pollFirst();
				if (alternativeState.getX() == finalState.getX() && alternativeState.getY() == finalState.getY()) {
					Route.routes.add(new Route(alternativeState.getPath(), Point.round(alternativeState.getDistance() + clientDistance,3), currentTaxi));	
				}
			}
		
		}
		/**
		 * Uncomment it if you want just the best path.
		 */
		//kmlWriter.addBest();
		kmlWriter.addRoutes();
		kmlWriter.closeFile();
		for(Route route : Route.routes) {
			System.out.println(route.getTaxi().getTaxiId() + " costs " + route.getCost() + "\n");
		}
	
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
	}
}

