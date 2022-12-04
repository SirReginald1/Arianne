package Sensors;
import java.util.*;  
import java.io.*; 
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;

public class CalibrateColorSensor {
	/**
	 * Classe permettant de calibrer le capteur de couleur du robot
	 * @author Adrien
	 * @attribut pFile fichier ou seront stocké les données
	 * @attribut nbMesure nombre de mesure pour calibrer chaque couleur
	 */
	private static SampleProvider colorProvider;
	private static EV3ColorSensor colorSensor;
	private Properties pFile;
	private final static int nbMesure=3;


	/**
	 * constructeur de la classe
	 * @param port indique le port du capteur de couleur
	 * @throws Exception si erreur avec le fichier Properties
	 */
	public CalibrateColorSensor(Port port) throws Exception {
		this.colorSensor = new EV3ColorSensor(port);
		colorProvider = new MeanFilter(colorSensor.getRGBMode(), 1);
		colorSensor.setFloodlight(Color.WHITE);
		this.pFile=this.calibre();
		this.pFile.store(new FileWriter("calibrate.properties"), "Calibrage de toute les couleurs");


	}
	/**
	 * méthode permettant de calibrer le capteur de couleur 
	 * @return un fichier properties ou sont rangés les données des couleurs
	 */
	public Properties calibre() {
		Properties p=new Properties();
		float somme1=0;
		float somme2=0;
		float somme3=0;
		for (int i=0; i<nbMesure; i++) {
			System.out.println("Press enter to calibrate black");
			Button.ENTER.waitForPressAndRelease();
			float[] black = new float[this.colorProvider.sampleSize()];
			this.colorProvider.fetchSample(black, 0);
			somme1+=black[0];
			somme2+=black[1];
			somme3+=black[2];
		}		
		p.setProperty("black", somme1/nbMesure+" "+somme2/nbMesure+" "+somme3/nbMesure);

		somme1=0;
		somme2=0;
		somme3=0;
		for (int i=0; i<nbMesure; i++) {
			System.out.println("Press enter to calibrate white");
			Button.ENTER.waitForPressAndRelease();
			float[] white = new float[this.colorProvider.sampleSize()];
			this.colorProvider.fetchSample(white, 0);
			somme1+=white[0];
			somme2+=white[1];
			somme3+=white[2];
		}		
		p.setProperty("white", somme1/nbMesure+" "+somme2/nbMesure+" "+somme3/nbMesure);

		somme1=0;
		somme2=0;
		somme3=0;
		for (int i=0; i<nbMesure; i++) {
			System.out.println("Press enter to calibrate red");
			Button.ENTER.waitForPressAndRelease();
			float[] red = new float[this.colorProvider.sampleSize()];
			this.colorProvider.fetchSample(red, 0);
			somme1+=red[0];
			somme2+=red[1];
			somme3+=red[2];
		}		
		p.setProperty("red", somme1/nbMesure+" "+somme2/nbMesure+" "+somme3/nbMesure);

		somme1=0;
		somme2=0;
		somme3=0;
		for (int i=0; i<nbMesure; i++) {
			System.out.println("Press enter to calibrate blue");
			Button.ENTER.waitForPressAndRelease();
			float[] blue = new float[this.colorProvider.sampleSize()];
			this.colorProvider.fetchSample(blue, 0);
			somme1+=blue[0];
			somme2+=blue[1];
			somme3+=blue[2];
		}		
		p.setProperty("blue", somme1/nbMesure+" "+somme2/nbMesure+" "+somme3/nbMesure);

		somme1=0;
		somme2=0;
		somme3=0;
		for (int i=0; i<nbMesure; i++) {
			System.out.println("Press enter to calibrate green");
			Button.ENTER.waitForPressAndRelease();
			float[] green = new float[this.colorProvider.sampleSize()];
			this.colorProvider.fetchSample(green, 0);
			somme1+=green[0];
			somme2+=green[1];
			somme3+=green[2];
		}		
		p.setProperty("green", somme1/nbMesure+" "+somme2/nbMesure+" "+somme3/nbMesure);

		somme1=0;
		somme2=0;
		somme3=0;
		for (int i=0; i<nbMesure; i++) {
			System.out.println("Press enter to calibrate yellow");
			Button.ENTER.waitForPressAndRelease();
			float[] yellow = new float[this.colorProvider.sampleSize()];
			this.colorProvider.fetchSample(yellow, 0);
			somme1+=yellow[0];
			somme2+=yellow[1];
			somme3+=yellow[2];
		}		
		p.setProperty("yellow", somme1/nbMesure+" "+somme2/nbMesure+" "+somme3/nbMesure);

		somme1=0;
		somme2=0;
		somme3=0;
		for (int i=0; i<nbMesure; i++) {
			System.out.println("Press enter to calibrate gray");
			Button.ENTER.waitForPressAndRelease();
			float[] gray = new float[this.colorProvider.sampleSize()];
			this.colorProvider.fetchSample(gray, 0);
			somme1+=gray[0];
			somme2+=gray[1];
			somme3+=gray[2];
		}		
		p.setProperty("gray", somme1/nbMesure+" "+somme2/nbMesure+" "+somme3/nbMesure);

		return p;
	}	

}
