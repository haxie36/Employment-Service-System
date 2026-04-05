package gui.profile;

import common.Profile;
import gui.components.EmptyPanel;
import gui.components.ListPanel;
import gui.components.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;
import registration.Profiles;
import registration.RegistrationController;

import javax.swing.*;

public class ProfileUIController extends UIController<Profile> {
    private final RegistrationController registrationController;

    public ProfileUIController(MainWindow mainWindow, RegistrationController registrationController,Profiles profiles) {
        super(mainWindow, profiles);
        this.registrationController = registrationController;
    }

    public void open(){
        super.open();
        RightPanel rightPanel = mainWindow.getRightPanel();

        //+ New button shows a registration form
        rightPanel.getNewButton().addActionListener(a -> {
            RegistrationFormPanel regForm = new RegistrationFormPanel();
            //Upon pressing "Save" try to register a profile with the inputs
            regForm.setOnSave(() -> {
                try{
                    registrationController.create(regForm.getInputData());
                    JOptionPane.showMessageDialog(regForm, "Registration Successful");
                    rightPanel.setContent(new EmptyPanel()); //Return to the default state
                    updateList(); //Updates the list with a new profile
                } catch (IllegalArgumentException ie) {
                    regForm.setStatusText(ie.getMessage()); //If it fails, change the status of a form
                }                                           // to the exception message
            });
            //Upon pressing "Cancel" return to the default state
            regForm.setOnCancel(() -> {rightPanel.setContent(new EmptyPanel());});

            //Show the form and clear list selection
            rightPanel.setContent(regForm);
            mainWindow.getListPanel().clearSelection();
        });

        //Profile details
        ListPanel<Profile> listPanel = mainWindow.getListPanel();
        listPanel.getList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                //Get selected profile
                Profile selectedProfile = listPanel.getSelectedItem();
                //Create details panel
                ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel(selectedProfile);
                //Upon pressing "Edit" open an edit form
                profileDetailsPanel.setOnEdit(() -> {
                    ProfileEditPanel profileEditPanel = new ProfileEditPanel(selectedProfile);
                    //Apply the changes
                    profileEditPanel.setOnSave(() -> {
                        try {
                            registrationController.editProfile(selectedProfile, profileEditPanel.getInputData());
                        } catch (IllegalArgumentException ie) {
                            profileEditPanel.setStatusText(ie.getMessage());
                        }
                    });
                    //Cancel the changes
                    profileEditPanel.setOnCancel(() -> {
                        rightPanel.setContent(profileDetailsPanel);
                    });
                    //Set the edit panel in place
                    rightPanel.setContent(profileEditPanel);
                });
                //Upon pressing "Delete"... Delete the Object...
                profileDetailsPanel.setOnDelete(() -> {
                    registrationController.delete(selectedProfile);
                    updateList(); //And update the list
                });
                //Set the details panel in place
                rightPanel.setContent(profileDetailsPanel);
            }
        });
    }
}
