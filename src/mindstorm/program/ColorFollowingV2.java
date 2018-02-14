package mindstorm.program;

import lejos.hardware.Button;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * algorithme:
 * si sur la ligne alors tout droit
 * sinon si à gauche de la ligne alors tourner à droite
 * sinon si à droite de la ligne alors tourner à gauche
 */
public class ColorFollowingV2 extends ColorFollowing {
    private enum Side {
        LEFT, RIGHT
    }

    private Side oldSide = Side.LEFT;
    private boolean onLine = false;

    private int speed = 500;

    public ColorFollowingV2(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine, colorSamples);
    }

    @Override
    protected void handleInput(int buttonId) {
        switch (buttonId) {
            case Button.ID_ESCAPE:
                stop();
                break;
            case Button.ID_UP:
                speed += 2;
                break;
            case Button.ID_DOWN:
                speed -= 2;
                break;
        }
        System.out.println("S:" + speed);
    }

    @Override
    protected void actBehavior(int colorId) {
        switch (colorId) {
            case 0:
                goForward();
                onLine = true;
                break;
            case 1:
                if (oldSide == Side.LEFT)
                    goRight();
                else if (oldSide == Side.RIGHT)
                    goLeft();

                if (onLine) {
                    if (oldSide == Side.LEFT)
                        oldSide = Side.RIGHT;
                    else if (oldSide == Side.RIGHT)
                        oldSide = Side.LEFT;
                    onLine = false;
                }
                break;
        }
    }

    private void goLeft() {
        setSpeed(0, speed);
    }

    private void goRight() {
        setSpeed(speed, 0);
    }

    private void goForward() {
        setSpeed(speed, speed);
    }
}
