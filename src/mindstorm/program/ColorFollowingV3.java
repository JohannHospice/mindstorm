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
    private static final float CONTROL_STEP = 10;

    private final EV3LargeRegulatedMotor lMotor, rMotor;
    private final ColorList colorList = new ColorList();

    private boolean running = true;
    private float kp = 1250, speed = 200;
    private double mid;

    public ColorFollowingV3(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine.getColorSensor());
        lMotor = engine.getLeftMotor();
        rMotor = engine.getRightMotor();

        for (ColorList colorSample : colorSamples)
            colorList.add(colorSample.getAverage());

        double l0 = colorList.get(0).lum(), l1 = colorList.get(1).lum();
        mid = (l1 - l0) / 2 + l0;
    }

    @Override
    public void start() {
        System.out.println("start:colorFollowing");
        lMotor.forward();
        rMotor.forward();
    }

    @Override
    public void act() {
        switch (Button.readButtons()) {
            case Button.ID_ESCAPE:
                running = false;
                break;
            case Button.ID_UP:
                kp += CONTROL_STEP;
                System.out.println("kp: " + kp);
                break;
            case Button.ID_DOWN:
                kp -= CONTROL_STEP;
                System.out.println("kp: " + kp);
                break;
            case Button.ID_LEFT:
                speed -= CONTROL_STEP;
                System.out.println("speed: " + speed);
                break;
            case Button.ID_RIGHT:
                speed += CONTROL_STEP;
                System.out.println("speed: " + speed);
                break;
        }

        fetchSample();

        float err = (float) (mid - Color.lum(getSample()));

        float turn = kp * err;

        lMotor.setSpeed(speed + turn);
        rMotor.setSpeed(speed - turn);
    }

    @Override
    public void end() {
        System.out.println("end:colorFollowing");
        lMotor.stop();
        rMotor.stop();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

}
