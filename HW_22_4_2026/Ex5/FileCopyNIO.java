import java.nio.file.*;
import java.io.IOException;

public class FileCopyNIO {
    public static void main(String[] args) {
        Path source = Paths.get("source.txt");
        Path dest = Paths.get("dest.txt");

        try {
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully using NIO.");
        } catch (NoSuchFileException e) {
            System.err.println("Error: Source file does not exist.");
        } catch (IOException e) {
            System.err.println("Error: Could not copy file.");
            e.printStackTrace();
        }
    }
}