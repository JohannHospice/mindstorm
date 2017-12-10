package mindstorm.listeners;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;

import java.util.Arrays;

public class ColorRecognizing extends ColorApplicationListener {

    private final ColorTeaching.ColorTeachingMemento colorTeachingMemento;
    private int bound = 30;

    public ColorRecognizing(HiTechnicColorSensor colorSensor, ColorTeaching.ColorTeachingMemento colorTeachingMemento) {
        super(colorSensor);
        this.colorTeachingMemento = colorTeachingMemento;
    }

    @Override
    public void start() {
        super.start();
        System.out.println("start: color recognizing");
        for (int i = 0; i < colorTeachingMemento.getColorSize(); i++) {
            System.out.println("color " + i);
            System.out.println("min" + Arrays.toString(colorTeachingMemento.getAvgColor(i)));
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void act() {
        System.out.println("waiting for capture");
        Button.waitForAnyPress();
        colorRGBSensor.fetchSample(sample, 0);
        int j = -1;
        for (int i = 0; i < sample.length; i++) {
            if (isBounded(bound, colorTeachingMemento.getAvgColor(i), sample)) {
                j = i;
            }
        }
        if (j > -1)
            System.out.println("color " + j);
        else
            System.out.println("unknown color");
    }

    private boolean isBounded(int bound, float[] avg, float[] val) {
        boolean ok = true;
        int i = 0;
        while (ok && i < val.length) {
            ok = avg[i] - bound < val[i] && val[i] < avg[i] + bound;
            i++;
        }
        return ok;
    }
}
