package Model;

public record HSBInterval(double minH, double maxH, double minS, double maxS, double minB, double maxB) {

    public static double B_BLACK_LIMIT = 0.15;
    public static double B_WHITE_LIMIT = 0.85;
    public static double S_WHITE_LIMIT = 0.15;
    public static double H_RED_LIMIT = 0.07;
    public static double H_ORANGE_LIMIT = 0.11;
    public static double H_YELLOW_LIMIT = 0.22;
    public static double H_GREEN_LIMIT = 0.47;
    public static double H_CYAN_LIMIT = 0.5;
    public static double H_BLUE_LIMIT = 0.75;
    public static double H_VIOLET_LIMIT = 0.75;
    public static double H_ROSE_LIMIT = 0.83;
    public static double MAX_COLOR = 1;

}
