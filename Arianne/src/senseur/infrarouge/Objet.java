package senseur.infrarouge;

public abstract class Objet {

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
	 * Donne la distance entre 2 objets.
	 * @param o Objet. L'objet par rapport au quelle on veux meusurer la distance.
	 * @return double. La distance en centimètre par rapport à l'objet donnée.
	 */
	public int getDistance(Objet o) {
		return (int)(Math.sqrt( Math.pow( (this.x - o.getX()) , 2) + Math.pow( (this.y - o.getY()) , 2)));
	}
	
	
}
