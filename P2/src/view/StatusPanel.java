package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class StatusPanel extends JPanel {

    private JLabel status;

    public StatusPanel() {
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        initComponents();

    }

    private void initComponents() {
        status = new JLabel("Status");
        status.setAlignmentX(LEFT_ALIGNMENT);
        this.add(status);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 30);
    }
}
