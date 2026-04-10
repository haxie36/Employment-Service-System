package gui.profile;

import common.DateUtils;
import registration.Profile;
import gui.base.DetailsPanel;
import gui.components.Row;

import javax.swing.*;
import java.awt.*;

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

        details.add(new Row(label("Id:"), idValueLbl));
        details.add(new Row(label("Name:"), nameValueLbl));
        details.add(new Row(label("Birth Date:"), birthDateValueLbl));
        details.add(new Row(label("Passport Number:"), passportNumberValueLbl));
        details.add(new Row(label("RNOKPP:"), RNOKPPValueLbl));
        details.add(new Row(label("Specialty:"), specialtyValueLbl));
        details.add(new Row(label("Experience:"), experienceValueLbl));

        revalidate(); repaint();
    }

    public ProfileDetailsPanel(Profile profile) {
        this();
        update(profile);
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
