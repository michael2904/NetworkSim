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
    
    private boolean receivePacketUsingContent(Packet packet,int address){
        boolean found = false;
        System.out.println("Node "+this.address+" receivePacketUsingContent with address///"+packet.getDestination() +"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket()+"/// and address:"+address);
        if(address == this.address){
            System.out.println("Packet Arrived");
            found = true;
            if(address!=packet.getDestination()){
                System.out.println("Actually not arrived new destination not "+address+"  != "+packet.getDestination());
                //found = sendPacketToAddressUsingContent(packet,packet.getDestination());
                if(packet.getDestination() != packet.getSource().getAddress()){
                    found = sendPacketUsingContent(packet,packet.getDestination());
                }
            }else{
                System.out.println( packet.getRealPacket());
                if(packet.getRealPacket().contains("-//-")){
                    String[] realPacketVals = packet.getRealPacket().split("-//-");
                    int PDestination = Integer.parseInt(realPacketVals[0]);
                    Boolean encoded = Boolean.valueOf(realPacketVals[1]);
                    String content = realPacketVals[2];
                    packet.setDestination(PDestination);
                    System.out.println(PDestination+"-/-"+encoded+"-/-"+content);

                    if(encoded){
                        packet.setContent(content);
                        //decode with key
                        packet.setRealPacket(content);
                    }else{
                        packet.setContent(content);
                        //decode with key
                        packet.setRealPacket(content);
                    }
                    if(this.address == PDestination){
                        System.out.println("Packet is already at the destination");
                    }else{
                        found = sendPacketUsingContent(packet,PDestination);
                    }
                }else{
                    System.out.println("Packet Arrived at final destination ");
                }
            }
            
            
        }else{
            System.out.println("Node "+this.address+" is not the destination, looking for : "+address);
            for(int i =0;found == false && i<connectedNodes.size();i++){
                Node node = connectedNodes.get(i);
                System.out.println("Connected node is  "+node.getAddress()+" and source is  :"+packet.getSource().getAddress()+"  "+i);
                if(node.getAddress() != packet.getSource().getAddress()){
                    found = sendPacketUsingContent(packet,node.getAddress());
                }
               
            }
            if(!found){
                System.out.println("Node : "+packet.getSource().getAddress()+"No connected node, back at  :"+packet.getSource().getAddress());
            }
        }
        return found;
    }
    
    public void receivePacketWithPathUsingContent(Packet packet,ArrayList<Node> path){
        System.out.println("Node "+this.address+" received Packet with a path"+packet.getDestination() +"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket());
        if(packet.getDestination() == this.address){
            System.out.println("Packet Arrived");
            System.out.println( packet.getRealPacket());
            if(packet.getRealPacket().contains("-//-")){
                System.out.println("----- Decoding -----");

                String[] realPacketVals = packet.getRealPacket().split("-//-");
                int PDestination = Integer.parseInt(realPacketVals[0]);
                Boolean encoded = Boolean.valueOf(realPacketVals[1]);
                String content = realPacketVals[2];
                packet.setDestination(PDestination);
                System.out.println(PDestination+"-/-"+encoded+"-/-"+content);

                if(encoded){
                    //packet.setDestination(newDest)
                    packet.setContent(content);
                    //decode with key
                    packet.setRealPacket(content);
                }else{
                    packet.setContent(content);
                    //decode with key
                    packet.setRealPacket(content);
                }
                if(this.address == PDestination){
                    System.out.println("Packet is already at the destination");
                }else{
                    sendPacketUsingContent(packet,PDestination);
                }
            }else{
                System.out.println("Packet Arrived at final destination ");
            }
        
        }else{
            System.out.println("Node "+this.address+" is not the destination, looking for : "+packet.getDestination());
            Node node = connectedNodes.get(0);
            System.out.println("Connected node is  "+node.getAddress());
            sendPacketWithPathUsingContent(packet,path);
        }
    }
    
    private boolean sendPacketUsingContent(Packet packet,int address){
        packet.setSource(this);
        boolean found = false;
        System.out.println("Node "+this.address+" sendPacketUsingContent "+packet.getDestination()+"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket()+" with address "+address);
        if(packet.getDestination() == this.address){
            System.out.println("Packet is already here");
            found = true;
        }else{
            if(isConnectedToNode(address)){
                System.out.println("Sending packet to connected destination Node "+packet.getDestination());
                found = getConnectedNode(address).receivePacketUsingContent(packet,packet.getDestination());
            }else{
                System.out.println("Sending packet to connected intermediate Node ");
                int i =0;
                while(found == false && i<connectedNodes.size()){
                    found = connectedNodes.get(i).receivePacketUsingContent(packet,packet.getDestination());
                    i++;
                }
            }
        }
        return found;
    }
    
    public boolean sendPacketToAddressUsingContent(Packet packet,int address){
        packet.setSource(this);
        boolean found = false;
        System.out.println("Node "+this.address+" sendPacketToAddressUsingContent "+packet.getDestination()+"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket()+" with address "+address);
        if(packet.getDestination() == this.address){
            System.out.println("Packet is already here");
            found = true;
        }else{
            if(isConnectedToNode(address)){
                if(address==packet.getDestination()){
                    System.out.println("Sending packet to connected destination Node "+packet.getDestination());
                    found = getConnectedNode(address).receivePacketUsingContent(packet,packet.getDestination());
                }else{
                   System.out.println("Sending packet to connected destination Node "+address);
                   found = getConnectedNode(address).receivePacketUsingContent(packet,address); 
                }
            }else{
                System.out.println("The Node "+address+" is not connected. There is an edge missing between the current Node "+this.getAddress()+" and "+address);
            }
        }
        return found;
    }
    
    public void sendPacketWithPathUsingContent(Packet packet,ArrayList<Node> path){
        System.out.println("Node "+this.address+" sendPacketWithPathUsingContent "+packet.getDestination()+"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket());
        if(packet.getDestination() == this.address && path.get(0) == this){
            System.out.println("Packet is already here");
        }else{
            if(isConnectedToNode(address)){
                System.out.println("Sending packet to connected destination Node "+address);
                getConnectedNode(address).receivePacketWithPathUsingContent(packet,path);
            }else{
                System.out.println("Sending packet to connected intermediate Node ");
                path.remove(0);
                if(isConnectedToNode(path.get(0))){
                    path.get(0).receivePacketWithPathUsingContent(packet,path);

                }else{
                    System.out.print("The path : ");
                    for(Node node : path){
                        System.out.print(node.getAddress()+"-");
                    }
                    System.out.println(" is invalid. There is an edge missing between the current Node "+this.getAddress()+" and "+path.get(0).getAddress());

                }
            }
        }
    }
    
    private boolean receivePacket(Packet packet,int address){
        boolean found = false;
        System.out.println("Node "+this.address+" received Packet with address///"+packet.getDestination() +"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket()+"/// and address:"+address);
        if(address == this.address){
            System.out.println("Packet Arrived");
            found = true;
        }else{
            System.out.println("Node "+this.address+" is not the destination, looking for : "+address);
            for(int i =0;found == false && i<connectedNodes.size();i++){
                Node node = connectedNodes.get(i);
                System.out.println("Connected node is  "+node.getAddress()+" and source is  :"+packet.getSource().getAddress());
                if(node.getAddress() != packet.getSource().getAddress()){
                    found = sendPacket(packet,node.getAddress());
                }
               
            }
            if(!found){
                System.out.println("Node : "+packet.getSource().getAddress()+"No connected node, back at  :"+packet.getSource().getAddress());
            }
        }
        return found;
    }
    
    public void receivePacketWithPath(Packet packet,ArrayList<Node> path){
        System.out.println("Node "+this.address+" received Packet with a path"+packet.getDestination() +"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket());
        if(packet.getDestination() == this.address){
            System.out.println("Packet Arrived");
        }else{
            System.out.println("Node "+this.address+" is not the destination, looking for : "+packet.getDestination());
            Node node = connectedNodes.get(0);
            System.out.println("Connected node is  "+node.getAddress());
            sendPacketWithPath(packet,path);
        }
    }
    
    private boolean sendPacket(Packet packet,int address){
        packet.setSource(this);
        boolean found = false;
        System.out.println("Node "+this.address+" sendPacket "+packet.getDestination()+"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket()+" with address "+address);
        if(packet.getDestination() == this.address){
            System.out.println("Packet is already here");
            found = true;
        }else{
            if(isConnectedToNode(address)){
                System.out.println("Sending packet to connected destination Node "+packet.getDestination());
                found = getConnectedNode(address).receivePacket(packet,packet.getDestination());
            }else{
                System.out.println("Sending packet to connected intermediate Node ");
                int i =0;
                while(found == false && i<connectedNodes.size()){
                    found = connectedNodes.get(i).receivePacket(packet,packet.getDestination());
                    i++;
                }
            }
        }
        return found;
    }
    
    public boolean sendPacketToAddress(Packet packet,int address){
        packet.setSource(this);
        boolean found = false;
        System.out.println("Node "+this.address+" sendPacket "+packet.getDestination()+"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket()+" with address "+address);
        if(packet.getDestination() == this.address){
            System.out.println("Packet is already here");
            found = true;
        }else{
            if(isConnectedToNode(address)){
                System.out.println("Sending packet to connected destination Node "+packet.getDestination());
                found = getConnectedNode(address).receivePacket(packet,packet.getDestination());
            }else{
                System.out.println("The Node "+address+" is not connected. There is an edge missing between the current Node "+this.getAddress()+" and "+address);
            }
        }
        return found;
    }
    
    public void sendPacketWithPath(Packet packet,ArrayList<Node> path){
        System.out.println("Node "+this.address+" sendPacket with path "+packet.getDestination()+"-/-"+ packet.getContent() +"-/-"+ packet.getRealPacket());
        if(packet.getDestination() == this.address && path.get(0) == this){
            System.out.println("Packet is already here");
        }else{
            if(isConnectedToNode(address)){
                System.out.println("Sending packet to connected destination Node "+address);
                getConnectedNode(address).receivePacketWithPath(packet,path);
            }else{
                System.out.println("Sending packet to connected intermediate Node ");
                path.remove(0);
                if(isConnectedToNode(path.get(0))){
                    path.get(0).receivePacketWithPath(packet,path);

                }else{
                    System.out.print("The path : ");
                    for(Node node : path){
                        System.out.print(node.getAddress()+"-");
                    }
                    System.out.println(" is invalid. There is an edge missing between the current Node "+this.getAddress()+" and "+path.get(0).getAddress());

                }
            }
        }
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
    
    public boolean sendPacket(Packet packet){
        return sendPacket(packet,packet.getDestination());
    }
    
    public boolean sendPacketUsingContent(Packet packet){
        return sendPacketUsingContent(packet,packet.getDestination());
    }
    
    public void sendPacketWithShortestPath(Packet packet){
        sendPacketWithPath(packet, findPath(packet));
    }
    
    public void sendPacketWithShortestPathUsingContent(Packet packet){
        sendPacketWithPathUsingContent(packet, findPath(packet));
    }

    public Node getPathParent() {
        return pathParent;
    }

    public void setPathParent(Node pathParent) {
        this.pathParent = pathParent;
    }
    

}
