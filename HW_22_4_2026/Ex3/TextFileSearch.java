import java.io.*;
import java.util.*;

public class TextFileSearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter value x to search in c.txt: ");
        int x = input.nextInt();

        try {
            File file = new File("c.txt");
            if (!file.exists()) {
                System.out.println("Error: c.txt not found.");
                return;
            }

            Scanner reader = new Scanner(file);
            List<Integer> listC = new ArrayList<>();
            while (reader.hasNextInt()) {
                listC.add(reader.nextInt());
            }
            reader.close();

            if (listC.contains(x)) {
                System.out.println("Result: " + x + " exists in c.txt.");
            } else {
                System.out.println("Result: " + x + " does not exist in c.txt.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}