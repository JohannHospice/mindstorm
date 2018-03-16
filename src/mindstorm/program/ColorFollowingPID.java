package mindstorm.program;

import lejos.hardware.Button;
import mindstorm.listener.ColorFollowingListener;
import mindstorm.tools.Color;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * algorithme:
 * Proportionnal Controller
 */

//todo: change extends to colorapplicationlistener
public class ColorFollowingPID extends ColorFollowingListener {
    private int Kp = 1;
    private int avgColor = 40;
    private int error = 0;
    private int correction;

    public ColorFollowingPID(Engine engine, ArrayList<ColorList> colorSamples) {
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
    protected void actBehavior(int colorId) {
        fetchSample();

        //TODO...
        Color c = new Color(getSample());
        error = (int) (avgColor - c.getB());
        correction = Kp * error;
        setSpeed(200 + correction, 200 - correction);
    }
}
