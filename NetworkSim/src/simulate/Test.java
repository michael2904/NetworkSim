/**
 * 
 */
package simulate;

import java.util.ArrayList;

import network.Network;
import network.Node;
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
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] nodeMap = {{1,2},{0,3},{2,3},{4,5},{5,2},{3,4},{4,0},{0,2}};
        Network network = new Network(nodeMap);
        System.out.println(" ****** new NETWORK *****");
        Packet packet = new Packet(4,"test","test2");
        System.out.println(" ****** new Packet *****");
        String packetContent = "3-//-false-//-Lets fuck eduardo's mom";
        Packet packetWithContent = new Packet(4,"",packetContent);
        Packet packetWithContentSameDest = new Packet(3,"",packetContent);
        test1(network,packet);
        test2(network,packet);
        test3(network,packet);
        test4(network,packet);
        test5(network,packet);
        test6(network,packet);
        test7(network,packet);
        System.out.println(" \n****** test8 same destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test8(network,packetWithContentSameDest);
        System.out.println(" \n****** test8bis different destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test8(network,packetWithContent);
        System.out.println(" \n****** test9 same destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test9(network,packetWithContentSameDest);
        System.out.println(" \n****** test9bis different destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test9(network,packetWithContent);
        System.out.println(" \n****** test10 same destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test10(network,packetWithContentSameDest);
        System.out.println(" \n****** test10bis different destination *****\n");
        test10(network,packetWithContent);
        System.out.println(" \n****** test11 same destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test11(network,packetWithContentSameDest);
        System.out.println(" \n****** test11bis different destination *****\n");
        test11(network,packetWithContent);
        System.out.println(" \n****** test12 same destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test12(network,packetWithContentSameDest);
        System.out.println(" \n****** test12bis different destination *****\n");
        test12(network,packetWithContent);
        System.out.println(" \n****** test13 same destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test13(network,packetWithContentSameDest);
        System.out.println(" \n****** test13bis different destination *****\n");
        test13(network,packetWithContent);
        
        System.out.println(" \n****** test14 same destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test14(network,packetWithContentSameDest);
        System.out.println(" \n****** test14bis different destination *****\n");
        test14(network,packetWithContent);
        System.out.println(" \n****** test15 same destination *****\n");
        packetWithContent = new Packet(4,"",packetContent);
        packetWithContentSameDest = new Packet(3,"",packetContent);
        test15(network,packetWithContentSameDest);
        System.out.println(" \n****** test15bis different destination *****\n");
        test15(network,packetWithContent);
    }
    
    public static void test1(Network network,Packet packet){
        System.out.println(" \n****** test1 send Packet DFS *****\n");
        network.getNode(1).sendPacket(packet);
        System.out.println(" \n****** END test1 send Packet DFS *****\n");
    }
    public static void test2(Network network,Packet packet){
        System.out.println(" \n****** test2 send Packet using path and finish at destination *****\n");

        ArrayList<Node> path2 = new ArrayList<Node>();
        path2.add(network.getNode(1));
        path2.add(network.getNode(2));
        path2.add(network.getNode(5));
        path2.add(network.getNode(4));

        System.out.print("Path: ");
        for(Node node : path2){
            System.out.print(node.getAddress()+"-");
        }
        System.out.println(" End Path");
        network.getNode(1).sendPacketWithPath(packet, path2);
        System.out.println(" \n****** END test2 send Packet using path and finish at destination *****\n");        
    }
    public static void test3(Network network,Packet packet){
        System.out.println(" \n****** test3 send Packet using path invalid destination *****\n");

        ArrayList<Node> path2 = new ArrayList<Node>();
        path2.add(network.getNode(1));
        path2.add(network.getNode(5));
        path2.add(network.getNode(4));
        System.out.print("Path: ");
        for(Node node : path2){
            System.out.print(node.getAddress()+"-");
        }
        System.out.println(" End Path");
        network.getNode(1).sendPacketWithPath(packet, path2);
        System.out.println(" \n****** END test3 send Packet using path invalid destination *****\n");

    }
    public static void test4(Network network,Packet packet){
        System.out.println(" \n****** test4 send Packet BFS *****\n");
        network.getNode(1).sendPacketWithShortestPath(packet);
        System.out.println(" \n****** END test4 send Packet BFS *****\n");
    }
    public static void test5(Network network,Packet packet){
        System.out.println(" \n****** test5 send Packet to Address finishing at the packet destination *****\n");
        network.getNode(0).sendPacketToAddress(packet,4);
        System.out.println(" \n****** END test5 send Packet to Address finishing at the packet destination *****\n");
    }
    public static void test6(Network network,Packet packet){
        System.out.println(" \n****** test6 send Packet to Address stopping at Address finishing at the packet destination *****\n");
        network.getNode(1).sendPacketToAddress(packet,2);
        System.out.println(" \n****** END test6 send Packet to Address stopping at Address finishing at the packet destination *****\n");
    }
    public static void test7(Network network,Packet packet){
        System.out.println(" \n****** test7 send Packet to Address finishing at the packet destination but missing connection *****\n");
        network.getNode(1).sendPacketToAddress(packet,4);
        System.out.println(" \n****** END test7 send Packet to Address finishing at the packet destination but missing connection *****\n");
    }
    public static void test8(Network network,Packet packet){
        System.out.println(" \n****** test8 send Packet using Content DFS finish at destination*****\n");
        packet.getPacketInfo();
        network.getNode(1).sendPacketUsingContent(packet);
        System.out.println(" \n****** END test8 send Packet using Content DFS finish at destination *****\n");
    }
    public static void test9(Network network,Packet packet){
        System.out.println(" \n****** test9 send Packet using path and Content, and finish at destination *****\n");
        packet.getPacketInfo();
        ArrayList<Node> path2 = new ArrayList<Node>();
        path2.add(network.getNode(1));
        path2.add(network.getNode(2));
        path2.add(network.getNode(5));
        path2.add(network.getNode(4));
        path2.add(network.getNode(3));

        System.out.print("Path: ");
        for(Node node : path2){
            System.out.print(node.getAddress()+"-");
        }
        System.out.println(" End Path");
        network.getNode(1).sendPacketWithPathUsingContent(packet, path2);
        System.out.println(" \n****** END test9 send Packet using path and Content, and finish at destination *****\n");        
    }
    public static void test10(Network network,Packet packet){
        System.out.println(" \n****** test10 send Packet using path and Content invalid destination*****\n");

        ArrayList<Node> path2 = new ArrayList<Node>();
        path2.add(network.getNode(1));
        path2.add(network.getNode(5));
        path2.add(network.getNode(4));
        System.out.print("Path: ");
        for(Node node : path2){
            System.out.print(node.getAddress()+"-");
        }
        System.out.println(" End Path");
        network.getNode(1).sendPacketWithPathUsingContent(packet, path2);
        System.out.println(" \n****** END test10 send Packet using path and Contentinvalid destination *****\n");

    }
    public static void test11(Network network,Packet packet){
        System.out.println(" \n****** test11 send Packet and Content BFS *****\n");
        network.getNode(1).sendPacketWithShortestPathUsingContent(packet);
        System.out.println(" \n****** END test11 send Packet and Content BFS *****\n");
    }
    public static void test12(Network network,Packet packet){
        System.out.println(" \n****** test12 send Packet to Address and Content finishing at the packet destination *****\n");
        network.getNode(0).sendPacketToAddressUsingContent(packet,3);
        System.out.println(" \n****** END test12 send Packet to Address and Content finishing at the packet destination *****\n");
    }
    public static void test13(Network network,Packet packet){
        System.out.println(" \n****** test13 send Packet to invalid Address and Content finishing at the packet destination *****\n");
        network.getNode(0).sendPacketToAddressUsingContent(packet,2);
        System.out.println(" \n****** END test13 send Packet to invalid Address and Content finishing at the packet destination *****\n");
    }
    public static void test14(Network network,Packet packet){
        System.out.println(" \n****** test14 send Packet to Address stopping at Address and Content finishing at the packet destination *****\n");
        network.getNode(1).sendPacketToAddressUsingContent(packet,2);
        System.out.println(" \n****** END test14 send Packet to Address stopping at Address and Content finishing at the packet destination *****\n");
    }
    public static void test15(Network network,Packet packet){
        System.out.println(" \n****** test15 send Packet to Address finishing at the packet destination and Content but missing connection *****\n");
        network.getNode(1).sendPacketToAddressUsingContent(packet,4);
        System.out.println(" \n****** END test15 send Packet to Address finishing at the packet destination and Content but missing connection *****\n");
    }
    
}
