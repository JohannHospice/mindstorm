package mindstorm.tools;

public class Color {
    public static int SAMPLE_LENGTH = 3;
    private float[] sample = new float[SAMPLE_LENGTH];

    public Color(float r, float g, float b) {
        sample[0] = r;
        sample[1] = g;
        sample[2] = b;
    }

    public Color(float[] sample) {
        System.arraycopy(sample, 0, this.sample, 0, this.sample.length);
    }

    public static float[] distance(Color c1, Color c2) {
        float[] distance = new float[SAMPLE_LENGTH];
        for (int i = 0; i < SAMPLE_LENGTH; i++)
            distance[i] = Math.abs(c1.get(i) - c2.get(i));
        return distance;
    }

    public static double deltaE(Color c1, Color c2) {
        double sum = 0;
        for (int i = 0; i < Color.SAMPLE_LENGTH; i++)
            sum = Math.pow(c2.get(i) - c1.get(i), 2);
        return Math.sqrt(sum);
    }

    public float getR() {
        return sample[0];
    }

    public float getG() {
        return sample[1];
    }

    public float getB() {
        return sample[2];
    }

    public float[] getSample() {
        return sample;
    }

    public double deltaE(Color c) {
        return Color.deltaE(this, c);
    }

    public boolean inferior(Color color) {
        return inferior(color.getSample());
    }

    public boolean superior(Color color) {
        return superior(color.getSample());
    }

    public boolean inferior(float[] sampleParam) {
        for (int i = 0; i < 3; i++)
            if (sample[i] < sampleParam[i])
                return false;
        return true;
    }

    public boolean superior(float[] sampleParam) {
        for (int i = 0; i < 3; i++)
            if (sample[i] > sampleParam[i])
                return false;
        return true;
    }

    public float get(int i) {
        return sample[i];
    }

    public double lum() {
        return 0.21 * getR() + 0.72 * getG() + 0.07f * getB();
    }

    public static double lum(float[] sample) {
        return (new Color(sample)).lum();
    }
}
