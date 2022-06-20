package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

public class Cyan extends AbstractHSBColor {

    public Cyan() {
        super(
                HSBInterval.H_GREEN_LIMIT,
                HSBInterval.H_CYAN_LIMIT,
                HSBInterval.S_WHITE_LIMIT,
                HSBInterval.MAX_COLOR,
                HSBInterval.B_BLACK_LIMIT,
                HSBInterval.MAX_COLOR);
    }
}
