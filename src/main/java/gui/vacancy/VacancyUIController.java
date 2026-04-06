package gui.vacancy;

import gui.base.EmptyPanel;
import gui.base.ListPanel;
import vacancy.Vacancy;
import gui.base.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;
import vacancy.VacancyCollection;
import vacancy.VacancyController;

import javax.swing.*;

public class VacancyUIController extends UIController<Vacancy> {
    private VacancyController vacancyController;

    public VacancyUIController(MainWindow mainWindow,
                               VacancyController vacancyController, VacancyCollection vacancyCollection) {
        super(mainWindow, vacancyCollection);
        this.vacancyController = vacancyController;
    }

    public void open(){
        super.open();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Vacancy> listPanel = mainWindow.getListPanel();

        //+ New button shows a creation form
        rightPanel.getNewButton().addActionListener(e -> {
            listPanel.clearSelection();
            VacancyFormPanel vacForm= new VacancyFormPanel();
            //Press "Save" to save...
            vacForm.setOnSave(() -> {
                try{
                    vacancyController.create(vacForm.getInputData());
                    //FeedBack
                    JOptionPane.showMessageDialog(mainWindow, "Vacancy Created Successfully!");
                    rightPanel.setContent(new EmptyPanel()); //Return to the default state
                    updateList(); //Updating the list with a new vacancy
                } catch (IllegalArgumentException ie) {
                    vacForm.setStatusText(ie.getMessage()); //If something goes wrong, change the status
                }
            });
            //Press "Cancel" to cancel...
            vacForm.setOnCancel(() -> {rightPanel.setContent(new EmptyPanel());});

            //Show the form and clear selection
            rightPanel.setContent(vacForm);
            listPanel.clearSelection();
        });

        //Details and editing
        listPanel.getList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                //Get selected
                Vacancy selectedVacancy = listPanel.getList().getSelectedValue();
                //Create details panel
                VacancyDetailsPanel vacancyDetailsPanel = new VacancyDetailsPanel(selectedVacancy);
                //Press "Edit" to edit...
                vacancyDetailsPanel.setOnEdit(() -> {
                    VacancyEditPanel vacancyEditPanel = new VacancyEditPanel(selectedVacancy);
                    //Apply changes
                    vacancyEditPanel.setOnSave(() -> {
                        try {
                            //Edit and update the list
                            vacancyController.changeVacancyStatus(selectedVacancy,
                                    vacancyEditPanel.getStatus());
                            //FeedBack
                            JOptionPane.showMessageDialog(mainWindow, "Status Update Successful!");
                            //Update
                            updateList();
                            listPanel.getList().setSelectedValue(selectedVacancy, true);
                            //Update details panel, set it
                            vacancyDetailsPanel.update(selectedVacancy);
                            rightPanel.setContent(vacancyDetailsPanel);
                        } catch (IllegalArgumentException ie) {
                            vacancyEditPanel.setStatusText(ie.getMessage());
                        }
                    });
                    //Cancel changes
                    vacancyEditPanel.setOnCancel(() -> {
                        rightPanel.setContent(vacancyDetailsPanel);
                    });
                    //Set the edit panel in place
                    rightPanel.setContent(vacancyEditPanel);
                });
                //Delete selected
                vacancyDetailsPanel.setOnDelete(() -> {
                    vacancyController.delete(selectedVacancy);
                    //FeedBack
                    JOptionPane.showMessageDialog(mainWindow, "Vacancy Delete Successful!");
                    updateList(); //And update the list
                });
                //Show the panel
                rightPanel.setContent(vacancyDetailsPanel);
            }
        });

    }
}
