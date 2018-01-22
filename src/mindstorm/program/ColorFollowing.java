package mindstorm.program;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.ColorBounds;
import mindstorm.tools.ColorSample;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * Programme permettant de suivre une ligne de couleur
 * algorithme: rouler; si couleur detectée: reduire vitesse roue gauche sinon reduire
 */
public class ColorFollowing extends ColorApplicationListener {

    private static final int SPEED = 75;
    private static final int MIN_SPEED_PERCENT = 90;

    private final EV3LargeRegulatedMotor lMotor, rMotor;
    private final ColorBounds colorBounds;

    private int minSpeed;

    public ColorFollowing(Engine engine, ArrayList<ColorSample> colorSamples) {
        super(engine.getColorSensor());
        lMotor = engine.getLeftMotor();
        rMotor = engine.getRightMotor();
        colorBounds = new ColorBounds(colorSamples);
    }

    private static int percentTo(int a, int b) {
        return a * b / 100;
    }

    @Override
    public void start() {
        minSpeed = percentTo(SPEED, MIN_SPEED_PERCENT);
        lMotor.forward();
        rMotor.forward();
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void act() {
        colorRGBSensor.fetchSample(sample, 0);

        if (colorBounds.getIndex(sample) == 0) {
            lMotor.setSpeed(minSpeed);
            rMotor.setSpeed(SPEED);
        } else {
            lMotor.setSpeed(SPEED);
            rMotor.setSpeed(minSpeed);
        }
    }

}
