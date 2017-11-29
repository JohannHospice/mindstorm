package mindstorm.tools;

import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;

/**
 * Contient tous les composants moteurs du mindstorm
 */
public class Engine {
    private static final String PORT_COLOR = "S1";
    private static final String PORT_MOTOR_RIGHT = "A";
    private static final String PORT_MOTOR_LEFT = "B";

    private final EV3LargeRegulatedMotor lMotor;
    private final EV3LargeRegulatedMotor rMotor;
    private final ColorSensorHandler cSensorHandler;

    public Engine(EV3 ev3) {
        lMotor = new EV3LargeRegulatedMotor(ev3.getPort(PORT_MOTOR_RIGHT));
        rMotor = new EV3LargeRegulatedMotor(ev3.getPort(PORT_MOTOR_LEFT));
        cSensorHandler = new ColorSensorHandler(new EV3ColorSensor(ev3.getPort(PORT_COLOR)));
    }


    public ColorSensorHandler getColorSensorHandler() {
        return cSensorHandler;
    }

    public EV3LargeRegulatedMotor getLeftMotor() {
        return lMotor;
    }

    public EV3LargeRegulatedMotor getRightMotor() {
        return rMotor;
    }


	public void close() {
		lMotor.close();
		rMotor.close();
		cSensorHandler.close();
	}
}
