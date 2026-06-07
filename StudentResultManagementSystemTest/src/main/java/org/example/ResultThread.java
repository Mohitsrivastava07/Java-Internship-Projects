package org.example;

public class ResultThread extends Thread {
    private NewStudent newStudent;

    public ResultThread(NewStudent student) {
        this.newStudent = student;
    }

    @Override
    public void run() {
        System.out.println("\n....STUDENT RESULT....");
        System.out.println("Name --> " + newStudent.getName());

        int[] marks = newStudent.getMarks();
        for (int i = 0; i < marks.length; i++) {
            System.out.println("Subject " + (i+1) + ": " + marks[i]);
        }
        System.out.println("Total: " + newStudent.getTotal());
        System.out.println("Percentage: " +  newStudent.getPercentage());
        System.out.println("Result: " + newStudent.getResult());
    }
}
