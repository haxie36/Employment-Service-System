package gui.vacancy;

import gui.base.EmptyPanel;
import gui.main.ListPanel;
import vacancy.Vacancy;
import gui.base.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;
import vacancy.VacancyCollection;
import vacancy.VacancyController;

import javax.swing.*;

public class VacancyUIController extends UIController<Vacancy> {
    private final VacancyController vacancyController;

    public VacancyUIController(MainWindow mainWindow,
                               VacancyController vacancyController, VacancyCollection vacancyCollection) {
        super(mainWindow, vacancyCollection);
        this.vacancyController = vacancyController;
    }

    public void open(){
        super.open();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Vacancy> listPanel = mainWindow.getListPanel();

        //+ NEW BUTTON shows a creation form
        rightPanel.getNewButton().addActionListener(e -> {
            listPanel.clearSelection();
            VacancyFormPanel vacForm= new VacancyFormPanel();
            //Press "Save" to save...
            vacForm.setOnSave(() -> {
                try{
                    vacancyController.create(vacForm.getInputData());
                    //FeedBack
                    JOptionPane.showMessageDialog(mainWindow, "Vacancy Created Successfully!");
                    updateList(); //Updating the list with a new vacancy
                } catch (Exception ie) {
                    vacForm.setStatusText(ie.getMessage()); //If something goes wrong, change the status
                }
            });
            //Press "CANCEL" to cancel...
            vacForm.setOnCancel(() -> {rightPanel.setContent(new EmptyPanel());});

            //Show the form and clear selection
            rightPanel.setContent(vacForm);
            listPanel.clearSelection();
        });

        //DETAILS and EDITing (SELECTION)
        listPanel.getList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                //Get selected
                Vacancy selected = listPanel.getSelectedValue();
                //Null check
                if (selected == null) {return;}
                //Create DETAILS panel
                VacancyDetailsPanel vacancyDetailsPanel = new VacancyDetailsPanel(selected);
                //Press "EDIT" to edit...
                vacancyDetailsPanel.setOnEdit(() -> {
                    VacancyEditPanel vacancyEditPanel = new VacancyEditPanel(selected);
                    //Apply changes (SAVE)
                    vacancyEditPanel.setOnSave(() -> {
                        try {
                            //Edit and update the list
                            vacancyController.changeVacancyStatus(selected,
                                    vacancyEditPanel.getStatus());
                            //FeedBack
                            JOptionPane.showMessageDialog(mainWindow, "Status Update Successful!");
                            //Update
                            updateList();
                            listPanel.setSelectedValue(selected, true);
                            //Update details panel, set it
                            vacancyDetailsPanel.update(selected);
                            rightPanel.setContent(vacancyDetailsPanel);
                        } catch (Exception ie) {
                            vacancyEditPanel.setStatusText(ie.getMessage());
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
                    vacancyController.delete(selected);
                    //FeedBack
                    JOptionPane.showMessageDialog(mainWindow, "Vacancy Delete Successful!");
                    updateList(); //And update the list
                });
                //Show the panel
                rightPanel.setContent(vacancyDetailsPanel);
            }
        });
    }

    protected String getTitle() {return "Vacancy";}
}
