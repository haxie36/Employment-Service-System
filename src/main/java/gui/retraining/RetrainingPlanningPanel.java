package gui.retraining;

import gui.base.FormPanel;
import gui.components.CustomDateField;
import gui.components.FormRow;
import gui.components.TextRow;
import gui.profile.ProfileDetailsPanel;
import logic.profile.Profile;
import logic.retraining.PlanningInput;
import logic.retraining.Retraining;

import javax.swing.*;

public class RetrainingPlanningPanel extends FormPanel<PlanningInput> {
    private final CustomDateField startDateField;
    private final CustomDateField endDateField;

    public RetrainingPlanningPanel(Retraining retraining, Profile profile) {
        JLabel idValueLabel = label(retraining.getId());

        startDateField = new CustomDateField();
        endDateField = new CustomDateField();
        //If Date is null method setDate sets "" as text
        startDateField.setDate(retraining.getStartDate());
        endDateField.setDate(retraining.getEndDate());

        JLabel specialtyValueLabel = label(retraining.getSpecialty());
        //Profile details panel
        ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel(profile);
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Status
        JLabel statusValueLabel = label(retraining.getStatus().toString());

        form.add(new TextRow(label("Retraining Id"), idValueLabel));
        form.add(new FormRow(label("Start Date:"), startDateField));
        form.add(new FormRow(label("End Date:"), endDateField));
        form.add(new TextRow(label("Specialty:"), specialtyValueLabel));
        form.add(profileDetails);
        form.add(new TextRow(label("Status:"), statusValueLabel));

        revalidate(); repaint();
    }

    @Override
    public PlanningInput getInputData() {
        if (startDateField.getDate() == null
                || endDateField.getDate() == null) {
            throw new IllegalArgumentException("Please fill all fields!");
        }
        return new PlanningInput(
                startDateField.getDate(),
                endDateField.getDate(),
                99 //Fictional
        );
    }

    @Override
    public void setData(PlanningInput input) {
        startDateField.setDate(input.getStartDate());
        endDateField.setDate(input.getEndDate());
    }

    @Override
    public void clearForm() {
        startDateField.setText("");
        endDateField.setText("");
    }
}
