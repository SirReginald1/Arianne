package senseur.infrarouge;

public class Robot extends Objet {
	
	/**
	 * Indique si le robot est le nôtre.
	 */
	private boolean isMe;
	
	public Robot(int x, int y, boolean isMe) {
		super(x, y);
		this.isMe = isMe;
	}

	public boolean isMe() {
		return isMe;
	}

	public void setMe(boolean isMe) {
		this.isMe = isMe;
	}

}
