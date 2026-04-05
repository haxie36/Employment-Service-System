package gui.profile;

import common.DateUtils;
import common.Profile;
import gui.components.DetailsPanel;
import gui.components.Row;

import javax.swing.*;

public class ProfileDetailsPanel extends DetailsPanel<Profile> {
    public ProfileDetailsPanel(Profile profile) {
        super(profile);
        //Info
        details.add(new Row(new JLabel("Id:"), new JLabel(profile.getId())));
        details.add(new Row(new JLabel("Passport Number:"), new JLabel(profile.getPassportNumber())));
        details.add(new Row(new JLabel("RNOKPP:"), new JLabel(profile.getRNOKPP())));
        details.add(new Row(new JLabel("Name:"), new JLabel(profile.getName())));
        details.add(new Row(new JLabel("Birth Date:"), new JLabel(profile.getBirthDate().format(DateUtils.FORMATTER))));
        details.add(new Row(new JLabel("Specialty:"), new JLabel(profile.getSpecialty())));
        details.add(new Row(new JLabel("Experience:"), new JLabel(String.valueOf(profile.getExperience()))));

        revalidate(); repaint();
    }
}
