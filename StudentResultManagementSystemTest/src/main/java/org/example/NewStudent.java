package org.example;

public class NewStudent {
    private String name;
    private int[] marks;
    private int total;
    private double percentage;
    private String result;

    public NewStudent(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
        calculateResult();
    }

    private void calculateResult() {
        total = 0;
        boolean pass = true;

        for (int mark : marks) {
            total += mark;
            if (mark < 35) {
                pass = false;
            }
        }
        percentage = (double) total / marks.length;
        result = pass ? "PASS" : "FAIL";
    }

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getTotal() {
        return total;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getResult() {
        return result;
    }
}

