package mindstorm.tools;

public class Color {
    private float[] sample = new float[3];

    public Color(float r, float g, float b) {
        sample[0] = r;
        sample[1] = g;
        sample[2] = b;
    }

    public Color(float[] sample) {
        System.arraycopy(sample, 0, this.sample, 0, this.sample.length);
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
}
