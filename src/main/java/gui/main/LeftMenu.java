package gui.main;

import gui.components.CustomButton;

import javax.swing.*;

public class LeftMenu extends JPanel {
    private final CustomButton profilesBtn;
    private final CustomButton vacanciesBtn;
    private final CustomButton applicationsBtn;
    private final CustomButton retrainingsBtn;

    public LeftMenu(){
        profilesBtn = new CustomButton("Profiles");
        vacanciesBtn = new CustomButton("Vacancies");
        applicationsBtn = new CustomButton("Applications");
        retrainingsBtn = new CustomButton("Retrainings");

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        add(profilesBtn);
        add(vacanciesBtn);
        add(applicationsBtn);
        add(retrainingsBtn);
    }

    public JButton getProfilesBtn() {return profilesBtn;}
    public JButton getVacanciesBtn() {return vacanciesBtn;}
    public JButton getApplicationsBtn() {return applicationsBtn;}
    public JButton getRetrainingsBtn() {return retrainingsBtn;}
}
