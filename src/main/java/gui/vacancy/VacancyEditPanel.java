package gui.vacancy;

import gui.base.FormPanel;
import gui.components.CustomTextArea;
import gui.components.CustomTextField;
import gui.components.EnumComboBox;
import gui.components.Row;
import vacancy.VacInput;
import vacancy.Vacancy;
import vacancy.VacancyStatus;

import javax.swing.*;
import java.awt.*;

public class VacancyEditPanel extends FormPanel<VacInput> {
    private final JLabel titleValueLabel;
    private final JLabel idValueLabel;
    private final JLabel companyValueLabel;
    private final CustomTextField contactField;
    private final JLabel specialtyValueLabel;
    private final JLabel experienceValueLabel;
    private final CustomTextArea descriptionArea;
    private final EnumComboBox<VacancyStatus> statusComboBox;

    public VacancyEditPanel(Vacancy vacancy) {
        titleValueLabel = label(vacancy.getTitle());
        titleValueLabel.setFont(new Font("Arial",Font.BOLD,16));
        titleValueLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        idValueLabel = label(vacancy.getId());
        companyValueLabel = label(vacancy.getCompany());
        contactField = new CustomTextField(vacancy.getContact());
        specialtyValueLabel = label(vacancy.getSpecialty());
        experienceValueLabel = label(String.valueOf(vacancy.getMinExperience()));
        descriptionArea = new CustomTextArea(vacancy.getDescription(), true);
        statusComboBox = new EnumComboBox<>(VacancyStatus.class);
        statusComboBox.setSelectedItem(vacancy.getStatus());

        form.add(titleValueLabel);
        form.add(new Row(label("Id:"), idValueLabel));
        form.add(new Row(label("Company Name:"), companyValueLabel));
        form.add(new Row(label("Contact Number:"), contactField));
        form.add(new Row(label("Specialty:"), specialtyValueLabel));
        form.add(new Row(label("Experience Requirements:"), experienceValueLabel));
        form.add(new Row(label("Description:"), descriptionArea));
        form.add(new Row(label("Status:"), statusComboBox));

        revalidate(); repaint();
    }

    public int getStatus() {
        return statusComboBox.getSelectedIndex();
    }

    @Override
    public VacInput getInputData() {
        if (contactField.getText().isEmpty()
        || descriptionArea.getText().isEmpty())
            throw new IllegalArgumentException("Please fill all the fields!");
        try {
            return new VacInput(
                    titleValueLabel.getText(),
                    companyValueLabel.getText(),
                    contactField.getText(),
                    specialtyValueLabel.getText(),
                    Integer.parseInt(experienceValueLabel.getText()),
                    descriptionArea.getText()
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Experience must be an integer!");
        }
    }

    @Override
    public void setData(VacInput input) {}

    @Override
    public void clearForm() {}
}
