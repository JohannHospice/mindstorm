package mindstorm.listeners;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;

public class ColorTeaching extends ColorApplicationListener {

    private float[][] avgColors;
    private int colorSize = 3,
            currentColor = 0,
            capture = 0,
            captureSize = 3;
    private ColorTeachingMemento memento = new ColorTeachingMemento();

    public class ColorTeachingMemento {
        private float[][] avgColors;
        private int colorSize;

        public ColorTeachingMemento(int colorSize, float[][] avgColors) {
            this.colorSize = colorSize;
            this.avgColors = avgColors;
        }

        public ColorTeachingMemento() {
        }

        public float[][] getAvgColors() {
            return avgColors;
        }

        public float[] getAvgColor(int i) {
            return avgColors[i];
        }

        public void setAvgColors(float[][] avgColor) {
            avgColors = avgColor;
        }

        public int getColorSize() {
            return colorSize;
        }

        public void setColorSize(int colorSize) {
            this.colorSize = colorSize;
        }

        public ColorTeachingMemento copy() {
            return new ColorTeachingMemento(colorSize, avgColors);
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
        avgColors = new float[colorSize][sampleSize];
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
        for (int i = 0; i < sample.length; i++)
            avgColors[currentColor][i] += sample[i];

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
        for (int i = 0; i < avgColors.length; i++) {
            for (int j = 0; j < avgColors[i].length; j++) {
                avgColors[i][j] /= colorSize;
            }
        }
        store();
    }

    public void store() {
        System.out.println("store colors");
        memento.setAvgColors(avgColors);
        memento.setColorSize(colorSize);
    }

    public ColorTeachingMemento getMemento() {
        return memento.copy();
    }
}
