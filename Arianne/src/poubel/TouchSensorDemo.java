package poubel;


import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import lejos.hardware.Battery;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.MindsensorsGlideWheelMRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.EncoderMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TouchSensorDemo  {

	

		

			//Robot Configuration
			private static EV3TouchSensor touch1 = new EV3TouchSensor(SensorPort.S1);

			//Configuration
			private static int HALF_SECOND = 500;

			public static void main(String[] args) {



				final SampleProvider sp = touch1.getTouchMode();
				int touchValue = 0;

				//Control loop
				final int iteration_threshold = 20;
				for(int i = 0; i <= iteration_threshold; i++) {

					float [] sample = new float[sp.sampleSize()];
					sp.fetchSample(sample, 0);
					touchValue = (int)sample[0];

					System.out.println("Iteration: {}" + i);
					System.out.println("Touch: {}" + touchValue);

					Delay.msDelay(HALF_SECOND);
				}

				

			}
		}




