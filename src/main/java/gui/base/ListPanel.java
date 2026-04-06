package gui.base;

import javax.swing.*;
import java.awt.*;

public class ListPanel<T> extends JPanel {
    private final JList<T> list;
    private final DefaultListModel<T> model;

    public ListPanel(T[] items) {
        model = new DefaultListModel<>();
        for (T item : items) {
            model.addElement(item);
        }
        list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        JScrollPane scrollPane = new JScrollPane(list);

        add(scrollPane, BorderLayout.CENTER);
    }

    public T getSelectedItem() {
        return list.getSelectedValue();
    }
    public JList<T> getList() {return list;}

    public void updateList(T[] items) {
        model.clear();
        for (T item : items) {
            model.addElement(item);
        }
    }

    public void clearSelection() {list.clearSelection();}
}
