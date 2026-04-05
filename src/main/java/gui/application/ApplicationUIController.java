package gui.application;

import application.ApplicationController;
import application.Applications;
import common.Application;
import gui.components.EmptyPanel;
import gui.components.ListPanel;
import gui.main.MainWindow;
import gui.main.RightPanel;

public class ApplicationUIController {
    private final MainWindow mainWindow;
    private ApplicationController applicationController;
    private final Applications applications;

    public ApplicationUIController(MainWindow mainWindow, ApplicationController applicationController,Applications applications) {
        this.mainWindow = mainWindow;
        this.applicationController = applicationController;
        this.applications = applications;
    }

    public void open(){
        mainWindow.setListPanel(new ListPanel<Application>(applications.getAll()));
        RightPanel rightPanel = mainWindow.getRightPanel();

        rightPanel.setTitle("Application");
        rightPanel.getNewButton().setVisible(true);
        rightPanel.setContent(new EmptyPanel());

        rightPanel.getNewButton().addActionListener(e -> {
            rightPanel.setContent(new ApplicationFormPanel());
            mainWindow.getListPanel().clearSelection();
        });
    }
}
