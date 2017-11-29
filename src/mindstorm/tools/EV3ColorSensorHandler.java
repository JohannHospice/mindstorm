package mindstorm;

import lejos.hardware.sensor.EV3ColorSensor;

public class EV3ColorSensorHandler {
    private int color;
    private boolean newColor;
    private final EV3ColorSensor cSensor; // capteur couleur

    public EV3ColorSensorHandler(EV3ColorSensor cSensor) {
        this.cSensor = cSensor;
    }

    public void processColor() {
        int tmpColor = cSensor.getColorID();
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

    public EV3ColorSensor getColorSensor() {
        return cSensor;
    }
}
