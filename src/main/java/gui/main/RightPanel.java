package gui.main;

import gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private final JLabel title;
    private final CustomButton newButton;
    private JPanel content;

    public RightPanel() {
        this.title = new JLabel("");
        this.newButton = new CustomButton("+ New");
        JPanel top = new JPanel(new BorderLayout());
        this.content = new JPanel();

        newButton.setVisible(false);

        setLayout(new BorderLayout());
        top.add(title,BorderLayout.WEST);
        top.add(newButton,BorderLayout.EAST);
        add(top,BorderLayout.NORTH);
        add(content,BorderLayout.CENTER);
    }

    public void setTitle(String title) {this.title.setText(title);}
    public void setContent(JPanel content) {this.content = content;}
    public CustomButton getNewButton() {return newButton;}
    public JPanel getContent() {return content;}
}
