package Model.Colors;

import Model.AbstractColor;
import Model.HSVInterval;

public class Yellow extends AbstractColor {
    public Yellow() {
        super("Yellow",
                4,
                HSVInterval.ORANGE_YELLOW_BORDER,
                HSVInterval.YELLOW_GREEN_BORDER,
                HSVInterval.MAX_SATURATION_WHITE,
                HSVInterval.MAX_COLOR,
                HSVInterval.MAX_BRIGHTNESS_BLACK,
                HSVInterval.MAX_COLOR);
    }
}
