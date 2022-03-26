package model;

import model.pieces.*;

public class AbstractPieceCreator {

    public final static String[] pieces = new String[]{"Drunk", "King", "Knight", "Megaknight", "Messi", "Pony", "Queen", "Rook"};

    public static AbstractPiece create(String s) {
        switch(s) {
            case "Drunk":
                return new Drunk();
            case "King":
                return new King();
            case "Knight":
                return new Knight();
            case "Megaknight":
                return new Megaknight();
            case "Messi":
                return new Messi();
            case "Pony":
                return new Pony();
            case "Queen":
                return new Queen();
            case "Rook":
                return new Rook();
            default:
                return null;
        }
    }
}
