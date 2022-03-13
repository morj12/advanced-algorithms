package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import principal.Principal;
import principal.MeuError;
import principal.PerEsdeveniments;

public class Vista extends JFrame implements ActionListener, PerEsdeveniments, ChangeListener {

    private Principal prog;
    public static final String FUNCION_0 = "O(n)";
    public static final String FUNCION_1 = "O(n^2)";
    public static final String FUNCION_2 = "O(log(n))";

    public Vista(String s, Principal p) {
        super(s);
        prog = p;
        
        this.getContentPane().setLayout(new BorderLayout());
        
        // Inicio del panel de los botones
        JPanel panel = new JPanel();
        
        JButton b0 = new JButton(FUNCION_0);
        b0.addActionListener(this);
        panel.add(b0);
        
        JButton b1 = new JButton(FUNCION_1);
        b1.addActionListener(this);
        panel.add(b1);
        
        JButton b2 = new JButton(FUNCION_2);
        b2.addActionListener(this);
        panel.add(b2);
        
        this.add(BorderLayout.NORTH, panel);
        // Fin del panel de los botones.
        
        // Inicio del panel de dibujo.
        PanellDibuix panell = new PanellDibuix(600, 400, prog.getModel(), this);
        this.add(BorderLayout.CENTER, panell);
        // Fin del panel de dibujo.
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void mostrar() {
        this.pack();
        this.setVisible(true);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            MeuError.informaError(e);
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comanda = e.toString();
        int a = comanda.indexOf(",cmd=") + 5;
        comanda = comanda.substring(a, comanda.indexOf(",", a));
        prog.notificar(comanda);
    }

    @Override
    public void notificar(String s) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }
}
