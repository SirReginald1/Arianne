package CamInfraRouge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class EV3Client {


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
	public int[][] getData() {
		//System.out.println("getData() start");
		// Wait to receive a datagram
		long timeStart = System.currentTimeMillis();
		long waitTime = 10000;
		while(System.currentTimeMillis() - timeStart < waitTime) {
			try {
				dsocket.receive(packet);
			}catch(IOException e){
				System.out.println("Error in infrared camera data retreaval! getData()");
			}	
		}


		// Convert the contents to a string, and display them
		String msg = new String(buffer, 0, packet.getLength());

		String[] palets = msg.split("\n");

		// As many tabs as there are tracked elements
		int [][] out = new int[palets.length][3];

		for (int i = 0; i < palets.length; i++) {
			String[] coord = palets[i].split(";");
			out[i][0] = Integer.parseInt(coord[0]);
			out[i][2] = Integer.parseInt(coord[1]);
			out[i][1] = Integer.parseInt(coord[2]);
		}
		//        System.out.println(out.length);
		//        Delay.msDelay(3000);
		//        System.out.println("getData end");

		return out;
	}

}
