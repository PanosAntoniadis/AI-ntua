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

public class TaxiBeat {

	public static void main(String[] args) {
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
         * Read nodes.csv file and keep all nodes in an arraylist.
         */
        try (BufferedReader br = new BufferedReader(new FileReader(nodesFile))) {
        	headerLine = br.readLine();
            while ((line = br.readLine()) != null) {
                /**
                 * Use comma as separator
                 */
                fields = line.split(cvsSplitBy);
                Street street;
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
         * Read taxis.csv file and keep all taxis in an arraylist.
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
        try (BufferedReader br = new BufferedReader(new FileReader(taxisFile))) {
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
        
        /**=
        for (Node currentNode : Node.nodes) {
        	System.out.println(currentNode.toString());
        }
        
        for (Taxi currentTaxi : Taxi.taxis) {
        	System.out.println(currentTaxi.toString());
        }
        
		System.out.println(client.toString());
		*/
        
        /**
         * 
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
        for (Street street : Street.streetNodes.keySet()){
            String key = street.toString();
            System.out.println("Key: " + key);
            System.out.println("Value");
            for (Node node : Street.streetNodes.get(street)) {  
            	System.out.println(node);  
            }
        }
        */
        
        for (int i = 0; i < Node.nodes.size(); i++) {
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
        			Street.pointCrossings.put(currentNode, streets);
        		}
        	}
        }
        /**
        for (Point point : Street.pointCrossings.keySet()){
            String key = point.toString();
            System.out.println("Key: " + key);
            System.out.println("Value");
            for (Street street : Street.pointCrossings.get(point)) {  
            	System.out.println(street);  
            }
        }
        */
        System.out.println(Street.pointCrossings.size());
        System.out.println(Street.streetNodes.size());
        
	}

}
