package Model;

public class HSVInterval {

    public static double MAX_BRIGHTNESS_BLACK = 0.15;
    public static double MIN_BRIGHTNESS_WHITE = 0.85;
    public static double MAX_SATURATION_WHITE = 0.15;
    public static double RED_ORANGE_BORDER = 25.0/360;
    public static double ORANGE_YELLOW_BORDER = 40.0/360;
    public static double YELLOW_GREEN_BORDER = 80.0/360;
    public static double GREEN_BLUE_BORDER = 170.0/360;
    public static double BLUE_RED_BORDER = 280.0/360;
    public static double MAX_COLOR = 1;

    private final double minH;
    private final double maxH;
    private final double minS;
    private final double maxS;
    private final double minV;
    private final double maxV;

    public HSVInterval(double minH, double maxH, double minS, double maxS, double minV, double maxV) {
        this.minH = minH;
        this.maxH = maxH;
        this.minS = minS;
        this.maxS = maxS;
        this.minV = minV;
        this.maxV = maxV;
    }

    public double getMinH() {
        return minH;
    }

    public double getMaxH() {
        return maxH;
    }

    public double getMinS() {
        return minS;
    }

    public double getMaxS() {
        return maxS;
    }

    public double getMinV() {
        return minV;
    }

    public double getMaxV() {
        return maxV;
    }
}
