# javaapp2025

## Student Management System

This project is developed as part of the **COC 223 Java Programming** course and serves as a **Student Management System** with two implementations:

1️⃣ **File-based Storage System**  
2️⃣ **Database-based Storage System**  

## Project Description
The system manages students, lecturers, courses, and programmes. It provides functionality to assign lecturers to courses, enroll students in programmes and courses, assign student scores, and retrieve student details.

### Key Features
✅ **Person Hierarchy**:
- `Person` (Super class)
- `Student` (Subclass)
- `Lecturer` (Subclass)

✅ **Student Management**:
- Students can register for only **one programme**.
- Students can enroll in **a maximum of three courses**.

✅ **Lecturer Management**:
- Each lecturer can be assigned **a maximum of two courses**.
- No course sharing between lecturers.

✅ **Course & Programme Management**:
- Courses are attached to specific programmes.
- Students can be enrolled in courses within their programme.

✅ **Data Storage**:
- **File-based System**: Uses multiple files (`student.txt`, `lecturer.txt`, etc.) to store data.
- **Database-based System**: Uses **MySQL** for data persistence.

✅ **Results Management**:
- Assign and update student scores.
- Retrieve student results from file/database.
- Display students registered in a unit/course.
- Find lecturers assigned to a particular course.

## System Functionalities
🔹 Assign lecturers to courses and enforce the **2-course limit** per lecturer.  
🔹 Attach courses to programmes and manage course assignments.  
🔹 Enroll students in programmes and allow them to register for courses.  
🔹 Assign scores to students and store the results in either **files or a database**.  
🔹 Retrieve student results and update where necessary.  
🔹 Search for students and list courses they are registered in.  
🔹 Ensure that no lecturer shares the same course with another lecturer.  
🔹 Find all students registered in a particular programme or unit.  

## Technologies Used
- **Java** (Core logic)
- **Swing** (GUI for user interaction)
- **File Handling** (Text-based data storage)
- **MySQL** (Database storage option)
- **Object-Oriented Programming (OOP)**

## Setup Instructions
### Running the File-Based System
1. Clone this repository:
   ```bash
   git clone https://github.com/DRAVIS55/javaapp2025.git
   ```
2. Open the project in **your preferred Java IDE** (e.g., IntelliJ, VS Code, Eclipse, or NetBeans).
3. Run the `Main.java` class to start the system.

### Running the Database-Based System
1. Install **MySQL** and create a database for the project.
2. Configure database connection settings in the `DatabaseConfig.java` file.
3. Run the SQL script provided in `database_setup.sql` to create tables.
4. Run the `Main.java` class to launch the application.

## Future Enhancements
🚀 Implement **REST APIs** to extend the system for web and mobile use.  
🚀 Add **role-based authentication** (admin, lecturer, student).  
🚀 Introduce **real-time notifications** for results updates.  
🚀 Implement a **dashboard with analytics and reporting**.

## Author
👨‍💻 **Samuel Kibunja Macharia (DRAVIS55)**  in conjuction with computer science group 
📧 Email: [dravislotum@gmail.com](mailto:your-email@example.com)  
🔗 GitHub: [https://github.com/DRAVIS55](https://github.com/DRAVIS55)

---
### 🚀 *Developed for the award of COC 223 Java Programming*

