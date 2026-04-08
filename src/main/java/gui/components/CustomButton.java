package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setPreferredSize(new Dimension(120, 40));
        setMaximumSize(new Dimension(120, 40));
        setFont(new Font(this.getFont().getFontName(), Font.PLAIN,14));
    }
}
