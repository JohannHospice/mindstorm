package mindstorm.program;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.Color;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * Programme permettant de suivre une ligne de couleur
 * algorithme: rouler; si couleur detectée: reduire vitesse roue gauche sinon reduire
 */
public class ColorFollowing extends ColorApplicationListener {

    private static final int SPEED = 200;
    private static final int MIN_SPEED_PERCENT = 35;

    private final EV3LargeRegulatedMotor lMotor, rMotor;
    private final ColorList colorBounds = new ColorList();

    private int minSpeed;

    public ColorFollowing(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine.getColorSensor());
        lMotor = engine.getLeftMotor();
        rMotor = engine.getRightMotor();

        for (ColorList colorSample : colorSamples)
            colorBounds.add(colorSample.getAverage());
    }

    private static int percentTo(int a, int b) {
        return a * b / 100;
    }

    @Override
    public void start() {
        System.out.println("start:colorFollowing");
        minSpeed = percentTo(SPEED, MIN_SPEED_PERCENT);
        lMotor.forward();
        rMotor.forward();
    }

    @Override
    public void act() {
        colorRGBSensor.fetchSample(sample, 0);

        if (colorBounds.getIndex(new Color(sample), .3f) == 0) {
            lMotor.setSpeed(minSpeed);
            rMotor.setSpeed(SPEED);
        } else {
            lMotor.setSpeed(SPEED);
            rMotor.setSpeed(minSpeed);
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void end() {
        super.end();
        System.out.println("end:colorFollowing");
    }
}
