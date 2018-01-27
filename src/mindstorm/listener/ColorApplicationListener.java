package mindstorm.listener;

import lejos.hardware.sensor.HiTechnicColorSensor;
import lejos.robotics.SampleProvider;
import mindstorm.tools.ColorList;

public abstract class ColorApplicationListener extends ApplicationListenerAdapter {

    private static final int SHOT_REPETITION = 10;
    private SampleProvider colorRGBSensor;
    private float[] sample;

    public ColorApplicationListener(HiTechnicColorSensor colorSensor) {
        colorRGBSensor = colorSensor.getRGBMode();
        sample = new float[colorRGBSensor.sampleSize()];
    }

    protected void fetchSample() {
        // takes multiple capture
        ColorList list = new ColorList();
        for (int i = 0; i < SHOT_REPETITION; i++) {
            colorRGBSensor.fetchSample(sample, 0);
            list.add(sample);
        }

        // save the average
        sample = list.getAverage().getSample();
    }

    protected float[] getSample() {
        return sample;
    }
}
