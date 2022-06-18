package Controller;

import Main.Notifiable;
import Model.*;
import Utils.ReflectionHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Controller implements Notifiable {

    private static boolean DEBUG = Main.Main.getDebugMode();
    private static String FILE_EXTENSION = "png";
    private static String DIRECTORY_LOCATION = "src/resources/";
    private static String DIRECTORY_IMG_LOCATION = DIRECTORY_LOCATION + "flags/";
    private ArrayList<Colorimetry> imagesCollection;
    private static Thread algorithmThread;
    private Notifiable view;

    public Controller(Notifiable view) {
        this.view = view;
    }

    public void identifyFlag(Flag flag, int nPuntos, Algorithm algorithm, boolean singleThread) {
        algorithmThread = new Thread(() -> {
            long time = System.nanoTime();
            algorithm.identifyFlag(flag, nPuntos, singleThread);
            time = System.nanoTime() - time;

            view.notify("notifyAlgorithmEnd", new Object[] {time / 1000000});
        });

        algorithmThread.start();
    }

    public void loadDatabase() {
        (new Thread(()->{
            loadDataBase();
        })).start();
    }

    public void stop() {
        try {
            algorithmThread.interrupt();
        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
    }

    private static String getExt (String file) {
        int index = file.lastIndexOf('.');
        if (index == -1)
            return "";
        else
            return file.substring(index).toLowerCase();
    }

    /**
     * Metodo que leer una fichero de tipo imagen.
     * @param filename Ruta de la imagen que se quiere leer.
     */
    private BufferedImage readImage(String filename) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(filename));

        } catch(Exception e){
            e.printStackTrace();
        }

        return image;
    }

    /**
     * Metodo que procesa la base de datos. Si no esta creada, analiza todas las imagenes y lo graba en un fichero
     * (la base de datos queda ya en memoria), sino lee la base de datos del fichero y la trnasfiere a memoria. Durante
     * el proceso le pasa por parametro a la Vista el numero total de ficheros que hay en el directorio y el numero
     * total que ha analizado.
     */
    public void loadDataBase() {

        try {

            File database = new File(DIRECTORY_LOCATION + "database.dat");

            if (!database.exists()) {

                BufferedImage image;
                File directory = new File(DIRECTORY_IMG_LOCATION);
                String[] files = directory.list();
                Colorimetry coloRes;
                imagesCollection = new ArrayList<>();

                for (int i = 0; i < files.length; i++) {

                    if (files[i].contains(FILE_EXTENSION)) {
                        image = readImage(DIRECTORY_IMG_LOCATION + files[i]);
                        coloRes = processImage(image, files[i]);
                        imagesCollection.add(coloRes);
                    }

                }

                writeToFile(imagesCollection);

            } else {
                readDataBase(DIRECTORY_LOCATION + "database.dat");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            view.notify("notifyDatabaseLoaded", null);
        }

    }

    public Colorimetry processImage(BufferedImage image, String title) {
        Colour col;
        Palette paleta = new Palette();
        double colRes[] = new double[paleta.getTotalColours()];

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {

                int RGB = image.getRGB(i, j);
                int red = (RGB & 0x00FF0000) >> 16;
                int green = (RGB & 0x0000FF00) >> 8;
                int blue = (RGB & 0x000000FF);

                col = paleta.getColour(red, green, blue);
                colRes[col.getIndex()] += 1.0;
            }
        }

        for (int i = 0; i < colRes.length; i++) colRes[i] /= image.getWidth() * image.getHeight();

        Colorimetry result = new Colorimetry(title);
        result.setColorimetry(colRes);
        return result;
    }

    /**
     * Metodo para leer el conjunto de datos de todas las imagenes procesadas y guardalas en memoria principal.
     * @param filepath Nombre de la ruta del fichero que se quiere leer.
     */
    public void readDataBase(String filepath) {

        boolean hasData = true;

        try {

            FileInputStream in = new FileInputStream(filepath);
            ObjectInputStream stream = new ObjectInputStream(in);

            imagesCollection = (ArrayList<Colorimetry>) stream.readObject();

            if (imagesCollection == null) {
                imagesCollection = new ArrayList<>();
                throw new Exception("No objects retrieved");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Colorimetry> getDatabase() {
        return this.imagesCollection;
    }

    /**
     * Metodo que escribe en el fichero donde se almacena toda la informacion de las imagenes.
     * @param palette Conjunto de informacion que se quiere almacenar.
     */
    public void writeToFile(Object palette) {

        try {

            FileOutputStream out = new FileOutputStream(DIRECTORY_LOCATION + "database.dat");
            ObjectOutputStream stream = new ObjectOutputStream(out);

            stream.writeObject(palette);
            stream.close();

            if(DEBUG)
                System.out.println("Image Data was successfully written to a file.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notify(String function, Object[] args) {
        ReflectionHelper.execute(Controller.class,this, function, args);
    }

    public void notifyView(Flag found) {
        view.notify("notifyAlgorithmStep", new Object[] {found});
    }
}
