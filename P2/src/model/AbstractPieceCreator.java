package model;

import model.pieces.*;

public class AbstractPieceCreator {

    public final static String[] pieces = new String[]{"Drunk", "King", "Knight", "Megaknight", "Messi", "Pony", "Queen", "Rook"};

    public static AbstractPiece create(String s) {
        return switch(s) {
            case "Drunk" -> new Drunk();
            case "King" -> new King();
            case "Knight" -> new Knight();
            case "Megaknight" -> new Megaknight();
            case "Messi" -> new Messi();
            case "Pony" -> new Pony();
            case "Queen" -> new Queen();
            case "Rook" -> new Rook();
            default -> null;
        };
    }
}
