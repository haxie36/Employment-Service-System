package gui.application;

import gui.base.DetailsPanel;
import gui.components.TextRow;
import gui.profile.ProfileDetailsPanel;
import gui.vacancy.VacancyDetailsPanel;
import logic.application.Application;
import logic.profile.Profile;
import logic.vacancy.Vacancy;
import logic.vacancy.VacancyStatus;

import javax.swing.*;

public class ApplicationDetailsPanel extends DetailsPanel<Application> {
    private final JLabel statusValueLabel;

    public  ApplicationDetailsPanel(Application application, Profile profile, Vacancy vacancy) {
        //If vacancy isn't open, you can't change the status
        //I believe it makes sense, doesn't it?
        if (vacancy.getStatus() != VacancyStatus.OPEN)
            editBtn.setEnabled(false);

        JLabel idValueLabel = label(application.getId());
        JLabel applicationDateValueLabel = label(application.getApplicationDate().toString());
        statusValueLabel = label(application.getStatus().toString());
        //Profile details panel
        ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel(profile);
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Vacancy details panel
        VacancyDetailsPanel vacancyDetailsPanel = new VacancyDetailsPanel(vacancy);
        JPanel vacancyDetails = vacancyDetailsPanel.getDetails();
        vacancyDetails.setBorder(BorderFactory.createTitledBorder("Vacancy"));

        //Adding components
        details.add(new TextRow(label("Application Id:"), idValueLabel));
        details.add(profileDetails);
        details.add(vacancyDetails);
        details.add(new TextRow(label("Application Date:"), applicationDateValueLabel));
        details.add(new TextRow(label("Status:"), statusValueLabel));

        revalidate(); repaint();
    }

    @Override
    public void update(Application application) {
        statusValueLabel.setText(application.getStatus().toString());
    }
}
