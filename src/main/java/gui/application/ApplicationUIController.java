package gui.application;

import application.ApplicationController;
import application.Applications;
import common.Application;
import gui.components.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;

public class ApplicationUIController extends UIController<Application> {
    private final ApplicationController applicationController;

    public ApplicationUIController(MainWindow mainWindow, ApplicationController applicationController,Applications applications) {
        super(mainWindow,applications);
        this.applicationController = applicationController;
    }

    public void open(){
        super.open();
        RightPanel rightPanel = mainWindow.getRightPanel();

        rightPanel.getNewButton().addActionListener(e -> {
            rightPanel.setContent(new ApplicationFormPanel());
            mainWindow.getListPanel().clearSelection();
        });
    }
}
