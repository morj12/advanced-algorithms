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

    public static Colorimetry fuse(double[][] colorimetrias) {
        if (colorimetrias == null)
            return null;

        double[] newColour = new double[colorimetrias[0].length];

        for (int i = 0; i < newColour.length ; i++) {
            for (int j = 0; j<colorimetrias.length; j++) {
                newColour[i] += colorimetrias[j][i];
            }
            newColour[i] /= colorimetrias.length;
        }

        Colorimetry resCol = new Colorimetry("");

        resCol.setColorimetry(newColour);

        return resCol;
    }
}
