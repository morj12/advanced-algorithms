package Controller;

import Main.Main;
import Main.Notifiable;
import Model.AbstractHSBColor;
import Model.DistributionWrapper;
import Model.ColorExamples;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Controller {

    private Notifiable main;
    private List<DistributionWrapper> distributions;
    private volatile boolean isExecuted;

    public Controller(Notifiable main) {
        this.main = main;
        this.distributions = new ArrayList<>();
        this.isExecuted = false;
    }

    public void searchFlagAsync(BufferedImage flag) {
        isExecuted = true;
        new Thread(() -> searchFlag(flag)).start();
    }

    private void searchFlag(BufferedImage flag) {
        Random random = new Random();
        double[] distribution = new double[ColorExamples.getColorsLength()];

        main.notify("startProgress", Main.SAMPLES_NUMBER);
        for (int i = 0; i < Main.SAMPLES_NUMBER; i++) {
            main.notify("step");
            if (isExecuted) {
                int x = random.nextInt(flag.getWidth());
                int y = random.nextInt(flag.getHeight());
                chooseColor(flag.getRGB(x, y), distribution);
            } else {
                main.notify("startProgress", 100);
                return;
            }
        }

        for (int k = 0; k < distribution.length; k++) {
            distribution[k] /= Main.SAMPLES_NUMBER;
        }

        DistributionWrapper newFlag = new DistributionWrapper("New flag");
        newFlag.setDistribution(distribution);

        DistributionWrapper similarDistribution;
        if ((similarDistribution = findSimilarColorDistribution(newFlag)) == null) {
            main.notify("finished", "");
        } else {
            main.notify("finished", similarDistribution.getName());
        }

    }

    private DistributionWrapper findSimilarColorDistribution(DistributionWrapper flag) {
        for (DistributionWrapper distribution : distributions) {
            if (similar(flag, distribution)) {
                return distribution;
            }
        }
        return null;
    }

    private boolean similar(DistributionWrapper first, DistributionWrapper second) {
        double[] firstDistribution = first.getDistribution();
        double[] secondDistribution = second.getDistribution();

        return IntStream.range(0, firstDistribution.length)
                .noneMatch(i ->
                        tooDifferent(
                                firstDistribution[i],
                                secondDistribution[i]));
    }

    private boolean tooDifferent(double first, double second) {
        return Math.abs(first - second) > 0.05;
    }

    public void stopSearch() {
        isExecuted = false;
    }

    public void loadDistributionsAsync() {
        new Thread(this::loadDistributions).start();
    }

    public void loadDistributions() {
        String[] flags = new File(Main.ALL_FLAGS_DIRECTORY).list();
        BufferedImage flagImage;
        DistributionWrapper flagDistributionWrapper;

        main.notify("startProgress", flags.length);

        for (String flag : flags) {
            try {
                main.notify("step");
                flagImage = ImageIO.read(new File(Main.ALL_FLAGS_DIRECTORY + flag));
                flagDistributionWrapper = createDistribution(flagImage, flag);
                distributions.add(flagDistributionWrapper);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        main.notify("loaded");
    }

    private DistributionWrapper createDistribution(BufferedImage flagImage, String flag) {
        double[] distribution = new double[ColorExamples.getColorsLength()];

        for (int j = 0; j < flagImage.getHeight(); j++) {
            for (int i = 0; i < flagImage.getWidth(); i++) {
                chooseColor(flagImage.getRGB(i, j), distribution);
            }
        }

        for (int i = 0; i < distribution.length; i++) distribution[i] /= flagImage.getWidth() * flagImage.getHeight();

        DistributionWrapper result = new DistributionWrapper(flag);
        result.setDistribution(distribution);
        return result;
    }

    private void chooseColor(int rgb, double[] colors) {
        int red = (rgb & 0x00FF0000) >> 16;
        int green = (rgb & 0x0000FF00) >> 8;
        int blue = (rgb & 0x000000FF);

        AbstractHSBColor color = ColorExamples.getColor(red, green, blue);
        colors[ColorExamples.findColorIndex(color)] += 1.0;
    }
}
