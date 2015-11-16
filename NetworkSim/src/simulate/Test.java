/**
 * 
 */
package simulate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import CipherTools.CipherTool;
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

    
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        int[][] nodeMapArr = {{1,2},{0,3},{2,3},{4,5},{5,2},{3,4},{4,0},{0,2}};
        ArrayList<int[]> nodeMap = convertArrToArrListInt(nodeMapArr);
        
        
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
        
        String plaintext = "Eduardo's mother RRR"; /*Note null padding*/
/*
        try {

            BigInteger bi1 = new BigInteger("1234567890543218");
            BigInteger bi2 = new BigInteger("1234567890543217");
            System.out.println(bi1);
            System.out.println(bi2);
            String bi1str = bi1.toString();
            String bi2str = bi2.toString();

            CipherTool ct = new CipherTool(bi1str,bi2str);

            System.out.println("==Java==");
            System.out.println("plain:   " + plaintext);

            byte[] cipher = ct.encryptB(plaintext);
            String cipherStr = new String(cipher);
            System.out.println(cipherStr);

            System.out.print("cipher:  ");
            for (int i=0; i<cipher.length; i++)
              System.out.print(new Integer(cipher[i])+" ");
            System.out.println("");

            String decrypted = ct.decryptS(cipher);

            System.out.println("decrypt: " + decrypted);

          } catch (Exception e) {
            e.printStackTrace();
          }        
        try{
            //String testText2 = "Hello world";
            Node testNode = new Node(network,6);
            testNode.genereateKey();
            BigInteger testKey = testNode.getKey();
            System.out.println(testKey);
            network.getNode(0).genereateKey();
            BigInteger testKey2 = network.getNode(0).getKey();
            System.out.println(testKey2);
            CipherTool ct2 = new CipherTool(testKey,testKey2);
            String pcToEnc1 = "3-//-false-//-Lets fuck eduardo's mom";

            System.out.println("==Java==");
            System.out.println("pcToEnc1:   " + pcToEnc1+" length "+pcToEnc1.length()%16);

            byte[] pcStrEnc1 = ct2.encryptB(pcToEnc1);
            String pcStrEnc1Str = new String(pcStrEnc1);
            
            System.out.print("pcStrEnc1:  ");
            for (int i=0; i<pcStrEnc1.length; i++)
              System.out.print(pcStrEnc1[i]+" ");
            System.out.println("");
            System.out.println("pcStrEnc1Str:   " + pcStrEnc1Str);
            
            
            System.out.println("After one encoding");
            String pcStrToAdd = "2-//-false-//-";
            byte[] pcToAdd = pcStrToAdd.getBytes();
            System.out.print("pcToAdd:  ");
            for (int i=0; i<pcToAdd.length; i++)
              System.out.print(pcToAdd[i]+" ");
            System.out.println("");
            System.out.println("pcStrToAdd:   " + pcStrToAdd+"length "+pcStrToAdd.length());
            

            byte[] pcEncComb1 = new byte[pcToAdd.length+pcStrEnc1.length];
            for (int i = 0; i < pcEncComb1.length; ++i)
            {
                pcEncComb1[i] = i < pcToAdd.length ? pcToAdd[i] : pcStrEnc1[i - pcToAdd.length];
            }
            String pcEnc1Str = new String(pcEncComb1);
            System.out.print("pcStrComb2:  ");
            for (int i=0; i<pcEncComb1.length; i++)
              System.out.print(pcEncComb1[i]+" ");
            System.out.println("");
            System.out.println("pcEnc1Str:   " + pcEnc1Str);

            
            System.out.println("After two encoding");
            byte[] pcEncComb2 = ct2.encryptB(pcEncComb1);
            String pcStrEnc2 = new String(pcEncComb2);
            System.out.print("pcStrEnc2:  ");
            for (int i=0; i<pcEncComb2.length; i++)
              System.out.print(pcEncComb2[i]+" ");
            System.out.println("");
            System.out.println("pcStrEnc2:   " + pcStrEnc2);

            System.out.println("After two decode");
            byte[] pcDec2 = ct2.decryptB(pcEncComb2);
            String pcStrDec2 = new String(pcDec2);
            System.out.print("pcDec2:  ");
            for (int i=0; i<pcDec2.length; i++)
              System.out.print(pcDec2[i]+" ");
            System.out.println("");
            System.out.println("pcStrDec2:   " + pcStrDec2);
            
            String pcToDec2 = pcStrDec2.split("-//-")[2];
            System.out.println("pcToDec2:   " + pcToDec2);

            byte[] pcToDec = pcToDec2.getBytes();
            System.out.print("pcToDec:  ");
            for (int i=0; i<pcToDec.length; i++)
              System.out.print(pcToDec[i]+" ");
            System.out.println("");
            byte[] pcDec1 = ct2.decryptB(pcToDec);
            String pcStrDec1 = new String(pcDec1);

            System.out.print("pcStrDec1:  ");
            for (int i=0; i<pcDec1.length; i++)
              System.out.print(pcDec1[i]+" ");
            System.out.println("");
            System.out.println("pcStrDec1:   " + pcStrDec1);

            
        } catch (Exception e) {
            e.printStackTrace();
          }  
       */ 
    }
    
    public void printByteArr(byte[] arr){
        System.out.print("arr:  ");
        for (int i=0; i<arr.length; i++)
          System.out.print(new Integer(arr[i])+" ");
        System.out.println("");
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
    
    public static ArrayList<int[]> convertArrToArrListInt (int[][] arr){
        ArrayList<int[]> arrlist = new ArrayList<int[]>();
        for(int[] arrInput : arr){
            arrlist.add(arrInput);
        }
        return arrlist;
    }
    
}
