package gui.main;

import gui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private final JLabel title;
    private final CustomButton newButton;
    private JPanel content;

    public RightPanel() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(320, 0));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        //temp
        setBackground(Color.RED);

        JPanel top = new JPanel(new BorderLayout());
        title = new JLabel("");
        title.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        title.setFont(new Font("Arial",Font.BOLD,20));
        title.setPreferredSize(new Dimension(160, 50));
        newButton = new CustomButton("+ New  ");
        newButton.setVisible(false);
        content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createBevelBorder(1));

        top.add(title,BorderLayout.WEST);
        top.add(newButton,BorderLayout.EAST);
        add(top,BorderLayout.NORTH);
        add(content,BorderLayout.CENTER);
    }

    public void setTitle(String title) {this.title.setText(title);}
    public void setContent(JPanel content) {
        remove(this.content);
        this.content = content;
        add(this.content, BorderLayout.CENTER);

        revalidate(); repaint();
    }
    public CustomButton getNewButton() {return newButton;}
    public JPanel getContent() {return content;}
}
