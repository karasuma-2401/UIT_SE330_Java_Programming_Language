package Ex11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
public static void main(String[] args) {
        String fileName = "example.txt";
        String content = "Hello, this is a test file";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("=> File '" + fileName + "' created and written successfully!");
        } catch (IOException e) {
            System.err.println("=> Error when writing file: " + e.getMessage());
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("=> Error for reading file: " + e.getMessage());
        }
    }
}
