package gui.profile;

import common.Passport;
import gui.components.CustomDateField;
import gui.components.CustomTextField;
import gui.base.FormPanel;
import gui.components.Row;
import registration.RegInput;

import javax.swing.*;

import static java.lang.Integer.parseInt;

public class RegistrationFormPanel extends FormPanel<RegInput> {
    private final CustomTextField addressField;
    private final CustomTextField nameField;
    private final CustomDateField birthDateField;
    private final CustomTextField passportNumberField;
    private final CustomTextField RNOKPPField;
    private final CustomTextField specialtyField;
    private final CustomTextField experienceField;

    public RegistrationFormPanel() {
        addressField = new CustomTextField();
        nameField = new CustomTextField();
        birthDateField = new CustomDateField();
        passportNumberField = new CustomTextField();
        RNOKPPField = new CustomTextField();
        specialtyField = new CustomTextField();
        experienceField = new CustomTextField();

        form.add(new Row(label("Address:"), addressField));
        form.add(new Row(label("Full Name:"), nameField));
        form.add(new Row(label("Birth Date:"), birthDateField));
        form.add(new Row(label("Passport Number:"), passportNumberField));
        form.add(new Row(label("RNOKPP:"), RNOKPPField));
        form.add(new Row(label("Specialty:"), specialtyField));
        form.add(new Row(label("Experience:"), experienceField));

        revalidate(); repaint();
    }

    @Override
    public RegInput getInputData() throws Exception {
        //All the field have to be filled
        if (addressField.getText().isEmpty()
                || nameField.getText().isEmpty()
                || passportNumberField.getText().isEmpty()
                || RNOKPPField.getText().isEmpty()
                || specialtyField.getText().isEmpty()
                || experienceField.getText().isEmpty()) {
            throw new Exception("Please fill all the fields!");
        }

        int experience;
        try {
            experience = parseInt(experienceField.getText());
        } catch (Exception e) {
            throw new Exception("Experience must be an integer!");
        }
        //Return an input object
        return new RegInput(
                addressField.getText(),
                new Passport(nameField.getText(),
                        birthDateField.getDate(),
                        passportNumberField.getText(),
                        RNOKPPField.getText()),
                specialtyField.getText(),
                experience
        );
    }

    @Override
    public void setData(RegInput input) {
        addressField.setText(input.getAddress());
        //Passport
        Passport passport = input.getPassport();
        nameField.setText(passport.getName());
        birthDateField.setText(passport.getBirthday().toString());
        passportNumberField.setText(passport.getPassportNumber());
        RNOKPPField.setText(passport.getRNOKPP());

        specialtyField.setText(input.getSpecialty());
        experienceField.setText(Integer.toString(input.getExperience()));
    }

    @Override
    public void clearForm() {
        addressField.setText("");
        nameField.setText("");
        birthDateField.setText("");
        passportNumberField.setText("");
        RNOKPPField.setText("");
        specialtyField.setText("");
        experienceField.setText("");
    }
}
