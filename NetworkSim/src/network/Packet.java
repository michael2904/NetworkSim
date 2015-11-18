/**
 * 
 */
package network;

import java.util.ArrayList;

/**
 * @author michaelAM
 *
 */
public class Packet {
    private int destination;
    private String data;
    /**
     * Packet of the Network
     */
    public Packet(int destination,String data) {
        // TODO Auto-generated constructor stub
        this.destination = destination;
        this.data = data;
    }
    
    public Packet(int destination) {
        // TODO Auto-generated constructor stub
        this.destination = destination;
        this.data = "";
    }   
    
    public Packet(ArrayList<Integer> nodesOnPath,String dataToSend) {
        // TODO Auto-generated constructor stub
        this.destination = nodesOnPath.get(0);
        this.data="";
        Integer add = 0;
        for(int i=1;i<nodesOnPath.size();i++){
            add = nodesOnPath.get(i);
            this.data+="destination:"+add+"-//-data:";
        }
        this.data+=dataToSend;
        System.out.println("This is the data in the packet :\n"+data+"\nEND");     
    }   
    
    public int getDestination() {
        return destination;
    }
    public void setDestination(int destination) {
        this.destination = destination;
    }
    
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    
    public void getPacketInfo() {
        System.out.println("Packet Info : Destination : "+getDestination()+" data : "+getData());
    }

}
