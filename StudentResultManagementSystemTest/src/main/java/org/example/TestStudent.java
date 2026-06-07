package org.example;
import java.util.Scanner;

public class TestStudent
{
    public static void main( String[] args ) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter Student name --> ");
            String name = input.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < marks.length; i++) {
                System.out.print("Enter marks of subject " + (i+1) + ": ");
                marks[i] = input.nextInt();

                if (marks[i] < 0 || marks[i] > 100) {
                    throw new Exception("Marks must be between 0 and 100");
                }
            }

            NewStudent newStudent = new NewStudent(name, marks);

            ResultThread thread = new ResultThread(newStudent);
            thread.start();

            try {
                thread.join();
             } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            DatabaseUtil.saveStudent(newStudent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            input.close();
        }
    }
}
