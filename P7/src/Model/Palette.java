package Model;

import Model.Colors.*;

public class Palette {
    private static AbstractColor[] colors = new AbstractColor[] {
            new White(),
            new Black(),
            new Red(),
            new Orange(),
            new Yellow(),
            new Green(),
            new Blue()
    };

    public static int getTotalColours () { return colors.length; }

    public static String getColourName (int index) { return colors[index].getName(); }

    public static AbstractColor getColor(int R, int G, int B) {
        for (AbstractColor color : colors) {
            if (color.matches(R,G,B)){
                return color;
            }
        }
        return colors[0];
    }
}
