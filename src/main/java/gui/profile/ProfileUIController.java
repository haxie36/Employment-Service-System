package gui.profile;

import registration.Profile;
import gui.base.EmptyPanel;
import gui.main.ListPanel;
import gui.base.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;
import registration.ProfileDAO;
import registration.RegInput;
import registration.RegistrationController;

import javax.swing.*;

public class ProfileUIController extends UIController<Profile, RegInput, ProfileDAO, RegistrationController> {

    public ProfileUIController(MainWindow mainWindow, RegistrationController registrationController) {
        super(mainWindow, registrationController);
    }

    public void open(){
        super.open();
        //+New button setup
        setupNewButtonListener();
        //List setup
        setupListListener();
    }

    protected void setupNewButtonListener() {
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Profile> listPanel = mainWindow.getListPanel();
        //Listener
        rightPanel.getNewButton().addActionListener(a -> {
            listPanel.clearSelection();
            RegistrationFormPanel regForm = new RegistrationFormPanel();
            //Upon pressing "Save" try to register a profile with the inputs
            regForm.setOnSave(() -> {
                try{
                    controller.create(regForm.getInputData());
                    //FeedBack message
                    JOptionPane.showMessageDialog(mainWindow, "Registration Successful");
                    updateList(); //Updates the list with a new profile
                } catch (Exception ex) {
                    regForm.setStatusText(ex.getMessage()); //If it fails, change the status of a form
                }                                           // to the exception message
            });
            //Cancel
            regForm.setOnCancel(() -> {rightPanel.setContent(new EmptyPanel());});
            //Show the form and clear list selection
            rightPanel.setContent(regForm);
            listPanel.clearSelection();
        });
    }

    @Override
    protected void setupListListener() {
        super.setupListListener();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Profile> listPanel = mainWindow.getListPanel();
        //Listener
        listPanel.getList().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            //Get selected
            Profile selected = listPanel.getSelectedValue();
            //Null check
            if (selected == null) return;
            //Create details panel
            ProfileDetailsPanel profileDetailsPanel = new ProfileDetailsPanel(selected);
            //Edit
            profileDetailsPanel.setOnEdit(() -> {
                ProfileEditPanel profileEditPanel = new ProfileEditPanel(selected);
                //Apply changes (Save)
                profileEditPanel.setOnSave(() -> {
                    try {
                        //Edit and update the list
                        controller.editProfile(selected,
                                profileEditPanel.getInputData());
                        //FeedBack message
                        JOptionPane.showMessageDialog(mainWindow, "Profile Edit Successful");
                        //Update
                        updateList();
                        listPanel.setSelectedValue(selected, true);
                        //Update details panel, set it
                        profileDetailsPanel.update(selected);
                        rightPanel.setContent(profileDetailsPanel);
                    } catch (Exception ex) {
                        profileEditPanel.setStatusText(ex.getMessage());
                    }
                });
                //Cancel changes
                profileEditPanel.setOnCancel(() -> {
                    rightPanel.setContent(profileDetailsPanel);
                });
                //Set the edit panel
                rightPanel.setContent(profileEditPanel);
            });
            //Delete selected
            profileDetailsPanel.setOnDelete(() -> {
                controller.delete(selected);
                //FeedBack message
                JOptionPane.showMessageDialog(mainWindow, "Profile Deletion Successful");
                updateList(); //And update the list
            });
            //Set the panel
            rightPanel.setContent(profileDetailsPanel);

        });
    }

    protected String getTitle() {return "Profile";}
}