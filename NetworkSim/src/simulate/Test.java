/**
 * 
 */
package simulate;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import network.Network;
import network.Packet;

/**
 * @author michaelAM
 *
 */
public class Test {

    /**
     * @param args
     * we suppose that the graph is connected
     */

    
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        int[][] nodeMapArr = {{1,2},{0,3},{2,3},{4,5},{5,2},{3,4},{4,0},{0,2}};
        ArrayList<int[]> nodeMap = convertArrToArrListInt(nodeMapArr);
        
        
        Network network = new Network(nodeMap);
        System.out.println(" ****** new NETWORK *****");

        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(0);
        path.add(2);
        path.add(3);
        Packet packetNewContent = new Packet(path,"HELLo Bitches");
        System.out.println(" ****** new Packet with valid Path *****");
        packetNewContent.getPacketInfo();
        System.out.println(" ****** sending Packet with valid Path*****");
        boolean found = network.getNode(4).sendPacket(packetNewContent);
        System.out.println(" ****** return Packet with valid Path ***** "+found+"\n\n");
        
        ArrayList<Integer> invalidpath = new ArrayList<Integer>();
        invalidpath.add(0);
        invalidpath.add(5);
        invalidpath.add(2);
        Packet packetInvalidPath = new Packet(invalidpath,"HELLo Bitches wrong path");
        System.out.println(" ****** new Packet with invalid Path*****");
        packetInvalidPath.getPacketInfo();
        System.out.println(" ****** sending Packet with invalid Path*****");
        boolean found2 = network.getNode(4).sendPacket(packetInvalidPath);
        System.out.println(" ****** return Packet with invalid Path ***** "+found2);
    }
    
    public void printByteArr(byte[] arr){
        System.out.print("arr:  ");
        for (int i=0; i<arr.length; i++)
          System.out.print(new Integer(arr[i])+" ");
        System.out.println("");
    }
        
    public static ArrayList<int[]> convertArrToArrListInt (int[][] arr){
        ArrayList<int[]> arrlist = new ArrayList<int[]>();
        for(int[] arrInput : arr){
            arrlist.add(arrInput);
        }
        return arrlist;
    }
    
}
