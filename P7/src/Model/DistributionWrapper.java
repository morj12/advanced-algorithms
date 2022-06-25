package Model;

import java.io.Serializable;

public class DistributionWrapper implements Serializable {

    private final String name;
    private double[] distribution;

    public DistributionWrapper(String name) {
        this.name = name;
    }

    public void setDistribution(double[] distribution) {
        this.distribution = distribution;
    }

    public double[] getDistribution() {
        return distribution;
    }

    public String getName() {
        return name;
    }
}
