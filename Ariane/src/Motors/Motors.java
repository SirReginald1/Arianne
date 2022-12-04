package Motors;

import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.MovePilot.*;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.chassis.WheeledChassis.Modeler;
import lejos.utility.Delay;

public class Motors extends MovePilot {
	/**
	 * Cette classe permet de gérer tout les moteurs du robot
	 * @authors Foued et Adrien
	 * @attribut double boussole : permet au robot de savoir comment il est orienté sur le terrain en fonction de sa position de départ
	 * @attribut double posX : correspond au coordonée x du robot
	 * @attribut double posY : correpond au coordonée y du robot
	 */

	private static RegulatedMotor motorGauche = new EV3LargeRegulatedMotor(MotorPort.A);
	private static RegulatedMotor motorPince = new EV3MediumRegulatedMotor(MotorPort.B);
	private static RegulatedMotor motorDroite = new EV3LargeRegulatedMotor(MotorPort.C);
	private static Wheel leftWheel = WheeledChassis.modelWheel(motorGauche, 56).offset(60.5);
	private static Wheel rightWheel = WheeledChassis.modelWheel(motorDroite, 56).offset(-60.5);
	private static Chassis myChassis = new WheeledChassis( new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	protected double boussole;
	protected double posX;
	protected double posY;

	/**
	 * Constructeur de la classe
	 */
	public Motors() {
		super(myChassis);
		this.posX=0;
		this.posY=0;
		this.boussole = 0;
	}

	/**
	 * getter de l'attribut posX
	 */
	public double getPosX() {
		return this.posX;
	}
	/**
	 * getter de l'attribut posY
	 */
	public double getPosY() {
		return this.posY;
	}
	/**
	 * setter de l'attribut posX
	 */
	public void setPosX(double x) {
		this.posX=x;
	}
	/**
	 * setter de l'attribut posY
	 */
	public void setPosY(double y) {
		this.posY=y;
	}

	/**
	 * getter de l'attribut boussole
	 */
	public double getBoussole() {
		return this.boussole;
	}
	/**
	 * setter de l'attribut boussole
	 */
	public void setBoussole(double b) {
		this.boussole=b;
	}

	/**
	 * fait reculer le robot de mannière asynchrone
	 * @param d distance à parcourir par le robot 
	 */
	public void asyncBackward(double d) {
		super.travel(-d);
	}
	/**
	 * fait avancer le robot
	 * @param b si b=true : synchrone sinon asynchrone
	 * @param d distance à parcourir par le robot 
	 */
	public void forward(double d, boolean b) {
		super.travel(d,b);
	}
	/**
	 * fait avancer le robot de mannière asynchrone
	 * @param d distance à parcourir par le robot 
	 */
	public void asyncForward(double d) {
		super.travel(d);
	}
	/**
	 * fait avancer le robot indéfiniment
	 */
	public void avance() {
		super.forward();
	}

	/**
	 * méthode pour fermer les pinces du robot de mannière synchrone
	 */
	public void fermePince() {
		motorPince.setSpeed((int)motorPince.getMaxSpeed());
		motorPince.rotate(-1200, true);
	}
	/**
	 * méthode pour fermer les pinces du robot de mannière asynchrone
	 */
	public void asyncFermePince() {
		motorPince.setSpeed((int)motorPince.getMaxSpeed());
		motorPince.rotate(-1200);
	}
	/**
	 * méthode pour ouvrir les pinces du robot de mannière synchrone
	 */
	public void ouvrePince() {
		motorPince.setSpeed((int)motorPince.getMaxSpeed());
		motorPince.rotate(1200, true);
	}
	/**
	 * méthode pour ouvrir les pinces du robot de mannière asynchrone
	 */
	public void asyncOuvrePince() {
		motorPince.setSpeed((int)motorPince.getMaxSpeed());
		motorPince.rotate(1200);
	}

	/**
	 * fixe une vitesse quand le robot fera des rotations
	 */
	public void setVitesseRotation() {
		super.setAngularSpeed(110);
	}
	/**
	 * fixe la vitesse maximale du robot. Nous avons soutrait -100 à cette valeur pour que la distance parcouru par le robot soit droite
	 */
	public void setVitesseMax() {
		super.setLinearSpeed(super.getMaxLinearSpeed()-100);
	}
	
	/**
	 * fait tourner le robot d'un certain angle de mannière synchrone
	 * @param x angle de rotation 
	 */
	public void rotate(double x) {
		this.setVitesseRotation();
		super.rotate(x, true);
		boussole = boussole+x%360;	
	}
	/**
	 * fait tourner le robot d'un certain angle de mannière asynchrone
	 * @param x angle de rotation 
	 */
	public void asyncRotate(double d) {
		this.setVitesseRotation();
		//		tourne a droite avec x > 0, tourne a gauche avec x < 0
		super.rotate(d);
		//boussole = boussole+Math.sqrt(Math.pow(d, 2))%360;
		boussole = boussole+d%360;

	}	

	/**
	 * permet d'orienter le robot vers un angle en fonction de l'orientation de base du robot
	 * @param angle angle vers lequel le robot doit s'orienter en fonction de sa boussole
	 */
	public void rotateBoussole(double angle) { 
		double boussole2;
		double angle2;
		if(angle == this.boussole) {
			return;
		}
		if(Math.abs(angle) == Math.abs(this.boussole) && Math.abs(angle) == 180) {
			return;
		}
		if(boussole < 0) {
			boussole2 = 360 + boussole;
		}else {
			boussole2 = boussole;
		}
		if(angle < 0) {
			angle2 = 360 + angle;
		}else {
			angle2 = angle;
		}
		if(angle2 > boussole2) {
			if(angle2 - boussole2 > 180) { 
				this.asyncRotate((angle2-360)-boussole2);
			}else { 
				this.asyncRotate(angle2 - boussole2);
			}	
		}else {
			if(boussole2 - angle2 < 180) {
				this.asyncRotate(angle2 - boussole2);
			}else { 	
				this.asyncRotate((360-boussole2)+angle2);
			}
		}
		this.boussole = angle;

	}

	/**
	 * méthode qui permet au robot de se rendre aux coordonnées données 
	 * @param x position du souhaité en x
	 * @param y position du souhaité en y
	 * @param gauche permet de savoir si notre robot a commencé à gauche ou à droite
	 */
	public void goToCoor(double x, double y, boolean gauche) {
		if (gauche==true) {
			if(y==this.posY && x==this.posX) {
				return;
			}
			this.setAngularSpeed(200);
			if (x >= this.posX && y <= this.posY) {
				rotateBoussole(0);
				double a;
				if(this.posY-y == 0) {
					a = 0;
				}else if (x-this.posX == 0) {
					a = 90;
				}else {
					a = Math.toDegrees(Math.atan((this.posY-y)/(x-this.posX)));
				}
				this.asyncRotate(a);
				forward(Math.sqrt((Math.pow(Math.abs(this.posX-x), 2)) + (Math.pow(Math.abs(this.posY-y), 2)) ),false);
				this.posY = y;
				this.posX = x;
			}
			else if (x >= this.posX && y >= this.posY) {
				rotateBoussole(-90);
				double a;
				if(x-this.posX == 0) {
					a = 0;
				}else if( y - this.posY == 0){
					a = 90;
				}else {	
					a = Math.toDegrees(Math.atan((x-this.posX)/(y-this.posY)));
				}
				this.asyncRotate(a);
				forward(Math.sqrt((Math.pow(Math.abs(this.posX-x), 2)) + (Math.pow(Math.abs(y-this.posY), 2)) ),false);
				this.posY = y;
				this.posX = x;
			}
			else if (x <= this.posX && y <= this.posY) {
				rotateBoussole(90);
				double a;
				if(x - this.posX == 0) {
					a = 0;
				}else if(this.posY-y == 0){
					a = 90;
				}else {
					a = Math.toDegrees(Math.atan((this.posX-x)/(this.posY-y)));
				}
				this.asyncRotate(a);
				forward(Math.sqrt((Math.pow(Math.abs(x-this.posX), 2)) + (Math.pow(Math.abs(this.posY-y), 2)) ),false);
				this.posY = y;
				this.posX = x;
			}else {
				rotateBoussole(180);
				double a;
				if(y - this.posY == 0) {
					a = 0;
				}else if(this.posX-x == 0) {
					a = 90;
				}else {
					a = Math.toDegrees(Math.atan((y-this.posY)/(this.posX-x)));
				}			
				this.asyncRotate(a);
				forward(Math.sqrt((Math.pow(Math.abs(x-this.posX), 2)) + (Math.pow(Math.abs(y-this.posY), 2)) ),false);
				this.posY = y;
				this.posX = x;
				this.setAngularSpeed(240);
			}
		}
		else {
			if(y==this.posY && x==this.posX) {
				return;
			}
			this.setAngularSpeed(200);
			if (x >= this.posX && y <= this.posY) {
				rotateBoussole(0);
				double a;
				if(this.posY-y == 0) {
					a = 0;
				}else if (x-this.posX == 0) {
					a = 90;
				}else {
					a = Math.toDegrees(Math.atan((this.posY-y)/(x-this.posX)));
				}
				this.asyncRotate(a);
				forward(Math.sqrt((Math.pow(Math.abs(this.posX-x), 2)) + (Math.pow(Math.abs(this.posY-y), 2)) ),false);
				this.posY = y;
				this.posX = x;
			}
			else if (x >= this.posX && y >= this.posY) {
				rotateBoussole(-90);
				double a;
				if(x-this.posX == 0) {
					a = 0;
				}else if( y - this.posY == 0){
					a = 90;
				}else {	
					a = Math.toDegrees(Math.atan((x-this.posX)/(y-this.posY)));
				}
				this.asyncRotate(a+180);
				forward(Math.sqrt((Math.pow(Math.abs(this.posX-x), 2)) + (Math.pow(Math.abs(y-this.posY), 2)) ),false);
				this.posY = y;
				this.posX = x;
			}
			else if (x <= this.posX && y <= this.posY) {
				rotateBoussole(90);
				double a;
				if(x - this.posX == 0) {
					a = 0;
				}else if(this.posY-y == 0){
					a = 90;
				}else {
					a = Math.toDegrees(Math.atan((this.posX-x)/(this.posY-y)));
				}
				this.asyncRotate(a);
				forward(Math.sqrt((Math.pow(Math.abs(x-this.posX), 2)) + (Math.pow(Math.abs(this.posY-y), 2)) ),false);
				this.posY = y;
				this.posX = x;
			}
			else {
				rotateBoussole(180);
				double a;
				if(y - this.posY == 0) {
					a = 0;
				}else if(this.posX-x == 0) {
					a = 90;
				}else {
					a = Math.toDegrees(Math.atan((y-this.posY)/(this.posX-x)));
				}			
				this.asyncRotate(a);
				forward(Math.sqrt((Math.pow(Math.abs(x-this.posX), 2)) + (Math.pow(Math.abs(y-this.posY), 2)) ),false);
				this.posY = y;
				this.posX = x;
				this.setAngularSpeed(240);
			}
		}
	}
}
