package gui.application;

import application.ApplicationController;
import application.ApplicationCollection;
import application.Application;
import gui.base.ListPanel;
import gui.base.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;

public class ApplicationUIController extends UIController<Application> {
    private final ApplicationController applicationController;

    public ApplicationUIController(MainWindow mainWindow,
                                   ApplicationController applicationController, ApplicationCollection applicationCollection) {
        super(mainWindow, applicationCollection);
        this.applicationController = applicationController;
    }

    public void open(){
        super.open();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Application> listPanel = mainWindow.getListPanel();

        rightPanel.getNewButton().addActionListener(e -> {
            //rightPanel.setContent(new ApplicationFormPanel());
            mainWindow.getListPanel().clearSelection();
        });
    }

    protected String getTitle() {
        return "Application";
    }
}
