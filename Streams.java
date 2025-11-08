import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams{
    public static void main(String[] args){

        Student[] arr = {
                new Student(10, "abc", 70),
                new Student(11, "gfg", 87),
                new Student(12, "hfc", 69),
        };

       double res = Arrays.stream(arr)
                                .mapToInt(x -> x.getMarks())
                                .average()
                                .getAsDouble();

        Map<String, Integer> m = Arrays.stream(arr)
                        .collect(Collectors.toMap(Student::getName, Student::getMarks));

        Map<Integer, List<Student>> map = Arrays.stream(arr)
                .collect(Collectors.groupingBy(Student::getMarks));
        System.out.println(map);
    }
}
