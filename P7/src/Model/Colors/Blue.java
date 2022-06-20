package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

public class Blue extends AbstractHSBColor {

    public Blue() {
        super(
                HSBInterval.H_CYAN_LIMIT,
                HSBInterval.H_BLUE_LIMIT,
                HSBInterval.S_WHITE_LIMIT,
                HSBInterval.MAX_COLOR,
                HSBInterval.B_BLACK_LIMIT,
                HSBInterval.MAX_COLOR);
    }
}
