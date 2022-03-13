package control;

import static model.Model.N;
import model.Punt;
import principal.Principal;
import principal.MeuError;
import principal.PerEsdeveniments;
import static vista.Vista.FUNCION_0;
import static vista.Vista.FUNCION_1;
import static vista.Vista.FUNCION_2;

/**
 *
 * @author mascport
 */
public class Control extends Thread implements PerEsdeveniments {

    private Principal prog;
    private int id;
    private boolean seguir;

    public Control(Principal p, int x) {
        prog = p;
        id = x;
        seguir = true;
    }

    public void run() {
        Punt p = null;

        for (int iter = 0; seguir && iter < N.length; iter++) {

            if (id == 0) {
                p = ejecucion_On(iter);
            } else if (id == 1) {
                p = ejecucion_On2(iter);
            } else if (id == 2) {
                p = ejecucion_Ologn(iter);
            }
            if (seguir) {
                prog.getModel().meterPunt(p);
            }
        }
    }

    private Punt ejecucion_On(int iter) {

        long temps = System.currentTimeMillis();
        for (int i = 0; seguir && i < N[iter]; i++) {
            espera(5, 0);
        }
        temps = System.currentTimeMillis() - temps;

        Punt p = new Punt(id, temps, N[iter]);
        return p;
    }

    private Punt ejecucion_On2(int iter) {

        long temps = System.currentTimeMillis();
        for (int i = 0; seguir && i < N[iter]; i++) {
            for (int j = 0; seguir && j < N[iter]; j++) {
                espera(5, 0);
            }
        }
        temps = System.currentTimeMillis() - temps;

        Punt p = new Punt(id, temps, N[iter]);
        return p;
    }

    private Punt ejecucion_Ologn(int iter) {

        long temps = System.currentTimeMillis();
        for (int i = 1; seguir && i <= N[iter]; i *= 2) {
            espera(5, 0);
        }
        temps = System.currentTimeMillis() - temps;

        Punt p = new Punt(id, temps, N[iter]);
        return p;
    }

    private void espera(long m, int n) {
        try {
            Thread.sleep(m, n);
        } catch (Exception e) {
            MeuError.informaError(e);
        }
    }

    @Override
    public void notificar(String s) {
        if (s.startsWith("Arrancar")) {
            this.start();
        } else if (s.startsWith("Parar")) {
            seguir = false;
        }
    }
}
