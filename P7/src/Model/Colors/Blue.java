package Model.Colors;

import Model.AbstractColor;
import Model.HSVInterval;

public class Blue extends AbstractColor {
    public Blue() {
        super("Blue",
                6,
                HSVInterval.GREEN_BLUE_BORDER,
                HSVInterval.BLUE_RED_BORDER,
                HSVInterval.MAX_SATURATION_WHITE,
                HSVInterval.MAX_COLOR,
                HSVInterval.MAX_BRIGHTNESS_BLACK,
                HSVInterval.MAX_COLOR);
    }
}
