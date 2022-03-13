package principal;

import control.Control;
import model.Model;
import vista.Vista;
import static vista.Vista.FUNCION_0;
import static vista.Vista.FUNCION_1;
import static vista.Vista.FUNCION_2;

public class Principal implements PerEsdeveniments {

    private Model mod;    // Punter al Model del patró.
    private Vista vis;    // Punter a la Vista del patró.
    private Control[] con;  // Punter al Control del patró.

    /*
        Construcció de l'esquema MVC
     */
    public static void main(String[] args) {
        new Principal().inicio();
    }

    private void inicio() {
        // Para poder notificar al programa principal le pasaremos la referencia
        // del mismo por parámetro.
        mod = new Model(this);
        con = new Control[3];
        vis = new Vista("Práctica 1 RIMO", this);
        vis.mostrar();
    }

    /*
        Funció simple de la comunicació per Patró d'esdeveniments
     */
    @Override
    public void notificar(String s) {

        int n = -1;

        if (s.equals(FUNCION_0)) {
            n = 0;
        } else if (s.equals(FUNCION_1)) {
            n = 1;
        } else if (s.equals(FUNCION_2)) {
            n = 2;
        }

        if (con[n] == null) {
            con[n] = new Control(this, n);
            con[n].notificar("Arrancar");
        } else {
            mod.notificar(s);
            con[n].notificar("Parar");
            con[n] = null;
        }
    }

    /*
        Mètode public de retorn de la instància del model de dades
     */
    public Model getModel() {
        return mod;
    }
}
