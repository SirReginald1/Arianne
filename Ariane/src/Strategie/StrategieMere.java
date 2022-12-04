package Strategie;

import java.io.IOException;
import Sensors.UltraSonicSensor;
import Motors.Motors;
import Sensors.ColorSensor;
import Sensors.TouchSensor;
import CamInfraRouge.Infrarouge;
import lejos.hardware.Button;
import lejos.hardware.port.Port;

import lejos.utility.Delay;

public class StrategieMere   {
	/**
	 * Cette classe permet de définir toute les méthodes qui seront réutiliser dans la classe StrategieFille : scan, permierPalet...
	 * Elle comprend et lie toute les autres classes des autres packages
	 * @attribut boolean gauche : true si le robot commence à gauche dans l'arène et false à droite
	 * @attribut boolean ligneRouche : true si le robot commence sur la ligne rouge et false si il commence sur la ligne jaune
	 */

	protected ColorSensor colorSensor;
	protected TouchSensor touchSensor;
	protected UltraSonicSensor ultraSonicSensor;
	protected Motors motors;
	//protected Infrarouge camInfraRouge;
	protected boolean gauche;
	protected boolean ligneRouge;

	/**
	 * Constructeur de la classe
	 * @param portCS port du capteur de couleur
	 * @param portTS portCS port du capteur de couleur
	 * @param portUSS port du capteur à ultrason
	 * @param gauche si le robot commence à gauhce ou pas
	 * @param ligneRouge si le robot commence sur une ligne rouge ou pas
	 * @throws IOException si erreur avec le fichier Properties
	 */
	public StrategieMere(Port portCS, Port portTS, Port portUSS, boolean gauche, boolean ligneRouge) throws IOException {
		this.ultraSonicSensor=new UltraSonicSensor(portUSS);
		//this.camInfraRouge=new Infrarouge();
		this.colorSensor=new ColorSensor(portCS);
		this.touchSensor=new TouchSensor(portTS);
		this.motors=new Motors();
		this.ligneRouge=ligneRouge;
		this.gauche=gauche;
	}

	/**
	 * Méthode permettant d'aller chercher et marquer le premier palet au début de la partie de n'importe quel positiond e départ.
	 * @author Adrien
	 */
	public void premierPalet() {
		this.vaPalet();
		if ((this.gauche==true && this.ligneRouge==false) || (this.gauche==false && this.ligneRouge==true)) {
			this.motors.asyncRotate(45);
			this.motors.asyncForward(400);
		}
		else {
			this.motors.asyncRotate(-45);
			this.motors.asyncForward(400);
		}
		this.vaMarquer();
	}

	/**
	 * Méthode pour faire tourner le robot selon un angle et déterminer la plus petite valeur qu'il a perçue
	 * @authors Foued, Andrew et Adrien
	 * @param angleScan l'angle de scan du robot 
	 * @return la distance capté par l'objet le plus proche
	 */
	public double scan(int angleScan) {
		this.motors.setAngularSpeed(60);
		double valeurPlusPetite = 100;
		double indiceAngle = 0;
		double distanceMax = 0.8;
		this.motors.rotate(angleScan/2,false);
		this.motors.setBoussole(this.motors.getBoussole()+angleScan/2);
		this.motors.rotate(-angleScan,true);
		this.motors.setBoussole(this.motors.getBoussole()+angleScan);
		while(this.motors.isMoving()) {
			double valeurEnCours = this.ultraSonicSensor.doMeasure();
			if(valeurEnCours < valeurPlusPetite && valeurEnCours>0.025) {
				valeurPlusPetite = valeurEnCours;
				indiceAngle = this.motors.getMovement().getAngleTurned();
			}
			Delay.msDelay(5);
		}
		this.motors.rotate(angleScan+indiceAngle, false);
		this.motors.setBoussole(this.motors.getBoussole()+angleScan+indiceAngle);
		return valeurPlusPetite*10000;
	}


