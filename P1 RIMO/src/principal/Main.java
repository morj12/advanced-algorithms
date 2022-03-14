package principal;

import control.Complexity;
import control.Controller;
import model.Model;
import vista.View;

import javax.swing.*;

public class Main implements Notifiable {

    private Model model;    // Punter al Model del patró.
    private View view;    // Punter a la Vista del patró.
    private Controller[] controller;  // Punter al Control del patró.

    /*
        Construcció de l'esquema MVC
     */
    public static void main(String[] args) {
        new Main().inicio();
    }

    private void inicio() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Para poder notificar al programa principal le pasaremos la referencia
        // del mismo por parámetro.
        model = new Model(this);
        controller = new Controller[3];
        view = new View("Práctica 1 RIMO", this);
        view.showGUI();
    }

    /*
        Funció simple de la comunicació per Patró d'esdeveniments
     */
    @Override
    public void notify(String s) {

        Complexity type = Complexity.valueOf(s);
        int algo_index = -1;
        switch (type) {
            case LINEAR -> algo_index = 0;
            case QUADRATIC -> algo_index = 1;
            case LOGARITHMIC -> algo_index = 2;
        }

        if (controller[algo_index] == null) {
            controller[algo_index] = new Controller(this, algo_index);
            controller[algo_index].notify("START");
        } else {
            model.notify(s);
            controller[algo_index].notify("STOP");
            controller[algo_index] = null;
        }
    }

    /*
        Mètode public de retorn de la instància del model de dades
     */
    public Model getModel() {
        return model;
    }
}
