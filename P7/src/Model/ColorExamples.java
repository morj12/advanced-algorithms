package Model;

import Model.Colors.*;

public class ColorExamples {

    private static final AbstractHSBColor[] colors = new AbstractHSBColor[]{
            new White(),
            new Black(),
            new Red(),
            new Orange(),
            new Yellow(),
            new Green(),
            new Cyan(),
            new Blue(),
            new Violet(),
            new Rose()
    };

    public static int getColorsLength() {
        return colors.length;
    }

    public static AbstractHSBColor getColor(int R, int G, int B) {
        for (AbstractHSBColor color : colors) {
            if (color.matches(R, G, B)) return color;
        }
        return colors[0];
    }

    public static int findColorIndex(AbstractHSBColor color) {
        for (int i = 0; i < colors.length; i++) {
            if (color.getClass() == colors[i].getClass()) {
                return i;
            }
        }
        return 0;
    }
}
