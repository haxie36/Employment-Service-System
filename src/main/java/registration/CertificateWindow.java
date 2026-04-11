package registration;

import javax.swing.*;
import java.awt.*;

public class CertificateWindow extends JFrame {

    private final JTextArea textArea;

    public CertificateWindow(String certificateText) {
        super("Registration Certificate");

        setSize(320, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width - getWidth(),
                (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2
        );

        textArea = new JTextArea(certificateText);
        textArea.setFont(new Font("Serif", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
