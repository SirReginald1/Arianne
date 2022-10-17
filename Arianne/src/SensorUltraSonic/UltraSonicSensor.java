package SensorUltraSonic;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class UltraSonicSensor {

	private EV3UltrasonicSensor ultrasensor;
	private SampleProvider ultrasonProvider;
	private float[] ultrasonSample;
	private final static int nbMesure=10;
	
	public UltraSonicSensor(Port port) {
		this.ultrasensor=new EV3UltrasonicSensor(port);
		this.ultrasonProvider=this.ultrasensor.getDistanceMode();
		this.ultrasonSample= new float[this.ultrasonProvider.sampleSize()];		
	}
	
	public float doMeasure() {
		this.ultrasonSample=new float[nbMesure];
		float somme=0;
		for(int i=0; i<this.ultrasonSample.length; i++) {
			this.ultrasonProvider.fetchSample(ultrasonSample, 0);
			//obligé ne mettre un delay sinon ça ne marche pas ?
			//Delay.msDelay(10);
		}
		for (int i=0; i<this.ultrasonSample.length; i++) {
			somme+=this.ultrasonSample[i];
		}
		return somme/nbMesure;
	}


}
