package mindstorm.listeners;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import mindstorm.EV3ColorSensorHandler;
import mindstorm.Engine;

/**
 * Programme permettant de suivre une ligne de couleur
 * algorithme: rouler; si couleur detectée: reduire vitesse roue gauche sinon reduire
 */
public class FollowLine extends ApplicationListener {

    private static final int SPEED = 75;
    private static final int MIN_SPEED_PERCENT = 90;

    private final int followedColor;
    private final EV3ColorSensorHandler colorSensorHandler;
    private final EV3LargeRegulatedMotor lMotor;
    private final EV3LargeRegulatedMotor rMotor;
    private int minSpeed;

    public FollowLine(Engine engine, int followedColor) {
        this.followedColor = followedColor;
        colorSensorHandler = engine.getColorSensorHandler();
        lMotor = engine.getLeftMotor();
        rMotor = engine.getRightMotor();
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
        colorSensorHandler.processColor();
        if (colorSensorHandler.hasNewColor()) {
            if (colorSensorHandler.getColor() == followedColor) {
                lMotor.setSpeed(minSpeed);
                rMotor.setSpeed(SPEED);
            } else {
                lMotor.setSpeed(SPEED);
                rMotor.setSpeed(minSpeed);
            }
        }
    }

    @Override
    public void end() {
        lMotor.stop();
        rMotor.stop();
    }
}
