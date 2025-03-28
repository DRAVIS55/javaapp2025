package studentmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LecturerAssignmentForm extends JFrame {
    private JTextField txtLecturerID, txtCourseCode;
    private JLabel lblMessage;
    private AdminGUI parent;

    public LecturerAssignmentForm(AdminGUI parent) {
        this.parent = parent;

        setTitle("Assign Lecturer to Course");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form Labels & Fields
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Lecturer ID (e.g., AB123):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtLecturerID = new JTextField(20);
        txtLecturerID.setToolTipText("Enter lecturer ID in format XX123");
        add(txtLecturerID, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Course Code (e.g., COSC101):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtCourseCode = new JTextField(20);
        txtCourseCode.setToolTipText("Enter course code in format XXXX123");
        add(txtCourseCode, gbc);

        // Submit Button
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton btnSubmit = new JButton("Assign Lecturer");
        btnSubmit.setMnemonic(KeyEvent.VK_A);
        add(btnSubmit, gbc);

        // Exit Button
        gbc.gridx = 0; gbc.gridy = 3;
        JButton btnExit = new JButton("Exit");
        btnExit.setMnemonic(KeyEvent.VK_X);
        add(btnExit, gbc);

        // Message Label
        gbc.gridx = 0; gbc.gridy = 4;
        lblMessage = new JLabel("", SwingConstants.CENTER);
        add(lblMessage, gbc);

        // Submit Button Action
        btnSubmit.addActionListener(e -> {
            try {
                String lecturerID = txtLecturerID.getText().trim().toUpperCase();
                String courseCode = txtCourseCode.getText().trim().toUpperCase();

                // Validate inputs
                if (!AdminGUI.isValidLecturerID(lecturerID)) {
                    lblMessage.setText("Invalid lecturer ID! Use format XX123");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                if (!AdminGUI.isValidCourseCode(courseCode)) {
                    lblMessage.setText("Invalid course code! Use format XXXX123");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                Course course = new Course();
                String result = course.assignLecturerCourse(lecturerID, courseCode);
                
                if (result.startsWith("Success")) {
                    lblMessage.setForeground(Color.GREEN);
                    // Clear fields after successful submission
                    txtLecturerID.setText("");
                    txtCourseCode.setText("");
                    txtLecturerID.requestFocus();
                } else {
                    lblMessage.setForeground(Color.RED);
                }
                lblMessage.setText(result);
            } catch (Exception ex) {
                lblMessage.setText("Error: " + ex.getMessage());
                lblMessage.setForeground(Color.RED);
            }
        });

        // Exit Button Action
        btnExit.addActionListener(e -> dispose());

        setVisible(true);
    }
} 