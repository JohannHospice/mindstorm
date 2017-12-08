package mindstorm.listeners;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class RightWay extends ApplicationListenerAdapter {

    private static final int SPEED = 1000;
    private final static int TIME = 1; // seconde

    private final EV3LargeRegulatedMotor lMotor;
    private final EV3LargeRegulatedMotor rMotor;
    private int i;

    public RightWay(Port leftMotorPort, Port rightMotorPort) {
        super();
        lMotor = new EV3LargeRegulatedMotor(leftMotorPort);
        rMotor = new EV3LargeRegulatedMotor(rightMotorPort);
    }

    @Override
    public void start() {
        lMotor.setSpeed(SPEED);
        rMotor.setSpeed(SPEED);
        lMotor.forward();
        rMotor.forward();
        i = 0;
    }

    @Override
    public void act() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
    }

    @Override
    public boolean isRunning() {
        return i < TIME;
    }
}
