package CamInfraRouge;

import lejos.remote.ev3.RemoteRequestException;
import lejos.utility.Delay;
import java.math.MathContext;
import lejos.ev3.tools.EV3Console;


public class Infrarouge {

	private final static EV3Client camera = new EV3Client();

	public final static int X_MAX = 300, Y_MAX = 200;

	public final static int X_BUT1 = 27, X_BUT2 = 286; // Besoin d'être calibrer


	private static int[][] objPos;

	private static int[][] objPosNew;

	private static int nbObjet = 11;
	private static int nbPaletHorsJeux = 0;

	/**
	 * Se connecte à la caméra pour récupérer les données lors de l'instanciation.
	 */
	public Infrarouge() {
		//objPos = camera.getData(); // pas très utile de récup les data ici
	}


	/**
	 * Test si la partie est terminée. (le nombre de palets hors jeux est égal à 9).
	 * @return Vrais si la partie est terminée. Faux si non.
	 */
	public boolean gameOver() {
		if(nbPaletHorsJeux == 9)
			return true;

		return false;
	}


	// Maybe only update the number of objects at it's second call
	/**
	 * Retourne vraie si le nombre d'objets détecté par la caméra et inférieur au nombre d'objet précédemment stocké. Puis met à jour le nombre d'objets.
	 * @return Vrais si le nombre d'objets et inférieurs au nombre stocké en mémoire. Faux sinon.
	 */
	public static boolean isNbObjReduced() {
		objPosNew = camera.getData();
		if(objPosNew.length < nbObjet) {
			nbObjet = objPosNew.length;
			return true;
		}
		if(nbObjet < 12)
			nbObjet = objPosNew.length;
		return false;
	}

	// Maybe wait between this method call and the game over call to not end the game to early if the robots enter the goal zones.
	/**
	 * Met à jour le modèle avec l'information récupérée par la caméra.
	 * à appeller avant detDistance
	 */
	public static void updateModel() {
		objPos = camera.getData();
		int count = 0;
		for(int i=0;i<objPos.length;i++) {
			if(objPos[i][1] < X_BUT1 || objPos[i][1] > X_BUT2)
				count ++;
		}
		nbPaletHorsJeux = count;
		//		System.out.println(count);
		//		System.out.println(objPos.length);
		//		Delay.msDelay(3000);
		//		System.out.println("x: "+objPos[0][1]);
		//		System.out.println("y: "+objPos[0][2]);

		//EV3Console c = new EV3Console();
		//c.logMessage(null);
	}

	/**
	 * Gives the coordinates of the closest object to the given coordinates. Used to match scan coordinates with corresponding coordinates in objPos array.
	 * @param x Robot x position.
	 * @param y Robot y position.
	 * @return Array containing the x,y coordinates of the the robot as detected by the infrared camera. ([x,y])
	 */
	public static int[] findMe(int x, int y) {
		//this.updateModel();
		int smallest = 1000;
		int distance = 1000;
		int tabPos = -1;
		int marge = 2;
		for(int i=0;i<objPos.length;i++) {
			//System.out.println("i: "+i);
			distance = (int)(Math.sqrt( Math.pow( (objPos[i][1] - x) , 2) + Math.pow( (objPos[i][2] - y) , 2)));
			if(smallest > distance) {
				smallest = distance;
				tabPos = i;
			}
		}
		return new int[] {objPos[tabPos][1], objPos[tabPos][2]};
	}
	/**
	 * Gives the coordinates of the closest object to the given coordinates excluding the given coordinates. To be called with the coordinates from findMe().
	 * @param x Robot x position.
	 * @param y Robot y position.
	 * @return Array containing the x,y coordinates of the closest object. ([x,y])
	 */
	public static int[] getDistance(int x, int y) {
		//this.updateModel();
		int smallest = 1000;
		int distance = 1000;
		int tabPos = -1;
		for(int i=0;i<objPos.length;i++) {
			//System.out.println("i: "+i);
			distance = (int)(Math.sqrt( Math.pow( (objPos[i][1] - x) , 2) + Math.pow( (objPos[i][2] - y) , 2)));
			if(smallest > distance && objPos[i][1] != x && objPos[i][2] != y) {
				smallest = distance;
				tabPos = i;
			}
		}
		return new int[] {objPos[tabPos][1], objPos[tabPos][2]};
	}
	
}
