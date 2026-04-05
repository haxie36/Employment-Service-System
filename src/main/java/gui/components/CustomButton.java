package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setPreferredSize(new Dimension(120, 40));
    }
}
