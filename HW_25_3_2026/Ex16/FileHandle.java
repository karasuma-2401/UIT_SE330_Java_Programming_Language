package Ex16;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandle {
    public static void main(String[] args) {
        String in = "data.txt";
        String out = "output.txt";

        try {
            List<String> res = Files.lines(Paths.get(in))
                .filter(l -> !l.trim().isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());

            Files.write(Paths.get(out), res);
            System.out.println("=> Success");
        } catch (Exception e) {
            System.out.println("=> Error: " + e.getMessage());
        }
    }
}
