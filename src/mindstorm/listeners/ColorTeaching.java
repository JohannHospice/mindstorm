package mindstorm.listeners;

import lejos.hardware.lcd.LCD;
import mindstorm.tools.Engine;

import java.util.HashMap;

/**
 * Enregistre des couleurs et les affichent par ordre d'enregistrement
 */
public class ColorTeaching extends ColorApplicationListener {

    /**
     * clef: code couleur
     * valeur: identifiant
     */
    private HashMap<Integer, Integer> colors;
    private int id;

    public ColorTeaching(Engine engine) {
    	super(engine.getColorSensorHandler());
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
    public void actColor(int color) {
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
