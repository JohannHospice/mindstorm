package mindstorm.program;

import lejos.hardware.Button;
import lejos.hardware.sensor.HiTechnicColorSensor;
import mindstorm.listener.ColorApplicationListener;
import mindstorm.tools.Color;
import mindstorm.tools.ColorList;

import java.util.ArrayList;

/**
 * Programme permettant de enregistrer differentes captures de meme couleurs
 */
public class ColorRecognizing extends ColorApplicationListener {
    private static final int SHOT_REPETITION = 5;

    private final ColorList colorList = new ColorList();
    private boolean running = true;

    public ColorRecognizing(HiTechnicColorSensor colorSensor, ArrayList<ColorList> colorSamples) {
        super(colorSensor);
        for (ColorList colorSample : colorSamples)
            colorList.add(colorSample.getAverage());
    }

    @Override
    public void start() {
        System.out.println("start:colorRecognizing");
    }

    @Override
    public void act() {
        switch (Button.waitForAnyPress()) {
            case Button.ID_ESCAPE:
                running = false;
                break;
            case Button.ID_ENTER:
                fetchSample();

                // takes multiple capture
                ColorList list = new ColorList();
                for (int i = 0; i < SHOT_REPETITION; i++) {
                    fetchSample();
                    list.add(getSample());
                }

                int colorIndex = colorList.getIndex(new Color(list.getAverage().getSample()), .1f);
                System.out.println("CO:" + colorIndex);
                break;
        }
    }


    @Override
    public void end() {
        System.out.println("end:colorRecognizing");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
