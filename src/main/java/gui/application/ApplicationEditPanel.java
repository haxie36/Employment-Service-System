package gui.application;

import application.AppInput;
import application.Application;
import application.ApplicationStatus;
import gui.base.FormPanel;
import gui.components.EnumComboBox;
import gui.components.Row;
import gui.profile.ProfileDetailsPanel;
import gui.vacancy.VacancyDetailsPanel;

import javax.swing.*;

public class ApplicationEditPanel extends FormPanel<AppInput> {
    private final JLabel idValueLabel;
    private final JLabel profileIdValueLabel;
    private final JLabel vacancyIdValueLabel;
    private final JLabel applicationDateValueLabel;
    private final EnumComboBox<ApplicationStatus> statusComboBox;
    private final JPanel profileDetails;
    private final JPanel vacancyDetails;

    public ApplicationEditPanel(Application application) {
        idValueLabel = label(application.getId());
        profileIdValueLabel = label(application.getProfileId());
        vacancyIdValueLabel = label(application.getVacancyId());
        applicationDateValueLabel = label(application.getApplicationDate().toString());
        statusComboBox = new EnumComboBox<>(ApplicationStatus.class);
        statusComboBox.setSelectedItem(application.getStatus());

        //Details panels
        ProfileDetailsPanel temp1 = new ProfileDetailsPanel(application.getProfile());
        profileDetails = temp1.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));

        VacancyDetailsPanel temp2 = new VacancyDetailsPanel(application.getVacancy());
        vacancyDetails = temp2.getDetails();
        vacancyDetails.setBorder(BorderFactory.createTitledBorder("Vacancy"));

        //Adding components
        form.add(new Row(label("Id:"), idValueLabel));
        form.add(new Row(label("Profile Id:"), profileIdValueLabel));
        form.add(profileDetails);
        form.add(new Row(label("Vacancy Id:"), vacancyIdValueLabel));
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
