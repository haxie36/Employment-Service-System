package gui.components;

import javax.swing.*;
import java.awt.*;

public class Row extends JPanel {
    public Row(JLabel label, Component component) {
        super(new FlowLayout(FlowLayout.LEFT,10,0));
        add(label); add(component);
    }
}
