package senseur.infrarouge;

public class Infrarouge {
	
	private EV3Client camera;
	
	private final static int xMax = 300, yMax = 200;
	
	private final static int xBut1 = 20, xBut2 = 280; // Besoin d'être calibrer
	
	private Objet[][] terrain;
	
	private Palet[] paletTab = new Palet[9];
	
	private Robot other, me;
	
	
	public Infrarouge() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retourne un tableau contenant les coordonnées de notre robot.
	 * @return int[]. Tableau contenant les coordonnées de notre robot. {x, y}
	 */
	public int[] getMyPos() {
		return new int[] {me.getX(), me.getY()};
	}
	
	/**
	 * Retourne un tableau contenant les coordonnées de l'autre robot.
	 * @return int[]. Tableau contenant les coordonnées de l'autre robot. {x, y}
	 */
	public int[] getOtherPos() {
		return new int[] {other.getX(), other.getY()};
	}
	
	/**
	 * Retourne un tableau contenant les coordonnées du palet le plus proche.
	 * @return int[]. Tableau contenant les coordonnées du palet le plus proche. {x, y}
	 */
	public int[] getClosestPaletPos() {
		Palet out = new Palet(300, 300);
		int distance = 300; // May need changing
		for(int i=0;i<paletTab.length;i++)
			if(paletTab[i].getDistance(me) < distance)
				out = paletTab[i];
		
		if(out.getX() > xMax || out.getY() > yMax)
			throw new IllegalArgumentException();
		
		return new int[] {out.getX(), out.getY()};
	}

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
