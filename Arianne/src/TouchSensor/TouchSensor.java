package TouchSensor;

import lejos.robotics.SampleProvider;
import lejos.robotics.filter.AbstractFilter;


public class TouchSensor  extends AbstractFilter{


	float [] sample;


	public TouchSensor(SampleProvider source) {
		super(source);
		sample = new float[sampleSize];
	}

	public boolean isPressed() {
		super.fetchSample(sample,0);
		if (sample[0] == 0) {
			return false;
		}else {
			return true;
		}

	}

}
