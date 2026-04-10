package gui.base;

import gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public abstract class DetailsPanel<T> extends JPanel {
    private Runnable onEdit;
    private Runnable onDelete;
    private Runnable onAdditional;
    protected final JPanel details;
    private final JPanel btnPanel;
    private final CustomButton editBtn;
    private final CustomButton deleteBtn;

    public DetailsPanel(){
        super(new BorderLayout());
        setPreferredSize(new Dimension(320,0));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        //Details
        details = new JPanel();
        details.setLayout(new BoxLayout(details,BoxLayout.Y_AXIS));
        details.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        //Buttons
        btnPanel = new JPanel(new GridLayout(1,0,10,0));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        editBtn = new CustomButton("Edit");
        deleteBtn = new CustomButton("Delete");
        editBtn.addActionListener(e -> onEdit.run());
        deleteBtn.addActionListener(e -> onDelete.run());
        //Add the buttons to the panel
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        //Finish
        add(details, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
    public DetailsPanel(String additionalBtnText){
        this();
        CustomButton additionalBtn = new CustomButton(additionalBtnText);
        additionalBtn.addActionListener(e -> onAdditional.run());
        additionalBtn.setVisible(true);

        btnPanel.add(additionalBtn, 0);
        revalidate(); repaint();
    }

    public abstract void update(T Object);

    public JPanel getDetails() {return details;}
    public void setOnEdit (Runnable onEdit) {this.onEdit = onEdit;}
    public void setOnDelete (Runnable onDelete) {this.onDelete = onDelete;}
    public void setOnAdditional (Runnable onAdditional) {this.onAdditional = onAdditional;}
    public void setEditButtonText(String text) {editBtn.setText(text);}
    public void setDeleteButtonText(String text) {deleteBtn.setText(text);}

    protected JLabel label(String text){return new JLabel(text);}
}
