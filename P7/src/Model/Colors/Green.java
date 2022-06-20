package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

public class Green extends AbstractHSBColor {

    public Green() {
        super(
                HSBInterval.H_YELLOW_LIMIT,
                HSBInterval.H_GREEN_LIMIT,
                HSBInterval.S_WHITE_LIMIT,
                HSBInterval.MAX_COLOR,
                HSBInterval.B_BLACK_LIMIT,
                HSBInterval.MAX_COLOR);
    }
}
