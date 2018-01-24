package mindstorm.program;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.Color;
import mindstorm.tools.ColorList;

import java.util.ArrayList;

public class ColorRecognizing extends ColorApplicationListener {

    private final ColorList colorBounds = new ColorList();

    public ColorRecognizing(HiTechnicColorSensor colorSensor, ArrayList<ColorList> colorSamples) {
        super(colorSensor);
        for (ColorList colorSample : colorSamples)
            colorBounds.add(colorSample.getAverage());
    }

    @Override
    public void start() {
        super.start();
        System.out.println("start:colorRecognizing");
    }

    @Override
    public void act() {
        System.out.println("capture...");

        Button.waitForAnyPress();
        colorRGBSensor.fetchSample(sample, 0);

        int colorIndex = colorBounds.getIndex(new Color(sample), 30);

        System.out.println("CO:" + colorIndex);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void end() {
        super.end();
        System.out.println("end:colorRecognizing");
    }
}
