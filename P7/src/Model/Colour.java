package Model;

public abstract class Colour {
    protected static double MAX_BRIGHTNESS_BLACK = 15;
    protected static double MIN_BRIGHTNESS_WHITE = 85;
    protected static double MAX_SATURATION_WHITE = 15;
    protected static double MIN_SATURATION_COLOURS = MAX_SATURATION_WHITE;
    protected static double MIN_BRIGHTNESS_COLOURS = MAX_BRIGHTNESS_BLACK;
    protected static double RED_ORANGE_BORDER = 15;
    protected static double ORANGE_YELLOW_BORDER = 40;
    protected static double YELLOW_GREEN_BORDER = 85;
    protected static double GREEN_BLUE_BORDER = 180;
    protected static double BLUE_RED_BORDER = 280;

    protected String name;
    protected int index;
    protected double minH;
    protected double maxH;
    protected double minS;
    protected double maxS;
    protected double minV;
    protected double maxV;

    public Colour(String name, int index, double minH, double maxH, double minS, double maxS, double minV, double maxV) {
        this.name = name;
        this.index = index;
        this.minH = minH;
        this.maxH = maxH;
        this.minS = minS;
        this.maxS = maxS;
        this.minV = minV;
        this.maxV = maxV;
    }

    public int getIndex() { return index; }

    public String getName() {
        return name;
    }

    public static double[] RGBtoHSV(int R, int G, int B) {
        double red = R / 255.0;
        double green = G / 255.0;
        double blue = B / 255.0;

        double maxValue = Math.max(red, Math.max(green, blue));
        double minValue = Math.min(red, Math.min(green, blue));
        double delta = maxValue - minValue;

        //Default values and calculate V
        double hue = -1,
                saturation = -1,
                value = maxValue * 100;

        //Calculate H
        if (maxValue == minValue)
            hue = 0;
        else if (maxValue == red && green >= blue)
            hue = (60 * ((green - blue) / delta) + 0) % 360;
        else if (maxValue == red)
            hue = (60 * ((green - blue) / delta) + 360) % 360;
        else if (maxValue == green)
            hue = (60 * ((blue - red) / delta) + 120) % 360;
        else if (maxValue == blue)
            hue = (60 * ((red - green) / delta) + 240) % 360;

        //Calculate S
        if (maxValue == 0)
            saturation = 0;
        else
            saturation = (delta / maxValue) * 100;

        return new double[] {hue, saturation, value};
    }

    public boolean isAcceptedColour(int R, int G, int B) {
        double[] HSV = RGBtoHSV(R, G, B);

        return minV <= HSV[2] && HSV[2] <= maxV
                && minS <= HSV[1] && HSV[1] <= maxS
                && minH <= HSV[0] && HSV[0] < maxH;
    }
}
