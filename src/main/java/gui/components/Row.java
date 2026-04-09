package gui.components;

import javax.swing.*;
import java.awt.*;

public class Row extends JPanel {
    public Row(Component c1, Component c2) {
        super(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        //temp
        setBackground(Color.pink);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));

        if (!(c2 instanceof CustomTextArea))
            setMaximumSize(new Dimension(300, 60));
        else
            setMaximumSize(new Dimension(300, 240));

        add(c1); add(c2);
    }
}
