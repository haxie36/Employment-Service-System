package gui.base;

import javax.swing.*;
import java.awt.*;

public class EmptyPanel extends JPanel {
    public EmptyPanel() {
        super(new BorderLayout());
        JLabel text = new JLabel("Please select an Object");
        text.setFont(new Font(text.getFont().getFontName(), Font.BOLD, 16));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setBorder(BorderFactory.createBevelBorder(1));

        add(text, BorderLayout.CENTER);
    }
}
