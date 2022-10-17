package moteur;

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

public class Roue  extends MovePilot {

	static RegulatedMotor motorGauche = new EV3LargeRegulatedMotor(MotorPort.A);
	static RegulatedMotor motorPince = new EV3MediumRegulatedMotor(MotorPort.B);
	static RegulatedMotor motorDroite = new EV3LargeRegulatedMotor(MotorPort.C);
	static Wheel leftWheel = WheeledChassis.modelWheel(motorGauche, 56).offset(60.5);
	static Wheel rightWheel = WheeledChassis.modelWheel(motorDroite, 56).offset(-60.5);
	static Chassis myChassis = new WheeledChassis( new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	int boussole;
	
	public Roue() {
		super(myChassis);
		this.boussole = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void rotate(int x) {
//		tourne a droite avec x > 0, tourne a gauche avec x < 0
		super.rotate(x);
		boussole = boussole+x%360;
		
	}
	
	public int getBoussole() {
		return this.boussole;
	}
	
	public void forward(int x) {
		
		super.travel(x);
	}
	
	public void backward( int x) {
		super.travel(-x);
	}
	
	public void fermePince() {

		motorPince.rotate(-1500);
	}
	
	public void ouvrePince() {
		motorPince.rotate(1500);
	}
	
	public void setVitesseRotation() {
		super.setAngularSpeed(50);
	}
	
	public void setVitesseDeplace() {
		super.setLinearSpeed(super.getMaxLinearSpeed());
	}
	
	public void arret() {
		super.stop();
	}






	public static void main(String[] args) { 

//		RegulatedMotor motorGauche = new EV3LargeRegulatedMotor(MotorPort.A);
//		RegulatedMotor motorPince = new EV3MediumRegulatedMotor(MotorPort.B);
//		RegulatedMotor motorDroite = new EV3LargeRegulatedMotor(MotorPort.C);
//		Wheel leftWheel = WheeledChassis.modelWheel(motorGauche, 56).offset(60.5);
//		Wheel rightWheel = WheeledChassis.modelWheel(motorDroite, 56).offset(-60.5);
//		Chassis myChassis = new WheeledChassis( new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
//		MovePilot pilot = new MovePilot(myChassis);
		
//		Roues k = new Roues();
//		k.setLinearSpeed(WHEEL_SIZE_EV3);
//		k.setAngularSpeed(WHEEL_SIZE_EV3);
		
//		motorPince.rotate(-1500);
//		Delay.msDelay(4000);
		
		
//		System.out.println(k.getBoussole());
//		k.rotate(180);
//		System.out.println(k.getBoussole());
//		k.travel(100);
//		System.out.println(k.getBoussole());
//		k.rotate(-90);
//		System.out.println(k.getBoussole());
//		k.travel(500);
//		System.out.println(k.getBoussole());
//		System.out.println(pilot.getMaxLinearSpeed());
//		System.out.println(pilot.getMaxAngularSpeed());
//		pilot.travel(150);         // cm

//		pilot.travel(-150);  //  move backward for 50 cm
//		while(pilot.isMoving())Thread.yield();
//		pilot.rotate(-90);
//		pilot.rotateTo(270);
//		pilot.stop();


	}

}
