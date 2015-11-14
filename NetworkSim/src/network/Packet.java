/**
 * 
 */
package network;

/**
 * @author michaelAM
 *
 */
public class Packet {
    private int destination;
    private Node source;
    private String content;
    private String realPacket;
    /**
     * Packet of the Network
     */
    public Packet(int destination,String content,String realPacket) {
        // TODO Auto-generated constructor stub
        this.destination = destination;
        this.content = content;
        this.realPacket = realPacket;
    }
    
    public Packet(int destination,String realPacket) {
        // TODO Auto-generated constructor stub
        this.destination = destination;
        this.realPacket = realPacket;
        this.content = "";

    }
    
    public Packet(int destination) {
        // TODO Auto-generated constructor stub
        this.destination = destination;
        this.realPacket = "";
        this.content = "";

    }    
    public int getDestination() {
        return destination;
    }
    public void setDestination(int destination) {
        this.destination = destination;
    }
    
    
    
/*    RealPacket format:
        <destination>-//-<type>-//-<content>   
*/
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getRealPacket() {
        return realPacket;
    }
    public void setRealPacket(String realPacket) {
        this.realPacket = realPacket;
    }
    public Node getSource() {
        return source;
    }
    public void setSource(Node source) {
        this.source = source;
    }
    
    public void getPacketInfo() {
        System.out.println("Packet Info : Destination : "+getDestination()+" Content : "+getContent()+" RealPacket : "+getRealPacket());
    }

}
