package gui.vacancy;

import gui.base.FormPanel;
import gui.components.CustomTextArea;
import gui.components.EnumComboBox;
import gui.components.Row;
import vacancy.VacInput;
import vacancy.Vacancy;
import vacancy.VacancyStatus;

import javax.swing.*;
import java.awt.*;

public class VacancyEditPanel extends FormPanel<VacInput> {
    private final JLabel titleLabel;
    private final JLabel idLbl;
    private final JLabel companyLbl;
    private final JLabel contactLbl;
    private final JLabel specialtyLbl;
    private final JLabel experienceLbl;
    private final CustomTextArea descriptionArea;
    private final EnumComboBox<VacancyStatus> statusComboBox;

    public VacancyEditPanel(Vacancy vacancy) {
        titleLabel = new JLabel(vacancy.getTitle());
        titleLabel.setFont(new Font("Arial",Font.BOLD,16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        idLbl = new JLabel(vacancy.getId());
        companyLbl = new JLabel(vacancy.getCompany());
        contactLbl = new JLabel(vacancy.getContact());
        specialtyLbl = new JLabel(vacancy.getSpecialty());
        experienceLbl = new JLabel(String.valueOf(vacancy.getMinExperience()));
        descriptionArea = new CustomTextArea(vacancy.getDescription(), false);
        statusComboBox = new EnumComboBox<>(VacancyStatus.class);
        statusComboBox.setSelectedItem(vacancy.getStatus());

        form.add(titleLabel);
        form.add(new Row(new JLabel("Id:"), idLbl));
        form.add(new Row(new JLabel("Company Name:"), companyLbl));
        form.add(new Row(new JLabel("Contact Number:"), contactLbl));
        form.add(new Row(new JLabel("Specialty:"), specialtyLbl));
        form.add(new Row(new JLabel("Experience Requirements:"), experienceLbl));
        form.add(new Row(new JLabel("Description:"), descriptionArea));
        form.add(new Row(new JLabel("Status:"), statusComboBox));

        revalidate(); repaint();
    }

    public int getStatus() {
        return statusComboBox.getSelectedIndex();
    }

    @Override
    public VacInput getInputData() {return null;}

    @Override
    public void setData(VacInput input) {}

    @Override
    public void clearForm() {}
}
