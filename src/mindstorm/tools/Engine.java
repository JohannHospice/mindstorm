package mindstorm.tools;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.HiTechnicColorSensor;

/**
 * Contient tous les composants moteurs du mindstorm
 */
public class Engine {
    private final EV3LargeRegulatedMotor lMotor;
    private final EV3LargeRegulatedMotor rMotor;
    private final HiTechnicColorSensor colorSensor;

    public Engine(Port colorSensorPort, Port leftMotorPort, Port rightMotorPort) {
        lMotor = new EV3LargeRegulatedMotor(leftMotorPort);
        rMotor = new EV3LargeRegulatedMotor(rightMotorPort);
        colorSensor = new HiTechnicColorSensor(colorSensorPort);
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
        colorSensor.close();
    }

    public HiTechnicColorSensor getColorSensor() {
        return colorSensor;
    }
}
