package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomTextArea extends JScrollPane {
    private final JTextArea textArea;

    public CustomTextArea() {
        super(textArea = new JTextArea(6, 30));
    }
    public CustomTextArea(String text, boolean editable) {
        super(textArea = new JTextArea(text, 6, 30));
        textArea.setEditable(editable);
    }

    public String getText() {return textArea.getText();}
    public void setText(String text) {textArea.setText(text);}
    public void setEditable(boolean editable) {textArea.setEditable(editable);}
}
