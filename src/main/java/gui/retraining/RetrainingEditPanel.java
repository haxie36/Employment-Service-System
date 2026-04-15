package gui.retraining;

import gui.base.FormPanel;
import gui.components.CustomDateField;
import gui.components.EnumComboBox;
import gui.components.FormRow;
import gui.components.TextRow;
import gui.profile.ProfileDetailsPanel;
import logic.profile.Profile;
import logic.retraining.PlanningInput;
import logic.retraining.Retraining;
import logic.retraining.RetrainingStatus;

import javax.swing.*;

public class RetrainingEditPanel extends FormPanel<PlanningInput> {
    private final CustomDateField startDateField;
    private final CustomDateField endDateField;
    private final EnumComboBox<RetrainingStatus> statusComboBox;

    public RetrainingEditPanel(Retraining retraining, Profile profile) {
        JLabel idValueLabel = label(retraining.getId());

        startDateField = new CustomDateField();
        endDateField = new CustomDateField();
        //If Date is null method setDate sets "" as text
        startDateField.setDate(retraining.getStartDate());
        endDateField.setDate(retraining.getEndDate());

        JLabel specialtyLabel = label(retraining.getSpecialty());
        //Profile details panel
        ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel(profile);
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Status
        statusComboBox = new EnumComboBox<>(RetrainingStatus.class);
        statusComboBox.setSelectedIndex(retraining.getStatus().getId());

        form.add(new TextRow(label("Retraining Id"), idValueLabel));
        form.add(new FormRow(label("Start Date:"), startDateField));
        form.add(new FormRow(label("End Date:"), endDateField));
        form.add(new TextRow(label("Specialty:"), specialtyLabel));
        form.add(profileDetails);
        form.add(new TextRow(label("Status:"), statusComboBox));
    }

    @Override
    public PlanningInput getInputData() throws Exception {
        if (startDateField.getDate() == null
        || endDateField.getDate() == null) {
            throw new Exception("Please fill all the fields!");
        }
        return new PlanningInput(
                startDateField.getDate(),
                endDateField.getDate(),
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
