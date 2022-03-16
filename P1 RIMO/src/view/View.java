package view;

import controller.Complexity;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class View extends JFrame implements ActionListener {

    private static View instance;

    private final Main main;
    private JPanel buttonsPanel;
    private JButton[] buttons = {
            new JButton(Complexity.LINEAR.toString()),
            new JButton(Complexity.QUADRATIC.toString()),
            new JButton(Complexity.LOGARITHMIC.toString())
    };

    private View(Main main) {
        this.setTitle("Computational costs");
        this.main = main;
        this.getContentPane().setLayout(new BorderLayout());
        buttonsPanel = new JPanel();
        this.add(BorderLayout.NORTH, buttonsPanel);
        Arrays.stream(buttons).forEach(buttonsPanel::add);
        Arrays.stream(buttons).forEach(b -> b.addActionListener(this));
        this.add(new CustomChartPanel(this.main.getModel(), this));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public static View getInstance(Main main) {
        if (instance == null) {
            instance = new View(main);
        }
        return instance;
    }

    public void showGUI() {
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.notify(e.getActionCommand());
    }

}
