package mindstorm.program;

import lejos.hardware.Button;
import mindstorm.listener.ColorFollowingListener;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * algorithme:
 * si sur la ligne alors tout droit
 * sinon si à gauche de la ligne alors tourner à droite
 * sinon si à droite de la ligne alors tourner à gauche
 */
public class ColorFollowingV2 extends ColorFollowingListener {
    private static final int SEARCH_TIME = 1000;

    private boolean onLeftSide = true, onLine = false;
    private int counter = 0;

    public ColorFollowingV2(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine, colorSamples, 0, 500);
    }

    @Override
    protected void handleInput(int buttonId) {
        switch (buttonId) {
            case Button.ID_ESCAPE:
                stop();
                break;
            case Button.ID_UP:
                incMaxSpeed(2);
                System.out.println("S:" + getMaxSpeed());
                break;
            case Button.ID_DOWN:
                incMaxSpeed(-2);
                System.out.println("S:" + getMaxSpeed());
                break;
        }
    }

    @Override
    protected void actBehavior(int colorId, int a) {
        switch (colorId) {
            case 0:
                goForward();
                onLine = true;
                counter = 0;
                break;
            case 1:
                if (onLeftSide)
                    goRight();
                else
                    goLeft();

                if (onLine) {
                    onLeftSide = !onLeftSide;
                    onLine = false;
                }

                if (counter >= SEARCH_TIME)
                    onLeftSide = !onLeftSide;
                counter++;
                break;
        }
    }
}
