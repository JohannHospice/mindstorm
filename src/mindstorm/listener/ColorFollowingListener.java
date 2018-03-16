package mindstorm.listener;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import mindstorm.tools.Color;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

/**
 * Programme permettant de suivre une ligne de couleur
 */
public abstract class ColorFollowingListener extends ColorApplicationListener {
    private final EV3LargeRegulatedMotor lMotor, rMotor;
    private final ColorList colorList = new ColorList();

    private boolean running = true;
    private int maxSpeed = 500, minSpeed = 0;

    public ColorFollowingListener(Engine engine, ArrayList<ColorList> colorSamples) {
        super(engine.getColorSensor());
        lMotor = engine.getLeftMotor();
        rMotor = engine.getRightMotor();
        for (ColorList colorSample : colorSamples)
            colorList.add(colorSample.getAverage());
    }

    public ColorFollowingListener(Engine engine, ArrayList<ColorList> colorSamples, int minSpeed, int maxSpeed) {
        this(engine, colorSamples);
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
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
        int id = colorList.getIndex(new Color(getSample()), .3f);

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

    protected abstract void handleInput(int buttonId);

    protected abstract void actBehavior(int colorId);

    protected void setSpeed(int left, int right) {
        lMotor.forward();
        rMotor.forward();

        lMotor.setSpeed(left);
        rMotor.setSpeed(right);
    }

    protected void goLeft() {
        setSpeed(minSpeed, maxSpeed);
    }

    protected void goRight() {
        setSpeed(maxSpeed, minSpeed);
    }

    protected void goStraight() {
        setSpeed(maxSpeed, maxSpeed);
    }

    /*
     * Getters and Setters
     */

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public void incMaxSpeed(int speed) {
        maxSpeed += speed;
    }

    public void incMinSpeed(int speed) {
        minSpeed += speed;
    }
}
