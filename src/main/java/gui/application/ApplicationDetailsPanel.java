package gui.application;

import application.Application;
import gui.base.DetailsPanel;
import gui.components.Row;
import gui.profile.ProfileDetailsPanel;
import gui.vacancy.VacancyDetailsPanel;

import javax.swing.*;

public class ApplicationDetailsPanel extends DetailsPanel<Application> {
    private final JLabel idValueLabel;
    private final JLabel profileIdValueLabel;
    private final JLabel vacancyIdValueLabel;
    private final JLabel applicationDateValueLabel;
    private final JLabel statusValueLabel;
    private final JPanel profileDetails;
    private final JPanel vacancyDetails;

    public  ApplicationDetailsPanel(Application application) {
        idValueLabel = label(application.getId());
        profileIdValueLabel = label(application.getProfileId());
        vacancyIdValueLabel = label(application.getVacancyId());
        applicationDateValueLabel = label(application.getApplicationDate().toString());
        statusValueLabel = label(application.getStatus().toString());
        //Details panels
        ProfileDetailsPanel temp1 = new ProfileDetailsPanel(application.getProfile());
        profileDetails = temp1.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));

        VacancyDetailsPanel temp2 = new VacancyDetailsPanel(application.getVacancy());
        vacancyDetails = temp2.getDetails();
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

    }
}
