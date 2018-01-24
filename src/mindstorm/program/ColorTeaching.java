package mindstorm.program;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.ColorList;

import java.util.ArrayList;

public class ColorTeaching extends ColorApplicationListener {

    private final ArrayList<ColorList> colorSamples = new ArrayList<ColorList>();

    private final int captureSize;

    private int colorIndex = 0,
            captureIndex = 0,
            colorSize = 3;

    public ColorTeaching(HiTechnicColorSensor colorSensor, int n) {
        super(colorSensor);
        captureSize = n;
    }

    @Override
    public void start() {
        System.out.println("start:colorTeaching");
        for (int i = 0; i < captureSize; i++)
            colorSamples.add(new ColorList());
    }

    @Override
    public void act() {
        System.out.println("CO:" + colorIndex + "; CA:" + captureIndex);
        Button.waitForAnyPress();
        colorRGBSensor.fetchSample(sample, 0);


        colorSamples.get(captureIndex).add(sample);

        if (captureIndex < captureSize - 1) {
            captureIndex++;
        } else {
            captureIndex = 0;
            colorIndex++;
        }
    }

    @Override
    public void end() {
        super.end();
        System.out.println("end:colorTeaching");
    }

    @Override
    public boolean isRunning() {
        return colorIndex < colorSize;
    }

    public ArrayList<ColorList> getColorSamples() {
        return colorSamples;
    }
}
