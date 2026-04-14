package gui.retraining;

import gui.base.EmptyPanel;
import gui.main.ListPanel;
import registration.Profile;
import registration.RegistrationController;
import retraining.*;
import gui.base.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;

import javax.swing.*;

public class RetrainingUIController extends UIController<Retraining, RetrInput, RetrainingDAO, RetrainingController> {
    private final RegistrationController registrationController;

    public RetrainingUIController(MainWindow mainWindow,
                                  RetrainingController retrainingController,
                                  RegistrationController registrationController) {
        super(mainWindow, retrainingController);
        this.registrationController = registrationController;
    }

    public void open(){
        super.open();
        //+New button setup
        setupNewButtonListener();
        //List setup
        setupListListener();
    }

    @Override
    protected void setupNewButtonListener() {
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Retraining> listPanel = mainWindow.getListPanel();
        //Listener
        rightPanel.getNewButton().addActionListener(e -> {
            RetrainingFormPanel retrForm = new RetrainingFormPanel();
            //Find button
            retrForm.setOnFind(() -> {
                try {
                    Profile foundProfile = controller.getByPassport(retrForm.getPassportNumber());
                    if (foundProfile == null) {
                        throw new IllegalArgumentException("Profile not found!");
                    }
                    retrForm.updateProfileDetails(foundProfile);
                } catch (Exception ex) {
                    retrForm.setStatusText(ex.getMessage());
                }
            });
            //Save
            retrForm.setOnSave(() -> {
                try {
                    controller.create(retrForm.getInputData());
                    //FeedBack
                    JOptionPane.showMessageDialog(mainWindow, "Retraining Creation Successful");
                    updateList();
                } catch (Exception ex) {
                    retrForm.setStatusText(ex.getMessage());
                }
            });
            //Cancel
            retrForm.setOnCancel(() -> {rightPanel.setContent(new EmptyPanel());});
            //Set the form and clear selection
            rightPanel.setContent(retrForm);
            listPanel.clearSelection();
        });
    }

    @Override
    protected void setupListListener() {
        super.setupListListener();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Retraining> listPanel = mainWindow.getListPanel();
        //Listener
        listPanel.getList().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            //Get selected
            Retraining selected = listPanel.getSelectedValue();
            //Null check
            if (selected == null) return;
            //Create details panel
            Profile profileOfSelected = registrationController.getById(selected.getProfileId());
            RetrainingDetailsPanel retrainingDetailsPanel = new RetrainingDetailsPanel(selected, profileOfSelected);
            //Plan
            retrainingDetailsPanel.setOnAdditional(() -> {
                RetrainingPlanningPanel retrainingPlanningPanel = new RetrainingPlanningPanel(selected, profileOfSelected);
                //Save
                retrainingPlanningPanel.setOnSave(() -> {
                    try {
                        PlanningInput planningInput = retrainingPlanningPanel.getInputData();
                        controller.planRetraining(selected,
                                planningInput.getStartDate(),
                                planningInput.getEndDate());
                        //FeedBack
                        JOptionPane.showMessageDialog(mainWindow, "Retraining Planning Successful");
                        updateList(); //Required, as planning a retraining changes its status
                    } catch (Exception ex) {
                        retrainingPlanningPanel.setStatusText(ex.getMessage());
                    }
                });
                //Cancel
                retrainingPlanningPanel.setOnCancel(() -> {
                    rightPanel.setContent(retrainingDetailsPanel);
                });
                //Set the panel
                rightPanel.setContent(retrainingDetailsPanel);
            });
            //Edit
            retrainingDetailsPanel.setOnEdit(() -> {
                RetrainingEditPanel retrainingEditPanel = new RetrainingEditPanel(selected, profileOfSelected);
                //Save
                retrainingEditPanel.setOnSave(() -> {
                    try {
                        //Edit and update the list
                        PlanningInput editInput = retrainingEditPanel.getInputData();
                        controller.edit(selected, editInput);
                        //FeedBack
                        JOptionPane.showMessageDialog(mainWindow, "Retraining Edit Successful");
                        //Update
                        updateList();
                        listPanel.setSelectedValue(selected, true);
                    } catch (Exception ex) {
                        retrainingEditPanel.setStatusText(ex.getMessage());
                    }
                });
                //Cancel
                retrainingEditPanel.setOnCancel(() -> {
                    rightPanel.setContent(retrainingDetailsPanel);
                });
            });
            //Delete
            retrainingDetailsPanel.setOnDelete(() -> {
                controller.delete(selected);
                //FeedBack
                JOptionPane.showMessageDialog(mainWindow, "Retraining Deletion Successful");
                updateList();
            });
            //Set the panel
            rightPanel.setContent(retrainingDetailsPanel);
        });
    }

    protected String getTitle() {return "Retraining";}
}
