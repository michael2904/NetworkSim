/**
 * 
 */
package networkParser;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import network.Network;
import network.Node;

/**
 * @author michaelAM
 *
 */
public class NetworkParser {

    public static Network parseJSON(String jsonlFileName) {
        Network network = new Network();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(jsonlFileName));
            JSONObject jsonObject = (JSONObject) obj;
            //Get nodes
            JSONArray nodes = (JSONArray) jsonObject.get("nodes");
            for (int i = 0; i < nodes.size(); i++) {
                JSONObject jsonNode = (JSONObject) nodes.get(i);
                int id = 0;
                String label = "";
                String country = "";
                double longitude = 0;
                double latitude = 0;
                int internal = 0;
                String type = "";

                try {
                    id = Integer.parseInt(jsonNode.get("id").toString());
                } catch (Exception e) {
                }
                try {
                    label = (String) jsonNode.get("label");
                } catch (Exception e) {
                }
                try {
                    country = (String) jsonNode.get("Country");
                } catch (Exception e) {
                }
                try {
                    longitude = Double.parseDouble(jsonNode.get("Longitude").toString());
                } catch (Exception e) {
                }
                try {
                    latitude = Double.parseDouble(jsonNode.get("Latitude").toString());
                } catch (Exception e) {
                }
                try {
                    internal = Integer.parseInt(jsonNode.get("Internal").toString());
                } catch (Exception e) {
                }
                try {
                    type = (String) jsonNode.get("type");
                } catch (Exception e) {
                }

                Node graphNode = new Node(id, label, country, longitude, latitude, internal, "");
                network.addNode(graphNode);
            }

            //Get edges
            JSONArray links = (JSONArray) jsonObject.get("links");
            for (int i = 0; i < links.size(); i++) {
                JSONObject jsonEdge = (JSONObject) links.get(i);
                int source = 0;
                int target = 0;
                String linkLabel = "";

                try {
                    source = Integer.parseInt(jsonEdge.get("source").toString());
                } catch (Exception e) {
                }
                try {
                    target = Integer.parseInt(jsonEdge.get("target").toString());
                } catch (Exception e) {
                }
                try {
                    linkLabel = (String) jsonEdge.get("LinkLabel");
                } catch (Exception e) {
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return network;
    }
    
}
