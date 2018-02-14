package mindstorm.program;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * Programme permettant de suivre une ligne de couleur
 */
public abstract class ColorFollowing extends ColorApplicationListener {
    private final EV3LargeRegulatedMotor lMotor, rMotor;
    private final ColorList colorList = new ColorList();
    private boolean running = true;

    public ColorFollowing(Engine engine, ArrayList<ColorList> colorSamples) {
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
        System.out.println("end:colorFollowing");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    protected void setSpeed(int left, int right) {
        lMotor.setSpeed(left);
        rMotor.setSpeed(right);
    }

    protected abstract void handleInput(int buttonId);

    protected abstract void actBehavior(int colorId);

    protected void stop() {
        running = false;
    }
}
