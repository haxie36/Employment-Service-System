package gui.components;

import javax.swing.*;
import java.awt.*;

public class TextAreaRow extends JPanel {

    public TextAreaRow(JLabel label, CustomTextArea area) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 0),
                BorderFactory.createEmptyBorder(0, 5, 10, 5)
        ));

        label.setAlignmentX(LEFT_ALIGNMENT);
        area.setAlignmentX(LEFT_ALIGNMENT);

        area.setPreferredSize(new Dimension(290, 120));
        area.setMinimumSize(new Dimension(290, 80));
        area.setMaximumSize(new Dimension(290, 200));

        add(label);
        add(Box.createVerticalStrut(5));
        add(area);

        setMaximumSize(new Dimension(300, 220));
    }
}