package gui.retraining;

import gui.main.ListPanel;
import retraining.Retraining;
import gui.base.UIController;
import gui.main.MainWindow;
import gui.main.RightPanel;
import retraining.RetrainingController;
import retraining.RetrainingCollection;

public class RetrainingUIController extends UIController<Retraining> {
    private final RetrainingController retrainingController;

    public RetrainingUIController(MainWindow mainWindow,
                                  RetrainingController retrainingController, RetrainingCollection retrainingCollection) {
        super(mainWindow, retrainingCollection);
        this.retrainingController = retrainingController;
    }

    public void open(){
        super.open();
        RightPanel rightPanel = mainWindow.getRightPanel();
        ListPanel<Retraining> listPanel = mainWindow.getListPanel();
    }

    protected String getTitle() {return "Retraining";}
}
