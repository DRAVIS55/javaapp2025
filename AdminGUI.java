import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import java.awt.*;

public class AdminGUI extends JFrame {
    private JButton btnAttachProgram, btnAssignLecturer, btnExit;

    public AdminGUI() {
        setTitle("Admin Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null); // Center the window

        // Attach Program Button (Unimplemented)
        btnAttachProgram = new JButton("Attach Program");
        btnAttachProgram.addActionListener(e -> {
            Course course = new Course();
            CourseFormGUI form = new CourseFormGUI(course);
        });
        add(btnAttachProgram);

        // Assign Lecturer Button
        btnAssignLecturer = new JButton("Assign Lecturer");
        btnAssignLecturer.addActionListener(e -> {
            new LecturerAssignmentForm(); // Open Lecturer Assignment Form
        });
        add(btnAssignLecturer);

        // Exit Button
        btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> dispose());
        add(btnExit);

        setVisible(true);
    }

    // Main method to run GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminGUI::new);
    }
}


class CourseFormGUI extends JFrame {
    private JTextField txtProgrammeCode, txtCourseCode, txtCourseName;
    private JButton btnSubmit, btnExit;
    private JLabel lblMessage;
    private Course course; // Reference to the Course class

    public CourseFormGUI(Course course) {
        this.course = course; // Injecting Course instance

        setTitle("Attach Course to Programme");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null); // Center the window

        // Form Labels & Fields
        add(new JLabel("Programme Code (eb1, cb1, eb2, eb3):"));
        txtProgrammeCode = new JTextField();
        add(txtProgrammeCode);

        add(new JLabel("Course Code (e.g., COSC101):"));
        txtCourseCode = new JTextField();
        add(txtCourseCode);

        add(new JLabel("Course Name:"));
        txtCourseName = new JTextField();
        add(txtCourseName);

        // Submit Button
        btnSubmit = new JButton("Attach Course");
        add(btnSubmit);

        // Exit Button (Bottom Right)
        btnExit = new JButton("Exit");
        add(btnExit);

        // Message Label
        lblMessage = new JLabel("", SwingConstants.CENTER);
        add(lblMessage);

        // Submit Button Action
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String programmeCode = txtProgrammeCode.getText().trim().toLowerCase();
                String courseCode = txtCourseCode.getText().trim().toUpperCase();
                String courseName = txtCourseName.getText().trim();

                if (programmeCode.isEmpty() || courseCode.isEmpty() || courseName.isEmpty()) {
                    lblMessage.setText("All fields are required!");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                course.attachCourseToAProgramme(programmeCode, courseCode, courseName);
                lblMessage.setText("Course added successfully!");
                lblMessage.setForeground(Color.GREEN);
            }
        });

        // Exit Button Action
        btnExit.addActionListener(e -> dispose());

        setVisible(true);
    }

   
}


 class LecturerAssignmentForm {
    public LecturerAssignmentForm() {
        // Create JFrame
        JFrame frame = new JFrame("Assign Lecturer to Course");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Labels and Text Fields
        JLabel lblLecturerID = new JLabel("Lecturer ID:");
        lblLecturerID.setBounds(30, 30, 100, 30);
        JTextField txtLecturerID = new JTextField();
        txtLecturerID.setBounds(140, 30, 200, 30);

        JLabel lblCourseCode = new JLabel("Course Code:");
        lblCourseCode.setBounds(30, 80, 100, 30);
        JTextField txtCourseCode = new JTextField();
        txtCourseCode.setBounds(140, 80, 200, 30);

        // Submit Button
        JButton btnSubmit = new JButton("Assign Course");
        btnSubmit.setBounds(140, 130, 150, 30);
        JLabel lblMessage = new JLabel("", SwingConstants.CENTER);
        lblMessage.setBounds(50, 170, 300, 30);

        // Button Action
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lecturerID = txtLecturerID.getText().trim();
                String courseCode = txtCourseCode.getText().trim();

               Course assignment = new Course();
                String result = assignment.assignLecturerCourse(lecturerID, courseCode);

                lblMessage.setText(result);
                lblMessage.setForeground(result.startsWith("Success") ? new java.awt.Color(0, 128, 0) : java.awt.Color.RED);
            }
        });

        // Add components to frame
        frame.add(lblLecturerID);
        frame.add(txtLecturerID);
        frame.add(lblCourseCode);
        frame.add(txtCourseCode);
        frame.add(btnSubmit);
        frame.add(lblMessage);

        // Show frame
        frame.setVisible(true);
    }

    
}


