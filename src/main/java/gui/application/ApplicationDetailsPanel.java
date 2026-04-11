package gui.application;

import application.Application;
import gui.base.DetailsPanel;
import gui.components.Row;
import gui.profile.ProfileDetailsPanel;
import gui.vacancy.VacancyDetailsPanel;
import vacancy.VacancyStatus;

import javax.swing.*;

public class ApplicationDetailsPanel extends DetailsPanel<Application> {
    private final JLabel idValueLabel;
    private final JLabel profileIdValueLabel;
    private final JLabel vacancyIdValueLabel;
    private final JLabel applicationDateValueLabel;
    private final JLabel statusValueLabel;
    private final ProfileDetailsPanel profileDetailsPanel;
    private final VacancyDetailsPanel vacancyDetailsPanel;

    public  ApplicationDetailsPanel(Application application) {
        //If vacancy isn't open, you can't change the status
        //I believe it makes sense, doesn't it?
        if (application.getVacancy().getStatus() != VacancyStatus.OPEN)
            editBtn.setEnabled(false);

        idValueLabel = label(application.getId());
        profileIdValueLabel = label(application.getProfileId());
        vacancyIdValueLabel = label(application.getVacancyId());
        applicationDateValueLabel = label(application.getApplicationDate().toString());
        statusValueLabel = label(application.getStatus().toString());
        //Profile details panel
        profileDetailsPanel = new ProfileDetailsPanel(application.getProfile());
        JPanel profileDetails = profileDetailsPanel.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));
        //Vacancy details panel
        vacancyDetailsPanel = new VacancyDetailsPanel(application.getVacancy());
        JPanel vacancyDetails = vacancyDetailsPanel.getDetails();
        vacancyDetails.setBorder(BorderFactory.createTitledBorder("Vacancy"));

        //Adding components
        details.add(new Row(label("Id:"), idValueLabel));
        details.add(new Row(label("Profile Id:"), profileIdValueLabel));
        details.add(profileDetails);
        details.add(new Row(label("Vacancy Id:"), vacancyIdValueLabel));
        details.add(vacancyDetails);
        details.add(new Row(label("Application Date:"), applicationDateValueLabel));
        details.add(new Row(label("Status:"), statusValueLabel));

        revalidate(); repaint();
    }

    @Override
    public void update(Application application) {
        idValueLabel.setText(application.getId());
        profileIdValueLabel.setText(application.getProfileId());
        vacancyIdValueLabel.setText(application.getVacancyId());
        applicationDateValueLabel.setText(application.getApplicationDate().toString());
        statusValueLabel.setText(application.getStatus().toString());
        profileDetailsPanel.update(application.getProfile());
        vacancyDetailsPanel.update(application.getVacancy());
    }
}
