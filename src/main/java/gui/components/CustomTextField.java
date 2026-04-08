package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField() {
        setPreferredSize(new Dimension(250,30));
    }
    public CustomTextField(String text) {
        super(text);
        setPreferredSize(new Dimension(240,30));
    }

    public CustomTextField(float wM, float hM) {
        setPreferredSize(new Dimension((int)(250*wM),(int)(30*hM)));
    }
    public CustomTextField(String text, float wM, float hM) {
        super(text);
        setPreferredSize(new Dimension((int)(250*wM),(int)(30*hM)));
    }
}
