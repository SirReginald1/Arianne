package senseur.infrarouge;

public class Palet extends Objet {
	
	private boolean enJeux;
	
	public Palet(int x, int y) {
		super(x, y);
		enJeux = true;
	}
	
	
	public boolean isEnJeux() {
		return enJeux;
	}



	public void setEnJeux(boolean enJeux) {
		this.enJeux = enJeux;
	}


}
