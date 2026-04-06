package gui.base;

import base.EntityCollection;
import gui.main.MainWindow;
import gui.main.RightPanel;
import base.HasId;

public abstract class UIController<T extends HasId> {
    protected final MainWindow mainWindow;
    protected final EntityCollection<T> collection;

    public UIController(MainWindow mainWindow,
                        EntityCollection<T> entityCollection) {
        this.mainWindow = mainWindow;
        this.collection = entityCollection;
    }

    public void open(){
        mainWindow.setListPanel(new ListPanel<T>(collection.getAll()));
        RightPanel rightPanel = mainWindow.getRightPanel();
        //Default right panel settings
        rightPanel.getNewButton().setVisible(true);
        rightPanel.setTitle(T.getClassName());
    }

    protected void updateList(){
        mainWindow.getListPanel().clearSelection();
        mainWindow.getListPanel().updateList(collection.getAll());
        mainWindow.getRightPanel().setContent(new EmptyPanel());
    }
}
