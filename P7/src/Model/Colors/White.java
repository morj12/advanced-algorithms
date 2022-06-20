package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

public class White extends AbstractHSBColor {

    public White() {
        super(
                0,
                HSBInterval.MAX_COLOR,
                0,
                HSBInterval.S_WHITE_LIMIT,
                HSBInterval.B_WHITE_LIMIT,
                HSBInterval.MAX_COLOR);
    }
}
