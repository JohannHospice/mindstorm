package mindstorm.tools;

import java.util.ArrayList;

public class ColorBounds {
    private final ArrayList<Color> minimalColors = new ArrayList<Color>();
    private final ArrayList<Color> maximalColors = new ArrayList<Color>();
    private final int colorSampleSize;

    public ColorBounds(ArrayList<ColorSample> colorSamples) {
        colorSampleSize = colorSamples.size();
        for (ColorSample colorSample : colorSamples) {
            minimalColors.add(colorSample.getMin());
            maximalColors.add(colorSample.getMax());
        }
    }

    public int getIndex(float[] sample) {
        for (int i = 0; i < colorSampleSize; i++)
            if (minimalColors.get(i).superior(sample) &&
                    maximalColors.get(i).inferior(sample))
                return i;
        return -1;

    }
}
