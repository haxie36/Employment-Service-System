package gui.vacancy;

import gui.base.EmptyPanel;
import gui.base.UIController;
import gui.main.ListPanel;
import gui.main.MainWindow;
import gui.main.RightPanel;
import logic.vacancy.VacInput;
import logic.vacancy.Vacancy;
import logic.vacancy.VacancyController;
import logic.vacancy.VacancyDAO;

import javax.swing.*;

public class VacancyUIController extends UIController<Vacancy, VacInput, VacancyDAO, VacancyController> {
    public VacancyUIController(MainWindow mainWindow,
                               VacancyController vacancyController) {
        super(mainWindow, vacancyController);
    }

    public void open(){
        super.open();
        //+New button setup
        setupNewButtonListener();
        //List setup
        setupListListener();
    }

    protected void setupNewButtonListener(){
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Vacancy> listPanel = mainWindow.getListPanel();
        //Listener
        rightPanel.getNewButton().addActionListener(e -> {
            listPanel.clearSelection();
            VacancyFormPanel vacForm = new VacancyFormPanel();
            //Save
            vacForm.setOnSave(() -> {
                try{
                    controller.create(vacForm.getInputData());
                    //FeedBack
                    JOptionPane.showMessageDialog(mainWindow, "Vacancy Creation Successful!");
                    updateList(); //Updating the list with a new vacancy
                } catch (Exception ex) {
                    vacForm.setStatusText(ex.getMessage()); //If something goes wrong, change the status
                }
            });
            //Cancel
            vacForm.setOnCancel(() -> {rightPanel.setContent(new EmptyPanel());});
            //Show the form and clear selection
            rightPanel.setContent(vacForm);
            listPanel.clearSelection();
        });
    }

    @Override
    protected void setupListListener() {
        super.setupListListener();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Vacancy> listPanel = mainWindow.getListPanel();
        //Listener
        listPanel.getList().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            //Get selected
            Vacancy selected = listPanel.getSelectedValue();
            //Null check
            if (selected == null) return;
            //Create details panel
            VacancyDetailsPanel vacancyDetailsPanel = new VacancyDetailsPanel(selected);
            //Edit
            vacancyDetailsPanel.setOnEdit(() -> {
                VacancyEditPanel vacancyEditPanel = new VacancyEditPanel(selected);
                //Apply changes (Save)
                vacancyEditPanel.setOnSave(() -> {
                    try {
                        //Edit and update the list
                        controller.editVacancy(selected,
                                vacancyEditPanel.getInputData(),
                                vacancyEditPanel.getStatus());
                        //FeedBack
                        JOptionPane.showMessageDialog(mainWindow, "Status Update Successful!");
                        //Update
                        updateList();
                        listPanel.setSelectedValue(selected, true);
                        //Update the details panel and set it
                        vacancyDetailsPanel.update(selected);
                        rightPanel.setContent(vacancyDetailsPanel);
                    } catch (Exception ex) {
                        vacancyEditPanel.setStatusText(ex.getMessage());
                    }
                });
                //CANCEL changes
                vacancyEditPanel.setOnCancel(() -> {
                    rightPanel.setContent(vacancyDetailsPanel);
                });
                //Set the edit panel
                rightPanel.setContent(vacancyEditPanel);
            });
            //DELETE selected
            vacancyDetailsPanel.setOnDelete(() -> {
                controller.delete(selected);
                //FeedBack
                JOptionPane.showMessageDialog(mainWindow, "Vacancy Deletion Successful!");
                updateList(); //And update the list
            });
            //Show the panel
            rightPanel.setContent(vacancyDetailsPanel);
        });
    }

    protected String getTitle() {return "Vacancy";}
}
