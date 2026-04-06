package gui.vacancy;

import gui.base.DetailsPanel;
import gui.components.CustomTextArea;
import gui.components.Row;
import vacancy.Vacancy;

import javax.swing.*;

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
        titleLabel = new JLabel(vacancy.getTitle());
        idLbl = new JLabel(vacancy.getId());
        companyLbl = new JLabel(vacancy.getCompany());
        contactLbl = new JLabel(vacancy.getContact());
        specialtyLbl = new JLabel(vacancy.getSpecialty());
        experienceLbl = new JLabel(String.valueOf(vacancy.getMinExperience()));
        descriptionArea = new CustomTextArea(vacancy.getDescription(), false);
        statusLbl = new JLabel(vacancy.getStatus().toString());

        details.add(titleLabel);
        details.add(new Row(new JLabel("Id:"), idLbl));
        details.add(new Row(new JLabel("Company Name:"), companyLbl));
        details.add(new Row(new JLabel("Contact Number:"), contactLbl));
        details.add(new Row(new JLabel("Specialty:"), specialtyLbl));
        details.add(new Row(new JLabel("Experience Requirements:"), experienceLbl));
        details.add(new Row(new JLabel("Description:"), descriptionArea));
        details.add(new Row(new JLabel("Status:"), statusLbl));

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
