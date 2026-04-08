package gui.vacancy;

import gui.base.FormPanel;
import gui.components.CustomTextArea;
import gui.components.CustomTextField;
import gui.components.Row;
import vacancy.VacInput;

import javax.swing.*;

public class VacancyFormPanel extends FormPanel<VacInput> {
    private final CustomTextField titleField;
    private final CustomTextField companyField;
    private final CustomTextField contactField;
    private final CustomTextField specialtyField;
    private final CustomTextField experienceField;
    private final CustomTextArea descriptionField;

    public VacancyFormPanel() {
        titleField = new CustomTextField(2,1);
        companyField = new CustomTextField();
        contactField = new CustomTextField();
        specialtyField = new CustomTextField();
        experienceField = new CustomTextField();
        descriptionField = new CustomTextArea();

        form.add(new Row(new JLabel("Company Name:"), companyField));
        form.add(new Row(new JLabel("Contact Number:"), contactField));
        form.add(new Row(new JLabel("Specialty:"), specialtyField));
        form.add(new Row(new JLabel("Experience Requirements:"), experienceField));
        form.add(new Row(new JLabel("Description:"), descriptionField));

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
                || descriptionField.getText().isEmpty()) {
            throw new Exception("Please fill all the fields!");
        }
        try {
            //Return an input object
            return new VacInput(titleField.getText(),
                    companyField.getText(),
                    contactField.getText(), specialtyField.getText(),
                    Integer.parseInt(experienceField.getText()),
                    descriptionField.getText());
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
        descriptionField.setText(input.getDescription());
    }

    @Override
    public void clearForm() {
        companyField.setText("");
        contactField.setText("");
        specialtyField.setText("");
        experienceField.setText("");
        descriptionField.setText("");
    }
}
