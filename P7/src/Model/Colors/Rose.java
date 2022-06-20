package Model.Colors;

import Model.AbstractHSBColor;
import Model.HSBInterval;

public class Rose extends AbstractHSBColor {

    public Rose() {
        super(
                HSBInterval.H_VIOLET_LIMIT,
                HSBInterval.H_ROSE_LIMIT,
                HSBInterval.S_WHITE_LIMIT,
                HSBInterval.MAX_COLOR,
                HSBInterval.B_BLACK_LIMIT,
                HSBInterval.MAX_COLOR);
    }
}
