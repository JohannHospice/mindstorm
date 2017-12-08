package mindstorm.tools;

import lejos.hardware.sensor.HiTechnicColorSensor;

/**
 * ensemble de fonction permettant de faciliter l'utilisation d'un capteur couleur
 */
public class ColorSensorHandler {
    private int color;
    private boolean newColor;
    private final HiTechnicColorSensor cSensor; // capteur couleur

    public ColorSensorHandler(HiTechnicColorSensor cSensor) {
        this.cSensor = cSensor;
    }

    /**
     * Change la couleur actuel et indique si elle a été modifiée
     */
    public void processColor() {
        int tmpColor = cSensor.getColorID();
        System.out.println(tmpColor);
        if (tmpColor != color) {
        	color = tmpColor;
            newColor = true;
        }
        newColor = false;
    }

    public boolean hasNewColor() {
        return newColor;
    }

    public int getColor() {
        return color;
    }

    public HiTechnicColorSensor getColorSensor() {
        return cSensor;
    }

    public void close() {
        cSensor.close();
    }
}
