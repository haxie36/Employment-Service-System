package gui.application;

import gui.base.FormPanel;
import gui.components.EnumComboBox;
import gui.components.TextRow;
import gui.profile.ProfileDetailsPanel;
import gui.vacancy.VacancyDetailsPanel;
import logic.application.AppInput;
import logic.application.Application;
import logic.application.ApplicationStatus;
import logic.profile.Profile;
import logic.vacancy.Vacancy;

import javax.swing.*;

public class ApplicationEditPanel extends FormPanel<AppInput> {
    private final EnumComboBox<ApplicationStatus> statusComboBox;

    public ApplicationEditPanel(Application application, Profile profile, Vacancy vacancy) {
        JLabel idValueLabel = label(application.getId());
        JLabel applicationDateValueLabel = label(application.getApplicationDate().toString());
        statusComboBox = new EnumComboBox<>(ApplicationStatus.class);
        statusComboBox.setSelectedItem(application.getStatus());

        //Details panels
        ProfileDetailsPanel temp1 = new ProfileDetailsPanel(profile);
        JPanel profileDetails = temp1.getDetails();
        profileDetails.setBorder(BorderFactory.createTitledBorder("Profile"));

        VacancyDetailsPanel temp2 = new VacancyDetailsPanel(vacancy);
        JPanel vacancyDetails = temp2.getDetails();
        vacancyDetails.setBorder(BorderFactory.createTitledBorder("Vacancy"));

        //Adding components
        form.add(new TextRow(label("Application Id:"), idValueLabel));
        form.add(profileDetails);
        form.add(vacancyDetails);
        form.add(new TextRow(label("Application Date:"), applicationDateValueLabel));
        form.add(new TextRow(label("Status:"), statusComboBox));

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
