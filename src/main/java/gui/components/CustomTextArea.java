package gui.components;

import common.LimitDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class CustomTextArea extends JScrollPane {
    private final JTextArea textArea;

    public CustomTextArea() {
        this(new JTextArea(6, 30));
    }

    public CustomTextArea(String text, boolean editable) {
        this(new JTextArea(text, 6, 30));
        setEditable(editable);
    }

    //General setup
    private CustomTextArea(JTextArea area) {
        super(area);
        this.textArea = area;

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setPreferredSize(new Dimension(290,200));
        //Limit of 2000 characters
        ((AbstractDocument) textArea.getDocument())
                .setDocumentFilter(new LimitDocumentFilter(2000));
    }

    public String getText() {return textArea.getText();}
    public void setText(String text) {textArea.setText(text);}
    public void setEditable(boolean editable) {textArea.setEditable(editable);}
}
