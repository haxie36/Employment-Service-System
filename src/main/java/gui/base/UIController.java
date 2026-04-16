package gui.base;

import gui.main.ListPanel;
import gui.main.MainWindow;
import gui.main.RightPanel;
import logic.application.Application;
import logic.base.EntityDAO;
import logic.base.HasId;
import logic.base.LogicController;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;

public abstract class UIController<T extends HasId, I, D extends EntityDAO<T>,
        C extends LogicController<T, I, D>> {
    protected final MainWindow mainWindow;
    protected final C controller;

    public UIController(MainWindow mainWindow,
                        C logicController) {
        this.mainWindow = mainWindow;
        this.controller = logicController;
    }

    public void open(){
        mainWindow.setListPanel(new ListPanel<T>(controller.getAll()));
        RightPanel rightPanel = mainWindow.getRightPanel();
        //Default right panel settings
        resetButton(rightPanel.getNewButton());
        rightPanel.getNewButton().setVisible(true);
        rightPanel.setTitle(getTitle());
        rightPanel.setContent(new EmptyPanel());
    }

    protected abstract void setupNewButtonListener();

    protected void setupListListener() {
        //Remove old ones
        ListPanel<Application> listPanel = mainWindow.getListPanel();
        for (ListSelectionListener listener : listPanel.getList().getListSelectionListeners()) {
            listPanel.getList().removeListSelectionListener(listener);
        }
    }

    protected void updateList(){
        ListPanel<T> listPanel = mainWindow.getListPanel();
        listPanel.clearSelection();
        listPanel.updateList(controller.getAll());
        mainWindow.getRightPanel().setContent(new EmptyPanel());
    }

    protected void resetButton(JButton btn) {
        for (ActionListener l : btn.getActionListeners()) {
            btn.removeActionListener(l);
        }
    }

    protected void forcedListUpdate() {
        mainWindow.setListPanel(new ListPanel<>(controller.getAll()));
        setupListListener();
    }

    protected abstract String getTitle();
}
