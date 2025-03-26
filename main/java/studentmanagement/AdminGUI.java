package main.java.studentmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.regex.Pattern;

public class AdminGUI extends JPanel {
    private JButton btnAttachProgram, btnAssignLecturer, btnExit, btnBack;
    private static final Pattern PROGRAMME_CODE_PATTERN = Pattern.compile("^(eb1|eb2|eb3|cb1)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern COURSE_CODE_PATTERN = Pattern.compile("^[A-Z]{4}\\d{3}$");
    private static final Pattern LECTURER_ID_PATTERN = Pattern.compile("^[A-Z]{2}\\d{3}$");
    private static final Pattern REG_NO_PATTERN = Pattern.compile("^(EB1|EB2|EB3|CB1)/\\d{5}/\\d{2}$");

    public AdminGUI() {
        setLayout(new FlowLayout());
        
        // Back Button
        btnBack = new JButton("Back to Login");
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnBack.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "login");
        });
        add(btnBack);

        // Attach Program Button
        btnAttachProgram = new JButton("Attach Program");
        btnAttachProgram.setMnemonic(KeyEvent.VK_A);
        btnAttachProgram.setToolTipText("Attach a course to a programme");
        btnAttachProgram.addActionListener(e -> {
            final Course course = new Course();
            try {
                CourseFormGUI form = new CourseFormGUI(course, this);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error initializing course form: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnAttachProgram);

        // Assign Lecturer Button
        btnAssignLecturer = new JButton("Assign Lecturer");
        btnAssignLecturer.setMnemonic(KeyEvent.VK_L);
        btnAssignLecturer.setToolTipText("Assign a lecturer to a course");
        btnAssignLecturer.addActionListener(e -> {
            try {
                new LecturerAssignmentForm(this);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error initializing lecturer form: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnAssignLecturer);

        // Exit Button
        btnExit = new JButton("Exit");
        btnExit.setMnemonic(KeyEvent.VK_X);
        btnExit.setToolTipText("Exit the application");
        btnExit.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        add(btnExit);
    }

    // Validation methods
    public static boolean isValidProgrammeCode(String code) {
        return PROGRAMME_CODE_PATTERN.matcher(code).matches();
    }

    public static boolean isValidCourseCode(String code) {
        return COURSE_CODE_PATTERN.matcher(code).matches();
    }

    public static boolean isValidLecturerID(String id) {
        return LECTURER_ID_PATTERN.matcher(id).matches();
    }

    public static boolean isValidRegNo(String regNo) {
        return REG_NO_PATTERN.matcher(regNo).matches();
    }
}

class CourseFormGUI extends JFrame {
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
        btnExit.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        // Set default button
        getRootPane().setDefaultButton(btnSubmit);
        
        // Add window listener for cleanup
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // No cleanup needed as course is final
            }
        });

        setVisible(true);
    }
}

class LecturerAssignmentForm extends JFrame {
    private JTextField txtLecturerID, txtCourseCode;
    private JLabel lblMessage;
    private AdminGUI parent;

    public LecturerAssignmentForm(AdminGUI parent) {
        this.parent = parent;
        
        setTitle("Assign Lecturer to Course");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        setLocationRelativeTo(parent);

        // Labels and Text Fields
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Lecturer ID:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtLecturerID = new JTextField(20);
        txtLecturerID.setToolTipText("Enter lecturer ID in format XX123 (e.g., LT001)");
        add(txtLecturerID, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Course Code:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtCourseCode = new JTextField(20);
        txtCourseCode.setToolTipText("Enter course code in format XXXX123 (e.g., COSC101)");
        add(txtCourseCode, gbc);

        // Submit Button
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton btnSubmit = new JButton("Assign Course");
        btnSubmit.setMnemonic(KeyEvent.VK_A);
        add(btnSubmit, gbc);

        // Message Label
        gbc.gridx = 0; gbc.gridy = 3;
        lblMessage = new JLabel("", SwingConstants.CENTER);
        add(lblMessage, gbc);

        // Button Action
        btnSubmit.addActionListener(e -> {
            String lecturerID = txtLecturerID.getText().trim().toUpperCase();
            String courseCode = txtCourseCode.getText().trim().toUpperCase();

            // Validate inputs
            if (!AdminGUI.isValidLecturerID(lecturerID)) {
                lblMessage.setText("Invalid lecturer ID! Use format XX123 (e.g., LT001)");
                lblMessage.setForeground(Color.RED);
                return;
            }

            if (!AdminGUI.isValidCourseCode(courseCode)) {
                lblMessage.setText("Invalid course code! Use format XXXX123 (e.g., COSC101)");
                lblMessage.setForeground(Color.RED);
                return;
            }

            final Course assignment = new Course();
            String result = assignment.assignLecturerCourse(lecturerID, courseCode);

            lblMessage.setText(result);
            lblMessage.setForeground(result.startsWith("Success") ? 
                new Color(0, 128, 0) : Color.RED);
            
            // Clear fields after successful submission
            txtLecturerID.setText("");
            txtCourseCode.setText("");
            txtLecturerID.requestFocus();
        });

        // Set default button
        getRootPane().setDefaultButton(btnSubmit);
        
        // Add window listener for cleanup
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Cleanup if needed
            }
        });

        setVisible(true);
    }
}


