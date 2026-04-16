package gui.retraining;

import gui.base.FormPanel;
import gui.components.CustomButton;
import gui.components.CustomTextField;
import gui.components.FormRow;
import gui.components.TextRow;
import gui.profile.ProfileDetailsPanel;
import logic.profile.Profile;
import logic.retraining.RetrInput;

import javax.swing.*;
import java.awt.*;

public class RetrainingFormPanel extends FormPanel<RetrInput> {
    private final CustomTextField specialtyField;
    private final CustomTextField passportNumberField;
    private Profile foundProfile = null;
    private Runnable onFind = null;
    private final ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel();

    public  RetrainingFormPanel() {
        specialtyField = new CustomTextField();
        passportNumberField = new CustomTextField();
        CustomButton findBtn = new CustomButton("Find");
        findBtn.addActionListener(e -> onFind.run());
        TextRow buttonRow = new TextRow(findBtn, null);
        buttonRow.setMaximumSize(new Dimension(300, 50));

        form.add(new FormRow(label("Specialty:"), specialtyField));
        form.add(new FormRow(label("Passport Number:"), passportNumberField));
        form.add(buttonRow);
        form.add(profileDetailsPanel.getDetails());

        revalidate(); repaint();
    }

    public String getPassportNumber() {
        return passportNumberField.getText();
    }

    @Override
    public RetrInput getInputData() throws Exception {
        if (specialtyField.getText().isEmpty()
                || passportNumberField.getText().isEmpty()) {
            throw new Exception("Please fill all the fields!");
        }
        return new RetrInput(specialtyField.getText(),
                passportNumberField.getText());
    }

    @Override
    public void setData(RetrInput input) {
        specialtyField.setText(input.getSpecialty());
        passportNumberField.setText(input.getPassportNumber());
    }

    @Override
    public void clearForm() {
        specialtyField.setText("");
        passportNumberField.setText("");
        profileDetailsPanel.update((Profile) null);
    }

    public void setOnFind(Runnable onFind) {this.onFind = onFind;}
    public void updateProfileDetails(Profile profile) {
        this.foundProfile = profile;
        profileDetailsPanel.update(profile);
    }

    public Profile getFoundProfile() {return foundProfile;}
}
