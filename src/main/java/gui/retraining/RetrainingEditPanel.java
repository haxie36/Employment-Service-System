package gui.retraining;

import gui.base.FormPanel;
import gui.components.CustomDateField;
import gui.components.CustomTextField;
import gui.components.EnumComboBox;
import gui.components.Row;
import gui.profile.ProfileDetailsPanel;
import retraining.PlanningInput;
import retraining.Retraining;
import retraining.RetrainingStatus;

import javax.swing.*;

public class RetrainingEditPanel extends FormPanel<PlanningInput> {
    private final CustomDateField startDateField;
    private final CustomDateField endDateField;
    private final CustomTextField specialtyField;
    private final JLabel profileIdValueLabel;
    private final ProfileDetailsPanel profileDetailsPanel;
    private final EnumComboBox<RetrainingStatus> statusComboBox;

    public RetrainingEditPanel(Retraining retraining) {
        startDateField = new CustomDateField();
        startDateField.setDate(retraining.getStartDate());
        endDateField = new CustomDateField();
        endDateField.setDate(retraining.getEndDate());
        specialtyField = new CustomTextField(retraining.getSpecialty());
        profileIdValueLabel = label(retraining.getProfileId());
        //Profile details panel
        profileDetailsPanel = new ProfileDetailsPanel(retraining.getProfile());
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Status
        statusComboBox = new EnumComboBox<>(RetrainingStatus.class);
        statusComboBox.setSelectedIndex(retraining.getStatus().getId());

        form.add(new Row(label("Start Date:"), startDateField));
        form.add(new Row(label("End Date:"), endDateField));
        form.add(new Row(label("Specialty:"), specialtyField));
        form.add(new Row(label("Profile Id:"), profileIdValueLabel));
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
        specialtyField.setText(input.getSpecialty());
        statusComboBox.setSelectedIndex(input.getStatus());
    }

    @Override
    public void clearForm() {}
}
