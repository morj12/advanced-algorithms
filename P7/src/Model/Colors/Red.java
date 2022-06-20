package Model.Colors;

import Model.AbstractColor;
import Model.HSVInterval;

import java.awt.*;

public class Red extends AbstractColor {
    public Red() {
        super("Red",
                2,
                HSVInterval.BLUE_RED_BORDER,
                HSVInterval.RED_ORANGE_BORDER,
                HSVInterval.MAX_SATURATION_WHITE,
                HSVInterval.MAX_COLOR,
                HSVInterval.MAX_BRIGHTNESS_BLACK,
                HSVInterval.MAX_COLOR);
    }

    @Override
    public boolean matches(int R, int G, int B) {
        float[] HSV = Color.RGBtoHSB(R, G, B, null);

        boolean v = interval.getMinV() <= HSV[2] && HSV[2] <= interval.getMaxV();
        boolean s = interval.getMinS() <= HSV[1] && HSV[1] <= interval.getMaxS();
        boolean h = HSV[0] >= 0 && HSV[0] <= interval.getMaxH() || HSV[0] > interval.getMinH() && HSV[0] < HSVInterval.MAX_COLOR;
        return v && s && h;
    }
}
