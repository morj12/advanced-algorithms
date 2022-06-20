package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

import java.awt.*;

public class Red extends AbstractHSBColor {

    public Red() {
        super(
                HSBInterval.H_ROSE_LIMIT,
                HSBInterval.H_RED_LIMIT,
                HSBInterval.S_WHITE_LIMIT,
                HSBInterval.MAX_COLOR,
                HSBInterval.B_BLACK_LIMIT,
                HSBInterval.MAX_COLOR);
    }

    @Override
    public boolean matches(int R, int G, int B) {
        float[] HSV = Color.RGBtoHSB(R, G, B, null);
        boolean h = HSV[0] >= 0 && HSV[0] <= interval.maxH()
                || HSV[0] > interval.minH() && HSV[0] < HSBInterval.MAX_COLOR;
        boolean s = interval.minS() <= HSV[1] && HSV[1] <= interval.maxS();
        boolean v = interval.minB() <= HSV[2] && HSV[2] <= interval.maxB();

        return v && s && h;
    }
}
