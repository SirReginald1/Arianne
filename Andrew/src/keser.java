
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.MindsensorsGlideWheelMRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class keser  {

	public static void main(String[] args) {
		int a = 0;
		// motor A roue droite
		// motor B Pince
		// motor C roue gauche

		RegulatedMotor motorGauche = new EV3LargeRegulatedMotor(MotorPort.A);
		// Bouge la roue droite via m.rotate
		RegulatedMotor pince = new EV3MediumRegulatedMotor(MotorPort.B);

		RegulatedMotor motorDroite = new EV3LargeRegulatedMotor(MotorPort.C);
		//        RegulatedMotor leftMotor = new MindsensorsGlideWheelMRegulatedMotor(MotorPort.B);

		//        EncoderMotor em = new UnregulatedMotor(MotorPort.C);
		//        em.forward();

		pince.rotate(-1400);
		Delay.msDelay(2000);
//		motorGauche.rotate(720, true);
//		motorDroite.rotate(720, true);
//		Delay.msDelay(2000);
//		
//		motorGauche.rotate(220, true);
//		motorDroite.rotate(-220, true);
//		Delay.msDelay(2000);
//		
//		motorGauche.rotate(720, true);
//		motorDroite.rotate(720, true);
//		Delay.msDelay(2000);
//		
//		motorGauche.rotate(220, true);
//		motorDroite.rotate(-220, true);
//		Delay.msDelay(2000);
//		
//		motorGauche.rotate(720, true);
//		motorDroite.rotate(720, true);
//		Delay.msDelay(2000);
//		
//		motorGauche.rotate(220, true);
//		motorDroite.rotate(-220, true);
//		Delay.msDelay(2000);
//		
//		motorGauche.rotate(720, true);
//		motorDroite.rotate(720, true);
//		Delay.msDelay(2000);
//		
//		motorGauche.rotate(220, true);
//		motorDroite.rotate(-220, true);
//		Delay.msDelay(2000);





		// rotate 360 roue droite et gauche = 16 CM
		//        leftMotor.rotate(190);

	}

}


