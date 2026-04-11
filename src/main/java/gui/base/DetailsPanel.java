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
    protected JLabel status;
    protected CustomButton additionalBtn;
    protected final CustomButton editBtn;
    protected final CustomButton deleteBtn;

    public DetailsPanel(){
        super(new BorderLayout());
        setPreferredSize(new Dimension(320,0));
        //Details
        details = new JPanel();
        details.setLayout(new BoxLayout(details,BoxLayout.Y_AXIS));
        details.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        //Bottom panel with buttons and status (shows up when and error occurs)
        JPanel btnAndStatusPanel = new JPanel();
        btnAndStatusPanel.setLayout(new BoxLayout(btnAndStatusPanel,BoxLayout.Y_AXIS));
        btnAndStatusPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //Status
        status = new JLabel(" ");
        status.setFont(new Font("Arial", Font.BOLD, 14));
        status.setForeground(Color.RED);
        status.setAlignmentX(Component.LEFT_ALIGNMENT);
        status.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        status.setHorizontalAlignment(SwingConstants.RIGHT);
        //Buttons
        btnPanel = new JPanel(new GridLayout(1,0,10,10));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        editBtn = new CustomButton("Edit");
        deleteBtn = new CustomButton("Delete");
        editBtn.addActionListener(e -> {if (onEdit!=null) onEdit.run();});
        deleteBtn.addActionListener(e -> {if (onDelete!=null) onDelete.run();});
        //Add the buttons to the panel
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        //Complete the buttons and status panel
        btnAndStatusPanel.add(status);
        btnAndStatusPanel.add(btnPanel);
        //Finish
        add(details, BorderLayout.CENTER);
        add(btnAndStatusPanel, BorderLayout.SOUTH);
    }
    public DetailsPanel(String additionalBtnText){
        this();
        additionalBtn = new CustomButton(additionalBtnText);
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
    public void setStatusText(String text) {status.setText(text);}

    protected JLabel label(String text){return new JLabel(text);}
}
