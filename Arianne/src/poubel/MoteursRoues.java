import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class MoteursRoues extends Ultrason {

	static int uneRotationRoue;
	private int distance;
	// public static int angleDeRotation;
	RegulatedMotor motorGauche = new EV3LargeRegulatedMotor(MotorPort.A);
	RegulatedMotor motorDroit = new EV3LargeRegulatedMotor(MotorPort.C);

	public MoteursRoues() {
		uneRotationRoue = this.getLimitAngle();
	}

	/**
	 * Return the angle that this Motor is rotating to.
	 * 
	 * @return angle in degrees
	 */
	public int getLimitAngle() {
		return this.getLimitAngle();
	}

	public int getDistance() {
		return distance;
	}

	public void avance(int d) {
		// motorGauche.rotate(d, true);
		// motorDroit.rotate(d, true);

		// est-ce que d ne doit pas dépasser un certain seuil ?
		motorGauche.setSpeed(d);
		motorDroit.setSpeed(d);
		motorGauche.forward();
		motorDroit.forward();
		motorGauche.stop();
		motorDroit.stop();
		// j'utilise setSpeed, d'après ce que j'ai compris c'est plus précis, faudra que
		// je demande au prof
	}

	public void recule(int d) {
		// motorGauche.rotate(-d, true);
		// motorDroit.rotate(-d, true);

		motorGauche.setSpeed(d);
		motorDroit.setSpeed(d);
		motorGauche.backward();
		motorDroit.backward();
		motorGauche.stop();
		motorDroit.stop();
	}

	public void arrete() {
		// si estMur(), estPalet() est false on avance
		motorGauche.stop();
		motorDroit.stop();
		if (!(estMur() || estPalet())) {
			avance(uneRotationRoue); // pas sur qu'une rotation suffise
		}
	}

	public void tourneAGauche(int angle) {
		// si estMur(), estRobot() est false on avance
		// si estMur(), estRobot() est true on arrete
		// si le mur est présent à droite, alors on bifurque
		// si le robot arrive à droite, on fait un tour vers la gauche
		motorGauche.rotate(-angle, true);
		motorDroit.rotate(angle, true);
		if (!(estMur() || estRobot())) {
			avance(uneRotationRoue);
		} else if (estMur()) { // et on détecte qu'il est à gauche
			arrete();
		} else if (estRobot()) { // et on détecte qu'il arrive à gauche
			tourneADroite(angle);
		}
	}

	public void tourneADroite(int angle) {
		// si estMur(), estRobot() est false on avance
		// si estMur(), estRobot() est true on arrete
		// si le mur est présent à gauche, alors on bifurque
		// si le robot arrive à gauche, on fait un tour vers la droite
		motorGauche.rotate(angle, true);
		motorDroit.rotate(-angle, true);
		if (!(estMur() || estRobot())) {
			avance(uneRotationRoue);
		} else if (estMur()) { // et on détecte qu'il est à droite
			arrete();
		} else if (estRobot()) { // et on détecte qu'il arrive à droite
			tourneAGauche(angle);
		}
	}

	public int vitesse() {
		// je calcule la vitesse parcourue en 5 min
		return distance / 300000;
	}
}
