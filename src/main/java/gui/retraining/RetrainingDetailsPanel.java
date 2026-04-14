package gui.retraining;

import gui.base.DetailsPanel;
import gui.components.Row;
import gui.profile.ProfileDetailsPanel;
import registration.Profile;
import retraining.Retraining;

import javax.swing.*;

public class RetrainingDetailsPanel extends DetailsPanel<Retraining> {
    private final JLabel startDateValueLabel;
    private final JLabel endDateValueLabel;
    private final JLabel specialtyValueLabel;
    private final JLabel profileIdValueLabel;
    private final ProfileDetailsPanel profileDetailsPanel;
    private final JLabel statusValueLabel;

    public RetrainingDetailsPanel(Retraining retraining, Profile profile) {
        super("Plan");
        //You either Plan the retraining or Edit it, can't do both
        if (retraining.getStartDate() != null) {
            additionalBtn.setEnabled(false);
        } else {
            editBtn.setEnabled(false);
        }
        startDateValueLabel = label(retraining.getStartDate().toString());
        endDateValueLabel = label(retraining.getEndDate().toString());
        specialtyValueLabel = label(retraining.getSpecialty());
        profileIdValueLabel = label(retraining.getProfileId());
        //Profile details panel
        profileDetailsPanel = new ProfileDetailsPanel(profile);
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Status
        statusValueLabel = label(retraining.getStatus().toString());

        details.add(new Row(label("Start Date:"), startDateValueLabel));
        details.add(new Row(label("End Date:"), endDateValueLabel));
        details.add(new Row(label("Specialty:"), specialtyValueLabel));
        details.add(new Row(label("Profile Id:"), profileIdValueLabel));
        details.add(profileDetails);
        details.add(new Row(label("Status:"), statusValueLabel));
    }

    @Override
    public void update(Retraining retraining) {
        startDateValueLabel.setText(retraining.getStartDate().toString());
        endDateValueLabel.setText(retraining.getEndDate().toString());
        statusValueLabel.setText(retraining.getStatus().toString());
    }
}
