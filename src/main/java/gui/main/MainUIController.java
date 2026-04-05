package gui.main;

import gui.application.ApplicationUIController;
import gui.profile.ProfileUIController;
import gui.retraining.RetrainingUIController;
import gui.vacancy.VacancyUIController;

public class MainUIController {
    private MainWindow mainWindow;
    private ProfileUIController profileUIController;
    private VacancyUIController vacancyUIController;
    private ApplicationUIController applicationUIController;
    private RetrainingUIController retrainingUIController;

    public MainUIController(MainWindow mainWindow,
                            ProfileUIController profileUIController, ApplicationUIController applicationUIController,
                            VacancyUIController vacancyUIController, RetrainingUIController retrainingUIController) {
        this.mainWindow = mainWindow;
        this.profileUIController = profileUIController;
        this.vacancyUIController = vacancyUIController;
        this.applicationUIController = applicationUIController;
        this.retrainingUIController = retrainingUIController;

        //Left menu tabs (buttons) actions
        LeftMenu leftMenu = mainWindow.getLeftMenu();
        leftMenu.getProfilesBtn().addActionListener(e -> {
            profileUIController.open();
        });
        leftMenu.getVacanciesBtn().addActionListener(e -> {
            vacancyUIController.open();
        });
        leftMenu.getApplicationsBtn().addActionListener(e -> {
            applicationUIController.open();
        });
        leftMenu.getRetrainingsBtn().addActionListener(e -> {
            retrainingUIController.open();
        });
    }
}
