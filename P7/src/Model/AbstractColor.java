package Model;

import java.awt.*;

public abstract class AbstractColor {

    protected String name;
    protected int index;
    protected HSVInterval interval;

    public AbstractColor(String name, int index, double minH, double maxH, double minS, double maxS, double minV, double maxV) {
        this.name = name;
        this.index = index;
        this.interval = new HSVInterval(minH, maxH, minS, maxS, minV, maxV);
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public boolean matches(int R, int G, int B) {
        float[] HSV = Color.RGBtoHSB(R, G, B, null);
        boolean h = interval.getMinH() <= HSV[0] && HSV[0] < interval.getMaxH();
        boolean s = interval.getMinS() <= HSV[1] && HSV[1] <= interval.getMaxS();
        boolean v = interval.getMinV() <= HSV[2] && HSV[2] <= interval.getMaxV();

        return v && s && h;
    }
}
