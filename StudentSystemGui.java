import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class StudentSystemGui extends JFrame{
    private JTextField regNoField,programmeField,fullnameField,emailField,phoneNoField;
    private JLabel label,regNoLabel,programmeLabel,fullnameLabel,emailLabel,phoneNoLabel;
    private JButton button1,button2,button3,button4,button5,button6,submit;
    private JPanel panel,formPanel;
     private static final JCheckBox[] checkBoxes = new JCheckBox[10]; // Max 10 courses selectable
    private static final JTextArea outputArea = new JTextArea(5, 30);

    public StudentSystemGui(){
        super("student dashboard");
        setSize(400,400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
         panel=new JPanel(new GridBagLayout());
        panel.setBackground(Color.white);
        add(panel,BorderLayout.CENTER);
        GridBagConstraints gdc=new GridBagConstraints();
        gdc.insets=new Insets(10,10, 10,10);
        gdc.fill=GridBagConstraints.HORIZONTAL;

        label=new JLabel();
        label.setText("Students Panel");
        label.setSize(10,10);
        gdc.gridx=1;
        gdc.gridy=0;
        panel.add(label,gdc);

         button1=new JButton("Register Student");
        button1.addActionListener(e->{
           registerGui(); });
        gdc.gridx=0;
        gdc.gridy=1;
        panel.add(button1,gdc);

         button2=new JButton("enroll  student to a course");
        button2.addActionListener(e->{
            String regNo=JOptionPane.showInputDialog("enter registration number");
            CourseSelection selection=new CourseSelection(regNo);
        });
        gdc.gridx=1;
        gdc.gridy=1;
        panel.add(button2,gdc);

         button3=new JButton("assign scores ");
        button3.addActionListener(e->{
            
        });
        gdc.gridx=0;
        gdc.gridy=2;
        panel.add(button3,gdc);

         button4=new JButton("Search Student");
        button4.addActionListener(e->{
            
        });
        gdc.gridx=1;
        gdc.gridy=2;
        panel.add(button4,gdc);

         button5=new JButton("retrieve  results");
        button5.addActionListener(e->{
            
        });
        gdc.gridx=0;
        gdc.gridy=3;
        panel.add(button5,gdc);
         button6=new JButton("update results");
        button6.addActionListener(e->{
            
        });
        gdc.gridx=1;
        gdc.gridy=3;
        panel.add(button6,gdc);


    }

    public static void main(String[] args) {
        try{
        SwingUtilities.invokeLater(()->{
            new StudentSystemGui().setVisible(true);
        });}
        catch(Exception e){
            e.printStackTrace();
        }
    }

    

    public void registerGui(){
        JFrame frame=new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        formPanel=new JPanel(new GridBagLayout());
        GridBagConstraints gdc=new GridBagConstraints();
        gdc.insets=new Insets(10,10, 10,10);
        gdc.fill=GridBagConstraints.HORIZONTAL;
        frame.add(formPanel,BorderLayout.CENTER);

 // Registration Number
  regNoLabel = new JLabel("Registration Number:");
 gdc.gridx = 0;
 gdc.gridy = 1;
 gdc.weightx = 0.3; // Less space for label
 formPanel.add(regNoLabel, gdc);

 regNoField = new JTextField();
 regNoField.setPreferredSize(new Dimension(200, 30)); // Set large width
 gdc.gridx = 1;
 gdc.weightx = 1; // More space for input
 formPanel.add(regNoField, gdc);

 // Full Name
 fullnameLabel = new JLabel("Full Name:");
 gdc.gridx = 0;
 gdc.gridy = 2;
 gdc.weightx = 0.3;
 formPanel.add(fullnameLabel, gdc);

 fullnameField = new JTextField();
 fullnameField.setPreferredSize(new Dimension(200, 30));
 gdc.gridx = 1;
 gdc.weightx = 1;
 formPanel.add(fullnameField, gdc);

 // Programme
 programmeLabel = new JLabel("Programme:");
 gdc.gridx = 0;
 gdc.gridy = 3;
 formPanel.add(programmeLabel, gdc);

  programmeField = new JTextField();
 programmeField.setPreferredSize(new Dimension(200, 30));
 gdc.gridx = 1;
 formPanel.add(programmeField, gdc);

 // Email
 emailLabel = new JLabel("Email:");
 gdc.gridx = 0;
 gdc.gridy = 4;
 formPanel.add(emailLabel, gdc);

 emailField = new JTextField();
 emailField.setPreferredSize(new Dimension(200, 30));
 gdc.gridx = 1;
 formPanel.add(emailField, gdc);

 // Phone Number
 phoneNoLabel = new JLabel("Phone Number:");
 gdc.gridx = 0;
 gdc.gridy = 5;
 formPanel.add(phoneNoLabel, gdc);

  phoneNoField = new JTextField();
 phoneNoField.setPreferredSize(new Dimension(200, 30));
 gdc.gridx = 1;
 formPanel.add(phoneNoField, gdc);
        submit=new JButton("SUBMIT");
        gdc.gridx=1;
        gdc.gridy=6;
        formPanel.add(submit,gdc);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String regNo =regNoField.getText();
                String fullname =fullnameField.getText();
                String programme =programmeField.getText();
                String email = emailField.getText();
                String phoneNo = phoneNoField.getText();
                Student s=new Student(regNo);
                s.registerStudent(fullname,programme,email,phoneNo);
                JOptionPane.showMessageDialog(null, s.message, "notification", JOptionPane.PLAIN_MESSAGE);
                
            }
        });   
    }
   
    
}




 class CourseSelection {
    private JCheckBox[] checkBoxes;
    private JTextArea outputArea = new JTextArea(5, 30);
    private StringBuilder selectedCourses = new StringBuilder();
    private String regNo;

    public CourseSelection(String regNo) {
        this.regNo=regNo;
        enrollGui();
    }

    public void enrollGui() {
        JFrame frame = new JFrame("Course Selection");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Add Label
        frame.add(new JLabel("Select 3 courses:\n"));

        // Sample Course Data
        Map<String, String> courses = eb1Courses();
        checkBoxes = new JCheckBox[courses.size()];

        // Create Checkboxes
        int index = 0;
        for (String code : courses.keySet()) {
            checkBoxes[index] = new JCheckBox(code + " - " + courses.get(code));
            frame.add(checkBoxes[index]);
            index++;
        }

        // Submit Button
        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        JButton exitButton = new JButton("exit");
        exitButton.addActionListener(e->{
            frame.dispose();
        });
        frame.add(exitButton);

        JButton finishButton = new JButton("finish");
        finishButton.addActionListener(e->{
            finishButton.setEnabled(false); // Disable button to prevent multiple clicks
            
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    getSelectedCourses();
                    frame.dispose(); // Exit entire application after delay
                }
            }, 30000); // 30-second delay
        });
        frame.add(finishButton);
        
        // Output Area
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));

        // Button ActionListener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCourses.setLength(0); // Clear previous selection
                int count = 0;

                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (count < 3) {
                            selectedCourses.append(checkBox.getText()).append("\n");
                            count++;
                        } else {
                            JOptionPane.showMessageDialog(frame, "You can only select 3 courses!", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                }

                if (count < 3) {
                    JOptionPane.showMessageDialog(frame, "Please select exactly 3 courses!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    outputArea.setText("Selected Courses:\n" + selectedCourses);
                }
            }
        });

        frame.setVisible(true);
    }

    // Getter method to retrieve selected courses
    public StringBuilder getSelectedCourses() {
            // Simulate retrieving the selected courses after the user interacts with the GUI
        Student student=new Student(regNo);
        student.enrollCourse(selectedCourses.toString());
        return selectedCourses;
    }

    // Sample Course Data
    public Map<String, String> eb1Courses() {
        Map<String, String> courses = new HashMap<>();
        courses.put("COSC101", "Introduction to Computer Science");
        courses.put("COSC102", "Computer Logics");
        courses.put("MATH241", "Probability and Statistics");
        courses.put("COSC110", "Introduction to Networking");
        courses.put("COSC142", "Techno Entrepreneurship");
        return courses;
    }

    
}
