package mindstorm.program;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.Color;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * algorithme:
 * si à gauche de la ligne alors tourner à droite
 * sinon si à droite de la ligne alors tourner à gauche
 */
public class ColorFollowingV3 extends ColorApplicationListener {
    private final EV3LargeRegulatedMotor lMotor, rMotor;
    private final ColorList colorList = new ColorList();

    private boolean running = true;

    private float kp = 10, speed = 50;
    private double mid;

    public ColorFollowingV3(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine.getColorSensor());
        lMotor = engine.getLeftMotor();
        rMotor = engine.getRightMotor();
        for (ColorList colorSample : colorSamples)
            colorList.add(colorSample.getAverage());
    }

    private static int percentTo(int a, int b) {
        return a * b / 100;
    }

    @Override
    public void start() {
        System.out.println("start:colorFollowing");
        lMotor.forward();
        rMotor.forward();

        mid = colorList.get(0).deltaE(colorList.get(1));
    }

    @Override
    public void act() {
        if (Button.waitForAnyPress() == Button.ID_ESCAPE) {
            running = false;
        }

        fetchSample();

        Color c = new Color(getSample());

        float turn = (float) (kp * error(c));
        lMotor.setSpeed(speed + turn);
        lMotor.setSpeed(speed - turn);
    }

    @Override
    public void end() {
        lMotor.stop();
        rMotor.stop();
        System.out.println("end:colorFollowing");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private double error(Color c) {
        return mid - colorList.get(0).deltaE(c);
    }
}
