package model;

import java.util.ArrayList;
import principal.Principal;
import principal.PerEsdeveniments;
import static vista.Vista.FUNCION_0;
import static vista.Vista.FUNCION_1;
import static vista.Vista.FUNCION_2;

public class Model implements PerEsdeveniments {

    private Principal prog;
    private ArrayList<Punt>[] listas;

    public static final int[] N = {1, 20, 35};

    public Model(Principal p) {
        prog = p;
        listas = (ArrayList<Punt>[]) new ArrayList[3];
        for (int i = 0; i < listas.length; i++) {
            listas[i] = new ArrayList();
        }
    }

    public void meterPunt(Punt p) {
        int id = p.getId();
        listas[id].add(p);
    }

    public ArrayList<Punt>[] getLists() {
        return listas;
    }

    @Override
    public void notificar(String s) {
        if (s.equals(FUNCION_0)) {
            listas[0].clear();
        } else if (s.equals(FUNCION_1)) {
            listas[1].clear();
        } else if (s.equals(FUNCION_2)) {
            listas[2].clear();
        }
    }
}
