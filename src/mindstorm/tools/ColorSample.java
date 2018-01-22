package mindstorm.tools;

import java.util.ArrayList;

public class ColorSample {
    private ArrayList<Color> colors = new ArrayList<Color>();

    public ColorSample() {
    }

    public int size() {
        return colors.size();
    }

    public void addColor(Color color) {
        colors.add(color);
    }

    public boolean addColor(float[] sample) {
        if (sample.length < 3) return false;
        return colors.add(new Color(sample[0], sample[1], sample[2]));
    }

    public Color getMin() {
        float[] min = new float[3];
        for (Color c : colors) {
            float[] sample = c.getSample();
            for (int i = 0; i < 3; i++) {
                if (min[i] > sample[i]) {
                    min[i] = sample[i];
                }
            }
        }
        return new Color(min[0], min[1], min[2]);
    }

    public Color getMax() {
        float[] max = new float[3];
        for (Color c : colors) {
            float[] sample = c.getSample();
            for (int i = 0; i < 3; i++) {
                if (max[i] < sample[i]) {
                    max[i] = sample[i];
                }
            }
        }
        return new Color(max[0], max[1], max[2]);
    }
}
