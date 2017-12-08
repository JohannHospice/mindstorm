package mindstorm.listeners;

import lejos.hardware.sensor.HiTechnicColorSensor;
import lejos.robotics.SampleProvider;

public abstract class ColorApplicationListener extends ApplicationListenerAdapter {

    protected SampleProvider colorRGBSensor;
    protected HiTechnicColorSensor colorSensor;
    protected float[] sample;
    protected int sampleSize;


    ColorApplicationListener(HiTechnicColorSensor colorSensor) {
        this.colorSensor = colorSensor;
        this.colorRGBSensor = colorSensor.getRGBMode();
        this.sampleSize = colorRGBSensor.sampleSize();
        sample = new float[sampleSize];
    }

}
