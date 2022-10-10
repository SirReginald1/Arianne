package senseur;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.*;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.*;
import lejos.utility.Delay;

import java.io.IOException;

import lejos.hardware.Button;

import java.util.*;


public class Ultrason {
	
	private EV3UltrasonicSensor sensor;
	
	/**
	 * 1 data point per angle of rotation. To be used during a 360 search.
	 */
	private float[] angleData; 
	
	/**
	 * The distance at which is no longer a palet.
	 */
	public final static float PALET_DISTANCE = (float) 0.3;
	
	/**
	 * The frequency of the sampling.
	 */
	private final static int frequency = 1;
	
	
	/**
	 * Visual memory.
	 */
	private float[] memory;
	
	int idx = 0;
	
	
	public Ultrason() throws IOException {
		sensor = new EV3UltrasonicSensor(SensorPort.S2);
		/*
		angleData = new ArrayList<Float>();
		angleData.ensureCapacity(360);
		memory = new ArrayList<Float>();
		memory.ensureCapacity(10);
		*/
		
		
		memory = new float[10]; // Size of the memory + buffer
		angleData = new float[360];
	}
	
	
	
	
	/**
	 * Takes a sample and adds it to the memory array.
	 */
	public void takeSample() {
		//if(idx < )
	}

	public static void main(String[] args) throws IOException {
		// get a port instance
		//Port port = LocalEV3.get().getPort("S2");
		
		
		
		/*
		// Taken code test
		//--------------------------------------------------------------------
		
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S2);

		// Get an instance of the Ultrasonic EV3 sensor
		
		
		
		float frequency = 1;
		SampleProvider sp = new PublishFilter(sensor, "Test String", 0);
		float[] sample = new float[sp.sampleSize()];

		
		while(!Button.ESCAPE.isDown()) {
			
			sp.fetchSample(sample, 0);
			LCD.clear(4);
			LCD.drawString("Sonic: "+sample[0], 0, 4);
			
			if(sample[0] < 0.1) {
				System.out.println(0.1);
			}
			else if(sample[0] < 0.3) {
				System.out.println(0.3);
			}
			
			Delay.msDelay(1000);
		
		}
		
		sensor.close();
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);
		
		//-----------------------------------------------------------------------
		*/
		
		
		// stack a filter on the sensor that gives the running average of the last 5 samples
		//SampleProvider average = new MeanFilter(distance, 5);

		// initialise an array of floats for fetching samples
		//float[] sample = new float[average.sampleSize()];

		// fetch a sample
		//average.fetchSample(sample, 0);

	}

}
