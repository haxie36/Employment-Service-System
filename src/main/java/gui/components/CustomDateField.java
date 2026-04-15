package gui.components;

import logic.common.DateUtils;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CustomDateField extends JFormattedTextField {
    public CustomDateField() {
        super(createFormatter());
        setPreferredSize(new Dimension(290, 30));
        setMaximumSize(new Dimension(290, 30));
        setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
    }
    public CustomDateField(LocalDate date) {
        this();
        setDate(date);
    }

    private static MaskFormatter createFormatter() {
        try {
            MaskFormatter mask = new MaskFormatter("##.##.####");
            mask.setPlaceholderCharacter('_');
            return mask;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDate getDate() {
        String text = getText();

        if (text.contains("_")) {throw new IllegalArgumentException("Incomplete date!");}

        try {
            return LocalDate.parse(text, DateUtils.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid Date Format!");
        }
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            setText("");
        } else {
            setText(date.format(DateUtils.FORMATTER));
        }
    }
}