package Model.Colors;

import Model.Color;

public class Red extends Color {
    public Red() {
        super("Red",
                2,
                BLUE_RED_BORDER,
                RED_ORANGE_BORDER,
                MAX_SATURATION_WHITE,
                100,
                MAX_BRIGHTNESS_BLACK,
                100);
    }

    @Override
    public boolean isAcceptedColour(int R, int G, int B) {
        double[] HSV = RGBtoHSV(R, G, B);

        boolean v = minV <= HSV[2] && HSV[2] <= maxV;
        boolean s = minS <= HSV[1] && HSV[1] <= maxS;
        boolean h = HSV[0] >= 0 && HSV[0] <= maxH || HSV[0] > minH && HSV[0] < MAX_COLOR;
        return v && s && h;
    }
}
