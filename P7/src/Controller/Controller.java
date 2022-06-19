package Controller;

import Main.Main;
import Main.Notifiable;
import Model.Color;
import Model.ColorDistribution;
import Model.Palette;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private Notifiable main;
    private List<ColorDistribution> distributions;
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
        Color color;
        double[] colors = new double[Palette.getTotalColours()];

        main.notify("startProgress", Main.SAMPLES_NUMBER);
        for (int i = 0; i < Main.SAMPLES_NUMBER; i++) {
            main.notify("step");
            if (isExecuted) {
                int x = random.nextInt(flag.getWidth());
                int y = random.nextInt(flag.getHeight());

                int RGB = flag.getRGB(x, y);
                int red = (RGB & 0x00FF0000) >> 16;
                int green = (RGB & 0x0000FF00) >> 8;
                int blue = (RGB & 0x000000FF);

                color = Palette.getColour(red, green, blue);
                colors[color.getIndex()] += 1.0;
            } else {
                main.notify("startProgress", 100);
                return;
            }
        }

        for (int k = 0; k < colors.length; k++) {
            colors[k] /= Main.SAMPLES_NUMBER;
        }

        ColorDistribution newFlag = new ColorDistribution("New flag");
        newFlag.setDistribution(colors);

        ColorDistribution similarDistribution = findSimilarColorDistribution(newFlag);
        if (similarDistribution == null) {
            main.notify("finished", "");
        } else {
            main.notify("finished", similarDistribution.getNameFlag());
        }

    }

    private ColorDistribution findSimilarColorDistribution(ColorDistribution flag) {
        for (ColorDistribution distribution : distributions) {
            if (similar(flag, distribution)) {
                return distribution;
            }
        }
        return null;

    }

    private boolean similar(ColorDistribution flag, ColorDistribution distribution) {
        double[] a1 = flag.getDistribution();
        double[] a2 = distribution.getDistribution();

        for (int i = 0; i < a1.length; i++) {
            if (a1[i] < a2[i] - 0.05 || a1[i] > a2[i] + 0.05) {
                return false;
            }
        }

        return true;
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
        ColorDistribution flagColorDistribution;

        main.notify("startProgress", flags.length);

        for (int i = 0; i < flags.length; i++) {
            String flag = flags[i];
            try {
                main.notify("step");
                flagImage = ImageIO.read(new File(Main.ALL_FLAGS_DIRECTORY + flag));
                flagColorDistribution = createDistribution(flagImage, flag);
                distributions.add(flagColorDistribution);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        main.notify("loaded");
    }

    private ColorDistribution createDistribution(BufferedImage flagImage, String flag) {
        Color color;
        double colors[] = new double[Palette.getTotalColours()];

        for (int j = 0; j < flagImage.getHeight(); j++) {
            for (int i = 0; i < flagImage.getWidth(); i++) {

                int RGB = flagImage.getRGB(i, j);
                int red = (RGB & 0x00FF0000) >> 16;
                int green = (RGB & 0x0000FF00) >> 8;
                int blue = (RGB & 0x000000FF);

                color = Palette.getColour(red, green, blue);
                colors[color.getIndex()] += 1.0;
            }
        }

        for (int i = 0; i < colors.length; i++) colors[i] /= flagImage.getWidth() * flagImage.getHeight();

        ColorDistribution result = new ColorDistribution(flag);
        result.setDistribution(colors);
        return result;
    }
}
