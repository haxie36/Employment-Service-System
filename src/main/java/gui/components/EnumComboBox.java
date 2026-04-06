package gui.components;

import javax.swing.*;

public class EnumComboBox<T extends Enum<T>> extends JComboBox<T> {
    public EnumComboBox(Class<T> enumClass) {
        super(enumClass.getEnumConstants());
    }
}