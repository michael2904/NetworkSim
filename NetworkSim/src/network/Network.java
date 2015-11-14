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
    int[][] nodeMap = null;
    ArrayList<Node> networkNodes;
    
    public Network(int[][] nodeMap) {
        // TODO Auto-generated constructor stub
        this.nodeMap=nodeMap;
        networkNodes = new ArrayList<Node>();
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
    public int[][] getNodeMap() {
        return nodeMap;
    }
    public void setNodeMap(int[][] nodeMap) {
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


}
