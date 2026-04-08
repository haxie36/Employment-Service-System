package gui.profile;

import common.Passport;
import registration.Profile;
import gui.components.CustomDateField;
import gui.components.CustomTextField;
import gui.base.FormPanel;
import gui.components.Row;
import registration.RegInput;

import javax.swing.*;

import static java.lang.Integer.parseInt;

public class ProfileEditPanel extends FormPanel<RegInput> {
    private final CustomTextField nameField;
    private final CustomDateField birthDateField;
    private final CustomTextField passportNumberField;
    private final CustomTextField RNOKPPField;
    private final CustomTextField specialtyField;
    private final CustomTextField experienceField;

    public ProfileEditPanel(Profile profile) {
        nameField = new CustomTextField(profile.getName());
        birthDateField = new CustomDateField(profile.getBirthDate());
        passportNumberField = new CustomTextField(profile.getPassportNumber());
        RNOKPPField = new CustomTextField(profile.getRNOKPP());
        specialtyField = new CustomTextField(profile.getSpecialty());
        experienceField = new CustomTextField(String.valueOf(profile.getExperience()));

        form.add(new Row(new JLabel("Id:"), new JLabel(profile.getId())));
        form.add(new Row(new JLabel("Full Name:"), nameField));
        form.add(new Row(new JLabel("Birth Date:"), birthDateField));
        form.add(new Row(new JLabel("Passport Number:"), passportNumberField));
        form.add(new Row(new JLabel("RNOKPP:"), RNOKPPField));
        form.add(new Row(new JLabel("Specialty:"), specialtyField));
        form.add(new Row(new JLabel("Experience:"), experienceField));

        revalidate(); repaint();
    }

    @Override
    public RegInput getInputData() throws Exception {
        //All the field have to be filled
        if (nameField.getText().isEmpty()
                || passportNumberField.getText().isEmpty()
                || RNOKPPField.getText().isEmpty()
                || specialtyField.getText().isEmpty()
                || experienceField.getText().isEmpty()) {
            throw new Exception("Please fill all the fields!");
        }
        try {
            //Return an input object
            return new RegInput(
                    "-",
                    new Passport(nameField.getText(),
                            birthDateField.getDate(),
                            passportNumberField.getText(),
                            RNOKPPField.getText()),
                    specialtyField.getText(),
                    parseInt(experienceField.getText())
            );
        } catch (Exception e) {
            throw new Exception("Experience must be an integer!");
        }
    }

    @Override
    public void setData(RegInput input) {
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
        nameField.setText("");
        birthDateField.setText("");
        passportNumberField.setText("");
        RNOKPPField.setText("");
        specialtyField.setText("");
        experienceField.setText("");
    }
}
