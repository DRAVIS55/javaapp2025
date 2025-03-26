package studentmanagement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class Reusable extends JFrame {
    public Reusable(String Message) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JTextField text = new JTextField();
        text.setEditable(false);
        frame.add(text, BorderLayout.CENTER);
        text.setText(Message);

        JButton button = new JButton("Close");
        button.addActionListener(e -> {
            frame.dispose();
        });
        frame.add(button, BorderLayout.SOUTH);
    }
} 