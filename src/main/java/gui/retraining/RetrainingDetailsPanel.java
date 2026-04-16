package gui.retraining;

import gui.base.DetailsPanel;
import gui.components.TextRow;
import gui.profile.ProfileDetailsPanel;
import logic.profile.Profile;
import logic.retraining.Retraining;
import logic.retraining.RetrainingStatus;

import javax.swing.*;

public class RetrainingDetailsPanel extends DetailsPanel<Retraining> {
    private final JLabel startDateValueLabel;
    private final JLabel endDateValueLabel;
    private final JLabel statusValueLabel;

    public RetrainingDetailsPanel(Retraining retraining, Profile profile) {
        super("Plan");
        //If Retraining is complete, you should not be able to edit it
        if (retraining.getStatus() == RetrainingStatus.COMPLETED) {
            editBtn.setEnabled(false);
            additionalBtn.setEnabled(false);
        }
        //You either Plan the retraining or Edit it, can't do both
        else {
            if (retraining.getStartDate() != null) {
                additionalBtn.setEnabled(false);
            } else {
                editBtn.setEnabled(false);
            }
        }
        JLabel idValueLabel = label(profile.getId());
        JLabel specialtyValueLabel = label(retraining.getSpecialty());
        startDateValueLabel = label(
                retraining.getStartDate() != null ? retraining.getStartDate().toString() : "null"
        );
        endDateValueLabel = label(
                retraining.getEndDate() != null ? retraining.getEndDate().toString() : "null"
        );
        //Profile details panel
        ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel(profile);
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Status
        statusValueLabel = label(retraining.getStatus().toString());

        details.add(new TextRow(label("Retraining Id:"), idValueLabel));
        details.add(new TextRow(label("Start Date:"), startDateValueLabel));
        details.add(new TextRow(label("End Date:"), endDateValueLabel));
        details.add(new TextRow(label("Specialty:"), specialtyValueLabel));
        details.add(profileDetails);
        details.add(new TextRow(label("Status:"), statusValueLabel));
    }

    @Override
    public void update(Retraining retraining) {
        startDateValueLabel.setText(retraining.getStartDate().toString());
        endDateValueLabel.setText(retraining.getEndDate().toString());
        statusValueLabel.setText(retraining.getStatus().toString());
    }
}
