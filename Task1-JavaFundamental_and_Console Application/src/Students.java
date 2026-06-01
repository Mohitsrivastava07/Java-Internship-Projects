public class Students {
    private int studentId;
    private String studentName;
    private int studentAge;

    Students(int studentId, String studentName, int studentAge) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }
    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public void showDetails() {
        System.out.println();
        System.out.println("....Student Details....");
        System.out.println("Student Id --> " + this.studentId);
        System.out.println("Student Name --> " + this.studentName);
        System.out.println("Student Age --> " + this.studentAge);
        System.out.println(".........................................");
    }
}