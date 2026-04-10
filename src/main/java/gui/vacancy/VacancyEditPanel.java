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
        titleLabel = label(vacancy.getTitle());
        titleLabel.setFont(new Font("Arial",Font.BOLD,16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        idLbl = label(vacancy.getId());
        companyLbl = label(vacancy.getCompany());
        contactLbl = label(vacancy.getContact());
        specialtyLbl = label(vacancy.getSpecialty());
        experienceLbl = label(String.valueOf(vacancy.getMinExperience()));
        descriptionArea = new CustomTextArea(vacancy.getDescription(), false);
        statusComboBox = new EnumComboBox<>(VacancyStatus.class);
        statusComboBox.setSelectedItem(vacancy.getStatus());

        form.add(titleLabel);
        form.add(new Row(label("Id:"), idLbl));
        form.add(new Row(label("Company Name:"), companyLbl));
        form.add(new Row(label("Contact Number:"), contactLbl));
        form.add(new Row(label("Specialty:"), specialtyLbl));
        form.add(new Row(label("Experience Requirements:"), experienceLbl));
        form.add(new Row(label("Description:"), descriptionArea));
        form.add(new Row(label("Status:"), statusComboBox));

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
