import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOStream_Ex1 {
    public static void main(String[] args) {
        String fileName = "Name.txt";
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader((fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
            }
            String regex = "[.!?]+";
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(content.toString());

            int count = 0;
            while (matcher.find()) {
                count++;
            }
            System.out.println("Number of sentences: " + count);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}