package mindstorm.program;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.ColorList;

import java.util.ArrayList;

public class ColorTeaching extends ColorApplicationListener {

    private final int captureSize, colorSize;

    private ArrayList<ColorList> colorSamples = new ArrayList<ColorList>();

    private boolean running = true;

    private int colorIndex = 0, captureIndex = 0;

    public ColorTeaching(HiTechnicColorSensor colorSensor, int colorSize, int captureSize) {
        super(colorSensor);
        this.captureSize = captureSize;
        this.colorSize = colorSize;
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

        switch (Button.waitForAnyPress()) {
            case Button.ID_ENTER:
                fetchSample();

                colorSamples.get(captureIndex).add(getSample());

                if (captureIndex < captureSize - 1) {
                    captureIndex++;
                } else {
                    captureIndex = 0;
                    colorIndex++;
                }
                break;
            case Button.ID_ESCAPE:
                colorSamples = null;
                running = false;
                break;
        }
    }


    @Override
    public void end() {
        super.end();
        System.out.println("end:colorTeaching");
    }

    @Override
    public boolean isRunning() {
        return running && colorIndex < colorSize;
    }

    public ArrayList<ColorList> getColorSamples() {
        return colorSamples;
    }
}
