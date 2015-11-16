/**
 * 
 */
package network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author michaelAM
 *
 */
public class Network {
    
    /**
     * Network
     */
    private ArrayList<int[]> nodeMap = new ArrayList<int[]>();
    private ArrayList<Node> networkNodes = new ArrayList<Node>();
    
    public Network(ArrayList<int[]> nodeMap) {
        // TODO Auto-generated constructor stub
        this.nodeMap=nodeMap;
        Node newNodeSource = null;
        Node newNodeDestination = null;
        for(int[] pairs : nodeMap){
            newNodeSource = getNode(pairs[0]);
            newNodeDestination = getNode(pairs[1]);
            newNodeSource.addConnectedNodes(newNodeDestination);
            newNodeDestination.addConnectedNodes(newNodeSource);
        }
        for(Node node : networkNodes){
            node.getInfo();
        }

    }
    
    public Network() {
    }
    
    public ArrayList<int[]> getNodeMap() {
        return nodeMap;
    }
    public void setNodeMap(ArrayList<int[]> nodeMap) {
        this.nodeMap = nodeMap;
    }
    
    public boolean containsNode(ArrayList<Node> networkNodes, Node node){
        for(Node networkNode : networkNodes){
            if(networkNode.getAddress() == node.getAddress()){
                return true;
            }
        }
        return false;
    }
    
    public boolean containsAddress(int address){
        for(Node networkNode : networkNodes){
            if(networkNode.getAddress() == address){
                return true;
            }
        }
        return false;
    }
    public Node getOldNode(int address){
        for(Node networkNode : networkNodes){
            if(networkNode.getAddress() == address){
                return networkNode;
            }
        }
        return null;
    }
    
    public Node getNode(int address){
        Node node = null;
        if(!containsAddress(address)){
            //System.out.println("new Node "+address);
            node = new Node(this,address);
            networkNodes.add(node);
        }else{
            //System.out.println("old Node "+address);
            node = getOldNode(address);
        }
        return node;
    }
    
    protected ArrayList<Node> constructPath(Node node) {
        ArrayList<Node> path = new ArrayList<Node>();
        while (node.getPathParent() != null) {
          path.add(node);
          node = node.getPathParent();
        }
        Collections.reverse(path);
        return path;
      }
    
    public ArrayList<Node> search(Node startNode, Node goalNode) {
        // list of visited nodes
        ArrayList<Node> closedList = new ArrayList<Node>();
        
        // list of nodes to visit (sorted)
        ArrayList<Node> openList = new ArrayList<Node>();
        openList.add(startNode);
        startNode.setPathParent(null);
        
        while (!openList.isEmpty()) {
          Node node = (Node)openList.remove(0);
          if (node == goalNode) {
            // path found!
            return constructPath(goalNode);
          }
          else {
            closedList.add(node);
            
            // add neighbors to the open list
            Iterator<Node> i = node.getConnectedNodes().iterator();
            while (i.hasNext()) {
              Node neighborNode = (Node)i.next();
              if (!closedList.contains(neighborNode) &&
                !openList.contains(neighborNode)) 
              {
                neighborNode.setPathParent(node);
                openList.add(neighborNode);
              }
            }
          }
        }
        
        // no path found
        return null;
      }

    public void addNode(Node newNode) {
        // TODO Auto-generated method stub
        networkNodes.add(newNode);
        int newNodeAddress = newNode.getAddress();
        for(Node conNode : newNode.getConnectedNodes()){
            int conAddress = conNode.getAddress();
            int[] edge = {newNodeAddress,conAddress};
            nodeMap.add(edge);
        }
    }
    
    public void addEdge(int[] edge) {
        // TODO Auto-generated method stub
        if(edge.length == 2){
            if (getNode(edge[0]).isConnectedToNode(edge[1]) && getNode(edge[1]).isConnectedToNode(edge[0])){
                System.out.println("This edge is already made");
            }else{
                nodeMap.add(edge);
                if(!getNode(edge[0]).isConnectedToNode(edge[1])){
                    getNode(edge[0]).addConnectedNodes(getNode(edge[1]));
                }
                if(!getNode(edge[1]).isConnectedToNode(edge[0])){
                    getNode(edge[1]).addConnectedNodes(getNode(edge[0]));
                }
            }
        }else{
            System.out.println("This edge is malformed");
        }
    }


}
