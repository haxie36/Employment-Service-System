package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField() {
        setPreferredSize(new Dimension(250,30));
    }
    public CustomTextField(String text) {
        super(text);
        setPreferredSize(new Dimension(250,30));
    }
}
