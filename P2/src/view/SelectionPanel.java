package view;

import main.Notifiable;
import model.PieceTypes;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static java.util.Arrays.stream;

public class SelectionPanel extends JPanel {

    private Notifiable view;

    private JComboBox[] pieceCombos = {
            new JComboBox(),
            new JComboBox(),
            new JComboBox(),
            new JComboBox()
    };

    public SelectionPanel(Notifiable view) {
        this.view = view;
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
        initComponents();
    }

    private void initComponents() {
        // add comboboxes
        stream(pieceCombos).forEach(box -> {
            box.setPreferredSize(new Dimension(180, 30));
            box.addItem("Select a piece");
            this.add(box);
        });
        // add pieces to comboboxes
        stream(PieceTypes.values()).forEach(piece -> stream(pieceCombos).forEach(box -> box.addItem(piece.toString())));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 140);
    }
}
