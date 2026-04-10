package gui.main;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final LeftMenu leftMenu;
    private ListPanel listPanel;
    private RightPanel rightPanel;

    public MainWindow() {
        super("Vacancy Processing and Recommendation System (Employment Service)");
        setLayout(new BorderLayout());
        setSize(1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        leftMenu = new LeftMenu();
        listPanel = new ListPanel<Object>(new Object[0]);
        rightPanel = new RightPanel();

        add(leftMenu,BorderLayout.WEST);
        add(listPanel,BorderLayout.CENTER);
        add(rightPanel,BorderLayout.EAST);
        setVisible(true);
    }

    public ListPanel getListPanel() {return listPanel;}
    public LeftMenu getLeftMenu() {return leftMenu;}
    public RightPanel getRightPanel() {return rightPanel;}

    public void setListPanel(ListPanel listPanel) {
        remove(this.listPanel);
        this.listPanel = listPanel;
        add(this.listPanel, BorderLayout.CENTER);

        revalidate(); repaint();
    }
    public void setRightPanel(RightPanel rightPanel) {this.rightPanel = rightPanel;}
}
