package gui.application;

import application.AppInput;
import gui.base.FormPanel;
import gui.components.CustomButton;
import gui.components.CustomTextField;
import gui.components.FormRow;
import gui.profile.ProfileDetailsPanel;
import registration.Profile;

import javax.swing.*;

public class FindProfileFormPanel extends FormPanel<AppInput> {
    private final CustomTextField passportNumberField;
    private final ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel();
    private Runnable onFind;
    private Profile foundProfile = null;

    public FindProfileFormPanel() {
        passportNumberField = new CustomTextField();
        CustomButton findButton = new CustomButton("Find");
        findButton.addActionListener(e -> onFind.run());
        findButton.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        saveBtn.setText("Continue");

        //Profile info
        JPanel details = profileDetailsPanel.getDetails();

        form.add(new FormRow(label("Passport Number:"), passportNumberField));
        form.add(findButton);
        form.add(details);

        revalidate(); repaint();
    }

    @Override
    public AppInput getInputData() throws Exception {
        if (passportNumberField.getText().isEmpty())
            throw new Exception("Please fill the field!");

        return new AppInput(
                passportNumberField.getText(),
                null
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
