package view;

import main.Notifiable;
import model.PieceTypes;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static java.util.Arrays.stream;

public class SelectionPanel extends JPanel {

    private Notifiable view;

    private JComboBox box;

    public SelectionPanel(Notifiable view) {
        this.view = view;
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
        initComponents();
    }

    private void initComponents() {
        box = new JComboBox();
        box.setPreferredSize(new Dimension(180, 30));
        box.addItem("Select a piece");
        this.add(box);
        stream(PieceTypes.values()).forEach(piece -> box.addItem(piece.toString()));
    }

    public String getPiece() {
        return (String) box.getSelectedItem();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 140);
    }
}
