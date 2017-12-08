package mindstorm.listeners;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;

import java.util.Arrays;

public class ColorTeaching extends ColorApplicationListener {

    private float[][] maxColors, minColors;
    private int colorSize = 3,
            currentColor = 0,
            capture = 0,
            captureSize = 3;
    private ColorTeachingMemento memento = new ColorTeachingMemento();

    public class ColorTeachingMemento {
        private float[][] maxColors, minColors;
        private int colorSize;

        public ColorTeachingMemento(int colorSize, float[][] minColors, float[][] maxColors) {
            this.colorSize = colorSize;
            this.minColors = minColors;
            this.maxColors = maxColors;
        }

        public ColorTeachingMemento() {
        }

        public float[][] getMaxColors() {
            return maxColors;
        }
        public float[] getMaxColor(int i) {
            return maxColors[i];
        }

        public void setMaxColors(float[][] maxColors) {
            this.maxColors = maxColors;
        }

        public float[][] getMinColors() {
            return minColors;
        }
        public float[] getMinColor(int i) {
            return minColors[i];
        }

        public void setMinColors(float[][] minColors) {
            this.minColors = minColors;
        }

        public int getColorSize() {
            return colorSize;
        }

        public void setColorSize(int colorSize) {
            this.colorSize = colorSize;
        }

        public ColorTeachingMemento copy() {
            return new ColorTeachingMemento(colorSize, minColors, maxColors);
        }
    }

    public ColorTeaching(HiTechnicColorSensor colorSensor) {
        super(colorSensor);
    }

    @Override
    public void start() {
        System.out.println("start: color teaching");
        System.out.println("colors:" + colorSize);
        System.out.println("samples:" + captureSize);
        maxColors = new float[colorSize][sampleSize];
        minColors = new float[colorSize][sampleSize];
        System.out.println("step:" + currentColor);
    }

    @Override
    public boolean isRunning() {
        return currentColor < colorSize;
    }

    @Override
    public void act() {
        Button.waitForAnyPress();
        colorRGBSensor.fetchSample(sample, 0);
        System.out.println("new capture");
        System.out.println(sample);
        for (int i = 0; i < sample.length; i++) {
            if (maxColors[currentColor][i] < sample[i])
                maxColors[currentColor][i] = sample[i];
            if (minColors[currentColor][i] > sample[i])
                minColors[currentColor][i] = sample[i];
        }
        if (capture < captureSize) {
            capture++;
        } else {
            capture = 0;
            currentColor++;
            System.out.println("step:" + currentColor);
        }
    }

    @Override
    public void end() {
        super.end();
        store();
    }

    public void store() {
        System.out.println("store colors");
        memento.setMaxColors(maxColors);
        memento.setMinColors(minColors);
        memento.setColorSize(colorSize);
    }

    public ColorTeachingMemento getMemento() {
        return memento.copy();
    }
}
