package gui.components;

import javax.swing.*;
import java.awt.*;

public class TextRow extends JPanel {
    public TextRow(Component c1, Component c2) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        setAlignmentX(LEFT_ALIGNMENT);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 0),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        if (c1 != null)
            add(c1);
        if (c2 != null)
            add(c2);

        setMaximumSize(new Dimension(300, 30));
    }
}
