package SensorColor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;

public class ColorSensor {

	

	private static float[] colorSample;
	private static SampleProvider colorProvider;
	private static EV3ColorSensor colorSensor;
	private final static double ERROR = 0.01;
	private float[] tabBlack;
	private float[] tabWhite;
	private float[] tabRed;
	private float[] tabBlue;
	private float[] tabGreen;
	private float[] tabYellow;
	private float[] tabGray;

	public ColorSensor(Port port) throws IOException {
		this.colorSensor = new EV3ColorSensor(port);
		colorProvider = new MeanFilter(colorSensor.getRGBMode(), 1);
		colorSensor.setFloodlight(Color.WHITE);
		this.colorSample=new float[this.colorProvider.sampleSize()];
		this.initializeTab();
		//		System.out.println(this.toString(this.tabBlack));
		//		Delay.msDelay(100);
		//		System.out.println(this.toString(this.tabWhite));
		//		Delay.msDelay(100);
		//		System.out.println(this.toString(this.tabRed));
		//		Delay.msDelay(40000);
	}

	public void initializeTab() throws IOException {
		FileReader vMoy= new FileReader("calibrate.properties");
		Properties p = new Properties();
		p.load(vMoy);

		this.tabBlack=new float[3];
		String chaineBlack=p.getProperty("black");
		int ind=0;
		int j=0;
		for (int i=0;i<chaineBlack.length(); i++) {
			if (chaineBlack.charAt(i)==' ') {
				this.tabBlack[ind]=Float.parseFloat(chaineBlack.substring(j,i));
				j=i+1;
				ind++;
			}
		}
		this.tabBlack[ind]=Float.parseFloat(chaineBlack.substring(j,chaineBlack.length()));

		this.tabWhite=new float[3];
		String chaineWhite=p.getProperty("white");
		ind=0;
		j=0;
		for (int i=0;i<chaineWhite.length(); i++) {
			if (chaineWhite.charAt(i)==' ') {
				this.tabWhite[ind]=Float.parseFloat(chaineWhite.substring(j,i));
				j=i+1;
				ind++;
			}
		}
		this.tabWhite[ind]=Float.parseFloat(chaineWhite.substring(j,chaineWhite.length()));

		this.tabRed=new float[3];
		String chaineRed=p.getProperty("red");
		ind=0;
		j=0;
		for (int i=0;i<chaineRed.length(); i++) {
			if (chaineRed.charAt(i)==' ') {
				this.tabRed[ind]=Float.parseFloat(chaineRed.substring(j,i));
				j=i+1;
				ind++;
			}
		}
		this.tabRed[ind]=Float.parseFloat(chaineRed.substring(j,chaineRed.length()));

		this.tabBlue=new float[3];
		String chaineBlue=p.getProperty("blue");
		ind=0;
		j=0;
		for (int i=0;i<chaineBlue.length(); i++) {
			if (chaineBlue.charAt(i)==' ') {
				this.tabBlue[ind]=Float.parseFloat(chaineBlue.substring(j,i));
				j=i+1;
				ind++;
			}
		}
		this.tabBlue[ind]=Float.parseFloat(chaineBlue.substring(j,chaineBlue.length()));

		this.tabGreen=new float[3];
		String chaineGreen=p.getProperty("green");
		ind=0;
		j=0;
		for (int i=0;i<chaineGreen.length(); i++) {
			if (chaineGreen.charAt(i)==' ') {
				this.tabGreen[ind]=Float.parseFloat(chaineGreen.substring(j,i));
				j=i+1;
				ind++;
			}
		}
		this.tabGreen[ind]=Float.parseFloat(chaineGreen.substring(j,chaineGreen.length()));

		this.tabYellow=new float[3];
		String chaineYellow=p.getProperty("yellow");
		ind=0;
		j=0;
		for (int i=0;i<chaineYellow.length(); i++) {
			if (chaineYellow.charAt(i)==' ') {
				this.tabYellow[ind]=Float.parseFloat(chaineYellow.substring(j,i));
				j=i+1;
				ind++;
			}
		}
		this.tabYellow[ind]=Float.parseFloat(chaineYellow.substring(j,chaineYellow.length()));

		this.tabGray=new float[3];
		String chaineGray=p.getProperty("gray");
		ind=0;
		j=0;
		for (int i=0;i<chaineGray.length(); i++) {
			if (chaineGray.charAt(i)==' ') {
				this.tabGray[ind]=Float.parseFloat(chaineGray.substring(j,i));
				j=i+1;
				ind++;
			}
		}
		this.tabGray[ind]=Float.parseFloat(chaineGray.substring(j,chaineGray.length()));	
	}



	public String getCouleur() {
		//prend la mesure de la couleur perÃ§u
		float[] sample = new float[colorProvider.sampleSize()];
		colorProvider.fetchSample(sample, 0);

		double scalaireBlack=this.scalaire(sample, this.tabBlack);		
		double scalaireWhite=this.scalaire(sample, this.tabWhite);		
		double scalaireRed=this.scalaire(sample, this.tabRed);		
		double scalaireBlue=this.scalaire(sample, this.tabBlue);		
		double scalaireGreen=this.scalaire(sample, this.tabGreen);
		double scalaireYellow=this.scalaire(sample, this.tabYellow);	
		double scalaireGray=this.scalaire(sample, this.tabGray);

		double[] tabScalaire= {scalaireBlack,scalaireWhite, scalaireRed, scalaireBlue, scalaireGreen, scalaireYellow, scalaireGray};
		double minScal = scalaireBlack;

		for (int i=1; i<tabScalaire.length; i++) {
			if (tabScalaire[i]<minScal) {
				minScal=tabScalaire[i];
			}
		}
		if (minScal==tabScalaire[0]) {
			return "black";
		}
		else if(minScal==tabScalaire[1]) {
			return "white";
		}
		else if(minScal==tabScalaire[2]) {
			return "red";
		}
		else if(minScal==tabScalaire[3]) {
			return "blue";
		}
		else if(minScal==tabScalaire[4]) {
			return "green";
		}
		else if(minScal==tabScalaire[5]) {
			return "yellow";
		}
		else if(minScal==tabScalaire[6]) {
			return "gray";
		}
		else {
			return "none";
		}
	}

	public String toString(float[] tab) {
		String ch="";
		for (int i=0;i<tab.length;i++) {
			ch+=tab[i]+" ; ";
		}
		return ch;
	}

	public boolean onPath() {
		float[] sample = new float[colorProvider.sampleSize()];
		colorProvider.fetchSample(sample, 0);
		double scalaire = scalaire(sample, colorSample);
		System.out.println(scalaire < ERROR);
		//Button.ENTER.waitForPressAndRelease();
		return scalaire(sample, colorSample) < ERROR;

	}

	public static double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}

}
