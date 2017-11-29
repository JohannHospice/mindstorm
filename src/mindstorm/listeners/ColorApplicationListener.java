package mindstorm.listeners;

import mindstorm.tools.EV3ColorSensorHandler;

public abstract class ColorApplicationListener extends ApplicationListener{

    private final EV3ColorSensorHandler colorSensorHandler;

    ColorApplicationListener(EV3ColorSensorHandler colorSensorHandler ){
        this.colorSensorHandler = colorSensorHandler ;
    }

	@Override
	public void act() {
        colorSensorHandler.processColor();
        if (colorSensorHandler.hasNewColor()) {
    		actColor(colorSensorHandler.getColor());
        }
	}
	/**
	 * appelé uniquement lors de la capture d'une nouvelle couleur
	 * @param color
	 */
	abstract void actColor(int color);
	
	public void end() {
		colorSensorHandler.getColorSensor().close();
	}
}
