import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

//This class is to test everything in the UniversityRegister class runs as expected
public class UniversityRegisterTest {

    private UniversityRegister universityRegister;

    //Creating a list of example students
    //Adding the name, student ID, course, module, mark, and age
    @BeforeEach
    public void setUp() {
        List<Student> sampleStudents = Arrays.asList(
                new Student("Maya", "387260", "Architecture", "Technology and Environment", 26, 33),
                new Student("Christelle", "741225", "Product Design", "3D design", 18, 62),
                new Student("Efua", "005673", "Accounting", "Business Ethics", 19, 75)
        );
        universityRegister = new UniversityRegister(sampleStudents);
    }
    //Testing if the developer can successfully remove students from the system
    @Test
    public void testRemoveStudent() {
        String studentIdToRemove = "741225";
        universityRegister = universityRegister.unenrollStudent(studentIdToRemove);

        assertTrue(universityRegister.listStudents().stream().noneMatch(student -> student.getStudentId().equals(studentIdToRemove)));
    }
    //Testing if the developer can search for students by the course they take
    @Test
    public void testSearchStudentsByCourseTaken() {
        List<Student> result = universityRegister.organizeStudentsByModule("Product Design").get("3D design");

        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(student -> student.getCourseTaken().equals("Product Design")));
    }
    //Testing if the developer can find students based on the module they take
    @Test
    public void testFindStudentsByModuleTaken() {
        List<Student> result = universityRegister.organizeStudentsByModule("Product Design").get("3D design");

        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(student -> student.getModuleTaken().equals("3D design")));
    }
    //Testing if the developer can group students together by the module they take
    @Test
    public void testGroupStudentsByModuleTaken() {
        Map<String, List<Student>> groupByModule = universityRegister.organizeStudentsByModule("Product Design");

        assertEquals(1, groupByModule.size());
        assertTrue(groupByModule.containsKey("3D design"));
    }
    //Testing if the developer can search for students by their marks
    @Test
    public void testSortStudentsByMarks() {
        // Example students to test with
        // Listing their information: Name, student ID, course, module, mark, and age
        Student beth = new Student("Beth", "735733", "English Literature", "Modern Poetry", 67, 20);
        Student freddy = new Student("Freddy", "000532", "Fine Art", "Mixed Media", 52, 18);
        // Adding the example students to the University Register
            universityRegister = universityRegister.enrollNewStudent(beth);
            universityRegister = universityRegister.enrollNewStudent(freddy);
        // Creating a list of the students that is organized by their marks
             List<Student> sortedStudents = universityRegister.sortStudentsByMarks();
             assertEquals("Freddy", sortedStudents.get(0).getStudentName());
             assertEquals("Beth", sortedStudents.get(1).getStudentName());
    }

    //Testing if the developer can search for students by their name
    @Test
    public void testSearchByStudentName() {
        //Searching for a student called Christelle
        List<Student> result = universityRegister.findByStudentName(student -> student.getStudentName().equals("Christelle"));
        assertEquals(1, result.size());
        //It is expected that Christelle will be found in the system since she is one of the example students
        //If she is not found we know that searching students by their name doesn't work in this system
        assertEquals("Christelle", result.get(0).getStudentName());
    }

    //Testing if the developer can search for students by their student ID
    @Test
    public void testSearchByStudentId() {
        List<Student> result = universityRegister.findByStudentName(student -> student.getStudentId().equals("741225"));

        assertEquals(1, result.size());
        assertEquals("Christelle", result.get(0).getStudentName());
    }

    //Testing if the developer can remove students that don't exist
    //This should fail since this is impossible to do
    @Test
    public void testRemoveNonexistentStudent() {
        //Test removing a student that doesn't exist
        //This student ID does not match anyone on the system
        //This covers if anyone were to accidentally type the wrong student ID
        universityRegister.unenrollStudent("000021");
    }

    //Testing if the developer can successfully add students to the system
    @Test
    public void testEnrollNewStudent() {
        //Creating an example student
        Student enrollNewStudent = new Student("Brooke", "117436", "Mechanical Engineering", "Introduction to Mechanics", 87, 33);
        //Adding the example student to the University Register
        universityRegister = universityRegister.enrollNewStudent(enrollNewStudent);
        assertTrue(universityRegister.listStudents().contains(enrollNewStudent));
    }
}