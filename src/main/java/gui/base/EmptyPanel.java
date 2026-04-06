package gui.base;

import javax.swing.*;
import java.awt.*;

public class EmptyPanel extends JPanel {
    public EmptyPanel() {
        super(new BorderLayout());
        JLabel text = new JLabel("Please select an Object");
        add(text, BorderLayout.CENTER);
    }
}
