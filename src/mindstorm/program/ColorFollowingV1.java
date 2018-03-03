package mindstorm.program;

import lejos.hardware.Button;
import mindstorm.listener.ColorFollowingListener;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * algorithme:
 * si à gauche de la ligne alors tourner à droite
 * sinon si à droite de la ligne alors tourner à gauche
 */
public class ColorFollowingV1 extends ColorFollowingListener {

    private static final int SPEED = 200;
    private static final int MIN_SPEED_PERCENT = 35;

    private int speed = SPEED,
            speedPercent = MIN_SPEED_PERCENT,
            minSpeed = percentTo(SPEED, MIN_SPEED_PERCENT);

    public ColorFollowingV1(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine, colorSamples);
    }

    @Override
    protected void handleInput(int buttonId) {
        switch (buttonId) {
            case Button.ID_ESCAPE:
                stop();
                break;
            case Button.ID_RIGHT:
                speedPercent += 2;
                updateRotationSpeed();
                break;
            case Button.ID_LEFT:
                speedPercent -= 2;
                updateRotationSpeed();
                break;
            case Button.ID_UP:
                speed += 2;
                updateRotationSpeed();
                break;
            case Button.ID_DOWN:
                speed -= 2;
                updateRotationSpeed();
                break;
        }
    }

    @Override
    protected void actBehavior(int colorId) {
        if (colorId == 0)
            setSpeed(minSpeed, SPEED);
        else if (colorId == 1)
            setSpeed(SPEED, minSpeed);
    }

    private void updateRotationSpeed() {
        minSpeed = percentTo(speed, speedPercent);
        System.out.println("S:" + speed + "; A:" + speedPercent);
    }

    private static int percentTo(int a, int b) {
        return a * b / 100;
    }
}
