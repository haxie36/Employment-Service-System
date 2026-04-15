package gui.profile;

import common.DateUtils;
import gui.base.DetailsPanel;
import gui.components.TextRow;
import registration.Profile;

import javax.swing.*;

public class ProfileDetailsPanel extends DetailsPanel<Profile> {
    private final JLabel idValueLbl;
    private final JLabel nameValueLbl;
    private final JLabel birthDateValueLbl;
    private final JLabel passportNumberValueLbl;
    private final JLabel RNOKPPValueLbl;
    private final JLabel specialtyValueLbl;
    private final JLabel experienceValueLbl;

    public ProfileDetailsPanel() {
        idValueLbl = label("");
        nameValueLbl = label("");
        birthDateValueLbl = label("");
        passportNumberValueLbl = label("");
        RNOKPPValueLbl = label("");
        specialtyValueLbl = label("");
        experienceValueLbl = label("");

        details.add(new TextRow(label("Profile Id:"), idValueLbl));
        details.add(new TextRow(label("Name:"), nameValueLbl));
        details.add(new TextRow(label("Birth Date:"), birthDateValueLbl));
        details.add(new TextRow(label("Passport Number:"), passportNumberValueLbl));
        details.add(new TextRow(label("RNOKPP:"), RNOKPPValueLbl));
        details.add(new TextRow(label("Specialty:"), specialtyValueLbl));
        details.add(new TextRow(label("Experience:"), experienceValueLbl));

        revalidate(); repaint();
    }

    public ProfileDetailsPanel(Profile profile) {
        this();
        update(profile);
    }

    public void update(Profile profile) {
        if (profile == null) {
            idValueLbl.setText("");
            nameValueLbl.setText("");
            birthDateValueLbl.setText("");
            passportNumberValueLbl.setText("");
            RNOKPPValueLbl.setText("");
            specialtyValueLbl.setText("");
            experienceValueLbl.setText("");
        } else {
            idValueLbl.setText(String.valueOf(profile.getId()));
            nameValueLbl.setText(profile.getName());
            birthDateValueLbl.setText((profile.getBirthDate().format(DateUtils.FORMATTER)));
            passportNumberValueLbl.setText((profile.getPassportNumber()));
            RNOKPPValueLbl.setText((profile.getRNOKPP()));
            specialtyValueLbl.setText((profile.getSpecialty()));
            experienceValueLbl.setText((String.valueOf(profile.getExperience())));
        }
    }
}
