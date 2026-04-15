package gui.vacancy;

import gui.base.FormPanel;
import gui.components.*;
import vacancy.VacInput;

import java.awt.*;

public class VacancyFormPanel extends FormPanel<VacInput> {
    private final CustomTextField titleField;
    private final CustomTextField companyField;
    private final CustomTextField contactField;
    private final CustomTextField specialtyField;
    private final CustomTextField experienceField;
    private final CustomTextArea descriptionArea;

    public VacancyFormPanel() {
        titleField = new CustomTextField();
        companyField = new CustomTextField();
        contactField = new CustomTextField();
        specialtyField = new CustomTextField();
        experienceField = new CustomTextField();
        descriptionArea = new CustomTextArea();

        form.add(new FormRow(label("Title:"), titleField));
        form.add(new FormRow(label("Company Name:"), companyField));
        form.add(new FormRow(label("Contact Number:"), contactField));
        form.add(new FormRow(label("Specialty:"), specialtyField));
        form.add(new FormRow(label("Experience Requirements:"), experienceField));
        form.add(new TextAreaRow(label("Description:"), descriptionArea));
        //Template button
        CustomButton templateButton = new CustomButton("Template");
        templateButton.addActionListener(e -> applyTemplate());
        TextRow buttonRow = new TextRow(templateButton, null);
        buttonRow.setMaximumSize(new Dimension(300, 50));
        form.add(buttonRow);

        revalidate(); repaint();
    }

    @Override
    public VacInput getInputData() throws Exception {
        //All the field have to be filled
        if (titleField.getText().isEmpty()
                || companyField.getText().isEmpty()
                || contactField.getText().isEmpty()
                || specialtyField.getText().isEmpty()
                || experienceField.getText().isEmpty()
                || descriptionArea.getText().isEmpty()) {
            throw new Exception("Please fill all the fields!");
        }
        try {
            //Return an input object
            return new VacInput(titleField.getText(),
                    companyField.getText(),
                    contactField.getText(), specialtyField.getText(),
                    Integer.parseInt(experienceField.getText()),
                    descriptionArea.getText());
        } catch (Exception e) {
            throw new Exception("Experience must be an integer!");
        }
    }

    @Override
    public void setData(VacInput input) {
        companyField.setText(input.getCompany());
        contactField.setText(input.getContact());
        specialtyField.setText(input.getSpecialty());
        experienceField.setText(String.valueOf(input.getMinExperience()));
        descriptionArea.setText(input.getDescription());
    }

    @Override
    public void clearForm() {
        companyField.setText("");
        contactField.setText("");
        specialtyField.setText("");
        experienceField.setText("");
        descriptionArea.setText("");
    }

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
