package senseur.infrarouge;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import lejos.hardware.Button;

public class EV3Client extends Thread {
	
	
	final static int PORT = 8888;
	
	DatagramSocket dsocket;
	
	// Create a buffer to read datagrams into. If a
    // packet is larger than this buffer, the
    // excess will simply be discarded!
	byte[] buffer = new byte[2048];
	
	DatagramPacket packet  = new DatagramPacket(buffer, buffer.length);
	
	public EV3Client() {
		try {
			dsocket = new DatagramSocket(PORT);
		} catch (SocketException e) {
			 System.err.println(e);
		}
	}
	
	/**
	 * Attends de recevoir un packet d'information puis retourne une matrice contenante [i][0] = id; [i][1] = x;
	 * [i][2] = y. Attention, cette méthode attend de recevoir un packet, peut attendre plusieurs secondes.
	 * @return int [][]. Matrice contentent les coordonnées de tous les éléments détectés sur le terrain.
	 * @throws IOException - if an I/O error occurs.SocketTimeoutException - if setSoTimeout was previously calledand the timeout has expired.
	 * 	PortUnreachableException - may be thrown if the socket is connectedto a currently unreachable destination. Note, there is no guarantee that the
	 * exception will be thrown.java.nio.channels.
	 * IllegalBlockingModeException - if this socket has an associated channel,and the channel is in non-blocking mode.
	 */
	public int[][] getData() throws IOException{
		
		// Wait to receive a datagram
        dsocket.receive(packet);
		
     // Convert the contents to a string, and display them
        String msg = new String(buffer, 0, packet.getLength());
        
        String[] palets = msg.split("\n");
        
        // As many tabs as there are tracked elements
        int [][] out = new int[palets.length-1][3];
        
        for (int i = 0; i < palets.length; i++) {
        	String[] coord = palets[i].split(";");
        	out[i][0] = Integer.parseInt(coord[0]);
        	out[i][1] = Integer.parseInt(coord[1]);
        	out[i][2] = Integer.parseInt(coord[2]);
        }
        
		return out;
	}
	
	
	
	@ Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println();
		}
	}
	
	
  public static void main(String args[]) throws IOException  {
	  
	  FileWriter file = new FileWriter("C:\\Users\\andre\\Documents\\MIASHS\\S5\\IA\\DataFlow4.tkt");
	  
	  BufferedWriter bf = new BufferedWriter(file);
	  
	  int fi = 0;
	  
    try {
      int port = 8888;

      // Create a socket to listen on the port.
      DatagramSocket dsocket = new DatagramSocket(port);

      // Create a buffer to read datagrams into. If a
      // packet is larger than this buffer, the
      // excess will simply be discarded!
      byte[] buffer = new byte[2048];

      // Create a packet to receive data into the buffer
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      // Now loop forever, waiting to receive packets and printing them.
      while (true) 
      {
    	  
    	 System.out.println("Waiting for packet: "+ String.valueOf(System.currentTimeMillis() / 1000).substring(6));
        // Wait to receive a datagram
        dsocket.receive(packet);
        System.out.println("Packet recived: "+ String.valueOf(System.currentTimeMillis() / 1000).substring(6));

        // Convert the contents to a string, and display them
        String msg = new String(buffer, 0, packet.getLength());
       // System.out.println(/*packet.getAddress().getHostName() + ": "
        //    +*/ msg);
        
        
        bf.write(msg + " || " + String.valueOf(System.currentTimeMillis() / 1000).substring(6));
        bf.newLine();
        fi ++;
        
        if(fi> 1000)
        	file.close();
        

        
        String[] palets = msg.split("\n");
        
        for (int i = 0; i < palets.length; i++) {
        	String[] coord = palets[i].split(";");
        	int x = Integer.parseInt(coord[1]);
        	int y = Integer.parseInt(coord[2]);
        
        	System.out.println(Integer.toString(x) + " / " + Integer.toString(x) );
        }
        

        // Reset the length of the packet before reusing it.
        packet.setLength(buffer.length);
      }
     
    } 
    catch (Exception e) 
    {
      System.err.println(e);
    }
  }
}
           