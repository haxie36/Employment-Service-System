package gui.base;

import base.EntityCollection;
import gui.components.CustomButton;
import gui.main.MainWindow;
import gui.main.RightPanel;
import base.HasId;

import java.awt.event.ActionListener;

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
        resetButton(rightPanel.getNewButton());
        rightPanel.getNewButton().setVisible(true);
        rightPanel.setTitle(getTitle());
    }

    protected void updateList(){
        mainWindow.getListPanel().clearSelection();
        mainWindow.getListPanel().updateList(collection.getAll());
        mainWindow.getRightPanel().setContent(new EmptyPanel());
    }

    protected void resetButton(CustomButton btn) {
        for (ActionListener l : btn.getActionListeners()) {
            btn.removeActionListener(l);
        }
    }

    protected abstract String getTitle();
}
