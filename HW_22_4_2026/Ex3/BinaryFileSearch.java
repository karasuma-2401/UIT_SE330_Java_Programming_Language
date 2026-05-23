import java.io.*;
import java.util.*;

public class BinaryFileSearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter value x to search in c.dat: ");
        int x = input.nextInt();

        try (RandomAccessFile raf = new RandomAccessFile("c.dat", "r")) {
            boolean found = false;
            long fileLength = raf.length();
            
            for (long i = 0; i < fileLength; i += 4) {
                raf.seek(i);
                if (raf.readInt() == x) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Result: " + x + " found in c.dat.");
            } else {
                System.out.println("Result: " + x + " not found in c.dat.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: c.dat not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}