package taxibeat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * A class representing the writer to the kml file.
 */
public class KmlWriter {
	private final String kmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<kml xmlns=\"http://earth.google.com/kml/2.1\">\n";
    private final String kmlEnd = "</kml>";
    private BufferedWriter writer;
    
	public KmlWriter(String fileToWrite) throws IOException {
		this.writer = new BufferedWriter(new FileWriter(fileToWrite));
	}
    
    void defineStyle(String colourName, String colourCode, int width) throws IOException {
    	writer.write("<Style id=\"" + colourName + "\">\n" + "<LineStyle>\n" + "<color>" + colourCode + "</color>\n" +
				"<width>" + width + "</width>\n" + "</LineStyle>\n" + "</Style>\n");
    }

    void initializeFile(String name) throws IOException {
    	writer.write(kmlStart);
        writer.write("<Document>\n");
        writer.write("<name>" + name + "</name>\n");
    }
    
    void closeFile() throws IOException {
    	writer.write("</Document>\n");
    	writer.write(kmlEnd);
		writer.close();
    }
    
    void addRoutes() throws IOException {
    	int cnt = 1;
    	for (Route route : Route.routes) {
    		if (cnt == 1) {
    			writer.write("<Placemark>\n" +"<name>Taxi " + cnt + "</name>\n" + " <styleUrl>#green</styleUrl>\n" + "<LineString>\n" +
    					"<altitudeMode>relative</altitudeMode>\n" + "<coordinates>\n"); 
    		}
    		else {
    		writer.write("<Placemark>\n" +"<name>Taxi " + cnt + "</name>\n" + " <styleUrl>#red</styleUrl>\n" + "<LineString>\n" +
					"<altitudeMode>relative</altitudeMode>\n" + "<coordinates>\n");
    		}
    		for(State state : route.getRoute()) {
    			writer.write(state.getX() + "," + state.getY() + ",0\n");
        	}
			writer.write("</coordinates>\n" + "</LineString>\n" + "</Placemark>\n");
			cnt++;
    	}
    }
}
