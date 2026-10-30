import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int age;

    // This field will NOT be saved
    transient String password;

    Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Password: " + password);
        System.out.println();
    }
}

public class SaveLoadDemo {
    public static void main(String[] args) {

        Student[] students = {
            new Student("Rahul", 20, "abc123"),
            new Student("Priya", 21, "xyz789"),
            new Student("Amit", 19, "hello456")
        };

        // Save objects to file
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("students.dat"))) {

            out.writeObject(students);
            System.out.println("Objects saved successfully.\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load objects from file
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("students.dat"))) {

            Student[] loadedStudents = (Student[]) in.readObject();

            System.out.println("Objects loaded successfully:\n");

            for (Student s : loadedStudents) {
                s.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}