/**
 * 
 */
package network;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


/**
 * @author michaelAM
 *
 */

public class Node {
    private int address;
    private BigInteger key;
    private String name;
    private ArrayList<Node> connectedNodes;
    private Network network;
    private Node pathParent;
    private String label = "";
    private String country = "";
    private double longitude = 0;
    private double latitude = 0;
    private int internal = 0;
    private String type = "";

    /**
     * Nodes of the Network
     */
    public Node(Network network,int address) {
        // TODO Auto-generated constructor stub
        this.network = network;
        this.address=address;
        this.key = null;
        connectedNodes = new ArrayList<Node>();
    }
    public Node() {
    }

    public Node(int address, String label, String country, double longitude, double latitude, int internal, String type) {
        this.address = address;
        this.label = label;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.internal = internal;
        this.type = type;
    }
    
    public void printNode() {
        System.out.println("Address: " + address + "\nLabel: " + label + "\nCountry: " + country + "\nLongitude: " + longitude + "\nLatitude: " + latitude + "\nInternal: " + internal + "\nType: " + type + "\n");
    }
    
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public int getInternal() {
        return internal;
    }
    public void setInternal(int internal) {
        this.internal = internal;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
    
    private boolean receivePacket(Packet packet,int address){
        boolean found = false;
        if(address == this.address){
            System.out.println("Node "+this.getAddress()+": Packet received with data : "+packet.getData());
            String packetData =packet.getData();
            if(packetData.contains("-//-")){
                String[] packetInfo = packet.getData().split("-//-data:");
                int newDestination = Integer.parseInt(packetInfo[0].replace("destination:",""));
                String data = packet.getData().replace(packetInfo[0]+"-//-data:", "");
                packet.setData(data);
                packet.setDestination(newDestination);
                found = sendPacket(packet,newDestination);
            }else{
                System.out.println("No more destinations. The data is :"+packet.getData());
                found = true;
            }
        }else{
            System.out.println("Node "+this.address+" is not the destination, looking for : "+address+" Wrong path");
        }
        return found;
    }
            
    public boolean sendPacket(Packet packet,int address){
        boolean found = false;
        if(packet.getDestination() == this.address){
            System.out.println("Packet is already here");
            found = true;
        }else{
            if(isConnectedToNode(address)){
                if(address==packet.getDestination()){
                    System.out.println("Node "+this.getAddress()+": sending packet to connected destination Node "+address);
                    found = getConnectedNode(address).receivePacket(packet,address);
                }else{
                   System.out.println("The address "+address+" does not correspond to the destination of the packet "+packet.getDestination());
                }
            }else{
                System.out.println("The Node "+address+" is not connected. There is an edge missing between the current Node "+this.getAddress()+" and "+address);
            }
        }
        return found;
    }
    
    public boolean sendPacket(Packet packet){
        boolean found = false;
        found = sendPacket(packet,packet.getDestination());
        return found;
    }
    
    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public BigInteger getKey() {
        return key;
    }

    public void setKey(BigInteger key) {
        this.key = key;
    }
    
    public void genereateKey() {
        Random rnd = new Random();
        BigInteger key = new BigInteger(53,1000,rnd);
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Node> getConnectedNodes() {
        return connectedNodes;
    }
    
    public ArrayList<Integer> getConnectedNodesAddresses() {
        ArrayList<Integer> connectedNodesAddresses = new ArrayList<Integer>();
        for(Node networkNode : connectedNodes){
            connectedNodesAddresses.add(networkNode.getAddress());
        }
        return connectedNodesAddresses;
    }

    public void setConnectedNodes(ArrayList<Node> connectedNodes) {
        this.connectedNodes = connectedNodes;
    }
    
    public void addConnectedNodes(Node connectedNode) {
        this.connectedNodes.add(connectedNode);
    }
    
    public boolean isConnectedToNode(int address){
        for(Node networkNode : connectedNodes){
            if(networkNode.getAddress() == address){
                return true;
            }
        }
        return false;
    }
    
    public boolean isConnectedToNode(Node node){
        if(connectedNodes.contains(node)){
            return true;
        }else{
            return false;
        }
    }
    
    public Node getConnectedNode(int address){
        for(Node networkNode : connectedNodes){
            if(networkNode.getAddress() == address){
                return networkNode;
            }
        }
        return null;
    }

    public void getInfo() {
        // TODO Auto-generated method stub
        System.out.println("INFO : "+address+ " connected node " + getConnectedNodesAddresses());
        
    }
    
    public ArrayList<Node> findPath(Packet packet){
        System.out.println("findPath : ");
        ArrayList<Node> path = new ArrayList<Node>();
        path = this.network.search(this,this.network.getNode(packet.getDestination()));
        path.add(0,this);
        return path;
    }

    public Node getPathParent() {
        return pathParent;
    }

    public void setPathParent(Node pathParent) {
        this.pathParent = pathParent;
    }
    

}
