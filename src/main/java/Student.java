import java.util.Objects;
public class Student {
    //Defining the instance variables as final for immutability
    //The order the variable will be listed for the example students
    private final String studentName;
    private final String studentId;
    private final String courseTaken;
    private final String moduleTaken;
    private final double marks;
    private final int age;
    //Creating an instance of the 'Student' class
    //Adding attributes for name, student ID, course, and module as necessary so it can be left blank
    public Student(String studentName, String studentId, String courseTaken, String moduleTaken, double marks, int age) {
        Objects.requireNonNull(studentName, "Student name mandatory");
        Objects.requireNonNull(studentId, "Student ID mandatory");
        Objects.requireNonNull(courseTaken, "Course taken mandatory");
        Objects.requireNonNull(moduleTaken, "Module taken mandatory");
//Initialising both the required and unrequired variables
        this.studentName = studentName;
        this.studentId = studentId;
        this.courseTaken = courseTaken;
        this.moduleTaken = moduleTaken;
        this.marks = marks;
        this.age = age;
    }


    //I used getter methods for the student's ID, course, name, marks, age, and module
    public String getStudentId() {
        return studentId;
    }
    public String getCourseTaken() {
        return courseTaken;
    }
    public String getStudentName() {
        return studentName;
    }
    public double getMarks() {
        return marks;
    }
    public int getAge() {
        return age;
    }
    public String getModuleTaken() {
        return moduleTaken;
    }

//Produces a hash code
    @Override
    public int hashCode() {
        //Uses the student's ID to do this
        return Objects.hash(studentId);
    }
    //Finds out if there are two students that are both equal based on their student ID
    //A definitive answer is produced using a boolean
    @Override
    public boolean equals(Object obj) {
        //Finds out whether the object belongs to a different class
        if (this == obj) return true;
        //Sees if the objects is null
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return studentId.equals(student.studentId);
    }
}
