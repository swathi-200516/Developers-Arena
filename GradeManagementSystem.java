import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int[] marks; // marks for multiple subjects

    Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    double getAverage() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return (double) sum / marks.length;
    }

    int getHighest() {
        int max = marks[0];
        for (int m : marks) {
            if (m > max) max = m;
        }
        return max;
    }

    int getLowest() {
        int min = marks[0];
        for (int m : marks) {
            if (m < min) min = m;
        }
        return min;
    }

    char getGrade() {
        double avg = getAverage();
        if (avg >= 90) return 'A';
        else if (avg >= 75) return 'B';
        else if (avg >= 60) return 'C';
        else if (avg >= 50) return 'D';
        else return 'F';
    }
}

public class GradeManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Grade Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Performance Report");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> performanceReport();
                case 5 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int subjects = sc.nextInt();

        int[] marks = new int[subjects];

        for (int i = 0; i < subjects; i++) {
            while (true) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                int mark = sc.nextInt();
                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    break;
                } else {
                    System.out.println("Marks must be between 0 and 100!");
                }
            }
        }

        students.add(new Student(name, marks));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println("\nName: " + s.name);
            System.out.println("Average: " + s.getAverage());
            System.out.println("Grade: " + s.getGrade());
        }
    }

    static void searchStudent() {
        System.out.print("Enter student name to search: ");
        String name = sc.nextLine();

        for (Student s : students) {
            if (s.name.equalsIgnoreCase(name)) {
                System.out.println("\nStudent Found!");
                System.out.println("Name: " + s.name);
                System.out.println("Average: " + s.getAverage());
                System.out.println("Highest: " + s.getHighest());
                System.out.println("Lowest: " + s.getLowest());
                System.out.println("Grade: " + s.getGrade());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void performanceReport() {
        if (students.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        System.out.println("\n===== Performance Report =====");
        for (Student s : students) {
            System.out.println(
                s.name + " | Avg: " + s.getAverage() +
                " | Grade: " + s.getGrade()
            );
        }
    }
}
