package Model.Colors;

import Model.AbstractColor;
import Model.HSVInterval;

public class Orange extends AbstractColor {
    public Orange() {
        super("Orange",
                3,
                HSVInterval.RED_ORANGE_BORDER,
                HSVInterval.ORANGE_YELLOW_BORDER,
                HSVInterval.MAX_SATURATION_WHITE,
                HSVInterval.MAX_COLOR,
                HSVInterval.MAX_BRIGHTNESS_BLACK,
                HSVInterval.MAX_COLOR);
    }
}