	/**
	 * Méthode utilisé quand le robot est fasse à un palet. Le robot avance tant que sont capteur de touché n'est pas appuyé. 
	 * S'il rencontre un robot sur son chemin, il fait une pause le temps que l'autre robot soit partit.
	 * @author Adrien  
	 */
	public void vaPalet() {
		this.motors.asyncOuvrePince();
		this.motors.avance();
		while(this.touchSensor.isPressed()==false) {
			if (this.ultraSonicSensor.doMeasure()<0.020) {
				this.motors.stop();
				System.out.println("robot");
				Delay.msDelay(3000);
				this.motors.avance();
			}
			if (Button.ENTER.isDown()==true) {
				this.motors.stop();
				while(true) {
					Delay.msDelay(10);
					if (Button.ENTER.isDown()==false) {
						this.motors.avance();
						break;
					}
				}
			}
			Delay.msDelay(10);
		}
		this.motors.stop();
		this.motors.asyncFermePince();
	}

	/**
	 * Méthode qui va chercher un Palet. 
	 * Fait un scan puis va à la position indiqué par le scan
	 * Si le capteur de touché est appuyé alors, return
	 * Sinon le robot refait un scan tant que le capteur de touché n'est pas appuyé
	 * Prend en compte les cas on le robot à scanné un mur
	 * @Adrien
	 */
	public void vaChercherPalet()  {
		boolean TrouvePalet=false;
		
		while(TrouvePalet==false) {
			double distancePlusProchePalet=this.scan(180);
			if (motors.isMoving()==false) {
				this.motors.ouvrePince();
			}
			this.motors.forward(distancePlusProchePalet+600,true);
			while(this.motors.isMoving()) {
				if (this.touchSensor.isPressed()==true) {
					this.motors.stop();
					this.motors.asyncFermePince();
					this.motors.rotateBoussole(0);
					return;
				}
				if (this.ultraSonicSensor.doMeasure()<0.025 && this.gauche==true) {
					this.motors.asyncRotate(180);
				}
				if (this.ultraSonicSensor.doMeasure()<0.025 && this.gauche==false) {
					this.motors.asyncRotate(-180);
				}
				if (Button.ENTER.isDown()==true) {
					this.motors.stop();
					while(true) {
						Delay.msDelay(10);
						if (Button.ENTER.isDown()==false) {
							this.motors.avance();
							break;
						}
					}
				}
				Delay.msDelay(10);
			}
			if (this.motors.isMoving()==false) {
				this.motors.fermePince();
			}
		}	
	}

	/**
	 * Méthode appellé quanc le robot à un palet dans ses pinces. 
	 * Va marquer ce palet et à la fin actualise les coordonées du robot.
	 * @author Adrien
	 */
	public void vaMarquer() {	
		this.motors.setVitesseMax();
		this.motors.avance();
		while(this.colorSensor.getCouleur()!="white") {
			if (this.ultraSonicSensor.doMeasure()<0.020 && this.gauche==true) {
				this.motors.asyncRotate(90);
				this.motors.forward(300,false);
				this.motors.asyncRotate(-90);
			}
			if (this.ultraSonicSensor.doMeasure()<0.020 && this.gauche==false) {
				this.motors.asyncRotate(-90);
				this.motors.forward(300,false);
				this.motors.asyncRotate(90);
			}
			if (Button.ENTER.isDown()==true) {
				this.motors.stop();
				while(true) {
					Delay.msDelay(10);
					if (Button.ENTER.isDown()==false) {
						this.motors.avance();
						break;
					}
				}
			}
			Delay.msDelay(50);
		}
		this.motors.stop();
		this.motors.asyncOuvrePince();
		this.motors.asyncBackward(100);
		double posXM=this.ultraSonicSensor.doMeasure();
		this.motors.asyncFermePince();
		if (this.gauche==true) {
			this.motors.asyncRotate(90);
			double posYM=this.ultraSonicSensor.doMeasure(); 
			this.motors.setPosX(3000-posXM*10000);
			this.motors.setPosY(posYM*10000);
			this.motors.asyncRotate(90);
		}
		else {
			this.motors.asyncRotate(-90);
			double posYM=this.ultraSonicSensor.doMeasure();
			this.motors.setPosX(posXM*10000);
			this.motors.setPosY(posYM*10000);
			this.motors.asyncRotate(-90);
		}
	}
}
