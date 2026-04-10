package gui.vacancy;

import gui.base.DetailsPanel;
import gui.components.CustomTextArea;
import gui.components.Row;
import vacancy.Vacancy;

import javax.swing.*;
import java.awt.*;

public class VacancyDetailsPanel extends DetailsPanel<Vacancy> {
    private final JLabel titleLabel;
    private final JLabel idLbl;
    private final JLabel companyLbl;
    private final JLabel contactLbl;
    private final JLabel specialtyLbl;
    private final JLabel experienceLbl;
    private final CustomTextArea descriptionArea;
    private final JLabel statusLbl;
    
    public VacancyDetailsPanel(Vacancy vacancy) {
        titleLabel = label(vacancy.getTitle());
        titleLabel.setFont(new Font("Arial",Font.BOLD,16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        idLbl = label(vacancy.getId());
        companyLbl = label(vacancy.getCompany());
        contactLbl = label(vacancy.getContact());
        specialtyLbl = label(vacancy.getSpecialty());
        experienceLbl = label(String.valueOf(vacancy.getMinExperience()));
        descriptionArea = new CustomTextArea(vacancy.getDescription(), false);
        statusLbl = label(vacancy.getStatus().toString());

        details.add(titleLabel);
        details.add(new Row(label("Id:"), idLbl));
        details.add(new Row(label("Company Name:"), companyLbl));
        details.add(new Row(label("Contact Number:"), contactLbl));
        details.add(new Row(label("Specialty:"), specialtyLbl));
        details.add(new Row(label("Experience Requirements:"), experienceLbl));
        details.add(new Row(label("Description:"), descriptionArea));
        details.add(new Row(label("Status:"), statusLbl));

        revalidate(); repaint();
    }
    
    @Override
    public void update(Vacancy vacancy) {
        titleLabel.setText(vacancy.getTitle());
        idLbl.setText(String.valueOf(vacancy.getId()));
        companyLbl.setText(String.valueOf(vacancy.getCompany()));
        contactLbl.setText(String.valueOf(vacancy.getContact()));
        specialtyLbl.setText(String.valueOf(vacancy.getSpecialty()));
        experienceLbl.setText(String.valueOf(vacancy.getMinExperience()));
        descriptionArea.setText(String.valueOf(vacancy.getDescription()));
        statusLbl.setText(String.valueOf(vacancy.getStatus()));
    }
}
