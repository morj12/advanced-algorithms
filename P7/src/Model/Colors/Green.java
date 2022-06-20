package Model.Colors;

import Model.AbstractColor;
import Model.HSVInterval;

public class Green extends AbstractColor {
    public Green() {
        super("Green",
                5,
                HSVInterval.YELLOW_GREEN_BORDER,
                HSVInterval.GREEN_BLUE_BORDER,
                HSVInterval.MAX_SATURATION_WHITE,
                HSVInterval.MAX_COLOR,
                HSVInterval.MAX_BRIGHTNESS_BLACK,
                HSVInterval.MAX_COLOR);
    }
}
