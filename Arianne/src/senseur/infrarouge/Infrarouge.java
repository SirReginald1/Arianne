package senseur.infrarouge;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Infrarouge {
	
	private final static EV3Client camera = new EV3Client();
	
	public final static int X_MAX = 300, Y_MAX = 200;
	
	public final static int X_BUT1 = 20, X_BUT2 = 280; // Besoin d'être calibrer
	
	/**
	 * The margin for error when trying to match 2 object positions.
	 */
	public final static int MOVE_TOLERENCE = 5;
	/**
	 * The top margin for one object being next to an other.
	 */
	public final static int NEXT_TO_UPPER_TOLLERENCE = 4;
	/**
	 *  The bottom margin for one object being next to an other.
	 */
	public final static int NEXT_TO_LOWER_TOLLERENCE = 2;
	
	private static Objet[] allObjet = new Objet[11];
	
	private static Palet[] paletTab = new Palet[9];
	
	private static Robot other, me;
	
	
	
	
	public Infrarouge() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retourne un tableau contenant les coordonnées de notre robot.
	 * @return int[]. Tableau contenant les coordonnées de notre robot. {x, y}
	 */
	public static int[] getMyPos() {
		return new int[] {me.getX(), me.getY()};
	}
	
	/**
	 * Retourne un tableau contenant les coordonnées de l'autre robot.
	 * @return int[]. Tableau contenant les coordonnées de l'autre robot. {x, y}
	 */
	public static int[] getOtherPos() {
		return new int[] {other.getX(), other.getY()};
	}
	
	/**
	 * Retourne un tableau contenant les coordonnées du palet le plus proche.
	 * @return int[]. Tableau contenant les coordonnées du palet le plus proche. {x, y}
	 */
	public static int[] getClosestPaletPos() {
		Palet out = new Palet(300, 300);
		int distance = 300; // May need changing
		for(int i=0;i<paletTab.length;i++)
			if(paletTab[i].getDistance(me) < distance)
				out = paletTab[i];
		
		if(out.getX() > X_MAX || out.getY() > Y_MAX)
			throw new IllegalArgumentException();
		
		return new int[] {out.getX(), out.getY()};
	}

	// How to diferentiate the 2 robots inisiate a find me function which matches the if we are rurently moving from motor with the if me are currently moving from Infrarouge.
	// Can initiate a scan to see how far we are from the boundries and match it to the coordinates.
	public static void initiateMyPos() {
		
	}
	
	/*
	public void findRobots(int [][] tab) { // Need to deal with different sized tables.
		int idx = 0;
		for(int i=0;i<tab.length;i++) {
			Objet o = new Objet(tab[i][1], tab[i][2]);
			// Runs through object positions and determines if the object is still in the same position or has moved.
			if(allObjet[i].hasMoved(o))
			
			
		}
	}
	*/
	
	
	public boolean findMe() {
		return false;
	}
	
	
	
	public void placeCoordinatesInTab() {
		
	}
	
	
	public static void main(String[] args) {
		
		

	}

}
