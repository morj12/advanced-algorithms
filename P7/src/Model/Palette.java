package Model;

import Model.Colours.*;

public class Palette {
    private Colour[] colours;

    public Palette(){
        colours = new Colour[] {
                new White(),
                new Black(),
                new Red(),
                new Orange(),
                new Yellow(),
                new Green(),
                new Blue()
        };
    }

    public int getTotalColours () { return colours.length; }

    public String getColourName (int index) { return colours[index].getName(); }

    public Colour getColour (int R, int G, int B) {
        for (Colour colour:
             colours) {
            if (colour.isAcceptedColour(R,G,B)){
                return colour;
            }
        }
        return colours[0];
    }
}
