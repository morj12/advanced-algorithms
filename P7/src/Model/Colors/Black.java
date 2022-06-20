package Model.Colors;

import Model.AbstractColor;
import Model.HSVInterval;

public class Black extends AbstractColor {
    public Black() {
        super("Black",
                1,
                0,
                HSVInterval.MAX_COLOR,
                0,
                HSVInterval.MAX_COLOR,
                0,
                HSVInterval.MAX_BRIGHTNESS_BLACK);
    }
}
