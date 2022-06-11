package Model;

import java.io.File;

public class Flag {
    private File image;
    private String name;

    public Flag(File image, String name) {
        this.image = image;
        this.name = name;
    }

    public File getImage() { return image; }

    public String getName() { return name; }

    public void setImage(File file) {
        this.image = file;
    }

    public void setName(String name) {
        this.name = name;
    }
}
