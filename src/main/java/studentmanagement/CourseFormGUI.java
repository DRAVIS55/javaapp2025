package studentmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class CourseFormGUI extends JFrame {
    private JTextField txtProgrammeCode, txtCourseCode, txtCourseName;
    private JButton btnSubmit, btnExit;
    private JLabel lblMessage;
    private final Course course;
    private AdminGUI parent;

    public CourseFormGUI(Course course, AdminGUI parent) {
        this.course = course;
        this.parent = parent;

        setTitle("Attach Course to Programme");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        setLocationRelativeTo(parent);

        // Form Labels & Fields
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Programme Code (eb1, cb1, eb2, eb3):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtProgrammeCode = new JTextField(20);
        txtProgrammeCode.setToolTipText("Enter programme code (eb1, cb1, eb2, or eb3)");
        add(txtProgrammeCode, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Course Code (e.g., COSC101):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtCourseCode = new JTextField(20);
        txtCourseCode.setToolTipText("Enter course code in format XXXX123 (e.g., COSC101)");
        add(txtCourseCode, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Course Name:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        txtCourseName = new JTextField(20);
        txtCourseName.setToolTipText("Enter the full course name");
        add(txtCourseName, gbc);

        // Submit Button
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        btnSubmit = new JButton("Attach Course");
        btnSubmit.setMnemonic(KeyEvent.VK_A);
        add(btnSubmit, gbc);

        // Exit Button
        gbc.gridx = 0; gbc.gridy = 4;
        btnExit = new JButton("Exit");
        btnExit.setMnemonic(KeyEvent.VK_X);
        add(btnExit, gbc);

        // Message Label
        gbc.gridx = 0; gbc.gridy = 5;
        lblMessage = new JLabel("", SwingConstants.CENTER);
        add(lblMessage, gbc);

        // Submit Button Action
        btnSubmit.addActionListener(e -> {
            try {
                String programmeCode = txtProgrammeCode.getText().trim().toLowerCase();
                String courseCode = txtCourseCode.getText().trim().toUpperCase();
                String courseName = txtCourseName.getText().trim();

                // Validate inputs
                if (!AdminGUI.isValidProgrammeCode(programmeCode)) {
                    lblMessage.setText("Invalid programme code! Use eb1, cb1, eb2, or eb3");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                if (!AdminGUI.isValidCourseCode(courseCode)) {
                    lblMessage.setText("Invalid course code! Use format XXXX123 (e.g., COSC101)");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                if (courseName.isEmpty()) {
                    lblMessage.setText("Course name is required!");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                course.attachCourseToAProgramme(programmeCode, courseCode, courseName);
                lblMessage.setText("Course added successfully!");
                lblMessage.setForeground(Color.GREEN);
                
                // Clear fields after successful submission
                txtProgrammeCode.setText("");
                txtCourseCode.setText("");
                txtCourseName.setText("");
                txtProgrammeCode.requestFocus();
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