package senseur.infrarouge;

public class Palet extends Objet {
	
	private boolean enJeux;
	
	public Palet(int x, int y) {
		super(x, y);
		enJeux = true;
	}


	/**
	 * Met à jour la position de l'objet. Règle la valeur enJeux à false la valeur 
	 * de x passer en paramètre se trouve dans une zone d'embu.
	 * @param x int. Coordonnée x.
	 * @param y int. coordonnée y.
	 */
	public void updatePos(int x, int y) {
		this.updatePos(x, y);
		if(x > Infrarouge.X_BUT2 || x < Infrarouge.X_BUT1)
			enJeux = false;
	}


	public boolean isEnJeux() {
		return enJeux;
	}



	public void setEnJeux(boolean enJeux) {
		this.enJeux = enJeux;
	}

	public static void main(String[] args) {
		Palet p1 = new Palet(10,10);
		Palet p2 = new Palet(10,7);
		
		//System.out.println("Upper bound: "+Objet.NEXT_TO_UPPER_TOLLERENCE);
		//System.out.println(p1.isNextTo(p2));

		for(int i=5;i<16;i++) {
			Palet pt = new Palet(i,10);
			System.out.println();
			System.out.println("Upper bound: "+Infrarouge.NEXT_TO_UPPER_TOLLERENCE);
			System.out.println("pt: x: "+pt.getX()+"; y: "+pt.getY());
			System.out.println(p1.isNextTo(pt));
		
		}
		
	}
}
