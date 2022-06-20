package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

public class Violet extends AbstractHSBColor {

    public Violet() {
        super(
                HSBInterval.H_BLUE_LIMIT,
                HSBInterval.H_VIOLET_LIMIT,
                HSBInterval.S_WHITE_LIMIT,
                HSBInterval.MAX_COLOR,
                HSBInterval.B_BLACK_LIMIT,
                HSBInterval.MAX_COLOR);
    }
}
