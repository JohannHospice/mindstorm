import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.chassis.*;
import lejos.robotics.navigation.*;

public class Application {
    public static void main(String[] args) {
        LCD.clear();
        LCD.drawString("Hello World", 0, 5);
        Button.waitForAnyPress();
        LCD.clear();
        LCD.refresh();
        
        Brick brick=BrickFinder.getDefault();
        EV3LargeRegulatedMotor motor = new EV3LargeRegulatedMotor(brick.getPort("A"));
        
	    motor.forward();
	    motor.rotate(10);
	    motor.stop();
    }
}
