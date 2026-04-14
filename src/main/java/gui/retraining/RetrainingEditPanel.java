package gui.retraining;

import gui.base.FormPanel;
import gui.components.CustomDateField;
import gui.components.CustomTextField;
import gui.components.EnumComboBox;
import gui.components.Row;
import gui.profile.ProfileDetailsPanel;
import registration.Profile;
import retraining.PlanningInput;
import retraining.Retraining;
import retraining.RetrainingStatus;

import javax.swing.*;

public class RetrainingEditPanel extends FormPanel<PlanningInput> {
    private final CustomDateField startDateField;
    private final CustomDateField endDateField;
    private final CustomTextField specialtyField;
    private final EnumComboBox<RetrainingStatus> statusComboBox;

    public RetrainingEditPanel(Retraining retraining, Profile profile) {
        startDateField = new CustomDateField();
        endDateField = new CustomDateField();
        //If Date is null method setDate sets "" as text
        startDateField.setDate(retraining.getStartDate());
        endDateField.setDate(retraining.getEndDate());

        specialtyField = new CustomTextField(retraining.getSpecialty());
        //Profile details panel
        ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel(profile);
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Status
        statusComboBox = new EnumComboBox<>(RetrainingStatus.class);
        statusComboBox.setSelectedIndex(retraining.getStatus().getId());

        form.add(new Row(label("Start Date:"), startDateField));
        form.add(new Row(label("End Date:"), endDateField));
        form.add(new Row(label("Specialty:"), specialtyField));
        form.add(profileDetails);
        form.add(new Row(label("Status:"), statusComboBox));
    }

    @Override
    public PlanningInput getInputData() throws Exception {
        if (startDateField.getDate() == null
        || endDateField.getDate() == null
        || specialtyField.getText().isEmpty()) {
            throw new Exception("Please fill all the fields!");
        }
        return new PlanningInput(
                startDateField.getDate(),
                endDateField.getDate(),
                specialtyField.getText(),
                statusComboBox.getSelectedIndex()
        );
    }

    @Override
    public void setData(PlanningInput input) {
        startDateField.setDate(input.getStartDate());
        endDateField.setDate(input.getEndDate());
        statusComboBox.setSelectedIndex(input.getStatus());
    }

    @Override
    public void clearForm() {}
}
