package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

public class Yellow extends AbstractHSBColor {

    public Yellow() {
        super(
                HSBInterval.H_ORANGE_LIMIT,
                HSBInterval.H_YELLOW_LIMIT,
                HSBInterval.S_WHITE_LIMIT,
                HSBInterval.MAX_COLOR,
                HSBInterval.B_BLACK_LIMIT,
                HSBInterval.MAX_COLOR);
    }
}
