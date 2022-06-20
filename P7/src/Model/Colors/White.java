package Model.Colors;

import Model.AbstractColor;
import Model.HSVInterval;

public class White extends AbstractColor {
    public White() {
        super("White",
                0,
                0,
                HSVInterval.MAX_COLOR,
                0,
                HSVInterval.MAX_SATURATION_WHITE,
                HSVInterval.MIN_BRIGHTNESS_WHITE,
                HSVInterval.MAX_COLOR);
    }
}
