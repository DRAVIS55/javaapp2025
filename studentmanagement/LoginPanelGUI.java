package studentmanagement;
import javax.swing.*;

import java.awt.*;

public class LoginPanelGUI extends JFrame {
    public LoginPanelGUI() {
        setTitle("User Login Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Student System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton btnAdmin = new JButton("Admin Login");
        JButton btnStudent = new JButton("Student Login");
        JButton btnExit = new JButton("Exit");

        // Increase button size
        btnAdmin.setPreferredSize(new Dimension(200, 50));
        btnStudent.setPreferredSize(new Dimension(200, 50));
        btnExit.setPreferredSize(new Dimension(200, 50));

        panel.add(btnAdmin);
        panel.add(btnStudent);
        panel.add(btnExit);
        
        add(panel, BorderLayout.CENTER);

        // Button Actions
        btnAdmin.addActionListener(e -> openAdminPanel());
        btnStudent.addActionListener(e -> openStudentPanel());
        btnExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void openAdminPanel() {
        JOptionPane.showMessageDialog(this, "Redirecting to Admin Login...");
        new AdminGUI();
        dispose();
    }

    private void openStudentPanel() {
        JOptionPane.showMessageDialog(this, "Redirecting to Student Login...");
        new StudentGui().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPanelGUI::new);
    }
}
