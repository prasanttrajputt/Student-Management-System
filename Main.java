import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Register Student");
            System.out.println("2. Update Student Details");
            System.out.println("3. Add Grade");
            System.out.println("4. View Grades");
            System.out.println("5. View Student Info");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String regId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String regName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String regEmail = scanner.nextLine();
                    sms.registerStudent(regId, regName, regEmail);
                    break;

                case 2:
                    System.out.print("Enter Student ID to update: ");
                    String updId = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    sms.updateDetails(updId, newName, newEmail);
                    break;

                case 3:
                    System.out.print("Enter Student ID to add grade: ");
                    String gradeId = scanner.nextLine();
                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Enter Grade (0-100): ");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    sms.addGrade(gradeId, subject, grade);
                    break;

                case 4:
                    System.out.print("Enter Student ID to view grades: ");
                    String viewGradesId = scanner.nextLine();
                    sms.viewGrades(viewGradesId);
                    break;

                case 5:
                    System.out.print("Enter Student ID to view info: ");
                    String viewId = scanner.nextLine();
                    sms.viewStudentInfo(viewId);
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}


// Student class with encapsulated fields
class Student {
    private String studentId;
    private String name;
    private String email;
    private Map<String, Integer> grades;

    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.grades = new HashMap<>();
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Integer> getGrades() {
        return new HashMap<>(grades); // return copy for safety
    }

    // Setters
    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        }
    }

    public void addGrade(String subject, int grade) {
        if (grade >= 0 && grade <= 100) {
            grades.put(subject, grade);
        }
    }

    public void displayInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Grades: " + grades);
    }
}

// Management system class
class StudentManagementSystem {
    private Map<String, Student> students;

    public StudentManagementSystem() {
        students = new HashMap<>();
    }

    public void registerStudent(String studentId, String name, String email) {
        if (!students.containsKey(studentId)) {
            Student student = new Student(studentId, name, email);
            students.put(studentId, student);
            System.out.println("Student registered successfully.");
        } else {
            System.out.println("Student ID already exists.");
        }
    }

    public void viewGrades(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            System.out.println("Grades for " + student.getName() + ": " + student.getGrades());
        } else {
            System.out.println("Student not found.");
        }
    }

    public void updateDetails(String studentId, String newName, String newEmail) {
        Student student = students.get(studentId);
        if (student != null) {
            student.setName(newName);
            student.setEmail(newEmail);
            System.out.println("Details updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void addGrade(String studentId, String subject, int grade) {
        Student student = students.get(studentId);
        if (student != null) {
            student.addGrade(subject, grade);
            System.out.println("Grade added.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void viewStudentInfo(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            student.displayInfo();
        } else {
            System.out.println("Student not found.");
        }
    }
}

