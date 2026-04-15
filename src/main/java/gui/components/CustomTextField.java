package gui.components;

import common.LimitDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField() {
        this("");
    }
    public CustomTextField(String text) {
        super(text);
        setPreferredSize(new Dimension(290,30));
        setMaximumSize(new Dimension(290,30));
        //100 characters limit
        ((AbstractDocument) getDocument())
                .setDocumentFilter(new LimitDocumentFilter(100));
    }
}
