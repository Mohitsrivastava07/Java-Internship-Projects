import java.util.Scanner;

public class TestStudents {
    static Students[] students = new Students[100];
    static int count = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n...Student Management System...");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("0. Exit");

            System.out.print("Choose any option --> ");
            int keyOption = input.nextInt();
            switch (keyOption) {
                case 1: addStudent(input); break;
                case 2: viewStudent(input); break;
                case 3: updateStudent(input); break;
                case 4 : deleteStudent(input); break;
                case 5 : searchStudent(input); break;
                case 0 : exit(); break;
                default :
                    System.out.println("Invalid Choice! Try Again...");
            }
        }
    }

    public static void addStudent(Scanner input) {
        System.out.print("Enter Student Id --> ");
        int studentId = input.nextInt();
        input.nextLine();
        System.out.print("Enter Student Name --> ");
        String studentName = input.nextLine();
        System.out.print("Enter Student Age --> ");
        int studentAge = input.nextInt();

        students[count++] = new Students(studentId, studentName, studentAge);
        System.out.println("Students Added Successfully!");
    }

    public static void viewStudent(Scanner input) {
        if (count == 0) {
            System.out.println("No Students Found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            students[i].showDetails();
        }
    }

    public static void updateStudent(Scanner input) {
        System.out.print("Enter student id --> ");
        int studentId = input.nextInt();
        input.nextLine();

        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId() == studentId) {
                System.out.print("Enter new student name --> ");
                String newStudentName = input.nextLine();
                System.out.print("Enter new student age --> ");
                int newStudentAge = input.nextInt();

                students[i].setStudentName(newStudentName);
                students[i].setStudentAge(newStudentAge);
                System.out.println("Student update successfully!!");
            }
        }
    }

    public static void deleteStudent(Scanner input) {
        System.out.print("Enter student id --> ");
        int studentId = input.nextInt();

        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId() == studentId) {
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j+1];
                }
                students[count - 1] = null;
                count--;

                System.out.println("Student deleted successfully!");
            }
        }
    }

    public static void searchStudent(Scanner input) {
        System.out.print("Enter student id --> ");
        int studentId = input.nextInt();

        if (count == 0) {
            System.out.println("Student not available");
            return;
        }

        boolean studentFound = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId() == studentId) {
                students[i].showDetails();
                studentFound = true;
                break;
            }
        }
        if (!studentFound) {
            System.out.println("Student not found!");
        }
    }

    public static void exit() {
        Scanner input = new Scanner(System.in);
        System.out.println("Thank you for using student management system....");
        input.close();
        System.exit(0);
    }
}

