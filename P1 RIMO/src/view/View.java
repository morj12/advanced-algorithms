package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Complexity;
import main.Main;
import main.ErrorWriter;
import main.Notifiable;

public class View extends JFrame implements ActionListener, Notifiable, ChangeListener {

    private final Main main;
    private JPanel buttonsPanel;
    private JButton[] buttons = {
            new JButton(Complexity.LINEAR.toString()),
            new JButton(Complexity.QUADRATIC.toString()),
            new JButton(Complexity.LOGARITHMIC.toString())
    };

    public View(String s, Main main) {
        super(s);
        this.main = main;
        this.getContentPane().setLayout(new BorderLayout());

        buttonsPanel = new JPanel();
        this.add(BorderLayout.NORTH, buttonsPanel);
        Arrays.stream(buttons).forEach(buttonsPanel::add);
        Arrays.stream(buttons).forEach(b -> b.addActionListener(this));

        CustomChartPanel chartPanel = new CustomChartPanel(600, 400, this.main.getModel(), this);
        this.add(BorderLayout.CENTER, chartPanel);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void showGUI() {
        this.pack();
        this.setVisible(true);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            ErrorWriter.writeError(e);
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.notify(e.getActionCommand());
    }

    @Override
    public void notify(String s) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }
}
