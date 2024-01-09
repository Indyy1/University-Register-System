import java.util.stream.Stream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //Creating a list of example students
        //Adding their information: Name, student ID, course, module, mark, and age
        List<Student> students = Arrays.asList(
                //Making a diverse list to allow a variety of results to be produced
                new Student("Megan", "879036", "Computer Science", "Functional Programming", 89, 19),
                new Student("Afia", "228607", "Fine Art", "Sculpting", 13,19),
                new Student("Andre", "446732", "Bio Med", "Anatomy and Development",26,18),
                new Student("Maxwell", "885398", "Photography", "Portrait Photography", 66,20),
                new Student("Peter", "537888", "Dance", "Interpretive Dance", 86,18)
        );


        Stream<Integer> ageStream = students.stream().map(Student::getAge);


        Stream<Integer> newAgeStream = students.stream().map(Student::getAge);


        Optional<Integer> maxAge = newAgeStream.max(Integer::compareTo);


        Optional<Integer> minAge = ageStream.min(Integer::compareTo);

        Optional<Integer> totalAge = students.stream().map(Student::getAge).reduce(Integer::sum);


        List<Integer> sortedAges = students.stream().map(Student::getAge).sorted().toList();

        boolean allComputerScience = areAllStudentsEnrolledOnComputerScience(students.stream());


        System.out.println("Are the students all enrolled onto the Computer Science course? " + allComputerScience);
    }

    private static boolean areAllStudentsEnrolledOnComputerScience(Stream<Student> studentStream) {
    //Using 'studentStream.allMatch', an API
        return studentStream.allMatch(student -> student.getCourseTaken().equals("Computer Science"));
    }
}



