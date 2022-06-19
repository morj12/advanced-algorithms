package Model;

import Model.Colors.*;

public class Palette {
    private static Color[] colors = new Color[] {
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

    public static Color getColour (int R, int G, int B) {
        for (Color color :
                colors) {
            if (color.isAcceptedColour(R,G,B)){
                return color;
            }
        }
        return colors[0];
    }
}
