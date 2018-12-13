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
        
        
	}

}
