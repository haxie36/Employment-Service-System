package gui.retraining;

import gui.base.FormPanel;
import gui.components.*;
import gui.profile.ProfileDetailsPanel;
import registration.Profile;
import retraining.RetrInput;

public class RetrainingFormPanel extends FormPanel<RetrInput> {
    private final CustomTextField specialtyField;
    private final CustomTextField passportNumberField;
    private Profile foundProfile = null;
    private final CustomButton findBtn;
    private Runnable onFind = null;
    private final ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel();

    public  RetrainingFormPanel() {
        specialtyField = new CustomTextField();
        passportNumberField = new CustomTextField();
        findBtn = new CustomButton("Find");
        findBtn.addActionListener(e -> onFind.run());

        form.add(new Row(label("Specialty:"), specialtyField));
        form.add(new Row(label("Passport Number:"), passportNumberField));
        form.add(findBtn);
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
    public void setFoundProfile(Profile foundProfile) {this.foundProfile = foundProfile;}
}
