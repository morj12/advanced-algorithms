package Controller;

import Model.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Algorithm implements IAlgorithm {
    private double tolerance = 0.005;
    private Controller controller;

    public Algorithm(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void identifyFlag(Flag flag, int nPuntos, boolean singleThread) {
        // Variables a usar
        File file = flag.getImage();
        Random rnd = new Random();
        BufferedImage image;
        Colorimetry resCol = null;
        Palette paleta = new Palette();

        // Número de hilos y el factor de división para cada dimensión
        int nThreads = singleThread ? 1 : 4;
        int strideFactor = (int) Math.ceil(Math.sqrt(nThreads));

        // Abrir imagen legible
        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        // Pool de hilos a usar para lanzar varios hilos
        ExecutorService pool = Executors.newFixedThreadPool(nThreads);

        // stride marca el punto en el que inicia cada hilo
        int strideH = image.getHeight() / strideFactor;
        int strideW = image.getWidth() / strideFactor;
        int startX = 0, startY = 0;

        // Crear trabajos a realizar en paralelo
        ArrayList<Callable<Colorimetry>> tasks = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            int finalStrideW = strideW;
            int finalStartX = startX;
            int finalStartY = startY;
            int finalStrideH = strideH;

            tasks.add(new Callable() {
                @Override
                public Colorimetry call() throws Exception {
                    double [] colp = new double[paleta.getTotalColours()];
                    Colour col;

                    for (int k = 0; k < nPuntos; k++) {
                        int x = (int) ((rnd.nextDouble() * (finalStrideW-finalStartX)));
                        int y = (int) ((rnd.nextDouble() * (finalStrideH-finalStartY)));

                        int RGB = 0x0;
                        try {
                            RGB = image.getRGB(x + finalStartX, y + finalStartY);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        int red = (RGB & 0x00FF0000) >> 16;
                        int green = (RGB & 0x0000FF00) >> 8;
                        int blue = (RGB & 0x000000FF);

                        col = paleta.getColour(red, green, blue);

                        colp[col.getIndex()] += 1.0;
                    }

                    for (int k = 0; k < colp.length; k++) {
                        colp[k] /= nPuntos;
                    }

                    Colorimetry result = new Colorimetry("");
                    result.setColorimetry(colp);
                    return result;
                }
            });

            if (strideW >= image.getWidth()-1) {
                strideW = image.getWidth() / strideFactor;
                startX = 0;
                startY += strideH;
                strideH += strideH;
            } else {
                startX += strideW;
                strideW += strideW;
            }
        }

        resCol = launchThreads(pool, paleta, nThreads, tasks);

        // Contrastar con base de datos
        contrastColorimetryAgainstDatabase(resCol);
    }

    @Override
    public boolean similarColorimetry(Colorimetry candidate, Colorimetry target) {
        double[] a1 = candidate.getColorimetry();
        double[] a2 = target.getColorimetry();

        for (int i = 0; i<a1.length;i++) {
            if (a1[i] < a2[i]-tolerance || a1[i] > a2[i]+tolerance) return false;
        }

        return true;
    }

    @Override
    public void contrastColorimetryAgainstDatabase(Colorimetry foundColorimetry) {
        ArrayList<Colorimetry> bd = controller.getDatabase();
        Flag result = null;

        for (Colorimetry bandera : bd) {
            if (similarColorimetry(foundColorimetry, bandera)) {

                result = new Flag(new File("src/resources/flags/"+bandera.getNameFlag()),
                        bandera.getNameFlag());

                controller.notifyView(result);
            }
        }
    }

    @Override
    public Colorimetry launchThreads(ExecutorService threadPool, Palette paleta, int nThreads, ArrayList<Callable<Colorimetry>> tasks) {
        Colorimetry resCol = null;
        double[][] colorimetria = new double[nThreads][paleta.getTotalColours()];
        int idx = 0;
        try {
            List<Future<Colorimetry>> list = threadPool.invokeAll(tasks);

            for (Future<Colorimetry> col : list) {
                resCol = col.get();
                colorimetria[idx] = resCol.getColorimetry();
                idx++;
            }

        } catch (CancellationException | InterruptedException | ExecutionException ce) {
            ce.printStackTrace();
            return null;
        }

        return Colorimetry.fuse(colorimetria);
    }
}
