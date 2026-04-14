package gui.application;

import application.AppInput;
import application.Application;
import application.ApplicationStatus;
import gui.base.FormPanel;
import gui.components.EnumComboBox;
import gui.components.Row;
import gui.profile.ProfileDetailsPanel;
import gui.vacancy.VacancyDetailsPanel;
import registration.Profile;
import vacancy.Vacancy;

import javax.swing.*;

public class ApplicationEditPanel extends FormPanel<AppInput> {
    private final JLabel idValueLabel;
    private final JLabel applicationDateValueLabel;
    private final EnumComboBox<ApplicationStatus> statusComboBox;
    private final JPanel profileDetails;
    private final JPanel vacancyDetails;

    public ApplicationEditPanel(Application application, Profile profile, Vacancy vacancy) {
        idValueLabel = label(application.getId());
        applicationDateValueLabel = label(application.getApplicationDate().toString());
        statusComboBox = new EnumComboBox<>(ApplicationStatus.class);
        statusComboBox.setSelectedItem(application.getStatus());

        //Details panels
        ProfileDetailsPanel temp1 = new ProfileDetailsPanel(profile);
        profileDetails = temp1.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));

        VacancyDetailsPanel temp2 = new VacancyDetailsPanel(vacancy);
        vacancyDetails = temp2.getDetails();
        vacancyDetails.setBorder(BorderFactory.createTitledBorder("Vacancy"));

        //Adding components
        form.add(new Row(label("Id:"), idValueLabel));
        form.add(profileDetails);
        form.add(vacancyDetails);
        form.add(new Row(label("Application Date:"), applicationDateValueLabel));
        form.add(new Row(label("Status:"), statusComboBox));

        revalidate(); repaint();
    }

    public int getStatus() {
        return statusComboBox.getSelectedIndex();
    }

    @Override
    public AppInput getInputData() throws Exception {return null;}

    @Override
    public void setData(AppInput input) {}

    @Override
    public void clearForm() {}
}
