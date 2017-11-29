package mindstorm;

import lejos.hardware.ev3.LocalEV3;
import lejos.robotics.Color;
import mindstorm.listeners.ApplicationListener;
import mindstorm.listeners.ColorTeaching;
import mindstorm.listeners.FollowLine;

/**
 * Lance un programme écouteur
 *
 * @see mindstorm.listeners.ApplicationListener
 */
public abstract class Application {
    /*
    private ApplicationListener applicationListener;

    public Application(ApplicationListener applicationListener) {
        this.applicationListener = applicationListener;
    }

    public void run() {
        run(applicationListener);
    }
    */

    public static void run(ApplicationListener applicationListener) {
        applicationListener.start();
        while (applicationListener.isRunning())
            applicationListener.act();
        applicationListener.end();
    }

    public static void main(String[] args) {
        Engine engine = new Engine(LocalEV3.get());

        // FollowLine followLine = new FollowLine(engine, Color.BLACK);

        ColorTeaching colorTeaching = new ColorTeaching(engine);

        Application.run(colorTeaching);
    }
}
