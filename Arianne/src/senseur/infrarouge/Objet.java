package senseur.infrarouge;

public class Objet {
	/**
	 * Marge après laquelle l'objet sera considéré comme avoir bougé et dont les coordonnées 
	 * pourront être mises à jour.
	 */
	public final static int MOVE_TOLERENCE = 5;
	
	private int currentId;
	
	/**
	 * Coordonnées de l'objet.
	 */
	private int x, y;
	
	/**
	 * Constructeur d'Objet.
	 * @param x int. Position de l'objet par rapport à l'axe des x.
	 * @param y int. Position de l'objet par rapport à l'axe des y.
	 */
	public Objet(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Retourne la coordonnée, x de l'objet courent.
	 * @return int. x de l'objet courent.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Met à jour la position de l'objet.
	 * @param x int. Coordonnée x.
	 * @param y int. coordonnée y.
	 */
	public void updatePos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	


	public int getY() {
		return y;
	}


	/**
	 * Determines if the current Objet is next the Objet given as parameter.
	 * @param obj The Objet that we want to determine if we are next to.   
	 * @return True if object is within the tolerance zone. False otherwise.
	 */
	public boolean isNextTo(Objet obj) {
		if(obj.x >= (x + Infrarouge.NEXT_TO_LOWER_TOLLERENCE) && obj.x <= (x + Infrarouge.NEXT_TO_UPPER_TOLLERENCE) && // To the right
				(obj.y >= (y + Infrarouge.NEXT_TO_LOWER_TOLLERENCE) && obj.y <= (y + Infrarouge.NEXT_TO_UPPER_TOLLERENCE) || // Above
				obj.y <= (y - Infrarouge.NEXT_TO_LOWER_TOLLERENCE) && obj.y >= (y - Infrarouge.NEXT_TO_UPPER_TOLLERENCE))) // Below
			return true;
		
		if(obj.x <= (x - Infrarouge.NEXT_TO_LOWER_TOLLERENCE) && obj.x >= (x - Infrarouge.NEXT_TO_UPPER_TOLLERENCE) && // To the left
				(obj.y >= (y + Infrarouge.NEXT_TO_LOWER_TOLLERENCE) && obj.y <= (y + Infrarouge.NEXT_TO_UPPER_TOLLERENCE) || // Above
				obj.y <= (y - Infrarouge.NEXT_TO_LOWER_TOLLERENCE) && obj.y >= (y - Infrarouge.NEXT_TO_UPPER_TOLLERENCE))) // Below
			return true;
		
		return false;
	}

	/**
	 * Meant to be called Objet at time T0 with the suspected same Objet at T+1 as parameter. 
	 * @param obj What is suspected to be the same object at time t+1.
	 * @return Returns true if the coordinates of the parameter Objet are outside the tolerance zone. Else false.
	 */
	public boolean hasMoved(Objet obj) {
		if(obj.x > (x + Infrarouge.MOVE_TOLERENCE) || obj.x < (x - Infrarouge.MOVE_TOLERENCE) || obj.y > (y + Infrarouge.MOVE_TOLERENCE) || obj.y < (y - Infrarouge.MOVE_TOLERENCE))
			return true;
		return false;
	}
	
	/**
	 * Donne la distance entre 2 objets.
	 * @param o Objet. L'objet par rapport au quelle on veux meusurer la distance.
	 * @return double. La distance en centimètre par rapport à l'objet donnée.
	 */
	public int getDistance(Objet o) {
		return (int)(Math.sqrt( Math.pow( (this.x - o.getX()) , 2) + Math.pow( (this.y - o.getY()) , 2)));
	}

	public static void main(String[] args) {



	}


}
