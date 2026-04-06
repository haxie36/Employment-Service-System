package gui.profile;

import registration.Profile;
import gui.base.EmptyPanel;
import gui.base.ListPanel;
import gui.base.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;
import registration.ProfileCollection;
import registration.RegistrationController;

import javax.swing.*;

public class ProfileUIController extends UIController<Profile> {
    private final RegistrationController registrationController;

    public ProfileUIController(MainWindow mainWindow, RegistrationController registrationController, ProfileCollection profileCollection) {
        super(mainWindow, profileCollection);
        this.registrationController = registrationController;
    }

    public void open(){
        super.open();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Profile> listPanel = mainWindow.getListPanel();

        //+ New button shows a registration form
        rightPanel.getNewButton().addActionListener(a -> {
            listPanel.clearSelection();
            RegistrationFormPanel regForm = new RegistrationFormPanel();
            //Upon pressing "Save" try to register a profile with the inputs
            regForm.setOnSave(() -> {
                try{
                    registrationController.create(regForm.getInputData());
                    //FeedBack message
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
            listPanel.clearSelection();
        });

        //Profile details and editing
        listPanel.getList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                //Get selected profile
                Profile selectedProfile = listPanel.getSelectedItem();
                //Create details panel
                ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel(selectedProfile);
                //Upon pressing "Edit" open an edit form
                profileDetailsPanel.setOnEdit(() -> {
                    ProfileEditPanel profileEditPanel = new ProfileEditPanel(selectedProfile);
                    //Apply changes
                    profileEditPanel.setOnSave(() -> {
                        try {
                            //Edit and update the list
                            registrationController.editProfile(selectedProfile,
                                    profileEditPanel.getInputData());
                            //FeedBack message
                            JOptionPane.showMessageDialog(profileEditPanel, "Profile Edit Successful");
                            //Update
                            updateList();
                            listPanel.getList().setSelectedValue(selectedProfile, true);
                            //Update details panel, set it
                            profileDetailsPanel.update(selectedProfile);
                            rightPanel.setContent(profileDetailsPanel);
                        } catch (IllegalArgumentException ie) {
                            profileEditPanel.setStatusText(ie.getMessage());
                        }
                    });
                    //Cancel changes
                    profileEditPanel.setOnCancel(() -> {
                        rightPanel.setContent(profileDetailsPanel);
                    });
                    //Set the edit panel in place
                    rightPanel.setContent(profileEditPanel);
                });
                //Upon pressing "Delete"... Delete the Object...
                profileDetailsPanel.setOnDelete(() -> {
                    registrationController.delete(selectedProfile);
                    //FeedBack message
                    JOptionPane.showMessageDialog(profileDetailsPanel, "Profile Delete Successful");
                    updateList(); //And update the list
                });
                //Set the details panel in place
                rightPanel.setContent(profileDetailsPanel);
            }
        });
    }
}
