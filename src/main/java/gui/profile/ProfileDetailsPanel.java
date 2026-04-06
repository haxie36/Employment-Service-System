package gui.profile;

import common.DateUtils;
import registration.Profile;
import gui.base.DetailsPanel;
import gui.components.Row;

import javax.swing.*;

public class ProfileDetailsPanel extends DetailsPanel<Profile> {
    private final JLabel idValueLbl;
    private final JLabel nameValueLbl;
    private final JLabel birthDateValueLbl;
    private final JLabel passportNumberValueLbl;
    private final JLabel RNOKPPValueLbl;
    private final JLabel specialtyValueLbl;
    private final JLabel experienceValueLbl;

    public ProfileDetailsPanel(Profile profile) {
        idValueLbl = new JLabel(profile.getId());
        nameValueLbl = new JLabel(profile.getName());
        birthDateValueLbl = new JLabel(profile.getBirthDate().format(DateUtils.FORMATTER));
        passportNumberValueLbl = new JLabel(profile.getPassportNumber());
        RNOKPPValueLbl = new JLabel(profile.getRNOKPP());
        specialtyValueLbl = new JLabel(profile.getSpecialty());
        experienceValueLbl = new JLabel(String.valueOf(profile.getExperience()));

        details.add(new Row(new JLabel("Id:"), idValueLbl));
        details.add(new Row(new JLabel("Name:"), nameValueLbl));
        details.add(new Row(new JLabel("Birth Date:"), birthDateValueLbl));
        details.add(new Row(new JLabel("Passport Number:"), passportNumberValueLbl));
        details.add(new Row(new JLabel("RNOKPP:"), RNOKPPValueLbl));
        details.add(new Row(new JLabel("Specialty:"), specialtyValueLbl));
        details.add(new Row(new JLabel("Experience:"), experienceValueLbl));

        revalidate(); repaint();
    }

    public void update(Profile profile) {
        idValueLbl.setText(profile.getId());
        nameValueLbl.setText(profile.getName());
        birthDateValueLbl.setText((profile.getBirthDate().format(DateUtils.FORMATTER)));
        passportNumberValueLbl.setText((profile.getPassportNumber()));
        RNOKPPValueLbl.setText((profile.getRNOKPP()));
        specialtyValueLbl.setText((profile.getSpecialty()));
        experienceValueLbl.setText((String.valueOf(profile.getExperience())));
    }
}
