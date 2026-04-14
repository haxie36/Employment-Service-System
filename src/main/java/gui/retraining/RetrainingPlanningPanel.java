package gui.retraining;

import gui.base.FormPanel;
import gui.components.CustomDateField;
import gui.components.Row;
import gui.profile.ProfileDetailsPanel;
import registration.Profile;
import retraining.PlanningInput;
import retraining.Retraining;

import javax.swing.*;

public class RetrainingPlanningPanel extends FormPanel<PlanningInput> {
    private final CustomDateField startDateField;
    private final CustomDateField endDateField;
    private final JLabel specialtyValueLabel;
    private final JLabel profileIdValueLabel;
    private final ProfileDetailsPanel profileDetailsPanel;
    private final JLabel statusValueLabel;

    public RetrainingPlanningPanel(Retraining retraining, Profile profile) {
        startDateField = new CustomDateField();
        startDateField.setDate(retraining.getStartDate());
        endDateField = new CustomDateField();
        endDateField.setDate(retraining.getEndDate());
        specialtyValueLabel = label(retraining.getSpecialty());
        profileIdValueLabel = label(retraining.getProfileId());
        //Profile details panel
        profileDetailsPanel = new ProfileDetailsPanel(profile);
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Status
        statusValueLabel = label(retraining.getStatus().toString());

        form.add(new Row(label("Start Date:"), startDateField));
        form.add(new Row(label("End Date:"), endDateField));
        form.add(new Row(label("Specialty:"), specialtyValueLabel));
        form.add(new Row(label("Profile Id:"), profileIdValueLabel));
        form.add(profileDetails);
        form.add(new Row(label("Status:"), statusValueLabel));

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
                "-", //Fictional
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
