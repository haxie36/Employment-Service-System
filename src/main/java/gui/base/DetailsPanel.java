package gui.base;

import gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public abstract class DetailsPanel<T> extends JPanel {
    private Runnable onEdit;
    private Runnable onDelete;
    private Runnable onAdditional;
    private final CustomButton additionalBtn;
    protected final JPanel details;

    public DetailsPanel(){
        super(new BorderLayout());
        //Details
        details = new JPanel(new BoxLayout(this,BoxLayout.Y_AXIS));
        details.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        additionalBtn = new CustomButton("");
        additionalBtn.setVisible(false);
        CustomButton editBtn = new CustomButton("Edit");
        CustomButton deleteBtn = new CustomButton("Delete");
        editBtn.addActionListener(e -> {onEdit.run();});
        deleteBtn.addActionListener(e -> {onDelete.run();});
        //Complete the buttons panel
        btnPanel.add(additionalBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        //Finish
        add(details, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
    public DetailsPanel(T Object, String additionalBtnText){
        this();
        additionalBtn.setText(additionalBtnText);
        additionalBtn.addActionListener(e -> {onAdditional.run();});
        additionalBtn.setVisible(true);
        revalidate(); repaint();
    }

    public abstract void update(T Object);

    public void setOnEdit (Runnable onEdit) {this.onEdit = onEdit;}
    public void setOnDelete (Runnable onDelete) {this.onDelete = onDelete;}
    public void setOnAdditional (Runnable onAdditional) {this.onAdditional = onAdditional;}
}
