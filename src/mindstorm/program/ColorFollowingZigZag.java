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
public class ColorFollowingZigZag extends ColorFollowingListener {
	private boolean left = true; 
	private boolean changedDirection = false;

    public ColorFollowingZigZag(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine, colorSamples, 200, percentTo(200, 45));
    }

    private static int percentTo(int a, int b) {
        return a * b / 100;
    }

    @Override
    protected void handleInput(int buttonId) {
        switch (buttonId) {
            case Button.ID_ESCAPE:
                stop();
                break;
            case Button.ID_UP:
                incMaxSpeed(2);
                break;
            case Button.ID_DOWN:
                incMaxSpeed(-2);
                break;
        }
    }

    @Override
    protected void actBehavior(int colorId) {
        switch (colorId) {
            case 0:
                if (left) {
                	goLeft();
                	changedDirection = false;
                }                
                else {
                	goRight();
                	changedDirection = false;
                }
                break;
            case 1:
                if (!changedDirection) {
                	left = !left;
                	changedDirection = true;
                }
                break;
        }
    }
}
