package gui.application;

import gui.base.FormPanel;
import gui.components.CustomButton;
import gui.components.CustomTextField;
import gui.components.FormRow;
import gui.components.TextRow;
import gui.profile.ProfileDetailsPanel;
import logic.application.AppInput;
import logic.profile.Profile;

import javax.swing.*;
import java.awt.*;

public class FindProfileFormPanel extends FormPanel<AppInput> {
    private final CustomTextField passportNumberField;
    private final ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel();
    private Runnable onFind;
    private Profile foundProfile = null;

    public FindProfileFormPanel() {
        passportNumberField = new CustomTextField();
        CustomButton findButton = new CustomButton("Find");
        findButton.addActionListener(e -> onFind.run());
        TextRow buttonRow = new TextRow(findButton, null);
        buttonRow.setMaximumSize(new Dimension(300, 50));
        saveBtn.setText("Continue");

        //Profile info
        JPanel details = profileDetailsPanel.getDetails();

        form.add(new FormRow(label("Passport Number:"), passportNumberField));
        form.add(buttonRow);
        form.add(details);

        revalidate(); repaint();
    }

    @Override
    public AppInput getInputData() throws Exception {
        if (passportNumberField.getText().isEmpty())
            throw new Exception("Please fill the field!");

        return new AppInput(
                passportNumberField.getText(),
                -1 //Fictional
        );
    }

    @Override
    public void setData(AppInput input) {
        passportNumberField.setText(input.getPassportNumber());
    }

    @Override
    public void clearForm() {
        passportNumberField.setText("");
        profileDetailsPanel.update((Profile) null);
    }

    public void setOnFind(Runnable onFind) {this.onFind = onFind;}
    public void updateProfileDetails(Profile profile) {
        this.foundProfile = profile;
        profileDetailsPanel.update(profile);
    }

    public Profile getFoundProfile() {return foundProfile;}
    public void setFoundProfile(Profile foundProfile) {this.foundProfile = foundProfile;}
}
