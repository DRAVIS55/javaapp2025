package studentmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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


