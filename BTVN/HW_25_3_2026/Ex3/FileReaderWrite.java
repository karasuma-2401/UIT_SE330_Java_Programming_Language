package Ex3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
public class FileReaderWrite {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "output.txt";
        System.out.print("Enter the content to write to file:");
        String data = sc.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
            System.out.println("Data written to " + fileName + " successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
        }
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (Exception e) {
            System.out.println("=> Errors occurred while reading the file. + " + e.getMessage());
        }
        sc.close();
    }
}
