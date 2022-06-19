package Model;

public class ColorDistribution {
    private String nameFlag;
    private double[] distribution;

    public ColorDistribution(String nameFlag) {
        this.nameFlag = nameFlag;
    }

    public void setNameFlag (String nameFlag) {
        this.nameFlag = nameFlag;
    }

    public void setDistribution(double[] distribution) {
        this.distribution = distribution;
    }

    public double[] getDistribution() {
        return distribution;
    }

    public String getNameFlag() {
        return nameFlag;
    }

    public static ColorDistribution fuse(double[][] distributions) {
        if (distributions == null)
            return null;

        double[] newColour = new double[distributions[0].length];

        for (int i = 0; i < newColour.length ; i++) {
            for (int j = 0; j<distributions.length; j++) {
                newColour[i] += distributions[j][i];
            }
            newColour[i] /= distributions.length;
        }

        ColorDistribution resCol = new ColorDistribution("");

        resCol.setDistribution(newColour);

        return resCol;
    }
}
