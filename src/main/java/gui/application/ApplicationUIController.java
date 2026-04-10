package gui.application;

import application.AppInput;
import application.ApplicationController;
import application.ApplicationCollection;
import application.Application;
import gui.base.EmptyPanel;
import gui.main.ListPanel;
import gui.base.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;
import gui.vacancy.VacancyDetailsPanel;
import registration.Profile;
import registration.ProfileCollection;
import vacancy.Vacancy;

import javax.swing.*;

public class ApplicationUIController extends UIController<Application> {
    private final ApplicationController applicationController;
    private final ProfileCollection profileCollection;

    public ApplicationUIController(MainWindow mainWindow,
                                   ApplicationController applicationController,
                                   ApplicationCollection applicationCollection,
                                   ProfileCollection profileCollection) {
        super(mainWindow, applicationCollection);
        this.applicationController = applicationController;
        this.profileCollection = profileCollection;
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
        //Listener
        rightPanel.getNewButton().addActionListener(e -> {
            forcedListUpdate();
            ListPanel<Application> listPanel = mainWindow.getListPanel();
            FindProfileFormPanel findProfileForm = new FindProfileFormPanel();
            //Find button
            findProfileForm.setOnFind(() -> {
                try {
                    AppInput passportNumberInput = findProfileForm.getInputData();
                    Profile foundProfile = profileCollection.getByPassport(passportNumberInput.getPassportNumber());
                    if (foundProfile == null) {throw new Exception("Profile not found!");}
                    findProfileForm.updateProfileDetails(foundProfile);
                } catch (Exception ex) {findProfileForm.setStatusText(ex.getMessage());}
            });
            //Continue button
            findProfileForm.setOnSave(() -> {
                try {
                    if (findProfileForm.getFoundProfile() == null)
                        throw new Exception("Profile not found!");
                    Vacancy[] recommendations = applicationController.getRecommendations(
                            findProfileForm.getFoundProfile()
                    );
                    if (recommendations == null || recommendations.length == 0) {
                        JOptionPane.showMessageDialog(mainWindow, "No recommendations found!");
                        return;
                    }
                    rightPanel.setContent(new EmptyPanel());
                    mainWindow.setListPanel(new ListPanel<>(recommendations));
                    ListPanel<Vacancy> recListPanel = mainWindow.getListPanel();
                    //Choosing vacancy
                    recListPanel.getList().addListSelectionListener(e1 -> {
                        if (e1.getValueIsAdjusting()) return;
                        //Get selected
                        Vacancy selectedVacancy = recListPanel.getList().getSelectedValue();
                        //Null check
                        if (selectedVacancy == null) return;
                        //Create details panel
                        VacancyDetailsPanel vacancyDetailsPanel = getVacancyDetailsPanel(selectedVacancy, findProfileForm, rightPanel);
                        //Add the panel
                        rightPanel.setContent(vacancyDetailsPanel);
                    });
                } catch (Exception ex) {findProfileForm.setStatusText(ex.getMessage());}
            });
            //Cancel
            findProfileForm.setOnCancel(() -> {rightPanel.setContent(new EmptyPanel());});
            //Show the form and clear selection
            rightPanel.setContent(findProfileForm);
            listPanel.clearSelection();
        });
    }

    //Note: To not repeat the code, I use the same class here.
    //I've added methods to change the text of buttons,
    //but I cannot change the name of a Runnable,
    //therefore, don't mind me referring to "onEdit" as "onApply"
    //and to "onDelete" as "onCancel"...
    private VacancyDetailsPanel getVacancyDetailsPanel(Vacancy selectedVacancy, FindProfileFormPanel findProfileForm, RightPanel rightPanel) {
        VacancyDetailsPanel vacancyDetailsPanel = new VacancyDetailsPanel(selectedVacancy);
        vacancyDetailsPanel.setEditButtonText("Apply");
        vacancyDetailsPanel.setDeleteButtonText("Cancel");
        //Apply
        vacancyDetailsPanel.setOnEdit(() -> {
            try {
                AppInput finalInput = new AppInput(
                        findProfileForm.getFoundProfile().getId(),
                        selectedVacancy
                );
                applicationController.create(finalInput);
                forcedListUpdate();
                rightPanel.setContent(new EmptyPanel());
            } catch (Exception ex) {JOptionPane.showMessageDialog(mainWindow, ex.getMessage());}
        });
        //Cancel
        vacancyDetailsPanel.setOnDelete(() -> {
            //Back to Applications list, instead of recommended Vacancies
            forcedListUpdate();
            rightPanel.setContent(new EmptyPanel());
        });
        return vacancyDetailsPanel;
    }

    @Override
    protected void setupListListener() {
        super.setupListListener();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Application> listPanel = mainWindow.getListPanel();
        //Listener
        listPanel.getList().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            //Get selected
            Application selected = listPanel.getSelectedValue();
            //Null check
            if (selected == null) return;
            //Create DetailsPanel
            ApplicationDetailsPanel applicationDetailsPanel = new ApplicationDetailsPanel(selected);
            //Edit
            applicationDetailsPanel.setOnEdit(() -> {
                ApplicationEditPanel applicationEditPanel = new ApplicationEditPanel(selected);
                //Apply the changes
                applicationEditPanel.setOnSave(() -> {
                    //Edit and update the list
                    applicationController.changeApplicationStatus(selected,
                            applicationEditPanel.getStatus());
                    //FeedBack
                    JOptionPane.showMessageDialog(mainWindow, "Status Update Successful!");
                    //Update
                    updateList();
                    listPanel.setSelectedValue(selected, true);
                    //Update the details panel and set it
                    applicationDetailsPanel.update(selected);
                    rightPanel.setContent(applicationDetailsPanel);
                });
                //Cancel the changes
                applicationEditPanel.setOnCancel(() -> {
                    rightPanel.setContent(applicationDetailsPanel);
                });
                //Set the panel
                rightPanel.setContent(applicationEditPanel);
            });
            //Delete
            applicationDetailsPanel.setOnDelete(() -> {
                applicationController.delete(selected);
                //FeedBack
                JOptionPane.showMessageDialog(mainWindow, "Application Delete Successful!");
                updateList();
            });
            //Add the panel
            rightPanel.setContent(applicationDetailsPanel);
        });
    }

    private void forcedListUpdate() {
        mainWindow.setListPanel(new ListPanel<>(collection.getAll()));
        setupListListener();
    }

    protected String getTitle() {return "Application";}
}
