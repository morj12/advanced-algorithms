package model;

import java.awt.image.BufferedImage;

public abstract class Piece {
    protected String name;
    protected static final String PATH = "images/";
    protected Movement[] movements;
    protected BufferedImage image;

}
