package view;

import main.Notifiable;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private Notifiable view;
    private SelectionPanel selectionPanel;
    private ButtonsPanel buttonsPanel;

    public SettingsPanel(Notifiable view) {
        this.view = view;
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        initComponents();
    }

    private void initComponents() {
        selectionPanel = new SelectionPanel(this.view);
        buttonsPanel = new ButtonsPanel(this.view);
        this.add(selectionPanel);
        this.add(buttonsPanel);
    }

    public void setDimension(int value) {
        buttonsPanel.setDimension(Integer.toString(value));
    }

    public String getPiece() {
        return selectionPanel.getPiece();
    }
}
