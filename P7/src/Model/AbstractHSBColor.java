package Model;

import java.awt.*;

public abstract class AbstractHSBColor {

    protected HSBInterval interval;

    public AbstractHSBColor(double minH, double maxH, double minS, double maxS, double minB, double maxB) {
        this.interval = new HSBInterval(minH, maxH, minS, maxS, minB, maxB);
    }

    public boolean matches(int R, int G, int B) {
        float[] HSV = Color.RGBtoHSB(R, G, B, null);
        boolean h = HSV[0] < interval.maxH() && interval.minH() <= HSV[0];
        boolean s = HSV[1] <= interval.maxS() && interval.minS() <= HSV[1];
        boolean b = HSV[2] <= interval.maxB() && interval.minB() <= HSV[2];

        return h && s && b;
    }
}
