package Ex15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentList {
    public static void main(String[] args) {
        List<Student> ls = Arrays.asList(
            new Student("Le Minh Thang", 100),
            new Student("Kan", 90),
            new Student("Leonardo", 42),
            new Student("David Beckham", 36)
        );

        String res = ls.stream()
            .filter(x -> x.getScore() < 50)
            .sorted((x, y) -> Integer.compare(y.getScore(), x.getScore()))
            .map(x -> x.getName().toUpperCase() + " - " + x.getScore())
            .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(res);
    }
}
