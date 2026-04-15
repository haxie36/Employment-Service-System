package gui.vacancy;

import gui.base.DetailsPanel;
import gui.components.CustomTextArea;
import gui.components.TextAreaRow;
import gui.components.TextRow;
import logic.vacancy.Vacancy;

import javax.swing.*;
import java.awt.*;

public class VacancyDetailsPanel extends DetailsPanel<Vacancy> {
    private final JLabel titleValueLabel;
    private final JLabel idValueLabel;
    private final JLabel companyValueLabel;
    private final JLabel contactValueLabel;
    private final JLabel specialtyValueLabel;
    private final JLabel experienceValueLabel;
    private final CustomTextArea descriptionArea;
    private final JLabel statusValueLabel;
    
    public VacancyDetailsPanel(Vacancy vacancy) {
        titleValueLabel = label(vacancy.getTitle());
        titleValueLabel.setFont(new Font("Arial",Font.BOLD,16));
        titleValueLabel.setBorder(BorderFactory.createEmptyBorder(3,10,5,10));
        idValueLabel = label(vacancy.getId());
        companyValueLabel = label(vacancy.getCompany());
        contactValueLabel = label(vacancy.getContact());
        specialtyValueLabel = label(vacancy.getSpecialty());
        experienceValueLabel = label(String.valueOf(vacancy.getMinExperience()));
        descriptionArea = new CustomTextArea(vacancy.getDescription(), false);
        statusValueLabel = label(vacancy.getStatus().toString());

        details.add(titleValueLabel);
        details.add(new TextRow(label("Vacancy Id:"), idValueLabel));
        details.add(new TextRow(label("Company Name:"), companyValueLabel));
        details.add(new TextRow(label("Contact Number:"), contactValueLabel));
        details.add(new TextRow(label("Specialty:"), specialtyValueLabel));
        details.add(new TextRow(label("Experience Requirements:"), experienceValueLabel));
        details.add(new TextAreaRow(label("Description:"), descriptionArea));
        details.add(new TextRow(label("Status:"), statusValueLabel));

        revalidate(); repaint();
    }
    
    @Override
    public void update(Vacancy vacancy) {
        titleValueLabel.setText(vacancy.getTitle());
        idValueLabel.setText(String.valueOf(vacancy.getId()));
        companyValueLabel.setText(String.valueOf(vacancy.getCompany()));
        contactValueLabel.setText(String.valueOf(vacancy.getContact()));
        specialtyValueLabel.setText(String.valueOf(vacancy.getSpecialty()));
        experienceValueLabel.setText(String.valueOf(vacancy.getMinExperience()));
        descriptionArea.setText(String.valueOf(vacancy.getDescription()));
        statusValueLabel.setText(String.valueOf(vacancy.getStatus()));
    }
}
