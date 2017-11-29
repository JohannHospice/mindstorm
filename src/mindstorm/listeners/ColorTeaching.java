package mindstorm.listeners;

import lejos.hardware.lcd.LCD;
import mindstorm.EV3ColorSensorHandler;
import mindstorm.Engine;

import java.util.HashMap;

/**
 * Enregistre des couleurs et les affichent par ordre d'enregistrement
 */
public class ColorTeaching extends ApplicationListener {

    /**
     * clef: code couleur
     * valeur: identifiant
     */
    private HashMap<Integer, Integer> colors;
    private final EV3ColorSensorHandler handler;
    private int id;

    public ColorTeaching(Engine engine) {
        super(engine);
        handler = engine.getColorSensorHandler();
    }

    @Override
    public void start() {
        colors = new HashMap<>();
        id = 0;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void act() {
        handler.processColor();
        if (handler.hasNewColor()) {
            int color = handler.getColor();
            // ajout d'une couleur si nouvelle
            if (!colors.containsKey(color)) {
                colors.put(color, id);
                id++;
            }
            // affichage de la couleur
            LCD.clear();
            LCD.drawString("color: " + colors.get(color), 0, 0);
        }
    }

    @Override
    public void end() {
        handler.getColorSensor().close();
    }

}
