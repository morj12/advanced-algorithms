package Model;

public class Colorimetry {
    private String nameFlag;
    private double[] colorimetry;

    public Colorimetry (String nameFlag) {
        this.nameFlag = nameFlag;
    }

    public void setNameFlag (String nameFlag) {
        this.nameFlag = nameFlag;
    }

    public void setColorimetry(double[] colorimetry) {
        this.colorimetry = colorimetry;
    }

    public double[] getColorimetry() {
        return colorimetry;
    }

    public String getNameFlag() {
        return nameFlag;
    }
}
