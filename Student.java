//imports
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

//class Student
public class Student{
    public String regNo;
    public String fullname;
    public String programme;
    public String email;
    public String phoneNo;
    public ArrayList<String> courseCode;
    public ArrayList<String> courseCodeMarks;
    public String message="";

    //constructor for class student
    public Student(String regNo){
        this.regNo=regNo;
        this.courseCode=new ArrayList<>(); 
        this.courseCodeMarks=new ArrayList<>(); 

    }

    //method for register Student
    public void registerStudent(String fullname , String programme , String email ,String phoneNo){
        this.fullname=fullname;
        this.programme=programme;
        this.email=email;
        this.phoneNo=phoneNo;
       String filename = regNo + ".txt";
        File file = new File(filename);

        try {
            if (file.createNewFile()) {
                // File doesn't exist, create & write data
                try (BufferedWriter myWriter = new BufferedWriter(new FileWriter(filename))) {
                    myWriter.write("Reg No: " + regNo + "\n");
                    myWriter.flush();
                    myWriter.write("Full Name: " + fullname + "\n");
                    myWriter.flush();
                    myWriter.write("Programme: " + programme + "\n");
                    myWriter.flush();
                    myWriter.write("Email: " + email + "\n");
                    myWriter.flush();
                    myWriter.write("Phone No: " + phoneNo + "\n");
                }
                this.message = "Successfully registered: " + regNo;
            } else {
                this.message = "The user already exists.";
            }
        } catch (IOException e) {
            this.message = "Error: " + e.getMessage();
        }

    }


    public void enrollCourse(String course){
        JOptionPane.showMessageDialog(null, "engaged!", "fuck", 0);
        try{
            File myobj=new File(regNo+".txt");
        if(myobj.exists()){
            FileWriter writer=new FileWriter(regNo+".txt");
            writer.append("courses enrolled \n --------------------------------------------------");
            writer.flush();
            writer.append(course);
            writer.flush();
            writer.write("a fail not here:");
            writer.close();
            this.message="enrolling successful";
        }
        else{
            this.message="the user does not exist! register first !";
        }
        
        }
        catch(Exception e){
           this.message="Server: error enrolling course !";
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, message, "notification", JOptionPane.PLAIN_MESSAGE);
    }

    //method for assigning results to various courses
    public void assignStudentResults(String Code, int score){
        courseCodeMarks.add("+"+"CourseCode : "+Code+"\t"+"score: "+score);
        assignResults(regNo);

    }
    public void assignResults(String regNo){
        try{
            File myobj=new File(regNo+".txt");
            if(myobj.exists()){
                FileWriter writer=new FileWriter(regNo+".txt");
                writer.write("results section \n --------------------------------------------------");
                for(String marks:courseCodeMarks){
                    writer.append(marks);
                }
                writer.close();
                this.message="assigned results successfully";
            }
            else{
                this.message="unregistered student !";
            }

        }
        catch(Exception e){
            this.message="error assigning results ! ";
            e.printStackTrace();
        }

    }


    // method for searching a student
    public void SearchStudent(){
        try{
            File myobj=new File(regNo+".txt");
            if(myobj.exists()){
                String details="";
                Scanner scanner=new Scanner(myobj);
                while (scanner.hasNextLine()) {
                    details=scanner.nextLine();  
                }
                scanner.close();
                this.message="student exists ! \n"+details;
            }
            else{
                this.message="the student does not exist ! ";
            }

        }
        catch(Exception e){
            this.message="error accessing student ! ";
            e.printStackTrace();
        }
    }


//method for retrieving students results
    public void retrieveStudentResults(String courseCode){
        try{
            File myobj=new File(regNo+".txt");
            if(myobj.exists()){
                Scanner scanner=new Scanner(myobj);
                String line;
                while ((line=scanner.nextLine()) != null) {
                    if(line.contains("+"+courseCode)){
                        this.message=line;
                        scanner.close();
                        return;
                    }
                }
                scanner.close();
            }

            else{
                this.message="no student details found register first !";
            }

        
        }
        catch(Exception e){
           this.message="server error: searching student result";
            e.printStackTrace();
        }

    }
public void updateResults(){
   this.message="hahaha unimplemented !";
}


    

    
}