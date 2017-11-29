package mindstorm.listeners;

import mindstorm.tools.ColorSensorHandler;

public abstract class ColorApplicationListener extends ApplicationListener{

    private final ColorSensorHandler colorSensorHandler;

    ColorApplicationListener(ColorSensorHandler colorSensorHandler ){
        this.colorSensorHandler = colorSensorHandler ;
    }

	@Override
	public void act() {
        colorSensorHandler.processColor();
        if (colorSensorHandler.hasNewColor())
    		actColor(colorSensorHandler.getColor());
	}
	/**
	 * appelé uniquement lors de la capture d'une nouvelle couleur
	 */
	abstract void actColor(int color);
}
