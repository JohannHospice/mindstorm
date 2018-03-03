package mindstorm.listener;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * Programme permettant de suivre une ligne de couleur
 */
public abstract class ColorFollowingListener extends ColorApplicationListener {
    protected final EV3LargeRegulatedMotor lMotor, rMotor;
    private final ColorList colorList = new ColorList();
    private boolean running = true;

    public ColorFollowingListener(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine.getColorSensor());
        lMotor = engine.getLeftMotor();
        rMotor = engine.getRightMotor();
        for (ColorList colorSample : colorSamples)
            colorList.add(colorSample.getAverage());
    }

    @Override
    public void start() {
        System.out.println("start:colorFollowing");
        lMotor.forward();
        rMotor.forward();
    }

    @Override
    public void act() {
        fetchSample();
        int id = colorList.getIndex(new mindstorm.tools.Color(getSample()), .3f);

        actBehavior(id);
        handleInput(Button.readButtons());
    }

    @Override
    public void end() {
        lMotor.stop();
        rMotor.stop();
        System.out.println("end:colorFollowing");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    protected void stop() {
        running = false;
    }

    protected void setSpeed(int left, int right) {
        lMotor.forward();
        rMotor.forward();

        lMotor.setSpeed(left);
        rMotor.setSpeed(right);
    }

    protected abstract void handleInput(int buttonId);

    protected abstract void actBehavior(int colorId);
}
