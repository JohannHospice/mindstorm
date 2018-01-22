package mindstorm.program;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.ColorBounds;

public class ColorRecognizing extends ColorApplicationListener {

    private final ColorBounds colorBounds;

    public ColorRecognizing(HiTechnicColorSensor colorSensor, ColorTeaching.ColorTeachingMemento colorTeachingMemento) {
        super(colorSensor);
        colorBounds = new ColorBounds(colorTeachingMemento.getColorSamples());
    }

    @Override
    public void start() {
        super.start();
        System.out.println("start: color recognizing");
    }

    @Override
    public void act() {
        System.out.println("waiting for capture");

        Button.waitForAnyPress();
        colorRGBSensor.fetchSample(sample, 0);

        int colorIndex = colorBounds.getIndex(sample);

        if (colorIndex > -1)
            System.out.println("color " + colorIndex);
        else
            System.out.println("unknown color");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

}
