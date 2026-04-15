package gui.main;

import gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class LeftMenu extends JPanel {
    private final CustomButton profilesBtn;
    private final CustomButton vacanciesBtn;
    private final CustomButton applicationsBtn;
    private final CustomButton retrainingsBtn;

    public LeftMenu(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(240, 0));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        //temp
        //setBackground(Color.GREEN);

        profilesBtn = createButton("Profiles");
        vacanciesBtn = createButton("Vacancies");
        applicationsBtn = createButton("Applications");
        retrainingsBtn = createButton("Retrainings");

        add(Box.createVerticalGlue());
        add(profilesBtn);
        add(Box.createVerticalStrut(20));
        add(vacanciesBtn);
        add(Box.createVerticalStrut(20));
        add(applicationsBtn);
        add(Box.createVerticalStrut(20));
        add(retrainingsBtn);
        add(Box.createVerticalGlue());
    }

    public JButton getProfilesBtn() {return profilesBtn;}
    public JButton getVacanciesBtn() {return vacanciesBtn;}
    public JButton getApplicationsBtn() {return applicationsBtn;}
    public JButton getRetrainingsBtn() {return retrainingsBtn;}

    private CustomButton createButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(180, 60));
        button.setMaximumSize(new Dimension(180, 60));
        return button;
    }
}
