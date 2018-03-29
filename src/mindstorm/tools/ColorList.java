package mindstorm.tools;

import java.util.ArrayList;

public class ColorList extends ArrayList<Color> {

    public boolean add(float[] sample) {
        return add(new Color(sample[0], sample[1], sample[2]));
    }

    public Color getAverage() {
        float[] sample = new float[Color.SAMPLE_LENGTH];

        for (Color color : this)
            for (int i = 0; i < Color.SAMPLE_LENGTH; i++)
                sample[i] += color.get(i);

        for (int i = 0; i < Color.SAMPLE_LENGTH; i++)
            sample[i] /= size();
        return new Color(sample);
    }

    public int getIndex(Color color, double tolerateDistance) {
        double minDistance = Double.POSITIVE_INFINITY;
        int minIndex = -1;
        for (int i = 0; i < size(); i++) {
            double distance = get(i).deltaE(color);
            if (minDistance > distance) {
                minDistance = distance;
                minIndex = i;
            }
        }
        return minDistance <= tolerateDistance ? minIndex : -1;
    }

}
