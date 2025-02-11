package mindstorm;

import mindstorm.listener.ApplicationListener;
import mindstorm.program.*;
import mindstorm.tools.ColorList;
import mindstorm.tools.Engine;

import java.util.ArrayList;

public class Program {
    private static void run(ApplicationListener applicationListener) {
        applicationListener.start();
        while (applicationListener.isRunning())
            applicationListener.act();
        applicationListener.end();
    }

    public static void colorTeaching(Engine engine) {
        ColorTeaching colorTeaching = new ColorTeaching(engine.getColorSensor(), 4, 3);

        run(colorTeaching);

        ArrayList<ColorList> sample = colorTeaching.getColorSamples();

        if (sample != null)
            run(new ColorRecognizing(engine.getColorSensor(), sample));
    }

    public static void colorFollowing(Engine engine) {
        ColorTeaching colorTeaching = new ColorTeaching(engine.getColorSensor(), 2, 3);

        run(colorTeaching);

        ArrayList<ColorList> sample = colorTeaching.getColorSamples();

        if (sample != null)
            run(new ColorFollowingZigZag(engine, sample));
    }

}
