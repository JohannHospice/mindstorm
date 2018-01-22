package mindstorm.listener;

import lejos.hardware.sensor.HiTechnicColorSensor;
import lejos.robotics.SampleProvider;

public abstract class ColorApplicationListener extends ApplicationListenerAdapter {

    protected SampleProvider colorRGBSensor;
    protected float[] sample;

    public ColorApplicationListener(HiTechnicColorSensor colorSensor) {
        colorRGBSensor = colorSensor.getRGBMode();
        sample = new float[colorRGBSensor.sampleSize()];
    }

}
