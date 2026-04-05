package gui.retraining;

import common.Retraining;
import gui.components.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;
import retraining.RetrainingController;
import retraining.Retrainings;

public class RetrainingUIController extends UIController<Retraining> {
    private final RetrainingController retrainingController;

    public RetrainingUIController(MainWindow mainWindow, RetrainingController retrainingController,Retrainings retrainings) {
        super(mainWindow, retrainings);
        this.retrainingController = retrainingController;
    }

    public void open(){
        super.open();
        RightPanel rightPanel = mainWindow.getRightPanel();
    }
}
