import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private double grade;
    private String studentId;
    private String contact;

    public Student(String name, int age, double grade, String studentId, String contact) {
        setName(name);
        setAge(age);
        setGrade(grade);
        this.studentId = studentId;
        this.contact = contact;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGrade() { return grade; }
    public String getStudentId() { return studentId; }
    public String getContact() { return contact; }

    // Setters with validation
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age <= 0)
            throw new IllegalArgumentException("Age must be a positive number.");
        this.age = age;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 100)
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        this.grade = grade;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Display formatted student info
    public void display() {
        System.out.printf("%-10s %-15s %-5d %-7.2f %-15s%n",
                studentId, name, age, grade, contact);
    }
}

public class StudentManagementSystem {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> updateStudent();
                    case 4 -> deleteStudent();
                    case 5 -> searchStudent();
                    case 0 -> System.out.println("Exiting system. Goodbye!");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);
    }

    private static void displayMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student");
        System.out.println("0. Exit");
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        int age = getIntInput("Enter Age: ");
        double grade = getDoubleInput("Enter Grade (0-100): ");

        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();

        students.add(new Student(name, age, grade, id, contact));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-10s %-15s %-5s %-7s %-15s%n",
                "ID", "Name", "Age", "Grade", "Contact");
        System.out.println("--------------------------------------------------------");

        for (Student s : students) {
            s.display();
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String id = scanner.nextLine();

        Student student = findById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        student.setName(scanner.nextLine());

        student.setAge(getIntInput("Enter New Age: "));
        student.setGrade(getDoubleInput("Enter New Grade: "));

        System.out.print("Enter New Contact: ");
        student.setContact(scanner.nextLine());

        System.out.println("Student record updated.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();

        Student student = findById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudent() {
        System.out.print("Search by (1) ID or (2) Name: ");
        int option = getIntInput("");

        if (option == 1) {
            System.out.print("Enter Student ID: ");
            Student s = findById(scanner.nextLine());
            if (s != null) s.display();
            else System.out.println("Student not found.");
        } else if (option == 2) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            boolean found = false;

            for (Student s : students) {
                if (s.getName().equalsIgnoreCase(name)) {
                    s.display();
                    found = true;
                }
            }

            if (!found)
                System.out.println("No student found with that name.");
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static Student findById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(id))
                return s;
        }
        return null;
    }

    // Input helpers with error handling
    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid decimal number. Try again.");
            }
        }
    }
}
