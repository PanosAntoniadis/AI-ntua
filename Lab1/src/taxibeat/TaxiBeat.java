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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.*;

public class TaxiBeat {

	public static void main(String[] args) {
		/**
		 * Define the path of the csv files to read and csv separator (comma).
		 */
		String nodesFile = "./node_test.csv";
		String taxisFile = "./taxis1.csv";
		String clientFile = "./client.csv";
        String line = "";
        String cvsSplitBy = ",";
        String headerLine;
        String[] fields;
        /**
         * Read nodes.csv file and keep all nodes in a list.
         */
        try (BufferedReader br = new BufferedReader(new FileReader(nodesFile))) {
        	headerLine = br.readLine();
            while ((line = br.readLine()) != null) {
                /**
                 * Use comma as separator
                 */
                fields = line.split(cvsSplitBy);
                Street street;
                /**
                 * Check if current node provides the name of its street.
                 */
                if (fields.length == 4) {
                	street = new Street(Integer.parseInt(fields[2]), fields[3]);
                }
                else {
                	street = new Street(Integer.parseInt(fields[2]));
                }
                Node node = new Node(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]), street, false);
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
        Client client = null;
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
         * Add all taxis as nodes with isGoal true
         */
        for (Taxi taxi : Taxi.taxis) {
        	Node.nodes.get(Node.nodes.indexOf(taxi.getClosestNode())).setGoal(true);
        	System.out.println("Node of taxi");
        	System.out.println(Node.nodes.get(Node.nodes.indexOf(taxi.getClosestNode())));
        }
       
        /**
         * Compute streetNodes hashmap that contains the nodes that exist in each road.
         */
        ArrayList<Node> currentListOfNodes = null;
        Street prevStreet = null;
        Node currentNode = null;
        for (int i = 0; i < Node.nodes.size(); i++) {
        	currentNode = Node.nodes.get(i);
        	if (!currentNode.getStreet().equals(prevStreet)){
        		if (prevStreet != null) {
        			Street.streetNodes.put(prevStreet, currentListOfNodes);
        		}
        		currentListOfNodes = new ArrayList<Node>(); 
        		currentListOfNodes.add(currentNode);
        		prevStreet = currentNode.getStreet();
        	}
        	else {
        		currentListOfNodes.add(currentNode);
        	}
        }
        Street.streetNodes.put(currentNode.getStreet(), currentListOfNodes);
      
        
       
        
        /**
         * Compute pointCrossings that contains all the Crossings of the map.
         */
        for (int i = 0; i < Node.nodes.size(); i++) {
        	if (Node.nodes.get(i).getX() == 23.734351 && Node.nodes.get(i).getY() == 37.9761805) {
        		System.out.println(Node.nodes.get(i).getStreet().getStreetName());
        	}
        	currentNode = Node.nodes.get(i);
        	int count = 1;
        	if (!Street.pointCrossings.containsKey(currentNode)){
        		ArrayList<Street> streets = new ArrayList<Street>();
        		streets.add(currentNode.getStreet());
        		for (int j = i+1; j < Node.nodes.size(); j++) {
        			if ((currentNode.getX() == Node.nodes.get(j).getX()) && (currentNode.getY() == Node.nodes.get(j).getY())){
                		streets.add(Node.nodes.get(j).getStreet());
                		count ++;
        			}
        		}
        		if (count >= 2) {
        			Point currentPoint = new Point(currentNode.getX(), currentNode.getY());
        			Street.pointCrossings.put(currentPoint, streets);
        		}
        	}
        }
        
       
        
      
        /**
         * Create initial state for our algorithm.
         */
        State startState = new State(client.getClosestNode().getX(), client.getClosestNode().getY(), client.getClosestNode().getStreet(), Point.euclideanDistance(client, client.getClosestNode()), false, null);
        System.out.println("My initial state is " + startState.toString());
        /**
         * Define two data structures that will be used for A* algorithm.
         */
        PriorityQueue<State> searchQueue = new PriorityQueue<State>(new StateComparator());
        HashSet<Point> closedSet = new HashSet<Point>();
        
        searchQueue.add(startState);
        boolean found = false;
        int cnt = 0;
        while(!searchQueue.isEmpty() && !found) {
        	cnt ++;
        	/*
        	System.out.println(searchQueue.peek());
        	
        	System.out.println("BEFORE REMOVING THE FIRST");
        	System.out.println("searchQueue contains ");
        	System.out.println(searchQueue.toString());
        	System.out.println("closedSet contains ");
        	System.out.println(closedSet.toString());
        	*/
        	if (searchQueue.peek().isGoal()) {
        		found = true;
        		break;
        	}
        	State targetState = searchQueue.poll();        	
        	targetState.setChildren();
        	
        	//System.out.println("Target state has following childre:");
        	//System.out.println(targetState.getMyChildren());
        	
        	//System.out.println(targetState);
        	if (targetState.getMyChildren() == null) {
        		closedSet.add(new Point(targetState.getX(), targetState.getY()));
        		continue;
        	}
        	//System.out.println("Adding children to seqrchQueue");
        	for (State child : targetState.getMyChildren()) {
        		if (!closedSet.contains(new Point(child.getX(), child.getY()))) {
        			child.setHeuristic(child.computeHeuristic());
        			searchQueue.add(child);
        			//System.out.println("Added " + child.getStreet());
        		}
        	}
        	closedSet.add(new Point(targetState.getX(), targetState.getY()));
        	/*
        	System.out.println("End of the loop with ");
        	System.out.println("searchQueue");
        	System.out.println(searchQueue.toString());
        	System.out.println("closedSet");
        	System.out.println(closedSet.toString());
        	*/
        }
        
        //System.out.println(searchQueue);
        //System.out.println(closedSet);
        System.out.println(found);
       
        
        State finalState = searchQueue.poll();
        System.out.println("X     Y");
        System.out.println(finalState.getX() + "     " + finalState.getY());
        State prevState = finalState.getPrevious();
        while(prevState != null) {
        	System.out.println(finalState.getX() + "     " + finalState.getY());
        	//System.out.println(prevState);
        	prevState = prevState.getPrevious();
        }
        
      
       
	}

}
