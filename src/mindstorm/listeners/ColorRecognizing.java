package mindstorm.listeners;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;

import java.util.Arrays;

public class ColorRecognizing extends ColorApplicationListener {

    private final ColorTeaching.ColorTeachingMemento colorTeachingMemento;

    public ColorRecognizing(HiTechnicColorSensor colorSensor, ColorTeaching.ColorTeachingMemento colorTeachingMemento) {
        super(colorSensor);
        this.colorTeachingMemento = colorTeachingMemento;
    }

    @Override
    public void start() {
        super.start();
        System.out.println("start: color recognizing");
        for (int i = 0; i < colorTeachingMemento.getColorSize(); i++) {
            System.out.println("color "+i);
            System.out.println("min" + Arrays.toString(colorTeachingMemento.getMinColor(i)));
            System.out.println("max" + Arrays.toString(colorTeachingMemento.getMaxColor(i)));
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
            if (isBounded(sample, colorTeachingMemento.getMinColor(i), colorTeachingMemento.getMaxColor(i))) {
                j = i;
            }
        }
        if (j > -1)
            System.out.println("color " + j);
        else
            System.out.println("unknown color");
    }

    private boolean isBounded(float[] val, float[] min, float[] max) {
        boolean ok = true;
        int i = 0;
        while (ok && i < val.length) {
            ok = min[i] < val[i] && val[i] < max[i];
            i++;
        }
        return ok;
    }
}
