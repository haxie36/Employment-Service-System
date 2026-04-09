package gui.components;

import javax.swing.*;
import java.awt.*;

public class CustomTextArea extends JScrollPane {
    private final JTextArea textArea;

    public CustomTextArea() {
        super(textArea = new JTextArea(6, 30));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setPreferredSize(new Dimension(250,200));
    }
    public CustomTextArea(String text, boolean editable) {
        super(textArea = new JTextArea(text, 6, 30));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setEditable(editable);
        setPreferredSize(new Dimension(250,200));
    }

    public String getText() {return textArea.getText();}
    public void setText(String text) {textArea.setText(text);}
    public void setEditable(boolean editable) {textArea.setEditable(editable);}
}
