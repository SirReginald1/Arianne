package Pince;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;


public class MoteurPince {

	RegulatedMotor pince = new EV3MediumRegulatedMotor(MotorPort.B);

	final static int ouverture = 1500; 	// 	angle max d'ouverture 
	final static int fermeture = -1500; //	angle max de fermeture 
	boolean estOuvert = false;        	//	determiner si la pince est ouverte ou fermé

	public void ouvre () {
		pince.rotate(ouverture);
		estOuvert = true;
		}
		
	public void ferme () {
			pince.rotate(fermeture);	
			estOuvert = false;
		}
	

	public boolean getFermeOuverte() {
		return estOuvert;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

}
