package gui.vacancy;

import gui.base.FormPanel;
import gui.components.*;
import logic.vacancy.VacInput;
import logic.vacancy.Vacancy;
import logic.vacancy.VacancyStatus;

import javax.swing.*;
import java.awt.*;

public class VacancyEditPanel extends FormPanel<VacInput> {
    private final JLabel titleValueLabel;
    private final JLabel companyValueLabel;
    private final CustomTextField contactField;
    private final JLabel specialtyValueLabel;
    private final JLabel experienceValueLabel;
    private final CustomTextArea descriptionArea;
    private final EnumComboBox<VacancyStatus> statusComboBox;

    public VacancyEditPanel(Vacancy vacancy) {
        titleValueLabel = label(vacancy.getTitle());
        titleValueLabel.setFont(new Font("Arial",Font.BOLD,16));
        titleValueLabel.setBorder(BorderFactory.createEmptyBorder(0,10,5,10));
        JLabel idValueLabel = label(vacancy.getId());
        companyValueLabel = label(vacancy.getCompany());
        contactField = new CustomTextField(vacancy.getContact());
        specialtyValueLabel = label(vacancy.getSpecialty());
        experienceValueLabel = label(String.valueOf(vacancy.getMinExperience()));
        descriptionArea = new CustomTextArea(vacancy.getDescription(), true);
        statusComboBox = new EnumComboBox<>(VacancyStatus.class);
        statusComboBox.setSelectedItem(vacancy.getStatus());

        //Template button
        CustomButton templateButton = new CustomButton("Template");
        templateButton.addActionListener(e -> applyTemplate());
        TextRow buttonRow = new TextRow(templateButton, null);
        buttonRow.setMaximumSize(new Dimension(300, 50));

        form.add(titleValueLabel);
        form.add(new TextRow(label("Vacancy Id:"), idValueLabel));
        form.add(new TextRow(label("Company Name:"), companyValueLabel));
        form.add(new FormRow(label("Contact Number:"), contactField));
        form.add(new TextRow(label("Specialty:"), specialtyValueLabel));
        form.add(new TextRow(label("Experience Requirements:"), experienceValueLabel));
        form.add(new TextAreaRow(label("Description:"), descriptionArea));
        form.add(buttonRow);
        form.add(new TextRow(label("Status:"), statusComboBox));

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

    private String getTemplate() {
        return """
            Salary:
            
            Skill requirements:
            
            Responsibilities:
            
            Conditions:
            
            """;
    }
    public void applyTemplate() {descriptionArea.setText(getTemplate());}
}
