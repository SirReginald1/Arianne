package Sensors;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class UltraSonicSensor {
	/**
	 * Classe qui gère le capteur à ultrason
	 * @author Adrien
	 * @attribut nbMesure nombre de mesure que fait le robot 
	 */
	private EV3UltrasonicSensor ultrasensor;
	private SampleProvider ultrasonProvider;
	private float[] ultrasonSample;
	private final static int nbMesure=10;
	
	/**
	 * Constructeur de la classe
	 * @param port indique le port du capteur d'ultrason
	 */
	public UltraSonicSensor(Port port) {
		this.ultrasensor=new EV3UltrasonicSensor(port);
		this.ultrasonProvider=this.ultrasensor.getDistanceMode();
		this.ultrasonSample= new float[this.ultrasonProvider.sampleSize()];		
	}
	
	/**
	 * @return la distance perçue par le capteur en faisant la moyenne de 10 mesures
	 */
	public float doMeasure() {
		this.ultrasonSample=new float[nbMesure];
		float somme=0;
		for(int i=0; i<this.ultrasonSample.length; i++) {
			this.ultrasonProvider.fetchSample(ultrasonSample, 0);
		}
		for (int i=0; i<this.ultrasonSample.length; i++) {
			somme+=this.ultrasonSample[i];
		}
		return somme/nbMesure;
	}

}
