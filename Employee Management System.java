import java.io.*;
import java.util.*;

/* ===== Employee Class ===== */
class Employee implements Serializable {
    int id;
    String name, dept, position, joinDate;
    double salary;

    Employee(int id, String name, String dept, String position, double salary, String joinDate) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.position = position;
        this.salary = salary;
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + dept + " " + position + " " + salary + " " + joinDate;
    }
}

/* ===== Employee Management System ===== */
public class EmployeeManagementSystem {

    static ArrayList<Employee> empList = new ArrayList<>();
    static HashMap<Integer, Employee> empMap = new HashMap<>();
    static final String FILE = "employees.dat";

    /* CREATE */
    static void addEmployee(Employee e) {
        empList.add(e);
        empMap.put(e.id, e);
    }

    /* READ */
    static void viewEmployees() {
        if (empList.isEmpty()) {
            System.out.println("No employees found");
            return;
        }
        empList.forEach(System.out::println);
    }

    /* UPDATE */
    static void updateEmployee(int id, double salary) {
        Employee e = empMap.get(id);
        if (e != null) {
            e.salary = salary;
            System.out.println("Employee updated");
        } else {
            System.out.println("Employee not found");
        }
    }

    /* DELETE */
    static void deleteEmployee(int id) {
        Employee e = empMap.remove(id);
        if (e != null) {
            empList.remove(e);
            System.out.println("Employee deleted");
        } else {
            System.out.println("Employee not found");
        }
    }

    /* SAVE TO FILE */
    static void saveToFile() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(empList);
            System.out.println("Data saved to file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* LOAD FROM FILE */
    @SuppressWarnings("unchecked")
    static void loadFromFile() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE))) {
            empList = (ArrayList<Employee>) ois.readObject();
            empMap.clear();
            for (Employee e : empList) {
                empMap.put(e.id, e);
            }
            System.out.println("Data loaded from file");
        } catch (Exception e) {
            System.out.println("No previous data found");
        }
    }

    /* MAIN METHOD */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadFromFile();

        while (true) {
            System.out.println("\n1.Add 2.View 3.Update 4.Delete 5.Save 6.Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Id Name Dept Position Salary JoinDate: ");
                    addEmployee(new Employee(
                            sc.nextInt(),
                            sc.next(),
                            sc.next(),
                            sc.next(),
                            sc.nextDouble(),
                            sc.next()
                    ));
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter id and new salary: ");
                    updateEmployee(sc.nextInt(), sc.nextDouble());
                    break;

                case 4:
                    System.out.print("Enter id: ");
                    deleteEmployee(sc.nextInt());
                    break;

                case 5:
                    saveToFile();
                    break;

                case 6:
                    saveToFile();
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}