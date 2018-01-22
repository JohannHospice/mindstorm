package mindstorm.program;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.ColorSample;

import java.util.ArrayList;

public class ColorTeaching extends ColorApplicationListener {

    private ArrayList<ColorSample> colorSamples;
    private int colorIndex = 0,
            captureIndex = 0,
            colorSize = 3,
            captureSize = 3;

    public ColorTeaching(HiTechnicColorSensor colorSensor) {
        super(colorSensor);
    }

    @Override
    public void start() {
        colorSamples = new ArrayList<ColorSample>();
        for (int i = 0; i < captureSize; i++)
            colorSamples.add(new ColorSample());

        System.out.println("start: color teaching");
        System.out.println("colors:" + colorSize);
        System.out.println("samples:" + captureSize);
        System.out.println("step:" + colorIndex);
    }

    @Override
    public void act() {
        Button.waitForAnyPress();
        colorRGBSensor.fetchSample(sample, 0);

        System.out.println("new index captured" + captureIndex);

        colorSamples.get(captureIndex).addColor(sample);

        if (captureIndex < captureSize) {
            captureIndex++;
        } else {
            captureIndex = 0;
            colorIndex++;
            System.out.println("step:" + colorIndex);
        }
    }

    @Override
    public void end() {
        super.end();
        System.out.println("store colors");
    }

    @Override
    public boolean isRunning() {
        return colorIndex < colorSize;
    }

    public ColorTeachingMemento getMemento() {
        return new ColorTeachingMemento(colorSamples);
    }

    public class ColorTeachingMemento {
        private ArrayList<ColorSample> colorSamples;

        public ColorTeachingMemento(ArrayList<ColorSample> colorSamples) {
            this.colorSamples = colorSamples;
        }

        public ArrayList<ColorSample> getColorSamples() {
            return colorSamples;
        }

        public int getSampleSize() {
            return colorSamples.size();
        }
    }
}
