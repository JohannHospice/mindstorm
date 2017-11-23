import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class Application {
    private static final int SPEED = 75;
    private static final int ROTATION_PERCENT = 90;

    private static final String PORT_COLOR = "S1";
    private static final String PORT_MOTOR_RIGHT = "A";
    private static final String PORT_MOTOR_LEFT = "B";

    private static EV3ColorSensor cSensor;
    private static EV3LargeRegulatedMotor lMotor;
    private static EV3LargeRegulatedMotor rMotor;

    static {
        EV3 ev3 = LocalEV3.get();
        cSensor = new EV3ColorSensor(ev3.getPort(PORT_COLOR));
        rMotor = new EV3LargeRegulatedMotor(ev3.getPort(PORT_MOTOR_RIGHT));
        lMotor = new EV3LargeRegulatedMotor(ev3.getPort(PORT_MOTOR_LEFT));
    }

    private static int percentTo(int a, int b) {
        return a * b / 100;
    }

    private static void followLine(int colorFollowed) {
        int minSpeed = percentTo(SPEED, ROTATION_PERCENT);
        while (true) {
            if (cSensor.getColorID() == colorFollowed) {
                lMotor.setSpeed(minSpeed);
                rMotor.setSpeed(SPEED);
            } else {
                lMotor.setSpeed(SPEED);
                rMotor.setSpeed(minSpeed);
            }
            lMotor.forward();
            rMotor.forward();
        }
    }

    public static void main(String[] args) {
        followLine(Color.BLACK);
    }
}
