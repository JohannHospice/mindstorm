package mindstorm;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import mindstorm.listener.ApplicationListener;
import mindstorm.program.ColorRecognizing;
import mindstorm.program.ColorTeaching;
import mindstorm.tools.Engine;

/**
 * Lance un programme écouteur
 *
 * @see mindstorm.listener.ApplicationListener
 */
public final class Application {

    private static void run(ApplicationListener applicationListener) {
        applicationListener.start();
        while (applicationListener.isRunning())
            applicationListener.act();
        applicationListener.end();
    }

    private static void colorTeaching(Engine engine) {
        ColorTeaching colorTeaching = new ColorTeaching(engine.getColorSensor());

        Application.run(colorTeaching);

        ColorRecognizing colorRecognizing = new ColorRecognizing(engine.getColorSensor(), colorTeaching.getMemento());

        Application.run(colorRecognizing);
    }

    private static void colorFollowing(Engine engine) {
        ColorTeaching colorTeaching = new ColorTeaching(engine.getColorSensor());

        Application.run(colorTeaching);

        /*
        ColorFollowing colorFollowing = new ColorFollowing(engine, colorTeaching.getMemento());

        Application.run(colorFollowing);
        */
    }

    public static void main(String[] args) {
        Engine engine = new Engine(SensorPort.S1, MotorPort.A, MotorPort.B);
        colorTeaching(engine);
        engine.close();
    }
}
