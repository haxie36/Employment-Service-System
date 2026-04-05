package gui.components;

import javax.swing.*;
import java.awt.*;

//Abstract class for a form
//T isn't for a business model object (profile, vacancy etc.)
//T is for an input class (RegInput, VacInput etc.)
public abstract class FormPanel<T> extends JPanel {
    private Runnable onSave;
    private Runnable onCancel;
    protected JPanel form;
    protected JLabel status;

    public FormPanel() {
        super(new BorderLayout());
        //Form itself (text fields and stuff)
        form = new JPanel(new BoxLayout(this,BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //Bottom panel with buttons and status (shows up when and error occurs)
        JPanel btnAndStatusPanel = new JPanel(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAndStatusPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //Status
        status = new JLabel("");
        status.setForeground(Color.RED);
        //Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        CustomButton saveBtn = new CustomButton("Save");
        CustomButton cancelBtn = new CustomButton("Cancel");
        saveBtn.addActionListener(e -> {if (onSave!=null) onSave.run();});
        cancelBtn.addActionListener(e -> {if (onCancel!=null) onCancel.run();});
        //Complete the buttons panel
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        //Complete the buttons and status panel
        btnAndStatusPanel.add(status);
        btnAndStatusPanel.add(btnPanel);
        //Finish
        add(form, BorderLayout.CENTER);
        add(btnAndStatusPanel, BorderLayout.SOUTH);
    }

    public abstract T getInputData(); //Return whole input class
    public abstract void setData(T input); //Sets fields' text from input class
    public abstract void clearForm();

    public void setOnSave (Runnable onSave) {this.onSave = onSave;}
    public void setOnCancel (Runnable onCancel) {this.onCancel = onCancel;}
    public void setStatusText(String text) {status.setText(text);}
}