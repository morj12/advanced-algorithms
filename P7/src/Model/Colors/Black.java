package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

public class Black extends AbstractHSBColor {

    public Black() {
        super(
                0,
                HSBInterval.MAX_COLOR,
                0,
                HSBInterval.MAX_COLOR,
                0,
                HSBInterval.B_BLACK_LIMIT);
    }
}
