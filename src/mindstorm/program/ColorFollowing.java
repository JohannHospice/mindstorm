package mindstorm.program;

import lejos.hardware.Button;
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
    private final ColorList colorList = new ColorList();

    private boolean running = true;

    private int speed = SPEED,
            speedPercent = MIN_SPEED_PERCENT,
            minSpeed = percentTo(SPEED, MIN_SPEED_PERCENT);

    public ColorFollowing(Engine engine, ArrayList<ColorList> colorSamples) {
        this(engine);
        setColorSample(colorSamples);
    }

    public ColorFollowing(Engine engine) {
        super(engine.getColorSensor());
        lMotor = engine.getLeftMotor();
        rMotor = engine.getRightMotor();
    }

    private static int percentTo(int a, int b) {
        return a * b / 100;
    }

    @Override
    public void start() {
        System.out.println("start:colorFollowing");
        lMotor.forward();
        rMotor.forward();
    }

    @Override
    public void act() {
        actBehavior();
        handleInput(Button.readButtons());
    }

    private void handleInput(int id) {
        if (id == Button.ID_ESCAPE)
            running = false;
        else if (id != Button.ID_ENTER) {
            switch (id) {
                case Button.ID_RIGHT:
                    speedPercent += 2;
                    break;
                case Button.ID_LEFT:
                    speedPercent -= 2;
                    break;
                case Button.ID_UP:
                    speed += 2;
                    break;
                case Button.ID_DOWN:
                    speed -= 2;
                    break;
            }
            updateRotationSpeed();
        }
    }

    private void actBehavior() {
        fetchSample();
        int id = colorList.getIndex(new Color(getSample()), .3f);
        if (id == 0) {
            lMotor.setSpeed(minSpeed);
            rMotor.setSpeed(SPEED);
        } else if (id == 1) {
            lMotor.setSpeed(SPEED);
            rMotor.setSpeed(minSpeed);
        }
    }

    @Override
    public void end() {
        super.end();
        System.out.println("end:colorFollowing");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    public void setColorSample(ArrayList<ColorList> colorSamples) {
        for (ColorList colorSample : colorSamples)
            colorList.add(colorSample.getAverage());
    }

    private void updateRotationSpeed() {
        minSpeed = percentTo(speed, speedPercent);
        System.out.println("S:" + speed + "; A:" + speedPercent);
    }
}
