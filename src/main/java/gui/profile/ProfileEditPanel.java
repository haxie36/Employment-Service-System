package gui.profile;

import common.Passport;
import gui.base.FormPanel;
import gui.components.CustomDateField;
import gui.components.CustomTextField;
import gui.components.FormRow;
import gui.components.TextRow;
import registration.Profile;
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
        JLabel idValueLabel = label(profile.getId());
        nameField = new CustomTextField(profile.getName());
        birthDateField = new CustomDateField(profile.getBirthDate());
        passportNumberField = new CustomTextField(profile.getPassportNumber());
        RNOKPPField = new CustomTextField(profile.getRNOKPP());
        specialtyField = new CustomTextField(profile.getSpecialty());
        experienceField = new CustomTextField(String.valueOf(profile.getExperience()));

        form.add(new TextRow(label("Profile Id:"), idValueLabel));
        form.add(new FormRow(label("Full Name:"), nameField));
        form.add(new FormRow(label("Birth Date:"), birthDateField));
        form.add(new FormRow(label("Passport Number:"), passportNumberField));
        form.add(new FormRow(label("RNOKPP:"), RNOKPPField));
        form.add(new FormRow(label("Specialty:"), specialtyField));
        form.add(new FormRow(label("Experience:"), experienceField));

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
        int experience;
        try {
            experience = parseInt(experienceField.getText());
        } catch (Exception e) {
            throw new Exception("Experience must be an integer!");
        }
        //Return an input object
        return new RegInput(
                "-", //Fictional
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
