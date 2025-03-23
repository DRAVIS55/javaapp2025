import java.util.HashMap;
import java.util.Map;

public class Course {
   
     private final Map<String, String> eb1 = new HashMap<>();
     private final Map<String, String> cb1 = new HashMap<>();
     private final Map<String, String> eb2 = new HashMap<>();
     private final Map<String, String> eb3 = new HashMap<>();
    public Course(){
       addcourse();
    }
   
    
    public void assignLecturerCourse(){}
    public void attachCourseToAProgramme(){}
    public void assignScore(){}
    


    public Map<String, String> eb1Courses(){
        return this.eb1;
    }
    public Map<String, String> cb1Courses(){
        return this.cb1;
    }
    public Map<String, String> eb2Courses(){
        return this.eb2;
    }
    public Map<String, String> eb3Courses(){
        return this.eb3;
    }
    
    public  void addcourse(){
        cb1.put("MATH241", "Probability and Statistics");
        
        eb1.put("COSC101", "Introduction to Computer Science");
        eb1.put("COSC102", "Computer Logics");
        eb1.put("COSC110", "Introduction to Networking");
        eb1.put("COSC142", "Techno Entrepreneurship");
        eb1.put("COSC210", "Data Structures and Algorithms");
        eb1.put("COSC220", "Operating Systems");
        eb1.put("COSC230", "Software Engineering");
        eb1.put("COSC240", "Database Management Systems");

        eb3.put("COSC210", "Data Structures and Algorithms");
        eb3.put("COSC220", "Operating Systems");
        eb3.put("COSC230", "Software Engineering");
        eb3.put("COSC240", "Database Management Systems");
        eb3.put("COSC101", "Introduction to Computer Science");
        eb3.put("COSC102", "Computer Logics");
        eb3.put("COSC110", "Introduction to Networking");
        eb3.put("COSC142", "Techno Entrepreneurship");

        eb2.put("COSC250", "Web Development");
        eb2.put("COSC260", "Data Communications and Networks");
        eb2.put("MATH250", "Discrete Mathematics");
        eb2.put("MATH270", "Calculus III");
        eb2.put("PHY210", "Electronics for Computer Science");

        cb1.put("STA230", "Statistical Methods");
        cb1.put("BIO101", "Introduction to Biology");
        cb1.put("CHEM102", "Organic Chemistry");
        cb1.put("ECON201", "Microeconomics");
        cb1.put("ECON202", "Macroeconomics");
        
    }
   
    
}