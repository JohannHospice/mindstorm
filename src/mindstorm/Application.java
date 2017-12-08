package mindstorm;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import mindstorm.listeners.*;
import mindstorm.tools.Engine;

/**
 * Lance un programme écouteur
 *
 * @see mindstorm.listeners.ApplicationListener
 */
public abstract class Application {

    private static void run(ApplicationListener applicationListener) {
        applicationListener.start();
        while (applicationListener.isRunning())
            applicationListener.act();
        applicationListener.end();
    }

    public static void main(String[] args) {
        Engine engine = new Engine(SensorPort.S1, MotorPort.A, MotorPort.B);

        ColorTeaching colorTeaching = new ColorTeaching(engine.getColorSensor());

        Application.run(colorTeaching);

        ColorRecognizing colorRecognizing = new ColorRecognizing(engine.getColorSensor(), colorTeaching.getMemento());

        Application.run(colorRecognizing);

        engine.close();
    }
}
