package gui.components;

import common.EntityCollection;
import gui.main.MainWindow;
import gui.main.RightPanel;
import interfaces.HasId;

public abstract class UIController<T extends HasId> {
    protected final MainWindow mainWindow;
    protected final EntityCollection<T> collection;

    public UIController(MainWindow mainWindow,
                        EntityCollection<T> entityCollection) {
        this.mainWindow = mainWindow;
        this.collection = entityCollection;
    }

    public void open(){
        updateList();
        RightPanel rightPanel = mainWindow.getRightPanel();
        //Default right panel settings
        rightPanel.getNewButton().setVisible(true);
        rightPanel.setTitle(T.getClassName());
    }

    protected void updateList(){
        mainWindow.setListPanel(new ListPanel<>(collection.getAll()));
        mainWindow.getRightPanel().setContent(new EmptyPanel());
    }
}
