package mindstorm.listener;

import lejos.hardware.sensor.HiTechnicColorSensor;
import lejos.robotics.SampleProvider;

public abstract class ColorApplicationListener extends ApplicationListenerAdapter {
    private SampleProvider colorRGBSensor;
    private float[] sample;

    public ColorApplicationListener(HiTechnicColorSensor colorSensor) {
        colorRGBSensor = colorSensor.getRGBMode();
        sample = new float[colorRGBSensor.sampleSize()];
    }

    protected void fetchSample() {
        colorRGBSensor.fetchSample(sample, 0);
    }

    protected float[] getSample() {
        return sample;
    }
}
