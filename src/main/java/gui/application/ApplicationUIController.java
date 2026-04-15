package gui.application;

import gui.base.EmptyPanel;
import gui.base.UIController;
import gui.main.ListPanel;
import gui.main.MainWindow;
import gui.main.RightPanel;
import gui.vacancy.VacancyDetailsPanel;
import logic.application.AppInput;
import logic.application.Application;
import logic.application.ApplicationController;
import logic.application.ApplicationDAO;
import logic.profile.Profile;
import logic.profile.RegistrationController;
import logic.vacancy.Vacancy;
import logic.vacancy.VacancyController;

import javax.swing.*;

public class ApplicationUIController extends UIController<Application, AppInput, ApplicationDAO, ApplicationController> {
    private final RegistrationController registrationController;
    private final VacancyController vacancyController;

    public ApplicationUIController(MainWindow mainWindow,
                                   ApplicationController applicationController,
                                   RegistrationController registrationController,
                                   VacancyController  vacancyController) {
        super(mainWindow, applicationController);
        this.registrationController = registrationController;
        this.vacancyController = vacancyController;
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
                    Profile foundProfile = controller.getByPassport(passportNumberInput.getPassportNumber());
                    if (foundProfile == null) {
                        throw new IllegalArgumentException("Profile not found!");
                    }
                    findProfileForm.updateProfileDetails(foundProfile);
                } catch (Exception ex) {
                    findProfileForm.setStatusText(ex.getMessage());
                }
            });
            //Continue button
            findProfileForm.setOnSave(() -> {
                try {
                    if (findProfileForm.getFoundProfile() == null) {
                        throw new IllegalArgumentException("Profile not found!");
                    }
                    Vacancy[] recommendations = controller.getRecommendations(
                            findProfileForm.getFoundProfile()
                    );
                    if (recommendations == null || recommendations.length == 0) {
                        JOptionPane.showMessageDialog(mainWindow, "No recommendations found!");
                        return;
                    }
                    listPanel.clearSelection();
                    rightPanel.setContent(new EmptyPanel("Choose a vacancy to Apply"));
                    mainWindow.setListPanel(new ListPanel<>(recommendations));
                    ListPanel<Vacancy> recListPanel = mainWindow.getListPanel();
                    //Clear possible listener's duplicates
                    JList<Vacancy> recList = recListPanel.getList();
                    for (var listener : recList.getListSelectionListeners()) {
                        recList.removeListSelectionListener(listener);
                    }
                    //Choosing vacancy
                    recList.addListSelectionListener(e1 -> {
                        if (e1.getValueIsAdjusting()) return;
                        //Get selected
                        Vacancy selectedVacancy = recListPanel.getList().getSelectedValue();
                        //Null check
                        if (selectedVacancy == null) return;
                        //Create details panel
                        VacancyDetailsPanel vacancyDetailsPanel = getVacancyDetailsPanel(selectedVacancy, findProfileForm, rightPanel);
                        rightPanel.setContent(vacancyDetailsPanel);
                    });
                } catch (Exception ex) {
                    throw new RuntimeException(ex.getMessage());
                }
            });
            //Cancel
            findProfileForm.setOnCancel(() -> {rightPanel.setContent(new EmptyPanel());});
            //Show the form
            rightPanel.setContent(findProfileForm);
        });
    }

    //Note: To not repeat the code, I use the same class here.
    //I've added methods to change the text of buttons,
    //but I cannot change the name of a Runnable,
    //therefore, don't mind me referring to "onEdit" as "Apply"
    //and to "onDelete" as "Cancel"...
    private VacancyDetailsPanel getVacancyDetailsPanel(Vacancy selectedVacancy, FindProfileFormPanel findProfileForm, RightPanel rightPanel) {
        VacancyDetailsPanel vacancyDetailsPanel = new VacancyDetailsPanel(selectedVacancy);
        vacancyDetailsPanel.setEditButtonText("Apply");
        vacancyDetailsPanel.setDeleteButtonText("Cancel");
        //Apply
        vacancyDetailsPanel.setOnEdit(() -> {
            try {
                AppInput finalInput = new AppInput(
                        findProfileForm.getFoundProfile().getPassportNumber(),
                        selectedVacancy
                );
                controller.create(finalInput);
                //FeedBack
                JOptionPane.showMessageDialog(mainWindow, "Application Creation Successful");
                forcedListUpdate();
                rightPanel.setContent(new EmptyPanel());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainWindow, ex.getMessage());
            }
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
            Profile profileOfSelected = registrationController.getById(selected.getProfileId());
            Vacancy vacancyOfSelected = vacancyController.getById(selected.getVacancyId());
            ApplicationDetailsPanel applicationDetailsPanel = new ApplicationDetailsPanel(selected, profileOfSelected, vacancyOfSelected);
            //Edit
            applicationDetailsPanel.setOnEdit(() -> {
                ApplicationEditPanel applicationEditPanel = new ApplicationEditPanel(selected, profileOfSelected, vacancyOfSelected);
                //Apply changes
                applicationEditPanel.setOnSave(() -> {
                    //Edit and update the list
                    try {
                        controller.changeApplicationStatus(selected,
                                applicationEditPanel.getStatus());
                        //FeedBack
                        JOptionPane.showMessageDialog(mainWindow, "Status Update Successful!");
                        //Update
                        updateList();
                        listPanel.setSelectedValue(selected, true);
                        //Update the details panel and set it
                        applicationDetailsPanel.update(selected);
                        rightPanel.setContent(applicationDetailsPanel);
                    } catch (Exception ex) {
                        applicationEditPanel.setStatusText(ex.getMessage());
                    }
                });
                //Cancel changes
                applicationEditPanel.setOnCancel(() -> {
                    rightPanel.setContent(applicationDetailsPanel);
                });
                //Set the panel
                rightPanel.setContent(applicationEditPanel);
            });
            //Delete
            applicationDetailsPanel.setOnDelete(() -> {
                controller.delete(selected);
                //FeedBack
                JOptionPane.showMessageDialog(mainWindow, "Application Deletion Successful!");
                updateList();
            });
            //Add the panel
            rightPanel.setContent(applicationDetailsPanel);
        });
    }

    protected String getTitle() {return "Application";}
}
