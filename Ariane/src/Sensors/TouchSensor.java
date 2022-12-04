package Sensors;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;
import lejos.utility.Delay;

public class TouchSensor {
	/**
	 * Cette classe s'occupe de gérer le capteur de touché
	 * @author Adrien
	 */
	private static float[] touchSample;
	private static SampleProvider touchProvider;
	private static EV3TouchSensor touchSensor;
	
	/**
	 * Constructeur de la classe
	 * @param port indique le port du capteur de touché
	 */
	public TouchSensor(Port port) {
		this.touchSensor = new EV3TouchSensor(port);
		this.touchProvider=this.touchSensor.getTouchMode();
		this.touchSample=new float[this.touchProvider.sampleSize()];
	}
	
	/**
	 * @return true si le capteur est touché
	 */
	public boolean isPressed() {
		this.touchProvider.fetchSample(touchSample, 0);
		if (touchSample[0] == 0) {
			return false;
		}else {
			return true;
		}

	}
}