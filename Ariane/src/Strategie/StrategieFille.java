package Strategie;

import java.io.IOException;

import CamInfraRouge.Infrarouge;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class StrategieFille extends StrategieMere {
	/**
	 * Cette classe hérite de la classe StrategieMere. 
	 * Elle permet la définition de toute les stratégies en assamblant les méthodes de la classe mère.
	 * @author Adrien
	 */
	
		
	/**
	 * Constructeur de la classe
	 * @param portCS port du capteur de couleur
	 * @param portTS portCS port du capteur de couleur
	 * @param portUSS port du capteur à ultrason
	 * @param gauche si le robot commence à gauhce ou pas
	 * @param ligneRouge si le robot commence sur une ligne rouge ou pas
	 * @throws IOException si erreur avec le fichier Properties
	 */
	public StrategieFille(Port portCS, Port portTS, Port portUSS, boolean gauche, boolean ligneRouge) throws IOException {
		super(portCS, portTS, portUSS, gauche, ligneRouge);	
	}


	/**
	 * Strategie qu'on appelle au début de la partie.
	 * Elle va chercher les quatre premier palets en fonction d'ou le robot commence
	 */
	public void strategieDepart() {
		this.premierPalet();
		if (this.gauche==true && this.ligneRouge==true) {
			//2eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="green") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(90);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.motors.asyncRotate(40);
			this.vaMarquer();
			//3eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="green") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(-90);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.vaMarquer();
			//4eme palet
			this.motors.asyncRotate(30);
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="black") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(-90);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.vaMarquer();
		}
		else if (this.gauche==true && this.ligneRouge==false) {
			//2eme palet
			this.motors.asyncRotate(20);
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="green") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(-90);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.vaMarquer();
			//3eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="green") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(90);
			this.motors.asyncRotate(-10);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.vaMarquer();
			//4eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="black") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(90);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.motors.asyncRotate(20);
			this.vaMarquer();

		}
		else if (this.gauche==false && this.ligneRouge==true) {
			//2eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="blue") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(-90);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.motors.asyncRotate(-20);
			this.vaMarquer();
			// 3eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="blue") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(70);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.motors.asyncRotate(-20);
			this.vaMarquer();
			//4eme palet
			this.motors.asyncRotate(-20);
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="black") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(90);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.motors.asyncRotate(-20);
			this.vaMarquer();
		}
		else {
			//2eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="blue") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(70);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.vaMarquer();
			//3eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="blue") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(-80);
			this.vaPalet();
			this.motors.rotateBoussole(0);;
			this.vaMarquer();
			//4eme palet
			this.motors.avance();
			while(this.colorSensor.getCouleur()!="black") {
				Delay.msDelay(10);
			}
			this.motors.stop();
			this.motors.asyncRotate(-95);
			this.vaPalet();
			this.motors.rotateBoussole(0);
			this.motors.asyncRotate(-20);
			this.vaMarquer();
		}	
	}
	
	/**
	 * On appelle cette stratégie à la fin de strategieDepart.
	 * Dans cette méthode, on donne des coordonées au robot ou il doit effectuer la méthode vaChercherPalet de la classe mére et apres marquer.
	 */
	public void strategieFin()  {
		if (this.gauche==true) {
			this.motors.goToCoor(2200, 1000, gauche);
		}
		else {
			this.motors.goToCoor(800, 1000, gauche);
		}
		this.motors.rotateBoussole(180);
		this.vaChercherPalet();
		this.motors.asyncRotate(20);
		this.motors.setBoussole(0);
		this.vaMarquer();
		
		for (int i=0; i<5;i++) {
			if (gauche==true) {
				this.motors.goToCoor(1700, 1050, this.gauche); 
			}
			else {
				this.motors.goToCoor(1300, 1000, this.gauche);  
			}
			this.motors.rotateBoussole(180);
			this.vaChercherPalet();
			this.motors.asyncRotate(20);
			this.motors.setBoussole(0);
			this.vaMarquer();
		}
	}




	public static void main(String[] args) throws IOException {
		StrategieFille sf=null;
		int choixCote=0;
		int choixLigne=0;
		int choixStrategie;

		//choix du coté de départ de l'arène
		System.out.println("Gauche ?");
		System.out.println("Droite ?");
		while(choixCote==0) {
			Delay.msDelay(10);
			if (Button.LEFT.isDown()) {
				choixCote=1;
			}
			else if (Button.RIGHT.isDown()) {
				choixCote=2;
			}
		}
		//choix de la ligne de départ de l'arène
		System.out.println("Ligne rouge ? G");
		System.out.println("Ligne jaune ? D");
		while(choixLigne==0) {
			Delay.msDelay(10);
			if (Button.LEFT.isDown()) {
				choixLigne=1;
			}
			else if (Button.RIGHT.isDown()) {
				choixLigne=2;
			}
		}
		if (choixCote==1 && choixLigne==1) {
			sf=new StrategieFille(SensorPort.S1, SensorPort.S2, SensorPort.S3, true,true);
			sf.strategieDepart();
			//sf.premierPalet();
			sf.strategieFin();
		}
		else if (choixCote==1 && choixLigne==2) {
			sf=new StrategieFille(SensorPort.S1, SensorPort.S2, SensorPort.S3, true,false);
			sf.strategieDepart();
			//sf.premierPalet();
			sf.strategieFin();
		}
		else if (choixCote==2 && choixLigne==1) {
			sf=new StrategieFille(SensorPort.S1, SensorPort.S2, SensorPort.S3, false,true);
			sf.strategieDepart();
			//sf.premierPalet();
			sf.strategieFin();
		}
		else {
			sf=new StrategieFille(SensorPort.S1, SensorPort.S2, SensorPort.S3, false,false);
			sf.strategieDepart();
			//sf.premierPalet();
			sf.strategieFin();
		}
	}

}
