package gui.components;

import javax.swing.*;
import java.awt.*;

public class FormRow extends JPanel {
    public FormRow(JLabel label, JTextField field) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 0),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));

        label.setAlignmentX(LEFT_ALIGNMENT);
        field.setAlignmentX(LEFT_ALIGNMENT);
        add(label);
        add(Box.createVerticalStrut(5));
        add(field);

        setMaximumSize(new Dimension(300, 60));
    }
}