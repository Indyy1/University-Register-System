import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UniversityRegister {

//Final used for immutability
    private final List<Student> students;
//Initializing the University Register
    public UniversityRegister(List<Student> students) {
        //Using an array list
        this.students = new ArrayList<>(students);
    }

    //Adding new students
    public UniversityRegister enrollNewStudent(Student student) {
        List<Student> newStudentList = new ArrayList<>(students);
        newStudentList.add(student);
        return new UniversityRegister(newStudentList);
    }
//Removing students using student ID's
    public UniversityRegister unenrollStudent(String studentId) {
        List<Student> newStudentList = students.stream()
                .filter(student -> !student.getStudentId().equals(studentId))
                .collect(Collectors.toList());

        if (newStudentList.size() == students.size()) {
            throw new NoSuchElementException("There is no existing student with this student ID " + studentId);
        }

        return new UniversityRegister(newStudentList);
    }
//Searching for students by their name
    public List<Student> findByStudentName(Predicate<Student> predicate) {
        return students.stream()
                .filter(Objects::nonNull)
                .filter(predicate)
                .collect(Collectors.toList());
    }
    //Searching for students by their ID
    public List<Student> findStudentByStudentID(Predicate<Student> predicate) {
        return students.stream()
                .filter(Objects::nonNull)
                .filter(predicate)
                .collect(Collectors.toList());
    }
//Sorting students by their marks
    public List<Student> sortStudentsByMarks() {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getMarks))
                .collect(Collectors.toList());
    }
//Sorting students by their module
    public Map<String, List<Student>> organizeStudentsByModule(String course) {
        return students.stream()
                .filter(student -> student.getCourseTaken().equals(course))
                .collect(Collectors.groupingBy(Student::getModuleTaken));
    }

    // Listing all the students
    public List<Student> listStudents() {
        return Collections.unmodifiableList(students);
    }
}
