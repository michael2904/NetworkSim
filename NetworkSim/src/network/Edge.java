/**
 * 
 */
package network;

/**
 * @author michaelAM
 *
 */
public class Edge {
    private Node source;
    private Node target;
    private int bandwith;
    private String unit;
    private Network network = null;
    
    
    public Edge(Node source, Node target, int bandwith, String unit) {
        this.source = source;
        this.target = target;
        this.bandwith = bandwith;
        this.unit = unit;
    }
    public Edge(Node source, Node target) {
        this.source = source;
        this.target = target;
        this.bandwith = 1;
        this.unit = "G";
    }
    
    public Edge(Network network,Node source, Node target, int bandwith, String unit) {
        this.network = network;
        this.source = source;
        this.target = target;
        this.bandwith = bandwith;
        this.unit = unit;
    }
    public Edge(Network network,Node source, Node target) {
        this.network = network;
        this.source = source;
        this.target = target;
        this.bandwith = 1;
        this.unit = "G";
    }
    
    public Edge(Network network,int source, int target) {
        this.network = network;
        this.source = network.getNode(source);
        this.target = network.getNode(target);
        this.bandwith = 1;
        this.unit = "G";
    }
    
    public Network getNetwork() {
        return network;
    }
    public void setNetwork(Network network) {
        this.network = network;
    }
    
    public void printEdge() {
        System.out.println("Source: " + source.getAddress() + " Target: " + target.getAddress() + " Bandwith: " + bandwith + " "+unit);
    }
    
    public Node getSource() {
        return source;
    }
    public void setSource(Node source) {
        this.source = source;
    }
    public Node getTarget() {
        return target;
    }
    public void setTarget(Node target) {
        this.target = target;
    }
    public int getBandwith() {
        return bandwith;
    }
    public void setBandwith(int bandwith) {
        this.bandwith = bandwith;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    

}
